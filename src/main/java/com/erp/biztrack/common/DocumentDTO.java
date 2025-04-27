package com.erp.biztrack.common;

public class DocumentDTO implements java.io.Serializable {
	private static final long serialVersionUID = -7970793635367626682L;

	private String documentId; 				// DOCUMENT_ID VARCHAR2(30 BYTE)
	private String documentType; 			// DOCUMENT_TYPE VARCHAR2(30 BYTE)
	private String clientId; 						// CLIENT_ID VARCHAR2(30 BYTE)
	private String documentWriter; 			// DOCUMENT_WRITER_ID VARCHAR2(30 BYTE)
	private String documentManagerId; 	// DOCUMENT_MANAGER_ID VARCHAR2(30 BYTE)

	// Constructor
	public DocumentDTO() {
		super();
	}

	public DocumentDTO(String documentId, String documentType, String clientId, String documentWriter,
			String documentManagerId) {
		super();
		this.documentId = documentId;
		this.documentType = documentType;
		this.clientId = clientId;
		this.documentWriter = documentWriter;
		this.documentManagerId = documentManagerId;
	}

	// getters and setters
	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getDocumentWriter() {
		return documentWriter;
	}

	public void setDocumentWriter(String documentWriter) {
		this.documentWriter = documentWriter;
	}

	public String getDocumentManagerId() {
		return documentManagerId;
	}

	public void setDocumentManagerId(String documentManagerId) {
		this.documentManagerId = documentManagerId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DocumentDTO [documentId=" + documentId + ", documentType=" + documentType + ", clientId=" + clientId
				+ ", documentWriter=" + documentWriter + ", documentManagerId=" + documentManagerId + "]";
	}

}
