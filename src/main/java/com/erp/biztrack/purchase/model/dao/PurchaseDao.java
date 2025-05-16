

package com.erp.biztrack.purchase.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.erp.biztrack.inbound.model.dto.Inbound;
import com.erp.biztrack.product.model.dto.Product;
import com.erp.biztrack.purchase.model.dto.Purchase;

@Repository("purchaseDao")
public class PurchaseDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 문서 목록 조회 관련--품의서 -------------------------------------------------------------------
    // 본인 작성 문서 목록 조회
    public ArrayList<DocumentDTO> selectDocumentListByType(Map<String, Object> param) {
        List<DocumentDTO> list = sqlSessionTemplate.selectList("purchaseMapper.selectDocumentListByType", param);
        return (ArrayList<DocumentDTO>) list;
    }

    // 본인 작성 문서 수 조회
    public int selectDocumentListCountByType(Map<String, Object> param) {
        return sqlSessionTemplate.selectOne("purchaseMapper.selectDocumentListCountByType", param);
    }
    
    // 결재자 문서 목록 조회
    public ArrayList<DocumentDTO> selectDocumentListByType2(Map<String, Object> param) {
        List<DocumentDTO> list = sqlSessionTemplate.selectList("purchaseMapper.selectDocumentListByType2", param);
        return (ArrayList<DocumentDTO>) list;
    }

    // 결재자 문서 수 조회
    public int selectDocumentListCountByType2(Map<String, Object> param) {
        return sqlSessionTemplate.selectOne("purchaseMapper.selectDocumentListCountByType2", param);
    }
    
    // 전체 문서 목록 조회
    public ArrayList<DocumentDTO> selectDocumentListByType3(Map<String, Object> param) {
        List<DocumentDTO> list = sqlSessionTemplate.selectList("purchaseMapper.selectDocumentListByType3", param);
        return (ArrayList<DocumentDTO>) list;
    }

    // 전체 문서 수 조회
    public int selectDocumentListCountByType3(Map<String, Object> param) {
        return sqlSessionTemplate.selectOne("purchaseMapper.selectDocumentListCountByType3", param);
    }
    
 // 문서 목록 조회 관련--지출결의서 -------------------------------------------------------------------
    // 본인 작성 문서 목록 조회
    public ArrayList<DocumentDTO> selectDocumentListByTypeT(Map<String, Object> param) {
        List<DocumentDTO> list = sqlSessionTemplate.selectList("purchaseMapper.selectDocumentListByTypeT", param);
        return (ArrayList<DocumentDTO>) list;
    }

    // 본인 작성 문서 수 조회
    public int selectDocumentListCountByTypeT(Map<String, Object> param) {
        return sqlSessionTemplate.selectOne("purchaseMapper.selectDocumentListCountByTypeT", param);
    }
    
    // 결재자 문서 목록 조회
    public ArrayList<DocumentDTO> selectDocumentListByTypeT2(Map<String, Object> param) {
        List<DocumentDTO> list = sqlSessionTemplate.selectList("purchaseMapper.selectDocumentListByTypeT2", param);
        return (ArrayList<DocumentDTO>) list;
    }

    // 결재자 문서 수 조회
    public int selectDocumentListCountByTypeT2(Map<String, Object> param) {
        return sqlSessionTemplate.selectOne("purchaseMapper.selectDocumentListCountByTypeT2", param);
    }
    
    // 전체 문서 목록 조회
    public ArrayList<DocumentDTO> selectDocumentListByTypeT3(Map<String, Object> param) {
        List<DocumentDTO> list = sqlSessionTemplate.selectList("purchaseMapper.selectDocumentListByTypeT3", param);
        return (ArrayList<DocumentDTO>) list;
    }

    // 전체 문서 수 조회
    public int selectDocumentListCountByTypeT3(Map<String, Object> param) {
        return sqlSessionTemplate.selectOne("purchaseMapper.selectDocumentListCountByTypeT3", param);
    }
    
    

 // --------------------------------------------------------------------------------------------
 // 검색기능------------품의서
 // --------------------------------------------------------------------------------------------
 	// 검색기능
 	// 문서번호-사원
 	public List<Purchase> searchByDocumentId(String documentId, String empId) {
 		Map<String, Object> param = new HashMap<>();
 		param.put("documentId", documentId);
 		param.put("empId", empId);

 		return sqlSessionTemplate.selectList("purchaseMapper.searchByDocumentId", param);
 	}

 	// 문서번호-팀장/부서장
 	public List<Purchase> searchByDocumentId2(String documentId, String empId) {
 		Map<String, Object> param = new HashMap<>();
 		param.put("documentId", documentId);
 		param.put("empId", empId);

 		return sqlSessionTemplate.selectList("purchaseMapper.searchByDocumentId2", param);
 	}

 	// 문서번호-대표이사
 	public List<Purchase> searchByDocumentId3(String documentId, String empId) {
 		Map<String, Object> param = new HashMap<>();
 		param.put("documentId", documentId);
 		param.put("empId", empId);

 		return sqlSessionTemplate.selectList("purchaseMapper.searchByDocumentId3", param);
 	}
 	
 	//------------------------------------------------------------------------------------------------
 		// 검색기능
 		// 제목-사원
 		public List<Purchase> searchByTitle(String title, String empId) {
 			Map<String, Object> param = new HashMap<>();
 			param.put("title", title);
 			param.put("empId", empId);

 			return sqlSessionTemplate.selectList("purchaseMapper.searchByTitle", param);
 		}

 		// 제목-팀장/부서장
 		public List<Purchase> searchByTitle2(String title, String empId) {
 			Map<String, Object> param = new HashMap<>();
 			param.put("title", title);
 			param.put("empId", empId);

 			return sqlSessionTemplate.selectList("purchaseMapper.searchByTitle2", param);
 		}

 		// 제목-대표이사
 		public List<Purchase> searchByTitle3(String title, String empId) {
 			Map<String, Object> param = new HashMap<>();
 			param.put("title", title);
 			param.put("empId", empId);

 			return sqlSessionTemplate.selectList("purchaseMapper.searchByTitle3", param);
 		}
 		
 		//------------------------------------------------------------------------------------------------
 		// 검색기능
 		// 상태-사원
 		public List<Purchase> searchByStatus(String status, String empId) {
 			Map<String, Object> param = new HashMap<>();
 			param.put("status", status);
 			param.put("empId", empId);

 			return sqlSessionTemplate.selectList("purchaseMapper.searchByStatus", param);
 		}

 		// 상태-팀장/부서장
 		public List<Purchase> searchByStatus2(String status, String empId) {
 			Map<String, Object> param = new HashMap<>();
 			param.put("status", status);
 			param.put("empId", empId);

 			return sqlSessionTemplate.selectList("purchaseMapper.searchByStatus2", param);
 		}

 		// 상태-대표이사
 		public List<Purchase> searchByStatus3(String status, String empId) {
 			Map<String, Object> param = new HashMap<>();
 			param.put("status", status);
 			param.put("empId", empId);

 			return sqlSessionTemplate.selectList("purchaseMapper.searchByStatus3", param);
 		}

 	// --------------------------------------------------------------------------------------------
 		// 검색기능------------지출결의서
 		// 문서번호-사원
 	// 검색기능
 	 	// 문서번호-사원
 	 	public List<Purchase> searchByDocumentIdT(String documentId, String empId) {
 	 		Map<String, Object> param = new HashMap<>();
 	 		param.put("documentId", documentId);
 	 		param.put("empId", empId);

 	 		return sqlSessionTemplate.selectList("purchaseMapper.searchByDocumentIdT", param);
 	 	}

 	 	// 문서번호-팀장/부서장
 	 	public List<Purchase> searchByDocumentIdT2(String documentId, String empId) {
 	 		Map<String, Object> param = new HashMap<>();
 	 		param.put("documentId", documentId);
 	 		param.put("empId", empId);

 	 		return sqlSessionTemplate.selectList("purchaseMapper.searchByDocumentIdT2", param);
 	 	}

 	 	// 문서번호-대표이사
 	 	public List<Purchase> searchByDocumentIdT3(String documentId, String empId) {
 	 		Map<String, Object> param = new HashMap<>();
 	 		param.put("documentId", documentId);
 	 		param.put("empId", empId);

 	 		return sqlSessionTemplate.selectList("purchaseMapper.searchByDocumentIdT3", param);
 	 	}
 	 	
 	 	//------------------------------------------------------------------------------------------------
 	 		// 검색기능
 	 		// 제목-사원
 	 		public List<Purchase> searchByTitleT(String title, String empId) {
 	 			Map<String, Object> param = new HashMap<>();
 	 			param.put("title", title);
 	 			param.put("empId", empId);

 	 			return sqlSessionTemplate.selectList("purchaseMapper.searchByTitleT", param);
 	 		}

 	 		// 제목-팀장/부서장
 	 		public List<Purchase> searchByTitleT2(String title, String empId) {
 	 			Map<String, Object> param = new HashMap<>();
 	 			param.put("title", title);
 	 			param.put("empId", empId);

 	 			return sqlSessionTemplate.selectList("purchaseMapper.searchByTitleT2", param);
 	 		}

 	 		// 제목-대표이사
 	 		public List<Purchase> searchByTitleT3(String title, String empId) {
 	 			Map<String, Object> param = new HashMap<>();
 	 			param.put("title", title);
 	 			param.put("empId", empId);

 	 			return sqlSessionTemplate.selectList("purchaseMapper.searchByTitleT3", param);
 	 		}
 	 		
 	 		//------------------------------------------------------------------------------------------------
 	 		// 검색기능
 	 		// 상태-사원
 	 		public List<Purchase> searchByStatusT(String status, String empId) {
 	 			Map<String, Object> param = new HashMap<>();
 	 			param.put("status", status);
 	 			param.put("empId", empId);

 	 			return sqlSessionTemplate.selectList("purchaseMapper.searchByStatusT", param);
 	 		}

 	 		// 상태-팀장/부서장
 	 		public List<Purchase> searchByStatusT2(String status, String empId) {
 	 			Map<String, Object> param = new HashMap<>();
 	 			param.put("status", status);
 	 			param.put("empId", empId);

 	 			return sqlSessionTemplate.selectList("purchaseMapper.searchByStatusT2", param);
 	 		}

 	 		// 상태-대표이사
 	 		public List<Purchase> searchByStatusT3(String status, String empId) {
 	 			Map<String, Object> param = new HashMap<>();
 	 			param.put("status", status);
 	 			param.put("empId", empId);

 	 			return sqlSessionTemplate.selectList("purchaseMapper.searchByStatusT3", param);
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
    	
    	 // 결재자 결재 상태 수정 기능
      public int updateApprovalStatus(ApproveDTO approve) {
          return sqlSessionTemplate.update("purchaseMapper.updateApprovalStatus", approve);
      }
  }
