package com.csit.javaweb_02.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class ConnectLocal {
	HashMap<Thread, Connection> map = new HashMap<Thread, Connection>();

	public void put(Connection connection) {
		map.put(Thread.currentThread(), connection);
	}

	public Connection get() {
		return map.get(Thread.currentThread());
	}

	public void clear() {
		Connection conn = map.get(Thread.currentThread());
		try {
			if(conn!=null){
			conn.close();
			map.remove(Thread.currentThread());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
