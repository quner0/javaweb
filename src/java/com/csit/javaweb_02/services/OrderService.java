package com.csit.javaweb_02.services;

import java.sql.SQLException;

import com.csit.javaweb_02.domains.TOrder;

/**
 * ¶©µ¥½Ó¿Ú
 * @author Administrator
 *
 */
public interface OrderService {
public void insert(TOrder order) throws SQLException ;
}
