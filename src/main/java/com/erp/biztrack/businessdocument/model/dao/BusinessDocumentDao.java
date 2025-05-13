package com.erp.biztrack.businessdocument.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.businessdocument.model.dto.BusinessDocument;
import com.erp.biztrack.businessdocument.model.dto.DocumentPaging;
import com.erp.biztrack.common.Paging;

	@Repository("businessdocumentDao")
	public class BusinessDocumentDao {

		@Autowired
		private SqlSessionTemplate sqlSessionTemplate;
		
		 // 검색 조건이 포함된 문서 목록
		  public ArrayList<BusinessDocument> selectDocumentListBySearch(SqlSession session, Map<String, Object> param) {
		        List<BusinessDocument> list = session.selectList("businessdocumentMapper.selectBusinessDocumentListByFilter", param);
		        return new ArrayList<>(list);
		    }
		
		 // 검색 조건이 포함된 전체 문서 개수
		  public int selectDocumentListSearchCount(SqlSession session, Map<String, Object> param) {
		        return session.selectOne("businessdocumentMapper.selectBusinessDocumentListCountByFilter", param);
		    }
		
		// 출고서 목록 조회
		  public ArrayList<BusinessDocument> selectOutboundDocumentList(DocumentPaging pageInfo) {
		        return (ArrayList) sqlSessionTemplate.selectList("businessDocumentMapper.selectOutboundDocumentList", pageInfo);
		    }
	    
	    // 출고서 전체 개수 조회 (페이징용)
		  public int selectOutboundListCount(DocumentPaging pageInfo) {
		        return sqlSessionTemplate.selectOne("businessDocumentMapper.selectOutboundListCount", pageInfo);
		    }

	    // 세금계산서 목록 조회
	    public ArrayList<BusinessDocument> selectTaxInvoiceDocumentList(SqlSession session, Paging paging) {
	        return (ArrayList) session.selectList("businessdocumentMapper.selectTaxInvoiceDocumentList", paging);
	    }

	    // 세금계산서 전체 개수 조회 (페이징용)
	    public int selectTaxInvoiceDocumentListCount(SqlSession session) {
	        return session.selectOne("businessdocumentMapper.selectTaxInvoiceDocumentListCount");
	    }
}
