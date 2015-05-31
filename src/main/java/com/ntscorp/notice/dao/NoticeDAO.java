package com.ntscorp.notice.dao;

import java.util.List;

import com.ntscorp.notice.model.Notice;

public interface NoticeDAO {
	public List<Notice> getNoticeList(int page, int count) throws Exception;

	public Notice getNoticeBySeq(int seq) throws Exception;

	public int getNoticeCount() throws Exception;

	public boolean addNotice(Notice notice) throws Exception;

	public boolean updateNotice(Notice notice) throws Exception;
}
