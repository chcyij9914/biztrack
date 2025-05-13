package com.erp.biztrack.businessdocument.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.businessdocument.model.dao.BusinessDocumentDao;
import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.product.model.dto.Product;

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
    public int insertDocumentItem(BusinessDocument item) {
        return businessdocumentDao.insertDocumentItem(item);
    }
    
    // 품목 목록 일괄 등록 (여러개)
    @Override
	public int insertDocumentItemList(List<BusinessDocument> items) {
    	 int count = 0;
         for (BusinessDocument item : items) {
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

    // 세금계산서 목록 조회
    @Override
    public ArrayList<BusinessDocument> selectTaxInvoiceDocumentList(DocumentPaging pageInfo) {
        return businessdocumentDao.selectTaxInvoiceDocumentList(pageInfo);
    }

    // 세금계산서 목록 개수
    @Override
    public int selectTaxInvoiceListCount(DocumentPaging pageInfo) {
        return businessdocumentDao.selectTaxInvoiceListCount(pageInfo);
    }
}