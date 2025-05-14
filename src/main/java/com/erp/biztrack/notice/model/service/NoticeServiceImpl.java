package com.erp.biztrack.notice.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.biztrack.common.Paging;
import com.erp.biztrack.common.Search;
import com.erp.biztrack.notice.model.dao.NoticeDao;
import com.erp.biztrack.notice.model.dto.Notice;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;

	@Override
	public ArrayList<Notice> selectTop3() {
		return noticeDao.selectTop3();
	}

	@Override
	public int selectListCount() {
		return noticeDao.selectListCount();
	}

	@Override
	public ArrayList<Notice> selectList(Paging paging) {
		return noticeDao.selectList(paging);
	}

	@Override
	public int selectSearchTitleCount(String keyword) {
		return noticeDao.selectSearchTitleCount(keyword);
	}

	@Override
	public int selectSearchContentCount(String keyword) {
		return noticeDao.selectSearchContentCount(keyword);
	}

	@Override
	public int selectSearchDateCount(Search search) {
		return noticeDao.selectSearchDateCount(search);
	}

	@Override
	public ArrayList<Notice> selectSearchTitle(Search search) {
		return noticeDao.selectSearchTitle(search);
	}

	@Override
	public ArrayList<Notice> selectSearchContent(Search search) {
		return noticeDao.selectSearchContent(search);
	}

	@Override
	public ArrayList<Notice> selectSearchWriter(Search search) {
		return noticeDao.selectSearchWriter(search);
	}
	@Override
	public ArrayList<Notice> selectSearchDate(Search search) {
		return noticeDao.selectSearchDate(search);
	}

	@Override
	public Notice selectNotice(int noticeNo) {
		return noticeDao.selectNotice(noticeNo);
	}

	@Override
	public void updateAddReadCount(int noticeNo) {
		noticeDao.updateAddReadCount(noticeNo);
	}

	@Override
	public int insertNotice(Notice notice) {
		return noticeDao.insertNotice(notice);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return noticeDao.deleteNotice(noticeNo);
	}

	@Override
	public int updateNotice(Notice notice) {
		return noticeDao.updateNotice(notice);
	}

	//ajax test : last notice select one
	@Override
	public Notice selectLast() {
		return noticeDao.selectLast();
	}

	@Override
	public ArrayList<Notice> getAllNotices() {
		return noticeDao.getAllNotices;
	}

	@Override
	public ArrayList<Notice> selectImportantList() {
		return noticeDao.selectImportantList;
	}

	@Override
	public ArrayList<Notice> selectNoticeList(String page) {
		return noticeDao.selectNoticeList;
	}
	

}