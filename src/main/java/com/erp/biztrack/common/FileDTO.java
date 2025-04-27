package com.erp.biztrack.common;

public class FileDTO implements java.io.Serializable {
	private static final long serialVersionUID = -9167851553414504848L;

	// Field
	private int uploadFileId; 						// UPLOAD_FILE_ID NUMBER(38,0)
	private Integer noticeNo; 						// NOTICE_NO NUMBER(38,0)
	private String documentId; 					// DOCUMENT_ID VARCHAR2(30 BYTE)
	private String trainingId; 						// TRAINING_ID VARCHAR2(30 BYTE)
	private String clientId;							// CLIENT_ID	VARCHAR2(30 BYTE)
	private String filePath; 						// FILE_NAME VARCHAR2(255 BYTE) + 파일경로
	private String originalFileName; 				// ORIGINAL_FILENAME VARCHAR2(100 BYTE)
	private String renameFileName; 				// RENAME_FILENAME VARCHAR2(100 BYTE)
	private int uploadFileSize; 					// UPLOAD_FILE_SIZE NUMBER

	// Constructor
	public FileDTO() {
		super();
	}

	public FileDTO(int uploadFileId, Integer noticeNo, String documentId, String trainingId, String clientId,
			String filePath, String originalFileName, String renameFileName, int uploadFileSize) {
		super();
		this.uploadFileId = uploadFileId;
		this.noticeNo = noticeNo;
		this.documentId = documentId;
		this.trainingId = trainingId;
		this.clientId = clientId;
		this.filePath = filePath;
		this.originalFileName = originalFileName;
		this.renameFileName = renameFileName;
		this.uploadFileSize = uploadFileSize;
	}

	// getters and setters
	public int getUploadFileId() {
		return uploadFileId;
	}

	public void setUploadFileId(int uploadFileId) {
		this.uploadFileId = uploadFileId;
	}

	public Integer getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(Integer noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}
	
	public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenameFileName() {
		return renameFileName;
	}

	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}

	public int getUploadFileSize() {
		return uploadFileSize;
	}

	public void setUploadFileSize(int uploadFileSize) {
		this.uploadFileSize = uploadFileSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FileDTO [uploadFileId=" + uploadFileId + ", noticeNo=" + noticeNo + ", documentId=" + documentId
				+ ", trainingId=" + trainingId + ", clientId=" + clientId + ", filePath=" + filePath
				+ ", originalFileName=" + originalFileName + ", renameFileName=" + renameFileName + ", uploadFileSize="
				+ uploadFileSize + "]";
	}
}
