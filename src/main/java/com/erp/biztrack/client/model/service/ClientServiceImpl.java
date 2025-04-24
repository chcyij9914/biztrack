package com.erp.biztrack.client.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.client.model.dao.ClientDao;
import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.Paging;

@Service("clientService")
public class ClientServiceImpl implements ClientService{
	//dao와 연결처리
	@Autowired
	private ClientDao clientDao;

	@Override
	public ArrayList<Client> selectClientList(Paging paging) {
		return clientDao.selectClientList(paging);
	}

	@Override
	public int selectListCount() {
		return clientDao.selectListCount();
	}
	
	
	
	
}
