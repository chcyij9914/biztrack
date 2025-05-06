package com.erp.biztrack.login.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.biztrack.login.model.dao.LoginDao;
import com.erp.biztrack.login.model.dto.LoginDto;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao dao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public LoginDto login(LoginDto dto) {
	    LoginDto storedUser = dao.findByEmpId(dto.getEmpId());

	    if (storedUser != null) {
	        System.out.println("DB에 저장된 암호화된 비밀번호: " + storedUser.getEmpPwd());
	        System.out.println("로그인 시 입력한 비밀번호: " + dto.getEmpPwd());
	        System.out.println("비밀번호 일치 여부: " + passwordEncoder.matches(dto.getEmpPwd(), storedUser.getEmpPwd()));

	        if (passwordEncoder.matches(dto.getEmpPwd(), storedUser.getEmpPwd())) {
	            storedUser.setEmpPwd(null); // 비밀번호 숨기기
	            return storedUser;
	        }
	    }
	    return null;
	}

	@Override
	public LoginDto findByEmpId(String empId) {
		return dao.findByEmpId(empId);
	}
	
	 @Override
	    public int updatePassword(LoginDto dto) {
	        String encodedPwd = passwordEncoder.encode(dto.getEmpPwd());
	        dto.setEmpPwd(encodedPwd);
	        return dao.updatePassword(dto);
	    }

	    @Override
	    public boolean isValidEmployeeEmail(String empId, String email) {
	        LoginDto dto = new LoginDto();
	        dto.setEmpId(empId);
	        dto.setEmail(email);
	        return dao.checkEmployeeEmail(dto) > 0;
	    }

		@Override
		public LoginDto adminLogin(LoginDto dto) {
			return dao.adminLogin(dto);
		}

		//첫 로그인 시 비밀번호 변경
		@Override
		public boolean isInitialPassword(String rawPwd, String encodedPwd) {
		    return passwordEncoder.matches(rawPwd, encodedPwd);
		}
		
}
