package com.erp.biztrack.purchase.model.service;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;
import com.erp.biztrack.product.model.dto.Product;
import com.erp.biztrack.purchase.model.dao.PurchaseDao;
import com.erp.biztrack.purchase.model.dto.Purchase;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseDao purchaseDao;

	//문서카운팅
	@Override
	public int selectDocumentListCountByType(String documentTypeId) {
	    return purchaseDao.selectDocumentListCountByType(documentTypeId);
	}
	//문서조회
	@Override
	public ArrayList<DocumentDTO> selectDocumentListByType(Map<String, Object> param) {
	    return purchaseDao.selectDocumentListByType(param);
	}
	
	//문서카운팅
		@Override
		public int selectDocumentListCountByTypeT(String documentTypeId) {
		    return purchaseDao.selectDocumentListCountByTypeT(documentTypeId);
		}
		//문서조회
		@Override
		public ArrayList<DocumentDTO> selectDocumentListByTypeT(Map<String, Object> param) {
		    return purchaseDao.selectDocumentListByTypeT(param);
		}
		
	//------------------------------------------------------------------------------------------
		// 검색 기능 - 문서번호
		@Override
		public List<Purchase> searchByDocumentId(Map<String, Object> param) {
		    return purchaseDao.searchByDocumentId(param);
		}

		// 검색 기능 - 제목
		@Override
		public List<Purchase> searchByTitle(Map<String, Object> param) {
		    return purchaseDao.searchByTitle(param);
		}

		// 검색 기능 - 상태
		@Override
		public List<Purchase> searchByStatus(Map<String, Object> param) {
		    return purchaseDao.searchByStatus(param);
		}
		
		
	//-------------------------------------------------------------------------------------------
	//문서 상세보기
	@Override
	public Purchase selectPurchaseDetail(String documentId) {
		return purchaseDao.selectPurchaseDetail(documentId);
	}
	
	//문서 등록관련
	@Override
	public int insertDocument(DocumentDTO document) {
		return purchaseDao.insertDocument(document);
	}

	@Override
	public int insertApproval(ApproveDTO approval) {
		return purchaseDao.insertApproval(approval);
	}

	@Override
	public String selectNextDocumentIdR() {
		return purchaseDao.selectNextDocumentIdR();
	}
	
	@Override
	public String selectNextDocumentIdT() {
		return purchaseDao.selectNextDocumentIdT();
	}

	@Override
	public int insertFile(FileDTO file) {
		return purchaseDao.insertFile(file);
	}
	
	@Override
	public ArrayList<Client> selectAllClients() {
		return purchaseDao.selectAllClients();
	}

	@Override
	public ArrayList<Purchase> selectCategoryList() {
		return purchaseDao.selectCategoryList();
	}

	@Override
	public int deleteClient(String clientId) {
		return purchaseDao.deleteClient(clientId);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentList(Paging paging) {
		return purchaseDao.selectDocumentList(paging);
	}

	@Override
	public int selectDocumentListCount() {
		return purchaseDao.selectDocumentListCount();
	}


	@Override
	public int insertDocumentItem(DocumentItemDTO item) {
		return purchaseDao.insertDocumentItem(item);
	}

	@Override
	public String selectNextApproveId() {
		return purchaseDao.selectNextApproveId();
	}

	@Override
	public String selectNextItemId() {
		return purchaseDao.selectNextItemId();
	}

	@Override
	public FileDTO selectFileByDocumentId(String documentId) {
		return purchaseDao.selectFileByDocumentId(documentId);
	}
	
	@Override
    public DocumentDTO selectOneDocument(String documentId) {
        return purchaseDao.selectOneDocument(documentId);
    }

    @Override
    public ArrayList<DocumentItemDTO> selectDocumentItemList(String documentId) {
        return purchaseDao.selectDocumentItemList(documentId);
    }

    @Override
    public ApproveDTO selectApprovalByDocumentId(String documentId) {
        return purchaseDao.selectApprovalByDocumentId(documentId);
    }

	@Override
	public int updateDocument(DocumentDTO document) {
		return purchaseDao.updateDocument(document);
	}

	@Override
	public int deleteDocumentItems(String documentId) {
		return purchaseDao.deleteDocumentItems(documentId);
	}

	@Override
	public int updateApprove(ApproveDTO approve) {
		return purchaseDao.updateApprove(approve);
	}

	@Override
	public int updateDocumentItem(DocumentItemDTO item) {
		return purchaseDao.updateDocumentItem(item);
	}
	
	@Override
	public int deleteFileByDocumentId(String documentId) {
	    return purchaseDao.deleteFileByDocumentId(documentId);
	}
	
	@Override
	public int deleteDocumentOnly(String documentId) {
		return purchaseDao.deleteDocumentOnly(documentId);
	}

	@Override
	public int deleteApprove(String documentId) {
		return purchaseDao.deleteApprove(documentId);
	}
}

