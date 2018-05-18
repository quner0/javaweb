package com.csit.javaweb_02.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

import com.csit.javaweb_02.annotation.Transation;
import com.csit.javaweb_02.tests.UserServiceImpl;
import com.csit.javaweb_02.web.ContextLoaderListener;

public class TransationProxy implements InvocationHandler {
	Object ob;
	Connection conn = ContextLoaderListener.jdbcUtil.get();

	public Object bind(Object ob) {
		this.ob = ob;
		return Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class<?>[] { (Class<?>) ob.getClass().getGenericInterfaces()[0] }, this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Method tmethod = ob.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
		Transation transation = tmethod.getAnnotation(Transation.class);
		if (transation != null) {
			try {
				// System.out.println("进入代理方法");
				method.invoke(ob, args);
				conn.commit();
				// System.out.println("结束代理方法");
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			} finally {
				conn.close();
			}
		} else {
			method.invoke(ob, args);
		}
		return null;
	}
}
