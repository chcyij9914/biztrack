
package com.erp.biztrack.common;

import java.sql.Date;

public class Search {
	private String keyword; // 제목, 내용, 아이디 (작성자, 회원) 검색 키워드
	private int startRow; // 한 페이지에 출력할 목록의 시작행
	private int endRow; // 한 페이지에 출력할 목록의 끝행
	private Date begin; // 등록날짜 검색의 시작날짜
	private Date end; // 등록날짜 검색의 끝날짜
	private String status; // 등록 상태
	private String scType; // 검색 유형
	private String categoryId; // 카테고리 명
	private String categoryName; // 카테고리 명
	private String filter; // 검색기준
	private String documentTypeId;  // 계약서(C), 제안서(D) 구분용
	private String roleId;
	private String empId;
	
	public Search() {
		super();
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getScType() {
		return scType;
	}

	public void setScType(String scType) {
		this.scType = scType;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(String documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "Search [keyword=" + keyword + ", startRow=" + startRow + ", endRow=" + endRow + ", begin=" + begin
				+ ", end=" + end + ", status=" + status + ", scType=" + scType + ", categoryId=" + categoryId
				+ ", categoryName=" + categoryName + ", filter=" + filter + ", documentTypeId=" + documentTypeId
				+ ", roleId=" + roleId + ", empId=" + empId + "]";
	}
}