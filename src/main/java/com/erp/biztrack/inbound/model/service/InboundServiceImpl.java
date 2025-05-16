package com.erp.biztrack.inbound.model.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dao.InboundDao;
import com.erp.biztrack.inbound.model.dto.Inbound;
import com.erp.biztrack.purchase.model.dto.Purchase;

@Service("inboundService")
public class InboundServiceImpl implements InboundService {

	@Autowired
	private InboundDao inboundDao;

	// 문서조회 -------------------------------------
	@Override
	public ArrayList<DocumentDTO> selectDocumentListByType(Map<String, Object> param) {
		return inboundDao.selectDocumentListByType(param);
	}

	@Override
	public int selectDocumentListCountByType(Map<String, Object> param) {
		return inboundDao.selectDocumentListCountByType(param);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentListByType2(Map<String, Object> param) {
		return inboundDao.selectDocumentListByType2(param);
	}

	@Override
	public int selectDocumentListCountByType2(Map<String, Object> param) {
		return inboundDao.selectDocumentListCountByType2(param);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentListByType3(Map<String, Object> param) {
		return inboundDao.selectDocumentListByType3(param);
	}

	@Override
	public int selectDocumentListCountByType3(Map<String, Object> param) {
		return inboundDao.selectDocumentListCountByType3(param);
	}

	// ------------------------------------------------------------------------------------------
	// 검색기능
	// 문서번호검색 (사원)
	@Override
	public List<Inbound> searchByDocumentId(String documentId, String empId) {
		return inboundDao.searchByDocumentId(documentId, empId);
	}

	// 문서번호검색 (팀장/부서장)
	@Override
	public List<Inbound> searchByDocumentId2(String documentId, String empId) {
		return inboundDao.searchByDocumentId2(documentId, empId);
	}

	// 문서번호검색 (대표이사)
	@Override
	public List<Inbound> searchByDocumentId3(String documentId, String empId) {
		return inboundDao.searchByDocumentId3(documentId, empId);
	}
//----------------------------------------------------------------------------------------------

	// 검색기능
	// 제목검색 (사원)
	@Override
	public List<Inbound> searchByTitle(String title, String empId) {
		return inboundDao.searchByTitle(title, empId);
	}

	// 제목검색 (팀장/부서장)
	@Override
	public List<Inbound> searchByTitle2(String title, String empId) {
		return inboundDao.searchByTitle2(title, empId);
	}

	// 제목검색 (대표이사)
	@Override
	public List<Inbound> searchByTitle3(String title, String empId) {
		return inboundDao.searchByTitle3(title, empId);
	}

	// ----------------------------------------------------------------------------------------------

	// 검색기능
		// 상태검색 (사원)
		@Override
		public List<Inbound> searchByStatus(String status, String empId) {
			return inboundDao.searchByStatus(status, empId);
		}

		// 상태검색 (팀장/부서장)
		@Override
		public List<Inbound> searchByStatus2(String status, String empId) {
			return inboundDao.searchByStatus2(status, empId);
		}

		// 상태검색 (대표이사)
		@Override
		public List<Inbound> searchByStatus3(String status, String empId) {
			return inboundDao.searchByStatus3(status, empId);
		}


	// -------------------------------------------------------------------------------------------

	// 문서 등록관련
	@Override
	public int insertDocument(DocumentDTO document) {
		return inboundDao.insertDocument(document);
	}

	@Override
	public int insertApproval(ApproveDTO approval) {
		return inboundDao.insertApproval(approval);
	}

	@Override
	public String selectNextDocumentIdI() {
		return inboundDao.selectNextDocumentIdI();
	}

	@Override
	public int insertDocumentItem(DocumentItemDTO item) {
		return inboundDao.insertDocumentItem(item);
	}

	@Override
	public String selectNextApproveId() {
		return inboundDao.selectNextApproveId();
	}

	@Override
	public String selectNextItemId() {
		return inboundDao.selectNextItemId();
	}

	@Override
	public int insertFile(FileDTO file) {
		return inboundDao.insertFile(file);
	}

	// 재고 수량 변경-----------------------------------------
	@Override
	public void insertInbound(String documentId) {
	    // 1. 문서 정보 조회
	    DocumentDTO document = inboundDao.selectDocumentById(documentId);

	    // 2. 해당 문서의 품목 목록 조회
	    List<DocumentItemDTO> items = inboundDao.selectItemsByDocumentId(documentId);
	    document.setItems(items);

	    // 3. 기존 재고/단가 갱신 로직 호출
	    insertInbound(document);
	}
	
	@Override
	public void insertInbound(DocumentDTO document) {
	    List<DocumentItemDTO> items = document.getItems();
	    for (DocumentItemDTO item : items) {
	        inboundDao.updateStock(item.getProductId(), item.getQuantity());
	        inboundDao.updateUnitPrice(item.getProductId(), item.getUnitPrice());
	    }
	}

	@Override
	public void deleteInbound(String documentId) {
		Date deletedDate = inboundDao.selectDocumentDate(documentId);
		System.out.println("[삭제] 삭제할 문서일자: " + deletedDate);

		List<DocumentItemDTO> items = inboundDao.selectItemsByDocumentId(documentId);
		for (DocumentItemDTO item : items) {
			System.out.println(
					"[삭제] 품목: " + item.getItemId() + ", 수량: " + item.getQuantity() + ", 상품: " + item.getProductId());

			// 재고 차감
			int result = inboundDao.updateStock(item.getProductId(), -item.getQuantity());
			System.out.println("[삭제] 재고 차감 결과: " + result);

			Integer latestUnitPrice = inboundDao.selectLatestUnitPrice(item.getProductId(), deletedDate);
			System.out.println("[삭제] 복원할 단가: " + latestUnitPrice);

			if (latestUnitPrice != null) {
				inboundDao.updateUnitPrice(item.getProductId(), latestUnitPrice);
			}
		}
	}

	// 문서 상세보기 -----------------------------------------------------
	@Override
	public ArrayList<Client> selectAllClients() {
		return inboundDao.selectAllClients();
	}

	@Override
	public ArrayList<Purchase> selectCategoryList() {
		return inboundDao.selectCategoryList();
	}

	@Override
	public int deleteClient(String clientId) {
		return inboundDao.deleteClient(clientId);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentList(Paging paging) {
		return inboundDao.selectDocumentList(paging);
	}

	@Override
	public int selectDocumentListCount() {
		return inboundDao.selectDocumentListCount();
	}

	@Override
	public String selectNextApproveId() {
		return inboundDao.selectNextApproveId();
	}

	@Override
	public String selectNextItemId() {
		return inboundDao.selectNextItemId();
	}

	@Override
	public FileDTO selectFileByDocumentId(String documentId) {
		return inboundDao.selectFileByDocumentId(documentId);
	}

	@Override
	public DocumentDTO selectOneDocument(String documentId) {
		return inboundDao.selectOneDocument(documentId);
	}

	@Override
	public ArrayList<DocumentItemDTO> selectDocumentItemList(String documentId) {
		return inboundDao.selectDocumentItemList(documentId);
	}

	@Override
	public ApproveDTO selectApprovalByDocumentId(String documentId) {
		return inboundDao.selectApprovalByDocumentId(documentId);
	}

	@Override
	public int updateDocument(DocumentDTO document) {
		return inboundDao.updateDocument(document);
	}

	@Override
	public int deleteDocumentItems(String documentId) {
		return inboundDao.deleteDocumentItems(documentId);
	}

	@Override
	public int updateApprove(ApproveDTO approve) {
		return inboundDao.updateApprove(approve);
	}

	@Override
	public int updateDocumentItem(DocumentItemDTO item) {
		return inboundDao.updateDocumentItem(item);
	}

	@Override
	public int deleteFileByDocumentId(String documentId) {
		return inboundDao.deleteFileByDocumentId(documentId);
	}

	@Override
	public int deleteDocumentOnly(String documentId) {
		return inboundDao.deleteDocumentOnly(documentId);
	}

	@Override
	public int deleteApprove(String documentId) {
		return inboundDao.deleteApprove(documentId);
	}


	@Override
	public int updateApprovalStatus(ApproveDTO approve) {
		return inboundDao.updateApprovalStatus(approve);
	}

}