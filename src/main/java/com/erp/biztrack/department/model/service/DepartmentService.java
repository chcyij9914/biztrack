package com.erp.biztrack.department.model.service;

import java.util.List;

import com.erp.biztrack.department.model.dto.Department;

public interface DepartmentService {
	List<Department> selectAll();  // 부서 전체 조회 (직원수 포함)
    int insertDepartment(Department department);  // 새 부서 등록
    int updateDepartment(Department department); //부서 수정
    int deleteDepartment(String deptId);  //부서 삭제
    int updateParenet(Department department);  //상위 부서 변경
	int selectParentLevel(String parentId);  //상위 부서 레벨 조회용
}
