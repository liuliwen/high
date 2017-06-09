package com.high.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.high.entity.User;

@Controller
@RequestMapping("/views")
public class PageController {

	/**
	 * 拦截所有请求的jsp页面（除了首页），若访问的页面为活动详情，则允许访问，否则判断用户是否已经登录，若没有登录则跳转到登录页面
	 * 
	 * @param session
	 * @param module
	 * @param page
	 * @return
	 */
	@RequestMapping("/{module}/{page}.htm")
	public String page(HttpSession session, HttpServletRequest request, HttpServletResponse response, @PathVariable String module, @PathVariable String page) {
		return module + "/" + page;
	}
	@RequestMapping("/toIndex.do")
	public String toIndex(){
		return "index";
	}
}
