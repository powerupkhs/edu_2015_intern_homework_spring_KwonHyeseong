/*
 * @(#)LoginController.java 2015. 5. 7.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author hyeseong.kwon@nhn.com
 */
@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	//	@RequestMapping("/login/form")
	//	public ModelAndView form() {
	//		logger.info("로긴폼 들어옴");
	//		System.out.println("로긴폼 들어옴");
	//
	//		ModelAndView mav = new ModelAndView();
	//		mav.setViewName("login_example");
	//		return mav;
	//	}

	// // 폼 값 받아서 save.jsp 에 값 넘겨주는거
	//	@RequestMapping("/login/save")
	//	public ModelAndView save(User user) {
	//		logger.info("서밋해서 들어옴");
	//		System.out.println("서밋해서 들어옴");
	//
	//		System.out.println(user.getId() + "    " + user.getPwd());
	//
	//		ModelAndView mav = new ModelAndView("/save");
	//
	//		mav.addObject("userInfo", user);
	//		return mav;
	//	}

	//	// 폼에서 값 받은거 json으로 출력하는거
	//	@RequestMapping("/login/save")
	//	@ResponseBody
	//	public User save(User user) {
	//		return user;
	//	}

	// 새로 생성해서 json으로 출력하는거
	@RequestMapping("/login/json")
	@ResponseBody
	public User json() {
		User user = new User();
		user.setId("kown");
		user.setPwd("hihihi");

		return user;
	}

	// json을 객체로 변환
	@RequestMapping("/login/convert/json")
	public ModelAndView convertJson() {
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject("http://10.67.68.54:8080/notice/json/test", User.class);

		ModelAndView mav = new ModelAndView("/convert_json");
		mav.addObject("userInfo", user);

		return mav;
	}

	// 커스톰 어노테이션 테스트 적용 안한거
	@RequestMapping("/login/noanno")
	@Auth(check = false)
	public ModelAndView noanno() {
		ModelAndView mav = new ModelAndView("/home");

		return mav;
	}

	// 커스톰 어노테이션 테스트 적용 한거
	@RequestMapping("/login/anno")
	@Auth(check = true)
	public ModelAndView anno() {
		ModelAndView mav = new ModelAndView("/home");

		return mav;
	}

	// 메세지 테스트
	@RequestMapping("/login/msg")
	public ModelAndView msg() {
		ModelAndView mav = new ModelAndView("/msg");

		return mav;
	}

	// 스케쥴러 테스트
	@RequestMapping("/login/scd")
	public ModelAndView scd() {
		ModelAndView mav = new ModelAndView("/msg");
		return mav;
	}
}
