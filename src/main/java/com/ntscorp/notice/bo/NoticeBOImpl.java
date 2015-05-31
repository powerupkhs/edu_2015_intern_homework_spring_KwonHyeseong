package com.ntscorp.notice.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntscorp.notice.dao.NoticeDAO;
import com.ntscorp.notice.model.Notice;

@Service
public class NoticeBOImpl implements NoticeBO {

	@Autowired
	private NoticeDAO dao;

	@Override
	public List<Notice> getNoticeList(int page, int count) throws Exception {
		return dao.getNoticeList(page, count);
	}

	@Override
	public Notice getNoticeBySeq(int seq) throws Exception {
		return dao.getNoticeBySeq(seq);
	}

	@Override
	public int getNoticeCount() throws Exception {
		return dao.getNoticeCount();
	}

	@Override
	public boolean addNotice(Notice notice) throws Exception {
		return dao.addNotice(notice);
	}

	@Override
	public boolean updateNotice(Notice notice) throws Exception {
		return dao.updateNotice(notice);
	}
}
