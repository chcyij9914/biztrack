package com.erp.biztrack.graph.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.graph.model.dto.Graph;

@Repository("graphDao")
public class GraphDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 제품별 영업이익 데이터 조회
	 public ArrayList<Graph> getProfitByProduct() {
	        return (ArrayList) sqlSessionTemplate.selectList("graphMapper.getProfitByProduct");
	 
	 }
}
