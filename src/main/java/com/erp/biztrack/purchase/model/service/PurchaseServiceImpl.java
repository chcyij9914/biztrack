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
import com.erp.biztrack.inbound.model.dto.Inbound;
import com.erp.biztrack.product.model.dto.Product;
import com.erp.biztrack.purchase.model.dao.PurchaseDao;
import com.erp.biztrack.purchase.model.dto.Purchase;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseDao purchaseDao;

	// 문서조회 ---품의서----------------------------------
	@Override
	public ArrayList<DocumentDTO> selectDocumentListByType(Map<String, Object> param) {
		return purchaseDao.selectDocumentListByType(param);
	}

	@Override
	public int selectDocumentListCountByType(Map<String, Object> param) {
		return purchaseDao.selectDocumentListCountByType(param);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentListByType2(Map<String, Object> param) {
		return purchaseDao.selectDocumentListByType2(param);
	}

	@Override
	public int selectDocumentListCountByType2(Map<String, Object> param) {
		return purchaseDao.selectDocumentListCountByType2(param);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentListByType3(Map<String, Object> param) {
		return purchaseDao.selectDocumentListByType3(param);
	}

	@Override
	public int selectDocumentListCountByType3(Map<String, Object> param) {
		return purchaseDao.selectDocumentListCountByType3(param);
	}

	// 문서조회 ---지출결의서----------------------------------
	@Override
	public ArrayList<DocumentDTO> selectDocumentListByTypeT(Map<String, Object> param) {
		return purchaseDao.selectDocumentListByTypeT(param);
	}

	@Override
	public int selectDocumentListCountByTypeT(Map<String, Object> param) {
		return purchaseDao.selectDocumentListCountByTypeT(param);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentListByTypeT2(Map<String, Object> param) {
		return purchaseDao.selectDocumentListByTypeT2(param);
	}

	@Override
	public int selectDocumentListCountByTypeT2(Map<String, Object> param) {
		return purchaseDao.selectDocumentListCountByTypeT2(param);
	}

	@Override
	public ArrayList<DocumentDTO> selectDocumentListByTypeT3(Map<String, Object> param) {
		return purchaseDao.selectDocumentListByTypeT3(param);
	}

	@Override
	public int selectDocumentListCountByTypeT3(Map<String, Object> param) {
		return purchaseDao.selectDocumentListCountByTypeT3(param);
	}

	// ------------------------------------------------------------------------------------------
	// 검색기능 품의서
	// ------------------------------------------------------------------------------------------
	// 검색기능
	// 문서번호검색 (사원)
	@Override
	public List<Purchase> searchByDocumentId(String documentId, String empId) {
		return purchaseDao.searchByDocumentId(documentId, empId);
	}

	// 문서번호검색 (팀장/부서장)
	@Override
	public List<Purchase> searchByDocumentId2(String documentId, String empId) {
		return purchaseDao.searchByDocumentId2(documentId, empId);
	}

	// 문서번호검색 (대표이사)
	@Override
	public List<Purchase> searchByDocumentId3(String documentId, String empId) {
		return purchaseDao.searchByDocumentId3(documentId, empId);
	}
	// ----------------------------------------------------------------------------------------------

	// 검색기능
	// 제목검색 (사원)
	@Override
	public List<Purchase> searchByTitle(String title, String empId) {
		return purchaseDao.searchByTitle(title, empId);
	}

	// 제목검색 (팀장/부서장)
	@Override
	public List<Purchase> searchByTitle2(String title, String empId) {
		return purchaseDao.searchByTitle2(title, empId);
	}

	// 제목검색 (대표이사)
	@Override
	public List<Purchase> searchByTitle3(String title, String empId) {
		return purchaseDao.searchByTitle3(title, empId);
	}

	// ----------------------------------------------------------------------------------------------

	// 검색기능
	// 상태검색 (사원)
	@Override
	public List<Purchase> searchByStatus(String status, String empId) {
		return purchaseDao.searchByStatus(status, empId);
	}

	// 상태검색 (팀장/부서장)
	@Override
	public List<Purchase> searchByStatus2(String status, String empId) {
		return purchaseDao.searchByStatus2(status, empId);
	}

	// 상태검색 (대표이사)
	@Override
	public List<Purchase> searchByStatus3(String status, String empId) {
		return purchaseDao.searchByStatus3(status, empId);
	}

	// -------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------
	// 검색기능 지출결의서
	// 문서번호검색 (사원)
	@Override
	public List<Purchase> searchByDocumentIdT(String documentId, String empId) {
		return purchaseDao.searchByDocumentIdT(documentId, empId);
	}

	// 문서번호검색 (팀장/부서장)
	@Override
	public List<Purchase> searchByDocumentIdT2(String documentId, String empId) {
		return purchaseDao.searchByDocumentIdT2(documentId, empId);
	}

	// 문서번호검색 (대표이사)
	@Override
	public List<Purchase> searchByDocumentIdT3(String documentId, String empId) {
		return purchaseDao.searchByDocumentIdT3(documentId, empId);
	}
	// ----------------------------------------------------------------------------------------------

	// 검색기능
	// 제목검색 (사원)
	@Override
	public List<Purchase> searchByTitleT(String title, String empId) {
		return purchaseDao.searchByTitleT(title, empId);
	}

	// 제목검색 (팀장/부서장)
	@Override
	public List<Purchase> searchByTitleT2(String title, String empId) {
		return purchaseDao.searchByTitleT2(title, empId);
	}

	// 제목검색 (대표이사)
	@Override
	public List<Purchase> searchByTitleT3(String title, String empId) {
		return purchaseDao.searchByTitleT3(title, empId);
	}

	// ----------------------------------------------------------------------------------------------

	// 검색기능
	// 상태검색 (사원)
	@Override
	public List<Purchase> searchByStatusT(String status, String empId) {
		return purchaseDao.searchByStatusT(status, empId);
	}

	// 상태검색 (팀장/부서장)
	@Override
	public List<Purchase> searchByStatusT2(String status, String empId) {
		return purchaseDao.searchByStatusT2(status, empId);
	}

	// 상태검색 (대표이사)
	@Override
	public List<Purchase> searchByStatusT3(String status, String empId) {
		return purchaseDao.searchByStatusT3(status, empId);
	}
	// -------------------------------------------------------------------------------------------
	// 문서 상세보기
	@Override
	public Purchase selectPurchaseDetail(String documentId) {
		return purchaseDao.selectPurchaseDetail(documentId);
	}

	// 문서 등록관련
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

	@Override
	public int updateApprovalStatus(ApproveDTO approve) {
		return purchaseDao.updateApprovalStatus(approve);
	}

}
