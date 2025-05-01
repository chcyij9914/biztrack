package com.erp.biztrack.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.biztrack.admin.model.dao.AdminDao;
import com.erp.biztrack.login.model.dto.LoginDto;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao dao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<LoginDto> getEmployeeList() {
        return null;
    }

    @Override
    public int insertEmployee(LoginDto dto) {
        return dao.insertEmployee(dto);
    }

    private String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int)(Math.random() * characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

    @Override
    public List<LoginDto> searchEmployees(String keyword, int offset, int limit) {
        return dao.searchEmployees(keyword, offset, limit);
    }

    @Override
    public int countEmployees(String keyword) {
        return dao.countEmployees(keyword);
    }

    @Override
    public LoginDto getEmployeeById(String empId) {
        return dao.getEmployeeById(empId);
    }
    
    //회원 정보 수정 
    @Override
    public int updateEmployee(LoginDto dto) {
        return dao.updateEmployee(dto);
    }
    
    @Override
    public int retireEmployee(String empId) {
        return dao.retireEmployee(empId);
    }
    

}
