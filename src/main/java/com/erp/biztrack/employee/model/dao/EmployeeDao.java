package com.erp.biztrack.employee.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.employee.model.dto.Employee;

@Repository("employeeDao")
public class EmployeeDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Employee> selectAll() {
        return sqlSessionTemplate.selectList("employeeMapper.selectAll");
    }
	
	public Employee selectEmpById(String empId) {
	    return sqlSessionTemplate.selectOne("employeeMapper.selectEmpById", empId);
	}
}
