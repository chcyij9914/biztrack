package com.erp.biztrack.admin.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.login.model.dto.LoginDto;

@Repository
public class AdminDao {

    @Autowired
    private SqlSession sqlSession;

    private final String namespace = "adminMapper.";

    public List<LoginDto> getEmployeeList() {
        return sqlSession.selectList(namespace + "getEmployeeList");
    }

    public int insertEmployee(LoginDto dto) {
        return sqlSession.insert(namespace + "insertEmployee", dto);
    }

    public List<LoginDto> searchEmployees(String keyword, int offset, int limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("offset", offset);
        params.put("limit", limit);
        return sqlSession.selectList(namespace + "searchEmployees", params);
    }

    public int countEmployees(String keyword) {
        return sqlSession.selectOne(namespace + "countEmployees", keyword);
    }

    public LoginDto getEmployeeById(String empId) {
        return sqlSession.selectOne(namespace + "getEmployeeById", empId);
    }
    
    public int updateEmployee(LoginDto dto) {
        return sqlSession.update("adminMapper.updateEmployee", dto);
    }
    
    public int retireEmployee(String empId) {
        return sqlSession.update(namespace + "retireEmployee", empId);
    }

}