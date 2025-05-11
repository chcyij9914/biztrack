package com.erp.biztrack.businessdocument.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.businessdocument.model.dao.BusinessDocumentDao;
import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.common.Paging;

@Service("businessdocumentService")
public class BusinessDocumentServiceImpl implements BusinessDocumentService {

	@Autowired
	private BusinessDocumentDao businessdocumentDao;

	@Override
	public ArrayList<BusinessDocument> selectDocumentListBySearch(SqlSession session, Map<String, Object> param) {
		return businessdocumentDao.selectDocumentListBySearch(session, param);
	}

	@Override
	public int selectDocumentListSearchCount(SqlSession session, String searchType, String keyword) {
	    Map<String, Object> param = new HashMap<>();
	    param.put("searchType", searchType);
	    param.put("keyword", keyword);
	    return businessdocumentDao.selectDocumentListSearchCount(session, param);
	}

	@Override
	public ArrayList<BusinessDocument> selectOutBoundDocumentList(SqlSession session, Paging paging) {
		return businessdocumentDao.selectOutBoundDocumentList(session, paging);
	}

	@Override
	public ArrayList<BusinessDocument> selectTaxInvoiceDocumentList(SqlSession session, Paging paging) {
		return businessdocumentDao.selectTaxInvoiceDocumentList(session, paging);
	}

	@Override
	public int selectOutBoundDocumentListCount(SqlSession session) {
		return businessdocumentDao.selectOutBoundDocumentListCount(session);
	}

	@Override
	public int selectTaxInvoiceDocumentListCount(SqlSession session) {
		return businessdocumentDao.selectTaxInvoiceDocumentListCount(session);
	}
}
