package com.erp.biztrack.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {
	
 public static void main(String[] args) {
	 
	 String plainPassword = "@SAR24010503"; // 암호화할 비밀번호
     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // 직접 new 해서 생성
     String encodedPassword = encoder.encode(plainPassword);

     System.out.println("DB에 넣을 암호화된 비밀번호: " + encodedPassword);
          
 }
}
