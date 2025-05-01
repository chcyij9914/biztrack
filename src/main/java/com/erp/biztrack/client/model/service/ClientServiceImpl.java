package com.erp.biztrack.client.model.service;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.client.model.dao.ClientDao;
import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;

@Service("clientService")
public class ClientServiceImpl implements ClientService {
	// dao와 연결처리
	@Autowired
	private ClientDao clientDao;

	@Override
	public ArrayList<Client> selectClientList(Paging paging) {
		return clientDao.selectClientList(paging);
	}

	@Override
	public int selectListCount() {
		return clientDao.selectListCount();
	}

	@Override
	public int insertClient(Client client) {
		// 계약상태 계산
		Date today = new Date(System.currentTimeMillis()); // 현재 날짜를 sql.Date로

		if (client.getContractStartDate() != null && client.getContractEndDate() != null) {
			if (today.compareTo(client.getContractStartDate()) >= 0
					&& today.compareTo(client.getContractEndDate()) <= 0) {
				client.setClientStatus("계약중");
			} else {
				client.setClientStatus("만료");
			}
		} else {
			client.setClientStatus("계약중");
		}
		
		return clientDao.insertClient(client);

	}

	@Override
	public String selectLastClientId() {
		return clientDao.selectLastClientId();
	}

	@Override
	public int insertFile(FileDTO file) {
		return clientDao.insertFile(file);
	}

	@Override
	public int insertDocument(DocumentDTO document) {
		return clientDao.insertDocument(document);
	}

	@Override
	public String selectLatestDocumentId() {
		return clientDao.selectLatestDocumentId();
	}

	@Override
	public Client selectClientDetail(String clientId) {
		return clientDao.selectClientDetail(clientId);
	}

	@Override
	public String selectContractFilePath(String clientId) {
		return clientDao.selectContractFilePath(clientId);
	}

	@Override
	public String selectBusinessCardFilePath(String clientId) {
		return clientDao.selectBusinessCardFilePath(clientId);
	}

	@Override
	public int updateClient(Client client) {
		return clientDao.updateClient(client);
	}

	@Override
	public int deleteFile(FileDTO file) {
		return clientDao.deleteFile(file);
	}

	@Override
	public int selectSearchClientNameCount(String keyword) {
		return clientDao.selectSearchClientNameCount(keyword);
	}

	@Override
	public ArrayList<Client> selectSearchClientNameList(Search search) {
		return clientDao.selectSearchClientNameList(search);
	}

	@Override
	public int selectSearchClientStatusCount(String status) {
		return clientDao.selectSearchClientStatusCount(status);
	}

	@Override
	public ArrayList<Client> selectSearchClientStatusList(Search search) {
		return clientDao.selectSearchClientStatusList(search);
	}

	@Override
	public int selectSearchClientCategoryCount(String categoryId) {
		return clientDao.selectSearchClientCategoryCount(categoryId);
	}

	@Override
	public ArrayList<Client> selectSearchClientCategoryList(Search search) {
		return clientDao.selectSearchClientCategoryList(search);
	}

	@Override
	public ArrayList<Client> selectCategoryList() {
		return clientDao.selectCategoryList();
	}

	

}
