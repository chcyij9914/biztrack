package com.erp.biztrack.inbound.model.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dto.Inbound;
import com.erp.biztrack.purchase.model.dto.Purchase;

@Repository("inboundDao")
public class InboundDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 문서 목록 조회 관련--
	// -------------------------------------------------------------------
	// 본인 작성 문서 목록 조회
	public ArrayList<DocumentDTO> selectDocumentListByType(Map<String, Object> param) {
		List<DocumentDTO> list = sqlSessionTemplate.selectList("inboundMapper.selectDocumentListByType", param);
		return (ArrayList<DocumentDTO>) list;
	}

	// 본인 작성 문서 수 조회
	public int selectDocumentListCountByType(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("inboundMapper.selectDocumentListCountByType", param);
	}

	// 결재자 문서 목록 조회
	public ArrayList<DocumentDTO> selectDocumentListByType2(Map<String, Object> param) {
		List<DocumentDTO> list = sqlSessionTemplate.selectList("inboundMapper.selectDocumentListByType2", param);
		return (ArrayList<DocumentDTO>) list;
	}

	// 결재자 문서 수 조회
	public int selectDocumentListCountByType2(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("inboundMapper.selectDocumentListCountByType2", param);
	}

	// 전체 문서 목록 조회
	public ArrayList<DocumentDTO> selectDocumentListByType3(Map<String, Object> param) {
		List<DocumentDTO> list = sqlSessionTemplate.selectList("inboundMapper.selectDocumentListByType3", param);
		return (ArrayList<DocumentDTO>) list;
	}

	// 전체 문서 수 조회
	public int selectDocumentListCountByType3(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("inboundMapper.selectDocumentListCountByType3", param);
	}

	// --------------------------------------------------------------------------------------------
	// 검색기능
	// 문서번호-사원
	public List<Inbound> searchByDocumentId(String documentId, String empId) {
		Map<String, Object> param = new HashMap<>();
		param.put("documentId", documentId);
		param.put("empId", empId);

		return sqlSessionTemplate.selectList("inboundMapper.searchByDocumentId", param);
	}

	// 문서번호-팀장/부서장
	public List<Inbound> searchByDocumentId2(String documentId, String empId) {
		Map<String, Object> param = new HashMap<>();
		param.put("documentId", documentId);
		param.put("empId", empId);

		return sqlSessionTemplate.selectList("inboundMapper.searchByDocumentId2", param);
	}

	// 문서번호-대표이사
	public List<Inbound> searchByDocumentId3(String documentId, String empId) {
		Map<String, Object> param = new HashMap<>();
		param.put("documentId", documentId);
		param.put("empId", empId);

		return sqlSessionTemplate.selectList("inboundMapper.searchByDocumentId3", param);
	}

	// ------------------------------------------------------------------------------------------------
	// 검색기능
	// 제목-사원
	public List<Inbound> searchByTitle(String title, String empId) {
		Map<String, Object> param = new HashMap<>();
		param.put("title", title);
		param.put("empId", empId);

		return sqlSessionTemplate.selectList("inboundMapper.searchByTitle", param);
	}

	// 제목-팀장/부서장
	public List<Inbound> searchByTitle2(String title, String empId) {
		Map<String, Object> param = new HashMap<>();
		param.put("title", title);
		param.put("empId", empId);

		return sqlSessionTemplate.selectList("inboundMapper.searchByTitle2", param);
	}

	// 제목-대표이사
	public List<Inbound> searchByTitle3(String title, String empId) {
		Map<String, Object> param = new HashMap<>();
		param.put("title", title);
		param.put("empId", empId);

		return sqlSessionTemplate.selectList("inboundMapper.searchByTitle3", param);
	}

	// ------------------------------------------------------------------------------------------------
	// 검색기능
	// 상태-사원
	public List<Inbound> searchByStatus(String status, String empId) {
		Map<String, Object> param = new HashMap<>();
		param.put("status", status);
		param.put("empId", empId);

		return sqlSessionTemplate.selectList("inboundMapper.searchByStatus", param);
	}

	// 상태-팀장/부서장
	public List<Inbound> searchByStatus2(String status, String empId) {
		Map<String, Object> param = new HashMap<>();
		param.put("status", status);
		param.put("empId", empId);

		return sqlSessionTemplate.selectList("inboundMapper.searchByStatus2", param);
	}

	// 상태-대표이사
	public List<Inbound> searchByStatus3(String status, String empId) {
		Map<String, Object> param = new HashMap<>();
		param.put("status", status);
		param.put("empId", empId);

		return sqlSessionTemplate.selectList("inboundMapper.searchByStatus3", param);
	}

	// --------------------------------------------------------------------------------------------

	// ------------------
	// 문서 등록
	public int insertDocument(DocumentDTO document) {
		return sqlSessionTemplate.insert("inboundMapper.insertDocument", document);
	}

	// 문서 품목 등록
	public int insertDocumentItem(DocumentItemDTO item) {
		return sqlSessionTemplate.insert("inboundMapper.insertDocumentItem", item);
	}

	// 결재 등록
	public int insertApproval(ApproveDTO approval) {
		return sqlSessionTemplate.insert("inboundMapper.insertApproval", approval);
	}

	// 입고서 문서번호 시퀀스
	public String selectNextDocumentIdI() {
		return sqlSessionTemplate.selectOne("inboundMapper.selectNextDocumentIdI");
	}

	// 결재 ID 시퀀스 조회
	public String selectNextApproveId() {
		return sqlSessionTemplate.selectOne("inboundMapper.selectNextApproveId");
	}

	// 품목 ID 시퀀스 조회
	public String selectNextItemId() {
		return sqlSessionTemplate.selectOne("inboundMapper.selectNextItemId");
	}

	// 파일 저장 (계약서, 명함 공통)
	public int insertFile(FileDTO file) {
		return sqlSessionTemplate.insert("inboundMapper.insertFile", file);
	}

	// 문서 파일 다운로드 & 상세정보 조회
	public FileDTO selectFileByDocumentId(String documentId) {
		return sqlSessionTemplate.selectOne("inboundMapper.selectFileByDocumentId", documentId);
	}

	// 문서 상세정보 조회
	public DocumentDTO selectOneDocument(String documentId) {
		return sqlSessionTemplate.selectOne("inboundMapper.selectOneDocument", documentId);
	}

	// 품목 상세정보 조회
	public ArrayList<DocumentItemDTO> selectDocumentItemList(String documentId) {
		List<DocumentItemDTO> list = sqlSessionTemplate.selectList("inboundMapper.selectDocumentItemList", documentId);
		return (ArrayList<DocumentItemDTO>) list;
	}

	// 문서 결재자 상세정보 조회
	public ApproveDTO selectApprovalByDocumentId(String documentId) {
		return sqlSessionTemplate.selectOne("inboundMapper.selectApprovalByDocumentId", documentId);
	}

	// 재고 수량 변경---------------------------------------
	// 품목 ID로 단일 품목 조회
	public DocumentItemDTO selectInboundItemById(String itemId) {
		return sqlSessionTemplate.selectOne("inboundMapper.selectItemById", itemId);
	}

	// 문서 ID로 모든 품목 조회
	public List<DocumentItemDTO> selectItemsByDocumentId(String documentId) {
		return sqlSessionTemplate.selectList("inboundMapper.selectItemsByDocumentId", documentId);
	}

	// 문서 ID로 문서내역조회 조회
	public DocumentDTO selectDocumentById(String documentId) {
		return sqlSessionTemplate.selectOne("inboundMapper.selectDocumentById", documentId);
	}

	// 재고 수량 갱신 (기존 수량에 diff 만큼 더하거나 뺌)
	public int updateStock(String productId, int quantityDiff) {
		Map<String, Object> param = new HashMap<>();
		param.put("productId", productId);
		param.put("quantityDiff", quantityDiff);
		return sqlSessionTemplate.update("inboundMapper.updateStock", param);
	}

	// 단가(unit price) 갱신
	public int updateUnitPrice(String productId, int unitPrice) {
		Map<String, Object> param = new HashMap<>();
		param.put("productId", productId);
		param.put("unitPrice", unitPrice);
		return sqlSessionTemplate.update("inboundMapper.updateUnitPrice", param);
	}

	// 직전 입고서 조회
	public Date selectDocumentDate(String documentId) {
		return sqlSessionTemplate.selectOne("inboundMapper.selectDocumentDate", documentId);
	}

	// 직전 입고서의 물품 단가 조회 (입고서 삭제 시 단가 원상복귀)
	public Integer selectLatestUnitPrice(String productId, Date deletedDate) {
		Map<String, Object> param = new HashMap<>();
		param.put("productId", productId);
		param.put("deletedDate", deletedDate);
		return sqlSessionTemplate.selectOne("inboundMapper.selectLatestUnitPrice", param);
	}

	// 문서 수정 관련 ------------------------------------------------------
	// 문서 수정
	public int updateDocument(DocumentDTO document) {
		return sqlSessionTemplate.update("inboundMapper.updateDocument", document);
	}

	// 기존 품목 삭제
	public int deleteDocumentItems(String documentId) {
		return sqlSessionTemplate.delete("inboundMapper.deleteDocumentItems", documentId);
	}

	// 결재자 수정
	public int updateApprove(ApproveDTO approve) {
		return sqlSessionTemplate.update("inboundMapper.updateApprove", approve);
	}

	// 품목 수정
	public int updateDocumentItem(DocumentItemDTO item) {
		return sqlSessionTemplate.update("inboundMapper.updateDocumentItem", item);
	}

	// 문서 삭제
	public int deleteDocumentOnly(String documentId) {
		return sqlSessionTemplate.delete("inboundMapper.deleteDocumentOnly", documentId);
	}

	// 결재 정보 삭제
	public int deleteApprove(String documentId) {
		return sqlSessionTemplate.delete("inboundMapper.deleteApprove", documentId);
	}

	// 문서 파일 삭제
	public int deleteFileByDocumentId(String documentId) {
		return sqlSessionTemplate.delete("inboundMapper.deleteFileByDocumentId", documentId);
	}

	// -----------------------------------
	// 거래처 계약 상태 => 거래처 전체 조회
	public ArrayList<Client> selectAllClients() {
		List<Client> list = sqlSessionTemplate.selectList("clientMapper.selectAllClients");
		return (ArrayList<Client>) list;
	}

	// 거래처 상세 조회
	public Client selectClientDetail(String clientId) {
		return sqlSessionTemplate.selectOne("clientMapper.selectClientDetail", clientId);
	}

	// 계약서 파일 경로 조회
	public String selectContractFilePath(String clientId) {
		return sqlSessionTemplate.selectOne("clientMapper.selectContractFilePath", clientId);
	}

	// --------------------------------
	// 결재자 결재 상태 수정 기능
	public int updateApprovalStatus(ApproveDTO approve) {
		return sqlSessionTemplate.update("inboundMapper.updateApprovalStatus", approve);
	}
}