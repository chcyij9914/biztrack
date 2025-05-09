package com.erp.biztrack.client.model.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.erp.biztrack.client.model.dao.ClientDao;
import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;
import com.erp.biztrack.product.model.dto.Product;

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
		Date today = new Date(System.currentTimeMillis());

		if (client.getContractStartDate() != null && client.getContractEndDate() != null) {
			if (today.before(client.getContractStartDate())) {
				client.setClientStatus("예정"); // 시작 전
			} else if (!today.after(client.getContractEndDate())) {
				client.setClientStatus("계약중"); // 시작 <= today <= 종료
			} else {
				client.setClientStatus("만료"); // 종료일 이후
			}
		} else {
			client.setClientStatus("예정"); // 날짜 정보가 부족한 경우도 예정 처리
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
	public int insertCategoryClient(Client client) {
		return clientDao.insertCategoryClient(client);
	}

	@Override
	public String selectLatestCategoryId() {
		return clientDao.selectLatestCategoryId();
	}
	
	@Override
	public int updateClientStatus(Client client) {
		return clientDao.updateClientStatus(client);
	}
	
	@Override
	public ArrayList<Client> selectAllClients() {
		return clientDao.selectAllClients();
	}

	@Scheduled(fixedRate = 1800000) // 1000ms = 1초마다 실행 => 30분마다
	public void updateClientStatusesDaily() {
		List<Client> allClients = clientDao.selectAllClients(); // 전체 조회
		Date today = new Date(System.currentTimeMillis());

		for (Client client : allClients) {
			String newStatus = null;

			if (client.getContractStartDate() != null && client.getContractEndDate() != null) {
				if (today.before(client.getContractStartDate())) {
					newStatus = "예정";
				} else if (!today.after(client.getContractEndDate())) {
					newStatus = "계약중";
				} else {
					newStatus = "만료";
				}
			}

			// 상태가 변경된 경우만 업데이트
			if (newStatus != null && !newStatus.equals(client.getClientStatus())) {
				client.setClientStatus(newStatus);
				clientDao.updateClientStatus(client);
			}
		}
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

	@Override
	public int deleteClient(String clientId) {
		return clientDao.deleteClient(clientId);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentList(Paging paging) {
		return clientDao.selectDocumentList(paging);
	}

	@Override
	public int selectDocumentListCount() {
		return clientDao.selectDocumentListCount();
	}
}
