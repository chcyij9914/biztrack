package com.erp.biztrack.inbound.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dao.InboundDao;
import com.erp.biztrack.inbound.model.dto.Inbound;

@Service("inboundService")
public class InboundServiceImpl implements InboundService {

	@Autowired
	private InboundDao inboundDao;

	// 입고 카운팅
	@Override
	public int selectListCount() {
		return inboundDao.selectListCount();
	}

	// 입고목록 조회
	@Override
	public ArrayList<Inbound> selectList(Paging paging) {
		return inboundDao.selectList(paging);
	}

	// 입고 상세보기
	@Override
	public Inbound selectInboundDetail(String documentId) {
		return inboundDao.selectInboundDetail(documentId);
	}

	@Transactional
    @Override
    public void insertInbound(Inbound inbound) {
        inboundDao.insertInboundDocument(inbound);

        for (DocumentItemDTO item : inbound.getItems()) {
            inboundDao.insertInboundItem(item);
            inboundDao.updateStock(item.getProductId(), item.getQuantity());
        }
    }

	// 재고 수량 변경
    @Transactional
    @Override
    public void updateInbound(Inbound inbound) {
        inboundDao.updateInboundDocument(inbound);

        for (DocumentItemDTO newItem : inbound.getItems()) {
            DocumentItemDTO oldItem = inboundDao.selectInboundItemById(newItem.getItemId());
            int diff = newItem.getQuantity() - oldItem.getQuantity();

            inboundDao.updateInboundItem(newItem);
            inboundDao.updateStock(newItem.getProductId(), diff);
        }
    }
}