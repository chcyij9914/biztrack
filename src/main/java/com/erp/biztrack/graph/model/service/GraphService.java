package com.erp.biztrack.graph.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erp.biztrack.graph.model.dto.Graph;

public interface GraphService {
	List<Graph> selectProfitGraphData();
	List<Graph> getTransactionCountData();
	ArrayList<Graph> getDepartmentSalesPerformanceData();
}
