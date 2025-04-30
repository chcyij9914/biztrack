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

    // toString
    @Override
    public String toString() {
        return "Schedule [scId=" + scId + ", empId=" + empId + ", empName=" + empName +
               ", scTitle=" + scTitle + ", calColor=" + calColor + ", scType=" + scType +
               ", place=" + place + ", startDatetime=" + startDatetime + ", endDatetime=" + endDatetime + "]";
    }
}
