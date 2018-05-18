package com.csit.javaweb_02.servlets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csit.javaweb_02.utils.ReflectUtil;

public class OrderServlet extends CommonServlet {
	List<Map<Class<?>, Map<Method, String>>> urllist;

	@Override
	public void init() {
		urllist = (List<Map<Class<?>, Map<Method, String>>>) getServletContext().getAttribute("urllist");
		//jdbcUtil = (JdbcUtil) getServletContext().getAttribute("jdbcUtil");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		 for (Map<Class<?>, Map<Method, String>> iurlmap : urllist) {
		 Set<Entry<Class<?>, Map<Method, String>>> entry = iurlmap.entrySet();
		 for (Entry<Class<?>, Map<Method, String>> en : entry) {
		 for (Entry<Method, String> urlmap : en.getValue().entrySet()) {
		 if (urlmap.getValue().equals(request.getServletPath())) {
		 Method method = urlmap.getKey();
		 try {
		 Object ob = ReflectUtil.get(en.getKey());	 
		 method.invoke(ob, request, response);
		 } catch (IllegalAccessException e) {
		 e.printStackTrace();
		 } catch (IllegalArgumentException e) {
		 e.printStackTrace();
		 } catch (InvocationTargetException e) {
		 e.printStackTrace();
		 }
		 break;
		 }
		 }
		 }
		 }
		//		String path = request.getServletPath();
//		switch (path) {
//		case "/order/index.do":
//			index(request, response);
//			break;
//		case "/order/all.do":
//			all(request, response);
//			break;
//		default:
//			nofound(request, response);
//			break;
//		}
	}

//	@Transation
//	public void index(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			Connection conn = jdbcUtil.get();
//			try {
//				PreparedStatement ps = conn
//						.prepareStatement("insert into tuser(username,password)values('xiaoli','123456')");
//				ps.execute();
//				conn.commit();
//			} catch (SQLException e) {
//				try {
//					conn.rollback();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				e.printStackTrace();
//
//			} finally {
//				jdbcUtil.close();
//			}
//			response.getWriter().println("index");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Transation
//	public void all(HttpServletRequest request, HttpServletResponse response) {
//		try {
//			Connection conn = jdbcUtil.get();
//			try {
//				PreparedStatement ps = conn
//						.prepareStatement("insert into tuser(username,password)values('xiaoli2','123456')");
//				ps.execute();
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				int i = 1 / 0;
//				conn.commit();
//			} catch (SQLException e) {
//				try {
//					conn.rollback();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				e.printStackTrace();
//
//			} finally {
//				jdbcUtil.close();
//			}
//			response.getWriter().println("index");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void doPost(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("post");
//	}
}
