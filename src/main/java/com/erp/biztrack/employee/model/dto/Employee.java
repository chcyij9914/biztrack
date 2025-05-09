package com.erp.biztrack.employee.model.dto;

public class Employee implements java.io.Serializable {
	private static final long serialVersionUID = 128317191044087198L;

	private String empId;       // 사원번호 (아이디)
    private String empPwd;      // 비밀번호
    private String empName;     // 이름
    private String email;       // 이메일
    private String phone;       // 전화번호
    private String roleId;      // 역할ID (예: TEMP, 일반, 관리자 등)
    private String roleName;	//ROLE_NAME
    private String deptId;      // 부서ID
    private String deptName;      // 부서ID
    private String jobId;       // 직급ID
    private String jobTitle;       // 직급ID
    private String adminYN;     // 관리자 여부 (Y/N)
    private String hireDay;     // 입사일
    private String salary;      // 급여
    private String marriageYN;  // 결혼여부 (Y/N)
    private String empNo;       // 사원 등록번호 (추가정보)
    
	public Employee() {
		super();
	}

	public Employee(String empId, String empPwd, String empName, String email, String phone, String roleId,
			String roleName, String deptId, String deptName, String jobId, String jobTitle, String adminYN,
			String hireDay, String salary, String marriageYN, String empNo) {
		super();
		this.empId = empId;
		this.empPwd = empPwd;
		this.empName = empName;
		this.email = email;
		this.phone = phone;
		this.roleId = roleId;
		this.roleName = roleName;
		this.deptId = deptId;
		this.deptName = deptName;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.adminYN = adminYN;
		this.hireDay = hireDay;
		this.salary = salary;
		this.marriageYN = marriageYN;
		this.empNo = empNo;
	}

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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empPwd=" + empPwd + ", empName=" + empName + ", email=" + email
				+ ", phone=" + phone + ", roleId=" + roleId + ", roleName=" + roleName + ", deptId=" + deptId
				+ ", deptName=" + deptName + ", jobId=" + jobId + ", jobTitle=" + jobTitle + ", adminYN=" + adminYN
				+ ", hireDay=" + hireDay + ", salary=" + salary + ", marriageYN=" + marriageYN + ", empNo=" + empNo
				+ "]";
	}
}
