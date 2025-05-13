package com.erp.biztrack.graph.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.graph.model.dto.Graph;

@Repository("graphDao")
public class GraphDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 제품별 영업이익 데이터 조회
	public List<Graph> selectProfitGraphData() {
        return sqlSessionTemplate.selectList("graphMapper.ProfitGraph");
	 }
	
	// 거래건수 그래프 데이터 조회
    public List<Graph> getTransactionCountData() {
        return sqlSessionTemplate.selectList("graphMapper.TransactionCountGraph");
    }
    
 // 부서별 영업실적 데이터 조회
    public ArrayList<Graph> getDepartmentSalesPerformanceData() {
        return new ArrayList<>(sqlSessionTemplate.selectList("graphMapper.DepartmentSalesPerformanceGraph"));
    }

}
