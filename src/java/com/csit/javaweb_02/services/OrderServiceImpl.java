package com.csit.javaweb_02.services;

import java.sql.SQLException;
import java.util.Date;

import com.csit.javaweb_02.annotation.Inject;
import com.csit.javaweb_02.annotation.Service;
import com.csit.javaweb_02.annotation.Transation;
import com.csit.javaweb_02.daos.OrderDao;
import com.csit.javaweb_02.daos.OrderDaoImpl;
import com.csit.javaweb_02.domains.TOrder;

/**
 * 业务处理
 * 
 * @author Administrator
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Inject
	OrderDaoImpl orderDao;
    @Transation
	@Override
	public void insert(TOrder order) throws SQLException {
		orderDao.insert(order);
		TOrder order2 = new TOrder();
		order2.setOrderName("02020020202");
		order2.setCreatedDate(new Date());
		orderDao.update(order2);
	}

}
