package com.ntscorp.notice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ntscorp.notice.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@Transactional(readOnly = true)
	public User getUserById(String id) throws Exception {
		return hibernateTemplate.get(User.class, id);
	}
}
