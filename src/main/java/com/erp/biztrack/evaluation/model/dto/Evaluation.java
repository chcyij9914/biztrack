package com.erp.biztrack.evaluation.model.dto;

import java.sql.Date;

public class Evaluation {
    private String evaluationId;      // 평가번호
    private String evaluationName;    // 평가항목
    private String evaluatorId;       // 평가자
    private Date evaluationDay;       // 평가일자
    private int totalScore;           // 총점
    private int scoreSales;           // 영업실적점수
    private int scoreAttitude;        // 근무태도점수
    private int scoreTraining;        // 교육점수
    private String comments;          // 평가멘트
    private String empId;             // 사번
    private String recordId;          // 교육ID
    private String grade;             // 평가등급   
    // 평가자 이름, 사원 이름, 부서명 등 조인으로 출력할 항목(옵션)
    private String empName;
    private String deptName;

    // 기본 생성자
    public Evaluation() {}

    // 전체 생성자
    public Evaluation(String evaluationId, String evaluationName, String evaluatorId, Date evaluationDay,
                         int totalScore, int scoreSales, int scoreAttitude, int scoreTraining, String comments,
                         String empId, String recordId, String grade) {
        this.evaluationId = evaluationId;
        this.evaluationName = evaluationName;
        this.evaluatorId = evaluatorId;
        this.evaluationDay = evaluationDay;
        this.totalScore = totalScore;
        this.scoreSales = scoreSales;
        this.scoreAttitude = scoreAttitude;
        this.scoreTraining = scoreTraining;
        this.comments = comments;
        this.empId = empId;
        this.recordId = recordId;
        this.grade = grade;
    }

    // getter/setter
    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getEvaluationName() {
        return evaluationName;
    }

    public void setEvaluationName(String evaluationName) {
        this.evaluationName = evaluationName;
    }

    public String getEvaluatorId() {
        return evaluatorId;
    }

    public void setEvaluatorId(String evaluatorId) {
        this.evaluatorId = evaluatorId;
    }

    public Date getEvaluationDay() {
        return evaluationDay;
    }

    public void setEvaluationDay(Date evaluationDay) {
        this.evaluationDay = evaluationDay;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getScoreSales() {
        return scoreSales;
    }

    public void setScoreSales(int scoreSales) {
        this.scoreSales = scoreSales;
    }

    public int getScoreAttitude() {
        return scoreAttitude;
    }

    public void setScoreAttitude(int scoreAttitude) {
        this.scoreAttitude = scoreAttitude;
    }

    public int getScoreTraining() {
        return scoreTraining;
    }

    public void setScoreTraining(int scoreTraining) {
        this.scoreTraining = scoreTraining;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

   
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Evaluation [evaluationId=" + evaluationId + ", evaluationName=" + evaluationName + ", evaluatorId="
				+ evaluatorId + ", evaluationDay=" + evaluationDay + ", totalScore=" + totalScore + ", scoreSales="
				+ scoreSales + ", scoreAttitude=" + scoreAttitude + ", scoreTraining=" + scoreTraining + ", comments="
				+ comments + ", empId=" + empId + ", recordId=" + recordId + ", grade=" + grade + ", empName=" + empName
				+ ", deptName=" + deptName + "]";
	}

	
	}
    
    

