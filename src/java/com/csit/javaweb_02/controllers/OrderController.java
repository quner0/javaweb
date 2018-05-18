package com.csit.javaweb_02.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csit.javaweb_02.annotation.Inject;
import com.csit.javaweb_02.annotation.RequestMapping;
import com.csit.javaweb_02.domains.TOrder;
import com.csit.javaweb_02.services.OrderService;
import com.csit.javaweb_02.services.OrderServiceImpl;

@com.csit.javaweb_02.annotation.Controller
@RequestMapping(url = "/order")
public class OrderController implements Controller {
	@Inject
	OrderService orderService;
	@RequestMapping(url = "/index.do")
	public void index(HttpServletRequest request, HttpServletResponse response) {
		TOrder order = new TOrder();
		order.setOrderName("01010010101");
		order.setCreatedDate(new Date());
		try {
			orderService.insert(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			request.getRequestDispatcher("/views/index.jsp").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
