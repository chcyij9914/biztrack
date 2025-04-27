package com.erp.biztrack.client.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;

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
}
