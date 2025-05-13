package com.erp.biztrack.training.model.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Training implements java.io.Serializable {
	
	    private String trainingId;
	    private String title;
	    private String courseContent;
	    private String instructorName;
	    
	    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	    private Date startDate;
	    private Date endDate;
	    private int capacity;
	    private String location;
	    private String detailContent;
	    
	    public Training() {
	    	super();
	    }

		public Training(String trainingId, String title, String courseContent, String instructorName, Date startDate,
				Date endDate, int capacity, String location , String detailContent) {
			super();
			this.trainingId = trainingId;
			this.title = title;
			this.courseContent = courseContent;
			this.instructorName = instructorName;
			this.startDate = startDate;
			this.endDate = endDate;
			this.capacity = capacity;
			this.location = location;
			this.detailContent = detailContent;
		}

		public String getTrainingId() {
			return trainingId;
		}

		public void setTrainingId(String trainingId) {
			this.trainingId = trainingId;
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
		
		public String getDetailContent() {
			return detailContent;
		}

		public void setDetailContent(String detailContent) {
			this.detailContent = detailContent;
		}

		@Override
		public String toString() {
			return "Training [trainingId=" + trainingId + ", title=" + title + ", courseContent=" + courseContent
					+ ", instructorName=" + instructorName + ", startDate=" + startDate + ", endDate=" + endDate
					+ ", capacity=" + capacity + ", location=" + location + ", detailContent="+ detailContent+" ]";
		}

	
	    
}
