package com.erp.biztrack.businessdocument.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;

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
	public int insertDocumentItem(DocumentItemDTO item) {
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
	
	// 출고서 상세 정보 조회 (문서 + 품목 목록 포함)
    public BusinessDocument selectOneDocument(String documentId) {
        return sqlSessionTemplate.selectOne("businessDocumentMapper.selectOneDocument", documentId);
    }
    
    // 첨부파일 조회
    public FileDTO selectOneFileByDocumentId(String documentId) {
        return sqlSessionTemplate.selectOne("businessDocumentMapper.selectOneFileByDocumentId", documentId);
    }
    
    // 결재자 정보 조회
    public ApproveDTO selectApprovalInfo(String documentId) {
        return sqlSessionTemplate.selectOne("businessDocumentMapper.selectApprovalInfo", documentId);
    }
    
    // 품목 리스트 추가 조회
    public List<DocumentItemDTO> selectDocumentItemList(String documentId) {
        return sqlSessionTemplate.selectList("businessDocumentMapper.selectDocumentItemList", documentId);
    }
    
    // 결재자 정보 INSERT
    public int insertApprovalInfo(BusinessDocument document) {
        return sqlSessionTemplate.insert("businessDocumentMapper.insertApprovalInfo", document);
    }
    
    // 출고서 정보 수정
    public int updateOutboundDocument(BusinessDocument document) {
        return sqlSessionTemplate.update("businessDocumentMapper.updateOutboundDocument", document);
    }

    // 품목 목록 다시 insert
    public int insertOutboundItem(DocumentItemDTO item) {
        return sqlSessionTemplate.insert("businessDocumentMapper.insertOutboundItem", item);
    }

    // 첨부파일 insert
    public int insertUploadFile(FileDTO file) {
        return sqlSessionTemplate.insert("businessDocumentMapper.insertUploadFile", file);
    }

    // 첨부파일 삭제 (documentId 기준)
    public int deleteUploadFileByDocumentId(String documentId) {
        return sqlSessionTemplate.delete("businessDocumentMapper.deleteUploadFileByDocumentId", documentId);
    }
    
    // 출고서 품목 삭제
    public int deleteOutboundItems(String documentId) {
        return sqlSessionTemplate.delete("businessDocumentMapper.deleteOutboundItems", documentId);
    }
    
    // 결재정보 삭제
    public int deleteApprovalInfo(String documentId) {
        return sqlSessionTemplate.delete("businessDocumentMapper.deleteApprovalInfo", documentId);
    }
    
    // 출고서 문서 삭제
    public int deleteOutboundDocument(String documentId) {
        return sqlSessionTemplate.delete("businessDocumentMapper.deleteOutboundDocument", documentId);
    }


	/*
	 * // 세금계산서 목록 조회 public ArrayList<BusinessDocument>
	 * selectTaxInvoiceDocumentList(DocumentPaging pageInfo) { return (ArrayList)
	 * sqlSessionTemplate.selectList(
	 * "businessDocumentMapper.selectTaxInvoiceDocumentList", pageInfo); }
	 * 
	 * // 세금계산서 목록 개수 조회 public int selectTaxInvoiceListCount(DocumentPaging
	 * pageInfo) { return sqlSessionTemplate.selectOne(
	 * "businessDocumentMapper.selectTaxInvoiceListCount", pageInfo); }
	 */
}
