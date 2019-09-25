package com.my.vo;

import java.sql.Timestamp;
import java.util.List;

public class OrderInfo {
	private int order_no;
	// private String order_id;
	private Customer customer;
	private Timestamp order_time;
	private List<OrderDetail> orderDetails;
	
	// 생성자: 매개변수 없는
	public OrderInfo() {
		super();
	}
	
	// 생성자: 멤버 필드 초기화
	public OrderInfo(int order_no, Customer customer, Timestamp order_time, List<OrderDetail> orderDetails) {
		super();
		this.order_no = order_no;
		this.customer = customer;
		this.order_time = order_time;
		this.orderDetails = orderDetails;
	}
	
	// getter / setter
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
