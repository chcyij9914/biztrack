package com.erp.biztrack.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileRenameUtil {

    public static String changeFileName(String originalName) {
        // 현재 시간 기반 문자열 생성 (년월일시분초)
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // 확장자 추출
        String ext = originalName.substring(originalName.lastIndexOf(".") + 1);
        // 새 이름 구성
        return timestamp + "." + ext;
    }
}