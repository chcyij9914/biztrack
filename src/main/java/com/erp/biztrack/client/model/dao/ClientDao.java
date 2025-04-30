package com.erp.biztrack.client.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.DocumentDTO;
import com.erp.biztrack.common.FileDTO;
import com.erp.biztrack.common.Paging;

@Repository("clientDao")
public class ClientDao {
	@Autowired // root-context.xml 에서 생성한 객체를 자동 연결하는 어노테이션임
	private SqlSessionTemplate sqlSessionTemplate;

	// 거래처 목록 조회 관련 -----------------------------------
	public ArrayList<Client> selectClientList(Paging paging) {
		List<Client> list = sqlSessionTemplate.selectList("clientMapper.selectClientList", paging);
		return (ArrayList<Client>) list;
	}

	public int selectListCount() {
		return sqlSessionTemplate.selectOne("clientMapper.selectListCount");
	}

	// 거래처 등록 관련 -----------------------------------
	public int insertClient(Client client) {
		return sqlSessionTemplate.insert("clientMapper.insertClient", client);
	}

	public String selectLastClientId() {
		return sqlSessionTemplate.selectOne("clientMapper.selectLastClientId");
	}

	public int insertFile(FileDTO file) {
		return sqlSessionTemplate.insert("clientMapper.insertFile", file);
	}

	public int insertDocument(DocumentDTO document) {
		return sqlSessionTemplate.insert("clientMapper.insertDocument", document);
	}

	public String selectLatestDocumentId() {
		return sqlSessionTemplate.selectOne("clientMapper.selectLatestDocumentId");
	}
	
	// 거래처 상세보기 관련 ------------------------------------
	public Client selectClientDetail(String clientId) {
		return sqlSessionTemplate.selectOne("clientMapper.selectClientDetail", clientId);
	}
	
	public String selectContractFilePath(String clientId) {
	    return sqlSessionTemplate.selectOne("clientMapper.selectContractFilePath", clientId);
	}
	
	public String selectBusinessCardFilePath(String clientId) {
	    return sqlSessionTemplate.selectOne("clientMapper.selectBusinessCardFilePath", clientId);
	}
}
