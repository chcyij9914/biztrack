package com.erp.biztrack.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("filedown")
public class FileDownloadView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(
        Map<String, Object> model,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {

        // 전달받은 파일 객체 추출
        File readFile = (File) model.get("renameFile");  // 실제 저장된 파일
        File downFile = (File) model.get("originFile");  // 원래 이름을 위한 객체

        String fileName = downFile.getName();  // 실제 보여줄 원래 파일명

        // 응답 헤더 설정 (한글 파일 깨짐 방지 및 파일 전송 처리)
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.addHeader(
            "Content-Disposition",
            "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\""
        );
        response.setContentLength((int) readFile.length());

        // 파일 스트림 전송
        try (
            FileInputStream fin = new FileInputStream(readFile);
            OutputStream out = response.getOutputStream()
        ) {
            FileCopyUtils.copy(fin, out);  // 스프링 유틸로 간단하게 복사
            out.flush();
        }
    }
}
