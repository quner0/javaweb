package com.csit.javaweb_02.utils;

public class Datasource {
	private String jdbcurl;
	private String driverClass;
	private String user;
	private String password;
	public String getJdbcurl() {
		return jdbcurl;
	}
	public void setJdbcurl(String jdbcurl) {
		this.jdbcurl = jdbcurl;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Datasource [jdbcurl=" + jdbcurl + ", driverClass=" + driverClass + ", user=" + user + ", password="
				+ password + "]";
	}
	
	
}
