package com.erp.biztrack.client.model.dao;

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
import com.erp.biztrack.common.Search;
import com.erp.biztrack.product.model.dto.Product;

@Repository("clientDao")
public class ClientDao {
	@Autowired // root-context.xml 에서 생성한 객체를 자동 연결하는 어노테이션임
	private SqlSessionTemplate sqlSessionTemplate;

	// 거래처 목록 조회 (페이징)
	public ArrayList<Client> selectClientList(Paging paging) {
		List<Client> list = sqlSessionTemplate.selectList("clientMapper.selectClientList", paging);
		return (ArrayList<Client>) list;
	}
	
	// 거래처 전체 수
	public int selectListCount() {
		return sqlSessionTemplate.selectOne("clientMapper.selectListCount");
	}

	// 거래처 등록
	public int insertClient(Client client) {
		return sqlSessionTemplate.insert("clientMapper.insertClient", client);
	}

	// 최신 거래처 ID 조회
	public String selectLastClientId() {
		return sqlSessionTemplate.selectOne("clientMapper.selectLastClientId");
	}

	// 파일 저장 (계약서, 명함 공통)
	public int insertFile(FileDTO file) {
		return sqlSessionTemplate.insert("clientMapper.insertFile", file);
	}
	
	// 새 카테고리 등록
	public int insertCategoryClient(Client client) {
	    return sqlSessionTemplate.insert("clientMapper.insertCategoryClient", client);
	}

	// 최신 카테고리 ID 조회
	public String selectLatestCategoryId() {
	    return sqlSessionTemplate.selectOne("clientMapper.selectLatestCategoryId");
	}
	
	// 거래 계약 상태 갱신
	public int updateClientStatus(Client client) {
		return sqlSessionTemplate.update("clientMapper.updateClientStatus", client);
	}
	
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

	// 명함 파일 경로 조회
	public String selectBusinessCardFilePath(String clientId) {
		return sqlSessionTemplate.selectOne("clientMapper.selectBusinessCardFilePath", clientId);
	}

	// 거래처 정보 수정
	public int updateClient(Client client) {
		return sqlSessionTemplate.update("clientMapper.updateClient", client);
	}

	// 명함 파일 삭제
	public int deleteFile(FileDTO file) {
	    return sqlSessionTemplate.delete("clientMapper.deleteFile", file);
	}
	
	// 거래처명 검색
    public int selectSearchClientNameCount(String keyword) {
        return sqlSessionTemplate.selectOne("clientMapper.selectSearchClientNameCount", keyword);
    }

    public ArrayList<Client> selectSearchClientNameList(Search search) {
    	List<Client> list = sqlSessionTemplate.selectList("clientMapper.selectSearchClientNameList", search);
		return (ArrayList<Client>) list;
    }

    // 계약상태 검색
    public int selectSearchClientStatusCount(String status) {
        return sqlSessionTemplate.selectOne("clientMapper.selectSearchClientStatusCount", status);
    }

    public ArrayList<Client> selectSearchClientStatusList(Search search) {
    	List<Client> list = sqlSessionTemplate.selectList("clientMapper.selectSearchClientStatusList", search);
		return (ArrayList<Client>) list;
    }

    // 카테고리 검색
    public int selectSearchClientCategoryCount(String categoryId) {
        return sqlSessionTemplate.selectOne("clientMapper.selectSearchClientCategoryCount", categoryId);
    }

    public ArrayList<Client> selectSearchClientCategoryList(Search search) {
    	List<Client> list = sqlSessionTemplate.selectList("clientMapper.selectSearchClientCategoryList", search);
		return (ArrayList<Client>) list;
    }
    
    public ArrayList<Client> selectCategoryList() {
        List<Client> list = sqlSessionTemplate.selectList("clientMapper.selectCategoryList");
        return (ArrayList<Client>) list;
    }
    
    // 거래처 삭제
    public int deleteClient(String clientId) {
        return sqlSessionTemplate.delete("clientMapper.deleteClient", clientId);
    }
    
    // 거래처 문서 목록 조회
    public ArrayList<DocumentDTO> selectDocumentList(Paging paging) {
    	List<DocumentDTO> list = sqlSessionTemplate.selectList("clientMapper.selectDocumentList", paging);
		return (ArrayList<DocumentDTO>) list;
    }
    
    // 거래처 문서 전체 수
    public int selectDocumentListCount() {
        return sqlSessionTemplate.selectOne("clientMapper.selectDocumentListCount");
    }
    
    // 문서 등록
    public int insertDocument(DocumentDTO document) {
    	return sqlSessionTemplate.insert("clientMapper.insertDocument", document);
    }

    // 문서 품목 등록
    public int insertDocumentItem(DocumentItemDTO item) {
    	return sqlSessionTemplate.insert("clientMapper.insertDocumentItem", item);
    }

    // 결재 등록
    public int insertApproval(ApproveDTO approval) {
    	return sqlSessionTemplate.insert("clientMapper.insertApproval", approval);
    }

    // 제안서 문서번호 시퀀스
    public String selectNextDocumentIdD() {
    	return sqlSessionTemplate.selectOne("clientMapper.selectNextDocumentIdD");
    }
    
    // 결재 ID 시퀀스 조회
    public String selectNextApproveId() {
    	return sqlSessionTemplate.selectOne("clientMapper.selectNextApproveId");
    }

    // 품목 ID 시퀀스 조회
    public String selectNextItemId() {
    	return sqlSessionTemplate.selectOne("clientMapper.selectNextItemId");
    }
}
