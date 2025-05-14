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

	// 입고 카운팅
	@Override
	public int selectListCount() {
		return inboundDao.selectListCount();
	}

	// 입고목록 조회
	@Override
	public ArrayList<Inbound> selectList(Paging paging) {
		return inboundDao.selectList(paging);
	}

	// 입고 상세보기
	@Override
	public Inbound selectInboundDetail(String documentId) {
		return inboundDao.selectInboundDetail(documentId);
	}

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
	public void insertInbound(DocumentDTO document) {
	    List<DocumentItemDTO> items = document.getItems();  
	    for (DocumentItemDTO item : items) {
	        inboundDao.updateStock(item.getProductId(), item.getQuantity());
	        inboundDao.updateUnitPrice(item.getProductId(), item.getUnitPrice());
	    }
	}

	@Override
	public void updateInbound(DocumentDTO document) {
	    List<DocumentItemDTO> newItems = document.getItems();

	    // 1. 기존 수량들을 먼저 조회해서 Map에 저장
	    Map<String, Integer> originalQuantities = new HashMap<>();

	    for (DocumentItemDTO newItem : newItems) {
	        DocumentItemDTO oldItem = inboundDao.selectInboundItemById(newItem.getItemId());
	        if (oldItem != null) {
	            originalQuantities.put(newItem.getItemId(), oldItem.getQuantity());
	        }
	    }

	    // 2. 새 값과 비교해서 재고/단가 갱신
	    for (DocumentItemDTO newItem : newItems) {
	        Integer oldQty = originalQuantities.get(newItem.getItemId());
	        if (oldQty == null) oldQty = 0;
	        int diff = newItem.getQuantity() - oldQty;

	        // 재고 반영
	        if (diff != 0) {
	            inboundDao.updateStock(newItem.getProductId(), diff);
	        }

	        // 단가 반영
	        inboundDao.updateUnitPrice(newItem.getProductId(), newItem.getUnitPrice());

	        // 문서 품목 업데이트
	        inboundDao.updateDocumentItem(newItem);
	    }
	}

	@Override
	public void deleteInbound(String documentId) {
	    Date deletedDate = inboundDao.selectDocumentDate(documentId);
	    System.out.println("[삭제] 삭제할 문서일자: " + deletedDate);

	    List<DocumentItemDTO> items = inboundDao.selectItemsByDocumentId(documentId);
	    for (DocumentItemDTO item : items) {
	        System.out.println("[삭제] 품목: " + item.getItemId() + ", 수량: " + item.getQuantity() + ", 상품: " + item.getProductId());

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

}