package com.erp.biztrack.admin.model.service;

import java.util.List;

import com.erp.biztrack.login.model.dto.LoginDto;

public interface AdminService {
    List<LoginDto> getEmployeeList();

    int insertEmployee(LoginDto dto);

    List<LoginDto> searchEmployees(String keyword, int offset, int limit);

    int countEmployees(String keyword);

    LoginDto getEmployeeById(String empId);
    
    int updateEmployee(LoginDto dto);
    
    int retireEmployee(String empId);
}