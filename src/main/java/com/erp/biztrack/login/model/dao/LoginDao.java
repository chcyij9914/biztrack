package com.erp.biztrack.login.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.login.model.dto.LoginDto;

@Repository
public class LoginDao {

    @Autowired
    private SqlSession sqlSession;

    private final String namespace = "employeeMapper.";

    public LoginDto login(LoginDto dto) {
        return sqlSession.selectOne(namespace + "selectLogin", dto);
    }

    public LoginDto findByEmpId(String empId) {
        return sqlSession.selectOne(namespace + "selectByEmpId", empId);
    }

    public int updatePassword(LoginDto dto) {
        return sqlSession.update(namespace + "updatePassword", dto);
    }

    public LoginDto adminLogin(LoginDto dto) {
        return sqlSession.selectOne(namespace + "selectAdminLogin", dto);
    }
    
    public int checkEmployeeEmail(LoginDto dto) {
        return sqlSession.selectOne(namespace + "checkEmployeeEmail", dto);
    }

}
