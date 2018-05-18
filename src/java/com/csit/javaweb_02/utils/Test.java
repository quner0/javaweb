package com.csit.javaweb_02.utils;

public class Test {
	public static void main(String[] args) {
		try {
			Class.forName("com.csit.javaweb_02.utils.Test2");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
