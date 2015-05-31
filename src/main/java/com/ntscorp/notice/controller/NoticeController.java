/*
 * @(#)NoticeController.java 2015. 5. 7.
 *
 * Copyright 2015 NAVER Corp. All rights Reserved. 
 * NAVER PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ntscorp.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ntscorp.annotation.AuthCheck;
import com.ntscorp.notice.bo.NoticeBO;
import com.ntscorp.notice.bo.UserBO;
import com.ntscorp.notice.model.Notice;
import com.ntscorp.notice.model.User;

/**
 * A. /notice/list?page=1&count=10 : 공지사항 리스트 보기(get방식으로 page 및 count 설정 가능)<br>
 * B. /notice/{seq} : 해당 sequence의 공지사항 상세 보기(수정 가능)<br>
 * C. /notice/form : 공지사항 신규등록<br>
 * D. /notice/save : 공지사항 제목 및 상세 저장<br>
 * E. /login/form : 로그인 폼<br>
 * F. /login/submit : 로그인 폼으로부터 넘어온 값 체크(일치하지 않을경우 세션 제거, 일치할 경우 세션 저장)<br>
 * G. /notice/json/list?page=1&count=10 : 공지사항 목록 json으로 보기(get방식으로 page 및 count
 * 설정 가능)<br>
 * H. /notice/json/{seq} : 공지사항 단건 json으로 보기<br>
 * 
 * @author hyeseong.kwon@nhn.com
 */
@Controller
public class NoticeController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	public static final String ADMIN_ID = "admin";

	@Autowired
	private NoticeBO noticeBo;

	@Autowired
	private UserBO userBo;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "count", required = true, defaultValue = "20") int count) throws Exception {
		logger.info("리스트 들어옴   " + page + "   " + count);

		int totalPage;
		int listSize;

		List<Notice> noticeList = noticeBo.getNoticeList(page, count);
		listSize = noticeBo.getNoticeCount();

		if (listSize % count == 0) {
			totalPage = listSize / count;
		} else {
			totalPage = (listSize / count) + 1;
		}

		ModelAndView mav = new ModelAndView("/list");
		mav.addObject("noticeList", noticeList);
		mav.addObject("totalPage", totalPage);
		mav.addObject("count", count);
		return mav;
	}

	@RequestMapping("/{seq}")
	public ModelAndView seqNotice(@PathVariable int seq) throws Exception {
		logger.info("seqNotice 들어옴   ");

		Notice notice = noticeBo.getNoticeBySeq(seq);

		ModelAndView mav = new ModelAndView("/detail");
		mav.addObject("notice", notice);
		mav.addObject("function", "update");
		mav.addObject("seq", seq);
		return mav;
	}

	@RequestMapping("/form")
	public ModelAndView noticeForm() throws Exception {
		logger.info("폼 들어옴   ");

		ModelAndView mav = new ModelAndView("/detail");
		mav.addObject("notice", new Notice());
		mav.addObject("function", "add");
		return mav;
	}

	// 폼 값 받아서 save.jsp 에 값 넘겨주는거
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@AuthCheck
	public ModelAndView save(Notice notice, @RequestParam(value = "function") String function) throws Exception {
		logger.info("save 들어옴   " + notice.getSeq() + "  " + notice.getTitle() + "  " + notice.getContent() + "  " + function);

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		notice.setUserId(userId);

		if (userId.equals(ADMIN_ID) == false) {
			mav.setViewName("redirect:/login/form");
			return mav;
		}

		if (function.equals("add")) {
			noticeBo.addNotice(notice);
		} else {
			noticeBo.updateNotice(notice);
		}

		mav.setViewName("redirect:/list");
		return mav;
	}

	@RequestMapping("/login/form")
	public ModelAndView loginForm() throws Exception {
		logger.info("로그인폼 들어옴");

		ModelAndView mav = new ModelAndView("/loginForm");
		return mav;
	}

	@RequestMapping(value = "/login/submit", method = RequestMethod.POST)
	public ModelAndView loginSubmit(User currentUser) throws Exception {
		logger.info("로그인폼 들어옴");

		ModelAndView mav = new ModelAndView();
		User dbUser = userBo.getUserById(currentUser.getId());
		HttpSession session = request.getSession();

		if (dbUser != null && dbUser.getPasswd().equals(currentUser.getPasswd())) {
			mav.setViewName("redirect:/list");
			session.setAttribute("userId", currentUser.getId());

		} else {
			mav.setViewName("redirect:/login/form");
			session.setAttribute("userId", null);
		}

		return mav;
	}

	// 새로 생성해서 json으로 출력하는거
	@RequestMapping("/json/list")
	@ResponseBody
	public List<Notice> jsonList(@RequestParam(value = "page", required = true, defaultValue = "1") int page, @RequestParam(value = "count", required = true, defaultValue = "20") int count) throws Exception {
		logger.info("jsonList 들어옴   " + page + "   " + count);

		List<Notice> noticeList = noticeBo.getNoticeList(page, count);
		return noticeList;
	}

	@RequestMapping("/json/{seq}")
	@ResponseBody
	public Notice jsonNotice(@PathVariable int seq) throws Exception {
		logger.info("jsonNotice 들어옴   ");

		Notice notice = noticeBo.getNoticeBySeq(seq);
		return notice;
	}

	//	// 폼에서 값 받은거 json으로 출력하는거
	//	@RequestMapping("/login/save")
	//	@ResponseBody
	//	public User save(User user) {
	//		return user;
	//	}

	//
	//	@RequestMapping("/")
	//	public ModelAndView save(User user) {
	//		logger.info("서밋해서 들어옴");
	//		System.out.println("서밋해서 들어옴");
	//
	//		System.out.println(user.getId() + "    " + user.getId());
	//
	//		ModelAndView mav = new ModelAndView("/save");
	//
	//		mav.addObject("userInfo", user);
	//		return mav;
	//	}
	//
	//	// 폼에서 값 받은거 json으로 출력하는거
	//	@RequestMapping("/form")
	//	@ResponseBody
	//	public User form(User user) {
	//		return user;
	//	}
	//
	//	// 새로 생성해서 json으로 출력하는거
	//	@RequestMapping("/save")
	//	@ResponseBody
	//	public User json() {
	//		User user = new User();
	//		//user.setId("kown");
	//		//user.setPwd("hihihi");
	//
	//		return user;
	//	}
	//
	//	// json을 객체로 변환
	//	@RequestMapping("/login/form")
	//	public ModelAndView convertJson() {
	//		RestTemplate restTemplate = new RestTemplate();
	//		User user = restTemplate.getForObject("http://10.67.68.54:8080/notice/json/test", User.class);
	//
	//		ModelAndView mav = new ModelAndView("/convert_json");
	//		mav.addObject("userInfo", user);
	//
	//		return mav;
	//	}
	//
	//	// 커스톰 어노테이션 테스트 적용 안한거
	//	@RequestMapping("/login/submit")
	//	@Auth(check = false)
	//	public ModelAndView noanno() {
	//		ModelAndView mav = new ModelAndView("/home");
	//
	//		return mav;
	//	}
	//
	//	// 커스톰 어노테이션 테스트 적용 한거
	//	@RequestMapping("/json")
	//	@Auth(check = true)
	//	public ModelAndView anno() {
	//		ModelAndView mav = new ModelAndView("/home");
	//
	//		return mav;
	//	}
}
