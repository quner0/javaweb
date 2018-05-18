package com.csit.javaweb_02.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {


	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		if(!request.getServletPath().equals("/views/login.jsp")&&request.getSession().getAttribute("username")==null){
			request.getRequestDispatcher("/views/login.jsp").forward(arg0, arg1);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {


	}

}
