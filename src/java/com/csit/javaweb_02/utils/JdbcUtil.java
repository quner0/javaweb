package com.csit.javaweb_02.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.csit.javaweb_02.annotation.Componet;
@Componet
public class JdbcUtil {
	// ThreadLocalµÄÊµÏÖ
	ConnectLocal connectLocal = new ConnectLocal();
	Datasource datasource;
	public Connection conn = null;

	public Connection get() {
		try {
			conn = connectLocal.get();
			if (conn == null) {
				try {
					Class.forName(datasource.getDriverClass());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				conn = DriverManager.getConnection(datasource.getJdbcurl(), datasource.getUser(),
						datasource.getPassword());
				conn.setAutoCommit(false);
				connectLocal.put(conn);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

	public void close() {
		this.connectLocal.clear();
	}

	@Override
	public String toString() {
		return "JdbcUtil [connectLocal=" + connectLocal + ", datasource=" + datasource + ", conn=" + conn + "]";
	}

}
