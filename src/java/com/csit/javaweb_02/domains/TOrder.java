package com.csit.javaweb_02.domains;

import java.util.Date;
/**
 * 订单实体类
 * @author Administrator
 *
 */
public class TOrder {
	private Integer orderId;
	private String orderName;
	private Date createdDate;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
