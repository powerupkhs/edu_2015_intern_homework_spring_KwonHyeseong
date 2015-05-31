package com.ntscorp.notice.bo;

import com.ntscorp.notice.model.User;

public interface UserBO {
	public User getUserById(String id) throws Exception;
}
