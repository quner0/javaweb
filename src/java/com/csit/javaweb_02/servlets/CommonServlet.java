package com.csit.javaweb_02.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonServlet extends HttpServlet {
	public void nofound(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().println("404");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
