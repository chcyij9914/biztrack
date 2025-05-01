package com.erp.biztrack.client.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;

public interface ClientService {
	//거래처 목록 조회 관련 ----------------------
	ArrayList<Client> selectClientList(Paging paging);
	int selectListCount();
	//거래처 등록 관련 ----------------------------
	int insertClient(Client client);
    String selectLastClientId();
    int insertFile(FileDTO file);
    int insertDocument(DocumentDTO document);
    String selectLatestDocumentId();
    //거래처 상세보기 관련 --------------------------------
    Client selectClientDetail(String clientId);
    String selectContractFilePath(String clientId);
    String selectBusinessCardFilePath(String clientId);
    //거래처 수정 관련 -----------------------------------
    int updateClient(Client client);
    int deleteFile(FileDTO file);
    //거래처 검색 관련 ------------------------------------
    int selectSearchClientNameCount(String keyword);
    ArrayList<Client> selectSearchClientNameList(Search search);
    int selectSearchClientStatusCount(String status);
    ArrayList<Client> selectSearchClientStatusList(Search search);
    int selectSearchClientCategoryCount(String categoryId);
    ArrayList<Client> selectSearchClientCategoryList(Search search);
    ArrayList<Client> selectCategoryList();
}

