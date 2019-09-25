package com.my.vo;

public class Category {
	private int cate_no;
	private int cate_parent_no;
	private String cate_name;
	// c1 char(1) --> private char c1;	 (X)
	// 			  --> private String c1; (O)

	// 생성자: 매개변수 없는
	public Category() {
		super();
	}

	// 생성자: 멤버 필드 초기화
	public Category(int cate_no, int cate_parent_no, String cate_name) {
		super();
		this.cate_no = cate_no;
		this.cate_parent_no = cate_parent_no;
		this.cate_name = cate_name;
	}

	// getter / setter
	public int getCate_no() {
		return cate_no;
	}

	public void setCate_no(int cate_no) {
		this.cate_no = cate_no;
	}

	public int getCate_parent_no() {
		return cate_parent_no;
	}

	public void setCate_parent_no(int cate_parent_no) {
		this.cate_parent_no = cate_parent_no;
	}

	public String getCate_name() {
		return cate_name;
	}

	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
}
