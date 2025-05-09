package com.erp.biztrack.employee.model.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.employee.model.dao.EmployeeDao;
import com.erp.biztrack.employee.model.dto.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> selectAll() {
        return employeeDao.selectAll();
    }

    @Override
    public Map<String, List<Employee>> getEmployeesGroupedByDept() {
        List<Employee> all = selectAll();
        return all.stream().collect(Collectors.groupingBy(Employee::getDeptId));
    }
}