package com.my.dao;

import java.util.List;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;

public interface OrderDAO {
	/**
	 * order_info테이블에 주문기본정보 추가
	 * order_line테이블에 주문상세정보 추가
	 * @param orderInfo 주문기본정보와 주문상세정보들
	 * @throws AddExeption
	 */
	public void insert(OrderInfo orderInfo) throws AddException;
	/**
	 * 아이디에 해당하는 주문정보들을 반환한다
	 * 최근 주문정보부터 저장된다.-내림차순정렬
	 * @param id 주문자ID
	 * @return 
	 * @throws NotFoundException 해당주문정보가 없다면 NotFoundException이 발생한다.
	 */
	
	public List<OrderInfo> selectById(String id) throws NotFoundException;
	/**
	 * 날짜구간에 해당하는 주문정보들을 반환한다
	 * @param strat 검색할 시작날짜(yy/MM/dd포맷사용)
	 * @param end   검색할 끝날짜(yy/MM/dd포맷사용)
	 * @return 
	 * @throws NotFoundException 해당주문정보가 없다면 NotFoundException이 발생한다.
	 */
	public List<OrderInfo> selectByDate(String start, String end) throws NotFoundException;
	public List<OrderInfo> selectAll();
}
