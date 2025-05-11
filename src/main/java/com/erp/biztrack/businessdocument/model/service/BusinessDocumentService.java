package com.erp.biztrack.businessdocument.model.service;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.common.Paging;

public interface BusinessDocumentService {

	ArrayList<BusinessDocument> selectDocumentListBySearch(SqlSession session, Map<String, Object> param);
	int selectDocumentListSearchCount(SqlSession session, String searchType, String keyword);
	ArrayList<BusinessDocument> selectOutBoundDocumentList(SqlSession session, Paging paging);
	ArrayList<BusinessDocument> selectTaxInvoiceDocumentList(SqlSession session, Paging paging);
	int selectOutBoundDocumentListCount(SqlSession session);
	int selectTaxInvoiceDocumentListCount(SqlSession session);
}
