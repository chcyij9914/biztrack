package com.erp.biztrack.common;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClovaOcrService {

    private static final String SECRET_KEY = "dmxnZ1Nma29VZVJBT2hjVWdXTUVWd2Zud3VRckpEbXo=";
    private static final String INVOKE_URL = "https://10tavm9nbg.apigw.ntruss.com/custom/v1/41285/0c3a0359cb56fa79d1346d8ac615690bbfa032a1e3417e2daf1cd7b5a3a1de00/general";

    // 명함 이미지 OCR
    public String readBusinessCard(File file) throws Exception {
        URL url = new URL(INVOKE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setUseCaches(false);
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("POST");
        String boundary = "----" + UUID.randomUUID().toString().replace("-", "");
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        con.setRequestProperty("X-OCR-SECRET", SECRET_KEY);

        JSONObject message = new JSONObject();
        message.put("version", "V2");
        message.put("requestId", UUID.randomUUID().toString());
        message.put("timestamp", System.currentTimeMillis());

        JSONArray images = new JSONArray();
        JSONObject imageObj = new JSONObject();
        imageObj.put("name", file.getName());
        imageObj.put("format", "jpg");
        imageObj.put("data", "");
        images.add(imageObj);
        message.put("images", images);

        DataOutputStream dos = new DataOutputStream(con.getOutputStream());

        writeFormData(dos, boundary, "message", message.toJSONString());
        writeFileData(dos, boundary, "file", file); // ★ 여기서는 File로 간다

        dos.writeBytes("--" + boundary + "--\r\n");
        dos.flush();
        dos.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }


    private void writeFormData(DataOutputStream dos, String boundary, String name, String value) throws IOException {
        dos.writeBytes("--" + boundary + "\r\n");
        dos.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"\r\n\r\n");
        dos.writeBytes(value + "\r\n");
    }

    private void writeFileData(DataOutputStream dos, String boundary, String name, File file) throws IOException {
        dos.writeBytes("--" + boundary + "\r\n");
        dos.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + file.getName() + "\"\r\n");
        dos.writeBytes("Content-Type: application/octet-stream\r\n\r\n");

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }
        }
        dos.writeBytes("\r\n");
    }

    // 명함 OCR 결과에서 정보 추출
    public Map<String, String> extractContactInfo(String jsonResult) {
        Map<String, String> info = new HashMap<>();
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonResult);
            JSONArray images = (JSONArray) json.get("images");
            JSONObject first = (JSONObject) images.get(0);
            JSONArray fields = (JSONArray) first.get("fields");

            boolean addressStarted = false;

            for (Object fieldObj : fields) {
                JSONObject field = (JSONObject) fieldObj;
                String inferText = (String) field.get("inferText");

                if (info.get("name") == null && inferText.matches("^[가-힣]{2,4}$")) {
                    info.put("name", inferText);
                } else if (info.get("email") == null && inferText.contains("@")) {
                    info.put("email", inferText);
                } else if (info.get("phone") == null && inferText.matches("^01[016789]-\\d{3,4}-\\d{4}$")) {
                    info.put("phone", inferText);
                } else if (info.get("tel") == null && inferText.matches("^0\\d{1,2}-\\d{3,4}-\\d{4}$")) {
                    info.put("tel", inferText);
                } else if (info.get("fax") == null && inferText.toLowerCase().contains("fax")) {
                    String faxNumber = inferText.replaceAll("[^0-9-]", "");
                    info.put("fax", faxNumber);
                }

                if (!addressStarted && (inferText.contains("시") || inferText.contains("구") || inferText.contains("로") || inferText.contains("길"))) {
                    addressStarted = true;
                }
                if (addressStarted) {
                    if (inferText.matches("^\\d{5}$")) {
                        break;
                    }
                    String currentAddress = info.getOrDefault("address", "");
                    info.put("address", (currentAddress + " " + inferText).trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
