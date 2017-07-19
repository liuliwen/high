package com.high.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.high.entity.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rep;
		/*HttpSession session = request.getSession();
		// 获得用户请求的URI
		String path = request.getRequestURI();
		System.out.println("拦截了！！！");
		System.out.println("路径： " + path);
		User user = (User) session.getAttribute("user");
		*//**
		 * 如果 session中没有保存用户信息，即用户还未登陆，并且不是正在进行登录，，，则重定向到登录页面
		 *//*
		boolean pass = false;
		if(path.contains("login.do") || path.contains("isRegister.do") || path.contains("registerAndLogin.do")){
			pass = true;
		}
		if(null == user && !pass){
			session.setAttribute("prePath", path);
			session.setAttribute("preRequest", request);
			session.setAttribute("preResponse", response);
			System.out.println("重定向登录页面！");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		//放行
		System.out.println("放行！！！");*/
		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
