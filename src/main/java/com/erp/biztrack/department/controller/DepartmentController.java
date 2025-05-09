package com.erp.biztrack.department.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.biztrack.department.model.dto.Department;
import com.erp.biztrack.department.model.service.DepartmentService;
import com.erp.biztrack.employee.model.dto.Employee;
import com.erp.biztrack.employee.model.service.EmployeeService;

@Controller
@RequestMapping("/dept")
public class DepartmentController {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping("/tree.do")
	@ResponseBody
	public List<Map<String, Object>> getTree() {
		logger.info("tree.do");
		List<Department> depts = departmentService.selectAll();
		List<Employee> employees = employeeService.selectAll();

		return buildTreeWithEmployees(depts, employees);
	}

	private List<Map<String, Object>> buildTreeWithEmployees(List<Department> departments, List<Employee> employees) {

		Map<String, Map<String, Object>> deptMap = new HashMap<>();
		List<Map<String, Object>> roots = new ArrayList<>();

		// 1. 부서 노드 구성
		for (Department dept : departments) {
			Map<String, Object> node = new HashMap<>();
			node.put("title", dept.getDeptName() + " (" + dept.getEmployeeCount() + "명)");
			node.put("key", "D" + dept.getDeptId());
			node.put("folder", true);
			node.put("children", new ArrayList<Map<String, Object>>());
			deptMap.put(dept.getDeptId(), node);
		}

		// 2. 부서 계층 트리 설정
		for (Department dept : departments) {
			Map<String, Object> node = deptMap.get(dept.getDeptId());
			if (dept.getParentId() == null || !deptMap.containsKey(dept.getParentId())) {
				roots.add(node); // 루트 부서
			} else {
				Map<String, Object> parent = deptMap.get(dept.getParentId());
				((List<Map<String, Object>>) parent.get("children")).add(node);
			}
		}

		// 3. 직원 노드 부서에 연결
		for (Employee emp : employees) {
		    String deptId = emp.getDeptId();
		    if (deptId != null && deptMap.containsKey(deptId)) {
		        Map<String, Object> empNode = new HashMap<>();
		        String roleTitle = (emp.getRoleName() != null) ? emp.getRoleName() : "직원";

		        empNode.put("title", emp.getEmpName() + " (" + roleTitle + ")");
		        empNode.put("key", "E" + emp.getEmpId());
		        empNode.put("folder", false);

//		        empNode.put("empId", emp.getEmpId());
//		        empNode.put("empName", emp.getEmpName());
//		        empNode.put("deptName", emp.getDeptName());
//		        empNode.put("jobTitle", emp.getJobTitle());
//		        empNode.put("roleName", emp.getRoleName());
		        
		        Map<String, Object> data = new HashMap<>();
		        data.put("empId", emp.getEmpId());
		        data.put("empName", emp.getEmpName());
		        data.put("deptName", emp.getDeptName());
		        data.put("jobTitle", emp.getJobTitle());
		        data.put("roleName", emp.getRoleName());

		        empNode.put("data", data);

		        ((List<Map<String, Object>>) deptMap.get(deptId).get("children")).add(empNode);
		    }
		}
		
		return roots;
	}

}
