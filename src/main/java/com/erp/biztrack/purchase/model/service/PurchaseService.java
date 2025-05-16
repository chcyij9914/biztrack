package com.erp.biztrack.purchase.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dto.Inbound;
import com.erp.biztrack.product.model.dto.Product;
import com.erp.biztrack.purchase.model.dto.Purchase;

public interface PurchaseService {
	// 문서 목록 조회
		ArrayList<DocumentDTO> selectDocumentListByType(Map<String, Object> param);
	    int selectDocumentListCountByType(Map<String, Object> param);
	    ArrayList<DocumentDTO> selectDocumentListByType2(Map<String, Object> param);
	    int selectDocumentListCountByType2(Map<String, Object> param);
	    ArrayList<DocumentDTO> selectDocumentListByType3(Map<String, Object> param);
	    int selectDocumentListCountByType3(Map<String, Object> param);
	    
	    ArrayList<DocumentDTO> selectDocumentListByTypeT(Map<String, Object> param);
	    int selectDocumentListCountByTypeT(Map<String, Object> param);
	    ArrayList<DocumentDTO> selectDocumentListByTypeT2(Map<String, Object> param);
	    int selectDocumentListCountByTypeT2(Map<String, Object> param);
	    ArrayList<DocumentDTO> selectDocumentListByTypeT3(Map<String, Object> param);
	    int selectDocumentListCountByTypeT3(Map<String, Object> param);

    //검색기능------품의서---------------------------------------------------------------
	    List<Purchase> searchByDocumentId(String documentId, String empId);
	    List<Purchase> searchByDocumentId2(String documentId, String empId);
	    List<Purchase> searchByDocumentId3(String documentId, String empId);
	    
	    List<Purchase> searchByTitle(String title, String empId);
	    List<Purchase> searchByTitle2(String title, String empId);
	    List<Purchase> searchByTitle3(String title, String empId);

	    List<Purchase> searchByStatus(String status, String empId);
	    List<Purchase> searchByStatus2(String status, String empId);
	    List<Purchase> searchByStatus3(String status, String empId);
	    
    //검색기능------지출결의서---------------------------------------------------------------
	    List<Purchase> searchByDocumentIdT(String documentId, String empId);
	    List<Purchase> searchByDocumentIdT2(String documentId, String empId);
	    List<Purchase> searchByDocumentIdT3(String documentId, String empId);
	    
	    List<Purchase> searchByTitleT(String title, String empId);
	    List<Purchase> searchByTitleT2(String title, String empId);
	    List<Purchase> searchByTitleT3(String title, String empId);

	    List<Purchase> searchByStatusT(String status, String empId);
	    List<Purchase> searchByStatusT2(String status, String empId);
	    List<Purchase> searchByStatusT3(String status, String empId);

    
	// 문서 상세보기
	Purchase selectPurchaseDetail(String documentId);

	// 문서등록관련---------------------------------
	int insertDocument(DocumentDTO document);
	int insertDocumentItem(DocumentItemDTO item);
	int insertApproval(ApproveDTO approval);
	String selectNextDocumentIdR();
	String selectNextDocumentIdT();
	String selectNextApproveId();
	String selectNextItemId();
    int insertFile(FileDTO file);

   //문서 상세정보 관련 -------------------------------------
    FileDTO selectFileByDocumentId(String documentId);
    DocumentDTO selectOneDocument(String documentId);
    ArrayList<DocumentItemDTO> selectDocumentItemList(String documentId);
    ApproveDTO selectApprovalByDocumentId(String documentId);
    
    //문서 수정 관련 --------------------------------------------
    int updateDocument(DocumentDTO document);
    int deleteDocumentItems(String documentId);
    int updateApprove(ApproveDTO approve);
    int updateDocumentItem(DocumentItemDTO item);
    int deleteFileByDocumentId(String documentId);
    
    //문서 삭제 관련 ----------------------------------------------
    int deleteDocumentOnly(String documentId);
    int deleteApprove(String documentId);

	List<Client> selectAllClients();
	
	 //결재자 결재 상태 수정----------------------------------------
    int updateApprovalStatus(ApproveDTO approve);
}