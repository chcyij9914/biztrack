package com.erp.biztrack.department.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.department.model.dao.DepartmentDao;
import com.erp.biztrack.department.model.dto.Department;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public List<Department> selectAll() {
		return departmentDao.selectAll();
	}

	@Override
	public int insertDepartment(Department department) {
		return departmentDao.insertDepartment(department);
	}

	@Override
	public int updateDepartment(Department department) {
		return departmentDao.updateDepartment(department);
	}

	@Override
	public int deleteDepartment(String deptId) {
		return departmentDao.deleteDepartment(deptId);
	}

	@Override
	public int updateParenet(Department department) {
		return departmentDao.updateParent(department);
	}

	@Override
	public int selectParentLevel(String parentId) {
		return departmentDao.selectParentLevel(parentId);
	}
	
}