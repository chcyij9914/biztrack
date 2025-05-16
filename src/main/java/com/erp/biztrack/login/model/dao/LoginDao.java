package com.erp.biztrack.login.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.employee.model.dto.Employee;
import com.erp.biztrack.login.model.dto.LoginDto;

@Repository
public class LoginDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    private final String namespace = "employeeMapper.";

    public LoginDto login(LoginDto dto) {
        return sqlSessionTemplate.selectOne(namespace + "selectLogin", dto);
    }

    public LoginDto findByEmpId(String empId) {
        return sqlSessionTemplate.selectOne(namespace + "selectByEmpId", empId);
    }

    public int updatePassword(LoginDto dto) {
        return sqlSessionTemplate.update(namespace + "updatePassword", dto);
    }

    public LoginDto adminLogin(LoginDto dto) {
        return sqlSessionTemplate.selectOne(namespace + "selectAdminLogin", dto);
    }
    
    public int checkEmployeeEmail(LoginDto dto) {
        return sqlSessionTemplate.selectOne(namespace + "checkEmployeeEmail", dto);
    }

	
}
