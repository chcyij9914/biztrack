package com.erp.biztrack.client.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.Paging;

@Repository("clientDao")
public class ClientDao {
	@Autowired  // root-context.xml 에서 생성한 객체를 자동 연결하는 어노테이션임
	private SqlSessionTemplate sqlSessionTemplate;
	
	public ArrayList<Client> selectClientList(Paging paging){
		List<Client> list = sqlSessionTemplate.selectList("clientMapper.selectClientList", paging);
		return (ArrayList<Client>)list;
	}
	
	public int selectListCount() {
		return sqlSessionTemplate.selectOne("clientMapper.selectListCount");
	}
}
