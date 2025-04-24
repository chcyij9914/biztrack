package com.erp.biztrack.client.model.service;

import java.util.List;

import com.erp.biztrack.client.model.dto.Client;

public interface ClientService {
	List<Client> selectClientList();
}
