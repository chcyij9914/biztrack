package com.erp.biztrack.client.model.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.erp.biztrack.client.model.dao.ClientDao;
import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
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
	public int deleteFileByClientIdOnly(String clientId) {
	    return clientDao.deleteFileByClientIdOnly(clientId);
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
	public int selectDocumentListCountByType(String documentTypeId) {
	    return clientDao.selectDocumentListCountByType(documentTypeId);
	}

	@Override
	public int insertDocument(DocumentDTO document) {
		return clientDao.insertDocument(document);
	}

	@Override
	public int insertDocumentItem(DocumentItemDTO item) {
		return clientDao.insertDocumentItem(item);
	}

	@Override
	public int insertApproval(ApproveDTO approval) {
		return clientDao.insertApproval(approval);
	}

	@Override
	public String selectNextDocumentIdD() {
		return clientDao.selectNextDocumentIdD();
	}

	@Override
	public String selectNextApproveId() {
		return clientDao.selectNextApproveId();
	}

	@Override
	public String selectNextItemId() {
		return clientDao.selectNextItemId();
	}

	@Override
	public FileDTO selectFileByDocumentId(String documentId) {
		return clientDao.selectFileByDocumentId(documentId);
	}
	
	@Override
    public DocumentDTO selectOneDocument(String documentId) {
        return clientDao.selectOneDocument(documentId);
    }

    @Override
    public ArrayList<DocumentItemDTO> selectDocumentItemList(String documentId) {
        return clientDao.selectDocumentItemList(documentId);
    }

    @Override
    public ApproveDTO selectApprovalByDocumentId(String documentId) {
        return clientDao.selectApprovalByDocumentId(documentId);
    }

	@Override
	public int updateDocument(DocumentDTO document) {
		return clientDao.updateDocument(document);
	}

	@Override
	public int deleteDocumentItems(String documentId) {
		return clientDao.deleteDocumentItems(documentId);
	}

	@Override
	public int updateApprove(ApproveDTO approve) {
		return clientDao.updateApprove(approve);
	}

	@Override
	public int updateDocumentItem(DocumentItemDTO item) {
		return clientDao.updateDocumentItem(item);
	}
	
	@Override
	public int deleteFileByDocumentId(String documentId) {
	    return clientDao.deleteFileByDocumentId(documentId);
	}
	
	@Override
	public int deleteDocumentOnly(String documentId) {
		return clientDao.deleteDocumentOnly(documentId);
	}

	@Override
	public int deleteApprove(String documentId) {
		return clientDao.deleteApprove(documentId);
	}

	@Override
	public int selectDocumentCountByTitle(Search search) {
		return clientDao.selectDocumentCountByTitle(search);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentListByTitle(Search search) {
		return clientDao.selectDocumentListByTitle(search);
	}

	@Override
	public int selectDocumentCountByClientName(Search search) {
		return clientDao.selectDocumentCountByClientName(search);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentListByClientName(Search search) {
		return clientDao.selectDocumentListByClientName(search);
	}

	@Override
	public int selectDocumentCountByStatus(Search search) {
		return clientDao.selectDocumentCountByStatus(search);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentListByStatus(Search search) {
		return clientDao.selectDocumentListByStatus(search);
	}

	@Override
	public String selectNextDocumentIdC() {
		return clientDao.selectNextDocumentIdC();
	}

	@Override
	public ArrayList<DocumentDTO> selectContractListByClientAndDept(DocumentDTO cond) {
		return clientDao.selectContractListByClientAndDept(cond);
	}

	@Override
	public ArrayList<DocumentDTO> selectProposalListByWriter(String empId) {
		return clientDao.selectProposalListByWriter(empId);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentListByType(Map<String, Object> param) {
		return clientDao.selectDocumentListByType(param);
	}

	@Override
	public int updateContractPeriod(Client client) {
		return clientDao.updateContractPeriod(client);
	}
}
