package com.erp.biztrack.businessdocument.model.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.businessdocument.model.dao.BusinessDocumentDao;
import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;

@Service("businessdocumentService")
public class BusinessDocumentServiceImpl implements BusinessDocumentService {

	@Autowired
	private BusinessDocumentDao businessdocumentDao;

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 출고서 목록 조회
	@Override
	public ArrayList<BusinessDocument> selectOutboundDocumentList(DocumentPaging pageInfo) {
		return businessdocumentDao.selectOutboundDocumentList(pageInfo);
	}

	// 출고서 목록 개수 조회
	@Override
	public int selectOutboundListCount(DocumentPaging pageInfo) {
		return businessdocumentDao.selectOutboundListCount(pageInfo);
	}

	// 출고서 등록
	@Override
	public int insertOutboundDocument(BusinessDocument document) {
		return businessdocumentDao.insertOutboundDocument(document);
	}

	// 출고서 품목 등록 (1개)
	@Override
	public int insertDocumentItem(DocumentItemDTO item) {
		return businessdocumentDao.insertDocumentItem(item);
	}

	// 품목 목록 일괄 등록 (여러개)
	@Override
	public int insertDocumentItemList(List<DocumentItemDTO> items) {
		int count = 0;
		for (DocumentItemDTO item : items) {
			count += businessdocumentDao.insertDocumentItem(item);
		}
		return count;
	}

	// 출고서 문서번호 시퀀스 조회
	@Override
	public String selectNextOutboundId() {
		return businessdocumentDao.selectNextOutboundId();
	}

	// 품목 ID 시퀀스 조회
	@Override
	public String selectNextItemId() {
		return businessdocumentDao.selectNextItemId();
	}

	// 출고서 상세정보 조회
	@Override
	public BusinessDocument selectOneDocument(String documentId) {
		return businessdocumentDao.selectOneDocument(documentId);
	}

	// 첨부파일 조회
	@Override
	public FileDTO selectOneFileByDocumentId(String documentId) {
		return businessdocumentDao.selectOneFileByDocumentId(documentId);
	}

	// 결재자 정보 조회
	@Override
	public ApproveDTO selectApprovalInfo(String documentId) {
		return businessdocumentDao.selectApprovalInfo(documentId);
	}

	// 품목 리스트 추가 조회
	@Override
	public List<DocumentItemDTO> selectDocumentItemList(String documentId) {
		return businessdocumentDao.selectDocumentItemList(documentId);
	}
	
	// 결재자 정보 입력
	@Override
	public int insertApprovalInfo(BusinessDocument document) {
		return businessdocumentDao.insertApprovalInfo(document);
	}
	
	// 출고서 정보 수정
	@Override
	public int updateOutboundDocument(BusinessDocument document) {
		return businessdocumentDao.updateOutboundDocument(document);
	}

	// 출고 품목 전체 삭제 후 재삽입
	@Override
	public void updateOutboundItems(String documentId, List<DocumentItemDTO> items) {
		
		// 기존 품목 삭제
		businessdocumentDao.deleteOutboundItems(documentId);

        // 새로운 품목 목록 등록
        for (DocumentItemDTO item : items) {
            item.setDocumentId(documentId); // documentId 세팅 필요
            item.setItemId(businessdocumentDao.selectNextItemId());
            businessdocumentDao.insertOutboundItem(item);
        }
    }

	// 첨부파일 업로드 후 DB 저장
	@Override
	public int insertUploadFile(FileDTO file) {
		return businessdocumentDao.insertUploadFile(file);
	}

	/*
	 * // 세금계산서 목록 조회
	 * 
	 * @Override public ArrayList<BusinessDocument>
	 * selectTaxInvoiceDocumentList(DocumentPaging pageInfo) { return
	 * businessdocumentDao.selectTaxInvoiceDocumentList(pageInfo); }
	 * 
	 * // 세금계산서 목록 개수
	 * 
	 * @Override public int selectTaxInvoiceListCount(DocumentPaging pageInfo) {
	 * return businessdocumentDao.selectTaxInvoiceListCount(pageInfo); }
	 */

}