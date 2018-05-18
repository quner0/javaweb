package com.csit.javaweb_02.tests;

import java.lang.reflect.Proxy;

import com.csit.javaweb_02.utils.TransationProxy;

public class ProxyTest {
public static void main(String[] args) {
	UserServiceImpl userServiceImpl = new UserServiceImpl();
	UserServiceImpl userServiceImpl2 = (UserServiceImpl) new TransationProxy().bind(userServiceImpl); 
	userServiceImpl2.test();
}
}
