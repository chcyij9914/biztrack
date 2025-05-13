package com.erp.biztrack.businessdocument.model.service;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;
import com.erp.biztrack.common.Paging;

public interface BusinessDocumentService {

	 ArrayList<BusinessDocument> selectDocumentListBySearch(SqlSessionTemplate sqlSessionTemplate, Map<String, Object> param);
	 int selectDocumentListSearchCount(SqlSessionTemplate sqlSessionTemplate, Map<String, Object> param);
	 ArrayList<BusinessDocument> selectOutboundDocumentList(DocumentPaging pageInfo);
	 int selectOutboundListCount(DocumentPaging pageInfo);
	 ArrayList<BusinessDocument> selectTaxInvoiceDocumentList(SqlSession session, Paging paging);
	 int selectTaxInvoiceDocumentListCount(SqlSession session);
}
