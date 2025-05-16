package com.erp.biztrack.graph.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.graph.model.dao.GraphDao;
import com.erp.biztrack.graph.model.dto.Graph;


@Service("graphService")
public class GraphServiceImpl implements GraphService {
	
	@Autowired
	private GraphDao graphDao;
	
	@Override
	public List<Graph> selectProfitGraphData() {
		return graphDao.selectProfitGraphData();
	}

	@Override
	public List<Graph> getTransactionCountData() {
		return graphDao.getTransactionCountData();
	}

	 @Override
	    public ArrayList<Graph> getDepartmentSalesPerformance() {
	        return graphDao.getDepartmentSalesPerformance();
	    }

	    @Override
	    public ArrayList<Graph> getEmployeeSalesPerformance() {
	        return graphDao.getEmployeeSalesPerformance();
	    }
}
