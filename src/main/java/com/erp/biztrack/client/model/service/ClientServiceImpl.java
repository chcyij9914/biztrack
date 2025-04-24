package com.erp.biztrack.client.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.client.model.dao.ClientDao;
import com.erp.biztrack.client.model.dto.Client;

@Service("clientService")
public class ClientServiceImpl implements ClientService{
	//dao와 연결처리
	@Autowired
	private ClientDao clientDao;

	@Override
	public List<Client> selectClientList() {
		return clientDao.selectClientList();
	}
	
	
}
