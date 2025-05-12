package com.erp.biztrack.inbound.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.biztrack.common.DocumentItemDTO;
import com.erp.biztrack.common.Paging;
import com.erp.biztrack.inbound.model.dto.Inbound;

@Repository("inboundDao")
public class InboundDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public ArrayList<Inbound> selectList(Paging paging) {
		List<Inbound> list = sqlSessionTemplate.selectList("inboundMapper.selectList", paging);
		return (ArrayList<Inbound>) list;
	}
	
	public int selectListCount() {
	    return sqlSessionTemplate.selectOne("inboundMapper.selectListCount");
	}
	
	//입고 상세보기
    public Inbound selectInboundDetail(String documentId) {
        return sqlSessionTemplate.selectOne("inboundMapper.selectInboundDetail", documentId);
    }
    
    //재고 수량 변경
 // 문서 등록
    public int insertInboundDocument(Inbound inbound) {
        return sqlSessionTemplate.insert("inboundMapper.insertInbound", inbound);
    }

    // 문서 수정
    public int updateInboundDocument(Inbound inbound) {
        return sqlSessionTemplate.update("inboundMapper.updateInbound", inbound);
    }

    // 품목 등록
    public int insertInboundItem(DocumentItemDTO item) {
        return sqlSessionTemplate.insert("inboundMapper.insertItem", item);
    }

    // 품목 수정
    public int updateInboundItem(DocumentItemDTO item) {
        return sqlSessionTemplate.update("inboundMapper.updateItem", item);
    }

    // 기존 품목 조회
    public DocumentItemDTO selectInboundItemById(String itemId) {
        return sqlSessionTemplate.selectOne("inboundMapper.selectItemById", itemId);
    }

    // 재고 변경
    public void updateStock(String productId, int quantity) {
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("quantity", quantity);
        sqlSessionTemplate.update("stockMapper.updateStock", map);
    }
}