package com.ntscorp.notice.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntscorp.notice.dao.UserDAO;
import com.ntscorp.notice.model.User;

@Service
public class UserBOImpl implements UserBO {

	@Autowired
	private UserDAO dao;

	@Override
	public User getUserById(String id) throws Exception {
		return dao.getUserById(id);
	}
}
