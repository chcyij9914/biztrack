package com.erp.biztrack.graph.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.graph.model.dao.GraphDao;
import com.erp.biztrack.graph.model.dto.Graph;


@Service("graphService")
public class GraphServiceImpl implements GraphService {
	
	@Autowired
	private GraphDao graphDao;

	@Override
	public ArrayList<Graph> getProfitByProduct() {
		return graphDao.getProfitByProduct();
	}
	
}
