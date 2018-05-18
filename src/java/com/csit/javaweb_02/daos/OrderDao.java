package com.csit.javaweb_02.daos;

import java.sql.SQLException;

import com.csit.javaweb_02.domains.TOrder;

public interface OrderDao {
public void insert(TOrder order) throws SQLException ;
}
