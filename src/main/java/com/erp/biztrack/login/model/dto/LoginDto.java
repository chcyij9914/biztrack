package com.erp.biztrack.login.model.dto;

public class LoginDto {
    private String empId;       	// 사원번호 (아이디)
    private String empPwd;      	// 비밀번호
    private String empName;     	// 이름
    private String email;       	// 이메일
    private String phone;       	// 전화번호
    private String roleId;      	// 역할ID (예: TEMP, 일반, 관리자 등)
    private String deptId;     		// 부서ID
    private String jobId;       	// 직급ID
    private String adminYN;     	// 관리자 여부 (Y/N)
    private String hireDay;     	// 입사일
    private String salary;      	// 급여
    private String marriageYN;  	// 결혼여부 (Y/N)
    private String empNo;       	// 사원 등록번호 (추가정보)
    private String retireYN; 		// 퇴사 여부
    private String retireDate; 		// 퇴사일
    private String isDefaultPwd; 	// 초기 비번

    // === Getter & Setter ===
    public String getEmpId() {
        return empId;
    }
    public void setEmpId(String empId) {
        this.empId = empId;
    }
    public String getEmpPwd() {
        return empPwd;
    }
    public void setEmpPwd(String empPwd) {
        this.empPwd = empPwd;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
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
    public String getJobId() {
        return jobId;
    }
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    public String getAdminYN() {
        return adminYN;
    }
    public void setAdminYN(String adminYN) {
        this.adminYN = adminYN;
    }
    public String getHireDay() {
        return hireDay;
    }
    public void setHireDay(String hireDay) {
        this.hireDay = hireDay;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getMarriageYN() {
        return marriageYN;
    }
    public void setMarriageYN(String marriageYN) {
        this.marriageYN = marriageYN;
    }
    public String getEmpNo() {
        return empNo;
    }
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
    
    public String getRetireYN() {
        return retireYN;
    }
    public void setRetireYN(String retireYN) {
        this.retireYN = retireYN;
    }
    
    public String getRetireDate() {
        return retireDate;
    }
    public void setRetireDate(String retireDate) {
        this.retireDate = retireDate;
    }
    
	public String getIsDefaultPwd() {
		return isDefaultPwd;
	}
	public void setIsDefaultPwd(String isDefaultPwd) {
		this.isDefaultPwd = isDefaultPwd;
	}
	
	@Override
	public String toString() {
		return "LoginDto [empId=" + empId + ", empPwd=" + empPwd + ", empName=" + empName + ", email=" + email
				+ ", phone=" + phone + ", roleId=" + roleId + ", deptId=" + deptId + ", jobId=" + jobId + ", adminYN="
				+ adminYN + ", hireDay=" + hireDay + ", salary=" + salary + ", marriageYN=" + marriageYN + ", empNo="
				+ empNo + ", retireYN=" + retireYN + ", retireDate=" + retireDate + ", isDefaultPwd=" + isDefaultPwd
				+ "]";
	}
	
	
}
