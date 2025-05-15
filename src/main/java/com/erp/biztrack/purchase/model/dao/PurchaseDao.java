package com.erp.biztrack.purchase.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.ApproveDTO;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.product.model.dto.Product;
import com.erp.biztrack.purchase.model.dto.Purchase;

@Repository("purchaseDao")
public class PurchaseDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	//품의서 개수 카운트
	public int selectDocumentListCountByType(String documentTypeId) {
        return sqlSessionTemplate.selectOne("purchaseMapper.selectDocumentListCountByType", documentTypeId);
    }
	
	//품의서 조회
	public ArrayList<DocumentDTO> selectDocumentListByType(Map<String, Object> param) {
        List<DocumentDTO> list = sqlSessionTemplate.selectList("purchaseMapper.selectDocumentListByType", param);
        return (ArrayList<DocumentDTO>) list;
    }
	
	//지출결의서 개수 카운트
		public int selectDocumentListCountByTypeT(String documentTypeId) {
	        return sqlSessionTemplate.selectOne("purchaseMapper.selectDocumentListCountByTypeT", documentTypeId);
	    }
		
		//지출결의서 조회
		public ArrayList<DocumentDTO> selectDocumentListByTypeT(Map<String, Object> param) {
	        List<DocumentDTO> list = sqlSessionTemplate.selectList("purchaseMapper.selectDocumentListByTypeT", param);
	        return (ArrayList<DocumentDTO>) list;
	    }
	//--------------------------------------------------------------------------------------------
		// 검색 기능 - 문서번호
		public List<Purchase> searchByDocumentId(Map<String, Object> param) {
		    return sqlSessionTemplate.selectList("purchaseMapper.searchByDocumentId", param);
		}

		// 검색 기능 - 제목
		public List<Purchase> searchByTitle(Map<String, Object> param) {
		    return sqlSessionTemplate.selectList("purchaseMapper.searchByTitle", param);
		}

		// 검색 기능 - 상태
		public List<Purchase> searchByStatus(Map<String, Object> param) {
		    return sqlSessionTemplate.selectList("purchaseMapper.searchByStatus", param);
		}
	
	//--------------------------------------------------------------------------------------------
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
	
	// 문서 파일 다운로드 & 상세정보 조회
    public FileDTO selectFileByDocumentId(String documentId) {
        return sqlSessionTemplate.selectOne("purchaseMapper.selectFileByDocumentId", documentId);
    }
    
    // 문서 상세정보 조회
    public DocumentDTO selectOneDocument(String documentId) {
        return sqlSessionTemplate.selectOne("purchaseMapper.selectOneDocument", documentId);
    }

    // 품목 상세정보 조회
    public ArrayList<DocumentItemDTO> selectDocumentItemList(String documentId) {
        List<DocumentItemDTO> list = sqlSessionTemplate.selectList("purchaseMapper.selectDocumentItemList", documentId);
        return (ArrayList<DocumentItemDTO>) list;
    }

    // 문서 결재자 상세정보 조회
    public ApproveDTO selectApprovalByDocumentId(String documentId) {
        return sqlSessionTemplate.selectOne("purchaseMapper.selectApprovalByDocumentId", documentId);
    }
    
    // 문서 수정 관련 ------------------------------------------------------
    // 문서 수정
    public int updateDocument(DocumentDTO document) {
        return sqlSessionTemplate.update("purchaseMapper.updateDocument", document);
    }
    
    // 기존 품목 삭제
    public int deleteDocumentItems(String documentId) {
        return sqlSessionTemplate.delete("purchaseMapper.deleteDocumentItems", documentId);
    }
    
    // 결재자 수정
    public int updateApprove(ApproveDTO approve) {
        return sqlSessionTemplate.update("purchaseMapper.updateApprove", approve);
    }
    
    // 품목 수정
    public int updateDocumentItem(DocumentItemDTO item) {
        return sqlSessionTemplate.update("purchaseMapper.updateDocumentItem", item);
    }
    
    // 문서 삭제
    public int deleteDocumentOnly(String documentId) {
    	return sqlSessionTemplate.delete("purchaseMapper.deleteDocumentOnly", documentId);
    }
    
    // 결재 정보 삭제
    public int deleteApprove(String documentId) {
    	return sqlSessionTemplate.delete("purchaseMapper.deleteApprove", documentId);
    }
    
    // 문서 파일 삭제
    public int deleteFileByDocumentId(String documentId) {
        return sqlSessionTemplate.delete("purchaseMapper.deleteFileByDocumentId", documentId);
    }

    
    //-----------------------------------
    
  //거래처 계약 상태 => 거래처 전체 조회
  	public ArrayList<Client> selectAllClients() {
  		List<Client> list = sqlSessionTemplate.selectList("clientMapper.selectAllClients");
  		return (ArrayList<Client>) list;
  	}

  	// 거래처 상세 조회
  	public Client selectClientDetail(String clientId) {
  		return sqlSessionTemplate.selectOne("clientMapper.selectClientDetail", clientId);
  	}

  	// 계약서 파일 경로 조회
  	public String selectContractFilePath(String clientId) {
  		return sqlSessionTemplate.selectOne("clientMapper.selectContractFilePath", clientId);
  	}
}