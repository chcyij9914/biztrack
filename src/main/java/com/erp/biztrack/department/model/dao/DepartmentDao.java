package com.erp.biztrack.department.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.department.model.dto.Department;

@Repository("departmentDao")
public class DepartmentDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<Department> selectAll() {		
		return sqlSessionTemplate.selectList("departmentMapper.selectAll");
	}

	public int insertDepartment(Department department) {
		return sqlSessionTemplate.insert("departmentMapper.insertDepartment", department);
	}

	public int updateDepartment(Department department) {
		return sqlSessionTemplate.update("departmentMapper.updateDepartment", department);
	}

	public int deleteDepartment(String deptId) {
		return sqlSessionTemplate.delete("departmentMapper.deleteDepartment", deptId);
	}

	public int updateParent(Department department) {
		return sqlSessionTemplate.update("departmentMapper.updateParent", department);
	}

	public int selectParentLevel(String parentId) {
		return sqlSessionTemplate.selectOne("departmentMapper.selectParentLevel", parentId);
	}
	
	
}
