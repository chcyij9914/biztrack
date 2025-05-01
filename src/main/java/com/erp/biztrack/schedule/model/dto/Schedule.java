package com.erp.biztrack.schedule.model.dto;

import java.sql.Timestamp;

public class Schedule implements java.io.Serializable {
    private static final long serialVersionUID = 7069296863418517748L;

    // Field
    private String scId;
    private String empId;
    private String empName;
    private String scTitle;
    private String calColor;
    private String scType;
    private String place;
    private Timestamp startDatetime;
    private Timestamp endDatetime;

    // 검색 필드 추가
    private String searchType;
    private String keyword;
    private String beginDate;
    private String endDate;

    // Constructor
    public Schedule() {
        super();
    }

    public Schedule(String scId, String empId, String empName, String scTitle, String calColor, String scType,
                    String place, Timestamp startDatetime, Timestamp endDatetime) {
        super();
        this.scId = scId;
        this.empId = empId;
        this.empName = empName;
        this.scTitle = scTitle;
        this.calColor = calColor;
        this.scType = scType;
        this.place = place;
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    // Getters and Setters
    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getScTitle() {
        return scTitle;
    }

    public void setScTitle(String scTitle) {
        this.scTitle = scTitle;
    }

    public String getCalColor() {
        return calColor;
    }

    public void setCalColor(String calColor) {
        this.calColor = calColor;
    }

    public String getScType() {
        return scType;
    }

    public void setScType(String scType) {
        this.scType = scType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Timestamp getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Timestamp startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Timestamp getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Timestamp endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    // toString
    @Override
    public String toString() {
        return "Schedule [scId=" + scId + ", empId=" + empId + ", empName=" + empName +
               ", scTitle=" + scTitle + ", calColor=" + calColor + ", scType=" + scType +
               ", place=" + place + ", startDatetime=" + startDatetime + ", endDatetime=" + endDatetime +
               ", searchType=" + searchType + ", keyword=" + keyword + 
               ", beginDate=" + beginDate + ", endDate=" + endDate + "]";
    }
}
