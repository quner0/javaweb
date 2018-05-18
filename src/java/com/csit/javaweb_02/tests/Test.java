package com.csit.javaweb_02.tests;

import java.lang.reflect.Proxy;

public class Test {
public static void main(String[] args) {
	UserService userService = (UserService) Proxy.newProxyInstance(DyProxy.class.getClassLoader(), new Class<?>[]{UserService.class}, new DyProxy());
	userService.test();
}
}
