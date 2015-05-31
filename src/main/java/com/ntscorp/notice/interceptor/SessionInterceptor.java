/*
 * @(#)LogInterceptor.java 2015. 5. 6.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.notice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ntscorp.annotation.AuthCheck;

/**
 * @author hyeseong.kwon@nhn.com
 */
public class SessionInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("HomeController 시작 전 로그");

		AuthCheck authCheck = ((HandlerMethod) handler).getMethodAnnotation(AuthCheck.class);

		if (authCheck != null) {
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");

			if (userId == null) {
				response.sendRedirect("login/form"); //세션에 userid가 없을 경우 로그인페이지로 리다이렉트 시킴...
				return false;
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info("HomeController 끝난 후 로그");
	}

}