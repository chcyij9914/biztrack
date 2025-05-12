package com.erp.biztrack.employee.model.service;

import java.util.List;
import java.util.Map;

import com.erp.biztrack.employee.model.dto.Employee;

public interface EmployeeService {
    List<Employee> selectAll();
    Map<String, List<Employee>> getEmployeesGroupedByDept();
    Employee selectEmpById(String empId);
}
