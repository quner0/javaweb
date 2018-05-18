package com.csit.javaweb_02.tests;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

import com.csit.javaweb_02.utils.JdbcUtil;

public class DyProxy implements InvocationHandler {
	JdbcUtil jdbcUtil = new JdbcUtil();
	UserService UserService = new UserServiceImpl();

	public DyProxy() {

	}

	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		Connection conn = jdbcUtil.get();
		arg1.invoke(UserService, arg2);
		conn.commit();
		return null;
	}

}
