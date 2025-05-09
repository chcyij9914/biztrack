package com.erp.biztrack.department.model.dto;

public class Department implements java.io.Serializable {
	private static final long serialVersionUID = 6222406416260481621L;
	
	private String deptId;		//DEPT_ID	CHAR(3 BYTE)
	private String deptName;	//DEPT_NAME	VARCHAR2(50 BYTE)
	private String phone;		//PHONE	VARCHAR2(15 BYTE)
	private int employeeCount;	//EMPLOYEE_COUNT	NUMBER
	private int deptLevel;		//DEPT_LEVEL	NUMBER
	private String parentId;		//PARENT_ID	NUMBER
	
	public Department() {
		super();
	}

	public Department(String deptId, String deptName, String phone, int employeeCount, int deptLevel, String parentId) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.phone = phone;
		this.employeeCount = employeeCount;
		this.deptLevel = deptLevel;
		this.parentId = parentId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(int employeeCount) {
		this.employeeCount = employeeCount;
	}

	public int getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(int deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", phone=" + phone + ", employeeCount="
				+ employeeCount + ", deptLevel=" + deptLevel + ", parentId=" + parentId + "]";
	}
	
}
