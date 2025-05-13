package com.erp.biztrack.purchase.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.purchase.model.dto.Purchase;

@Repository("purchaseDao")
public class PurchaseDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public ArrayList<Purchase> selectList(Paging paging) {
		List<Purchase> list = sqlSessionTemplate.selectList("purchaseMapper.selectList", paging);
		return (ArrayList<Purchase>) list;
	}

	public int selectListCount() {
		return sqlSessionTemplate.selectOne("purchaseMapper.selectListCount");
	}

	// 시퀀스 실제 발급
	public int selectNextDocumentSeq(String prefix) {
		if ("A".equals(prefix)) {
			return sqlSessionTemplate.selectOne("purchaseMapper.selectNextSeqA");
		} else if ("B".equals(prefix)) {
			return sqlSessionTemplate.selectOne("purchaseMapper.selectNextSeqB");
		} else {
			throw new IllegalArgumentException("Unknown document prefix: " + prefix);
		}
	}

	// 시퀀스 현재 값 조회 (추가)
	public int selectCurrentDocumentSeq(String prefix) {
		if ("A".equals(prefix)) {
			return sqlSessionTemplate.selectOne("purchaseMapper.selectCurrSeqA");
		} else if ("B".equals(prefix)) {
			return sqlSessionTemplate.selectOne("purchaseMapper.selectCurrSeqB");
		} else {
			throw new IllegalArgumentException("Unknown document prefix: " + prefix);
		}
	}

	// 문서 상세보기
	public Purchase selectPurchaseDetail(String documentId) {
		return sqlSessionTemplate.selectOne("purchaseMapper.selectPurchaseDetail", documentId);
	}

	// 문서 등록
	public int insertDocument(DocumentDTO document) {
		return sqlSessionTemplate.insert("purchaseMapper.insertDocument", document);
	}

	// 문서 품목 등록
	public int insertDocumentItem(DocumentItemDTO item) {
		return sqlSessionTemplate.insert("purchaseMapper.insertDocumentItem", item);
	}

	// 결재 등록
	public int insertApproval(ApproveDTO approval) {
		return sqlSessionTemplate.insert("purchaseMapper.insertApproval", approval);
	}

	// 품의서 문서번호 시퀀스
	public String selectNextDocumentIdR() {
		return sqlSessionTemplate.selectOne("purchaseMapper.selectNextDocumentIdR");
	}
	
	// 지출결의서 문서번호 시퀀스
		public String selectNextDocumentIdT() {
			return sqlSessionTemplate.selectOne("purchaseMapper.selectNextDocumentIdT");
		}

	// 결재 ID 시퀀스 조회
	public String selectNextApproveId() {
		return sqlSessionTemplate.selectOne("purchaseMapper.selectNextApproveId");
	}

	// 품목 ID 시퀀스 조회
	public String selectNextItemId() {
		return sqlSessionTemplate.selectOne("purchaseMapper.selectNextItemId");
	}

	// 파일 저장 (계약서, 명함 공통)
	public int insertFile(FileDTO file) {
		return sqlSessionTemplate.insert("purchaseMapper.insertFile", file);
	}

}