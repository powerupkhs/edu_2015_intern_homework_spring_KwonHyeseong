/*
 * @(#)User.java 2015. 5. 7.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.notice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author hyeseong.kwon@nhn.com
 */
@Entity(name = "user")
public class User {
	@Id
	private String id;
	private String passwd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
