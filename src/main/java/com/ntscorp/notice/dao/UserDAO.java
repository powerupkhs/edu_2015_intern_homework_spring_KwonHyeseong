package com.ntscorp.notice.dao;

import com.ntscorp.notice.model.User;

public interface UserDAO {
	public User getUserById(String id) throws Exception;
}
