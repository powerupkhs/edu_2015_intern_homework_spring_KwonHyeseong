package com.ntscorp.notice.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ntscorp.notice.model.Notice;

@Repository
public class NoticeDAOImpl implements NoticeDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Notice> getNoticeList(int page, int count) throws Exception {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from notice order by seq desc");

		int startNotice = (page - 1) * count;

		query.setFirstResult(startNotice);
		query.setMaxResults(count);

		return query.list();
	}

	@Override
	@Transactional(readOnly = true)
	public Notice getNoticeBySeq(int seq) throws Exception {
		return hibernateTemplate.get(Notice.class, seq);
	}

	@Override
	@Transactional(readOnly = true)
	public int getNoticeCount() throws Exception {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery("from notice");
		return query.list().size();
	}

	@Override
	@Transactional
	public boolean addNotice(Notice notice) throws Exception {
		hibernateTemplate.persist(notice);
		return true;
	}

	@Override
	@Transactional
	public boolean updateNotice(Notice notice) throws Exception {
		hibernateTemplate.update(notice);
		return true;
	}
}
