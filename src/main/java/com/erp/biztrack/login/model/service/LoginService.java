package com.erp.biztrack.login.model.service;

import com.erp.biztrack.login.model.dto.LoginDto;

public interface LoginService {
    LoginDto login(LoginDto dto) throws Exception;
    LoginDto findByEmpId(String empId);
    LoginDto adminLogin(LoginDto dto);
	boolean isValidEmployeeEmail(String empId, String email);
	int updatePassword(LoginDto dto);
	boolean isInitialPassword(String rawPwd, String encodedPwd);
}
