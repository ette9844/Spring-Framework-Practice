package com.my.service;

import java.util.List;

import com.my.vo.OrderInfo;
import com.studybb.exception.NotFoundException;

public interface OrderService {
	/**
	 * 주문정보를 추가한다
	 * @param info
	 * @return String
	 */
	String add(OrderInfo info);
	
	/**
	 * 아이디에 해당하는 주문정보들을 반환
	 * @param id 아이디
	 * @return List<OrderInfo>
	 * @throws NotFoundException 
	 */
	List<OrderInfo> findById(String id) throws NotFoundException;
}
