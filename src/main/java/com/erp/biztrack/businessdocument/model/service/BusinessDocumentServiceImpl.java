package com.erp.biztrack.businessdocument.model.service;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.businessdocument.model.dao.BusinessDocumentDao;
import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;
import com.erp.biztrack.common.Paging;

@Service("businessdocumentService")
public class BusinessDocumentServiceImpl implements BusinessDocumentService {

	@Autowired
	private BusinessDocumentDao businessdocumentDao;

	@Override
	public ArrayList<BusinessDocument> selectDocumentListBySearch(SqlSessionTemplate sqlSessionTemplate,
			Map<String, Object> param) {
		return businessdocumentDao.selectDocumentListBySearch(sqlSessionTemplate, param);
	}

	@Override
	public int selectDocumentListSearchCount(SqlSessionTemplate sqlSessionTemplate, Map<String, Object> param) {
		return businessdocumentDao.selectDocumentListSearchCount(sqlSessionTemplate, param);
	}
	
	@Override
	public ArrayList<BusinessDocument> selectOutboundDocumentList(DocumentPaging pageInfo) {
		return businessdocumentDao.selectOutboundDocumentList(pageInfo);
	}

	@Override
	public int selectOutboundListCount(DocumentPaging pageInfo) {
		return businessdocumentDao.selectOutboundListCount(pageInfo);
	}
	
	@Override
	public ArrayList<BusinessDocument> selectTaxInvoiceDocumentList(SqlSession session, Paging paging) {
		return businessdocumentDao.selectTaxInvoiceDocumentList(session, paging);
	}

	@Override
	public int selectTaxInvoiceDocumentListCount(SqlSession session) {
		return businessdocumentDao.selectTaxInvoiceDocumentListCount(session);
	}

}
