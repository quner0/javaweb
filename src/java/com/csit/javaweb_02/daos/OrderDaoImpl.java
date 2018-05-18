package com.csit.javaweb_02.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.csit.javaweb_02.annotation.Inject;
import com.csit.javaweb_02.annotation.Repository;
import com.csit.javaweb_02.domains.TOrder;
import com.csit.javaweb_02.utils.DateUtil;
/**
 * Êý¾Ý¿â½»»¥
 * @author Administrator
 *
 */
import com.csit.javaweb_02.utils.JdbcUtil;

@Repository
public class OrderDaoImpl implements OrderDao {
	@Inject
	JdbcUtil JdbcUtil;

	@Override
	public void insert(TOrder order) throws SQLException {
		Connection conn = JdbcUtil.get();
		StringBuffer sbf = new StringBuffer("insert into torder(ordername,datecreated)values(");
		sbf.append("'");
		sbf.append(order.getOrderName());
		sbf.append("'");
		sbf.append(",");
		sbf.append("'");
		sbf.append(DateUtil.getString(order.getCreatedDate()));
		sbf.append("'");
		sbf.append(")");
		PreparedStatement ps = conn.prepareStatement(sbf.toString());
		ps.execute();
	}

	public void update(TOrder order) throws SQLException {
		Connection conn = JdbcUtil.get();
		StringBuffer sbf = new StringBuffer("insert into torder(ordername,datecreated)values(");
		sbf.append("'");
		sbf.append(order.getOrderName());
		sbf.append("'");
		sbf.append(",");
		sbf.append("'");
		sbf.append(DateUtil.getString(order.getCreatedDate()));
		sbf.append("'");
		sbf.append(")");
		PreparedStatement ps = conn.prepareStatement(sbf.toString());
		ps.execute();
	}

}
