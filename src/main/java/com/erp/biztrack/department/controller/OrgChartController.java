package com.erp.biztrack.department.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.biztrack.department.model.dto.Department;
import com.erp.biztrack.department.model.service.DepartmentService;
import com.erp.biztrack.employee.model.dto.Employee;
import com.erp.biztrack.employee.model.service.EmployeeService;

@Controller
@RequestMapping("/department")
public class OrgChartController {
	private static final Logger logger = LoggerFactory.getLogger(OrgChartController.class);
	
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/chart.do")
    public String showChart(Model model) {
    	List<Department> departments = departmentService.selectAll();
        Map<String, List<Employee>> employeeMap = employeeService.getEmployeesGroupedByDept();

        logger.info("chart.do : " + employeeMap.size());
        
        model.addAttribute("deptList", departments);
        model.addAttribute("employeeMap", employeeMap);

        return "/department/orgChart_full_features";  // 파일 이름과 일치시킬 것
    }



    @RequestMapping(value="/add.do", method=RequestMethod.POST)    
    public String addDepartment(@ModelAttribute Department dept) {
    	logger.info("add.do : " + dept);
    	
    	//상위 부서의 레벨을 조회해 와서, 레벨을 1증가한 값을 새로 등록하는 부서 레벨로 지정함
    	dept.setDeptLevel(departmentService.selectParentLevel(dept.getParentId()) + 1);
    	//새 부서 등록 처리
        departmentService.insertDepartment(dept);
        return "redirect:/department/chart.do";
    }

    @RequestMapping(value="/update.do", method=RequestMethod.POST)   
    public String updateDepartment(@ModelAttribute Department dept) {
        departmentService.updateDepartment(dept);
        return "redirect:/department/chart.do";
    }

    @RequestMapping(value="/delete.do", method=RequestMethod.POST)     
    public String deleteDepartment(@RequestParam("deptId") String deptId) {
        departmentService.deleteDepartment(deptId);
        return "redirect:/department/chart.do";
    }

    @RequestMapping(value="/move.do", method=RequestMethod.POST) 
    @ResponseBody
    public String moveDepartment(@ModelAttribute Department dept) {
        departmentService.updateParenet(dept);
        return "success";
    }
    
    // 1. 팝업 JSP 연결
    @RequestMapping("approverPopup.do")
    public String showApproverPopup(@RequestParam String target, Model model) {
        model.addAttribute("target", target);
        return "department/approverPopup";
    }
}