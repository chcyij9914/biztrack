package com.erp.biztrack.client.model.service;

import java.util.ArrayList;
import java.util.List;

import com.erp.biztrack.client.model.dto.Client;
import com.erp.biztrack.common.Paging;

public interface ClientService {
	ArrayList<Client> selectClientList(Paging paging);
	int selectListCount();
}
