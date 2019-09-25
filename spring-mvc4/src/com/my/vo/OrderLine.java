package com.my.vo;
public class OrderLine {
	private int order_no;
	//private OrderInfo orderInfo;
	//private String order_prod_no;
	private int order_quantity;
	private Product product;
	public OrderLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderLine(int order_no,int order_quantity, Product product) {
		super();
		this.order_no = order_no;
		this.order_quantity = order_quantity;
		this.product = product;
	}
	public int getOrder_no() {
		return order_quantity;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
