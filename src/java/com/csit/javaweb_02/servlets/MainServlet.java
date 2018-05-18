package com.csit.javaweb_02.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet implements Servlet {

	@Override
	public void destroy() {
		System.out.println("destory..");
	}

	@Override
	public ServletConfig getServletConfig() {

		return null;
	}

	@Override
	public String getServletInfo() {

		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("init...");
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("service...");
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String username = httpServletRequest.getParameter("username");
		httpServletRequest.setAttribute("username", username+"hi");
		System.out.println(username);
//		PrintWriter writer = httpServletResponse.getWriter();
//		writer.println("hello world!");
		//httpServletRequest.getRequestDispatcher("/views/index.jsp").forward(request, response);
		httpServletResponse.sendRedirect("views/index.jsp?username=123");
	}

}
