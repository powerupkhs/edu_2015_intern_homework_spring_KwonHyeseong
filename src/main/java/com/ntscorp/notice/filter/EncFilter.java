/*
 * @(#)EncFilter.java 2015. 5. 6.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.notice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hyeseong.kwon@nhn.com
 */
public class EncFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(EncFilter.class);
	private String encoding;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
		logger.info("필터 적용");
	}

	@Override
	public void init(FilterConfig chain) throws ServletException {
		encoding = chain.getInitParameter("encoding");
	}

}
