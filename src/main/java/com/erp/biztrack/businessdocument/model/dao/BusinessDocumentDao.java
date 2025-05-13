package com.erp.biztrack.businessdocument.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;
import com.erp.biztrack.product.model.dto.Product;

@Repository("businessdocumentDao")
public class BusinessDocumentDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 출고서 목록 조회
	public ArrayList<BusinessDocument> selectOutboundDocumentList(DocumentPaging pageInfo) {
		return (ArrayList) sqlSessionTemplate.selectList("businessDocumentMapper.selectOutboundDocumentList", pageInfo);
	}

	// 출고서 전체 개수 조회 (페이징용)
	public int selectOutboundListCount(DocumentPaging pageInfo) {
		return sqlSessionTemplate.selectOne("businessDocumentMapper.selectOutboundListCount", pageInfo);
	}

	// 출고서 문서 등록
	public int insertOutboundDocument(BusinessDocument document) {
		return sqlSessionTemplate.insert("businessDocumentMapper.insertOutboundDocument", document);
	}

	// 출고서 품목 등록
	public int insertDocumentItem(BusinessDocument item) {
		return sqlSessionTemplate.insert("businessDocumentMapper.insertDocumentItem", item);
	}

	// 출고서 문서번호 시퀀스 조회
	public String selectNextOutboundId() {
		return sqlSessionTemplate.selectOne("businessDocumentMapper.selectNextOutboundId");
	}

	// 품목 ID 시퀀스 조회
	public String selectNextItemId() {
	    return sqlSessionTemplate.selectOne("businessDocumentMapper.selectNextItemId");
	}
	
	// 세금계산서 목록 조회
	public ArrayList<BusinessDocument> selectTaxInvoiceDocumentList(DocumentPaging pageInfo) {
		return (ArrayList) sqlSessionTemplate.selectList("businessDocumentMapper.selectTaxInvoiceDocumentList",
				pageInfo);
	}

	// 세금계산서 목록 개수 조회
	public int selectTaxInvoiceListCount(DocumentPaging pageInfo) {
		return sqlSessionTemplate.selectOne("businessDocumentMapper.selectTaxInvoiceListCount", pageInfo);
	}
}
