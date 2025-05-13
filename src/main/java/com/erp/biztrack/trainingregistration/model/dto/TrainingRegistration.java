package com.erp.biztrack.trainingregistration.model.dto;

import java.sql.Date;

import com.erp.biztrack.training.model.dto.Training;

public class TrainingRegistration {
	
	private String registrationId;
	private String trainingId;
	private String employeeId;
	private String registrationAt;
	private String isCancelled;
	private String name;
	private String birth;
	private String phone;
	private String email;
	private String org;
	
	private int current;
	private String status;

    private Training training; // 외래키 연동된 교육 정보 포함
    private String title;
    private String courseContent;
    private String instructorName;
    private Date startDate;
    private Date endDate;
    private int capacity;
    private String location;

    
    public TrainingRegistration () {
    	super();
    }


	public TrainingRegistration(String registrationId, String trainingId, String employeeId, String registrationAt,
			String isCancelled, String name, String birth, String phone, String email, String org, int current,
			String status, Training training, String title, String courseContent, String instructorName, Date startDate,
			Date endDate, int capacity, String location) {
		super();
		this.registrationId = registrationId;
		this.trainingId = trainingId;
		this.employeeId = employeeId;
		this.registrationAt = registrationAt;
		this.isCancelled = isCancelled;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.org = org;
		this.current = current;
		this.status = status;
		this.training = training;
		this.title = title;
		this.courseContent = courseContent;
		this.instructorName = instructorName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity = capacity;
		this.location = location;
	}


	public String getRegistrationId() {
		return registrationId;
	}


	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}


	public String getTrainingId() {
		return trainingId;
	}


	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public String getRegistrationAt() {
		return registrationAt;
	}


	public void setRegistrationAt(String registrationAt) {
		this.registrationAt = registrationAt;
	}


	public String getIsCancelled() {
		return isCancelled;
	}


	public void setIsCancelled(String isCancelled) {
		this.isCancelled = isCancelled;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getOrg() {
		return org;
	}


	public void setOrg(String org) {
		this.org = org;
	}


	public int getCurrent() {
		return current;
	}


	public void setCurrent(int current) {
		this.current = current;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Training getTraining() {
		return training;
	}


	public void setTraining(Training training) {
		this.training = training;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCourseContent() {
		return courseContent;
	}


	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}


	public String getInstructorName() {
		return instructorName;
	}


	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public String toString() {
		return "TrainingRegistration [registrationId=" + registrationId + ", trainingId=" + trainingId + ", employeeId="
				+ employeeId + ", registrationAt=" + registrationAt + ", isCancelled=" + isCancelled + ", name=" + name
				+ ", birth=" + birth + ", phone=" + phone + ", email=" + email + ", org=" + org + ", current=" + current
				+ ", status=" + status + ", training=" + training + ", title=" + title + ", courseContent="
				+ courseContent + ", instructorName=" + instructorName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", capacity=" + capacity + ", location=" + location + "]";
	}


	

}