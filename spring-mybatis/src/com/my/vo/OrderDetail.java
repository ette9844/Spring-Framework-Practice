package com.my.vo;

public class OrderDetail {
	private int order_no;
	// private OrderInfo order;
	// private String order_prod_no;
	private Product product;
	private int order_quantity;
	
	// 생성자: 매개변수 없는
	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// 생성자: 멤버 필드 초기화
	public OrderDetail(int order_no, Product product, int order_quantity) {
		super();
		this.order_no = order_no;
		this.product = product;
		this.order_quantity = order_quantity;
	}
	
	// getter / setter
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
}
