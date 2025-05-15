package com.erp.biztrack.client.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;
import com.erp.biztrack.product.model.dto.Product;

public interface ClientService {
	//거래처 목록 조회 관련 ----------------------
	ArrayList<Client> selectClientList(Paging paging);
	int selectListCount();
	//거래처 등록 관련 ----------------------------
	int insertClient(Client client);
    String selectLastClientId();
    int insertFile(FileDTO file);
    int insertCategoryClient(Client client);
    String selectLatestCategoryId();
    int updateClientStatus(Client client);
    ArrayList<Client> selectAllClients();
    //거래처 상세보기 관련 --------------------------------
    Client selectClientDetail(String clientId);
    String selectContractFilePath(String clientId);
    String selectBusinessCardFilePath(String clientId);
    ArrayList<DocumentDTO> selectContractListByClientAndDept(DocumentDTO cond);
    //거래처 수정 관련 -----------------------------------
    int updateClient(Client client);
    int deleteFileByClientIdOnly(String clientId);
    int updateContractPeriod(Client client);
    //거래처 검색 관련 ------------------------------------
    int selectSearchClientNameCount(String keyword);
    ArrayList<Client> selectSearchClientNameList(Search search);
    int selectSearchClientStatusCount(String status);
    ArrayList<Client> selectSearchClientStatusList(Search search);
    int selectSearchClientCategoryCount(String categoryId);
    ArrayList<Client> selectSearchClientCategoryList(Search search);
    ArrayList<Client> selectCategoryList();
    // 거래처 삭제
    int deleteClient(String clientId);
    //거래처 문서 목록 조회 관련 ---------------------------------------------
    ArrayList<DocumentDTO> selectDocumentListByWriter(Map<String, Object> param);
    int selectDocumentCountByWriter(Map<String, Object> param);
    ArrayList<DocumentDTO> selectDocumentListByApprover(Map<String, Object> param);
    int selectDocumentCountByApprover(Map<String, Object> param);
    ArrayList<DocumentDTO> selectAllDocumentList(Map<String, Object> param);
    int selectAllDocumentCount(Map<String, Object> param);
    //문서등록관련---------------------------------
    int insertDocument(DocumentDTO document);
    int insertDocumentItem(DocumentItemDTO item);
    int insertApproval(ApproveDTO approval);
    String selectNextDocumentIdC();
    String selectNextDocumentIdD();
    String selectNextApproveId();
    String selectNextItemId();
    ArrayList<DocumentDTO> selectProposalListByWriter(String empId);
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
    //문서 검색 관련----------------------------
    int selectDocumentCountByTitle(Search search);
    ArrayList<DocumentDTO> selectDocumentListByTitle(Search search);
    int selectDocumentCountByClientName(Search search);
    ArrayList<DocumentDTO> selectDocumentListByClientName(Search search);
    int selectDocumentCountByStatus(Search search);
    ArrayList<DocumentDTO> selectDocumentListByStatus(Search search);
    //결재자 결재 상태 수정
    int updateApprovalStatus(ApproveDTO approve);
}

