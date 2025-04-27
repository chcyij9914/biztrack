package com.erp.biztrack.inbound.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dto.Inbound;

@Repository("inboundDao")
public class InboundDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public ArrayList<Inbound> selectList(Paging paging) {
		List<Inbound> list = sqlSessionTemplate.selectList("inboundMapper.selectList", paging);
		return (ArrayList<Inbound>) list;
	}
	
	public int selectListCount() {
	    return sqlSessionTemplate.selectOne("inboundMapper.selectListCount");
	}
}