package com.erp.biztrack.evaluation.model.dto;

public class EvaluationFilterDto {
	private String evaluatorId;
	private String empId;
    private String year;
    private String quarter;
    private String roleId;
    private String deptId;

    // Getters & Setters
    
    public String getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(String evaluatorId) {
        this.evaluatorId = evaluatorId;
    }
    
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getDeptId() {
        return deptId;
    }
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
    
    public String getEmpId() {
        return empId;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    public String getQuarter() {
        return quarter;
    }
    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }
}
