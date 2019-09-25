package com.my.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.vo.OrderDetail;
import com.my.vo.OrderInfo;
import com.studybb.exception.AddException;
import com.studybb.exception.NotFoundException;

public class OrderDAOOracle implements OrderDAO {
	private SqlSessionFactory sqlSessionFactory;
	
	public OrderDAOOracle(){
		// 이 부분을 xml 파일이 대체 할 것
//		String resource = "mybatis-config.xml";
//		InputStream inputStream;
//		try {
//			inputStream = Resources.getResourceAsStream(resource);
//			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
	}
	
	// setter / getter 추가
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	/**
	 * order_info테이블에 주문기본정보 추가
	 * order_line테이블에 주문상세정보 추가
	 * @param orderInfo 주문기본정보와 주문상세정보들
	 * @throws AddException
	 */
	public void insert(OrderInfo orderInfo) throws AddException {
		SqlSession session = null;
		if(sqlSessionFactory != null) {
			// 설정xml을 보고 DB와 연결 (=Connection)
			session = sqlSessionFactory.openSession();
			
			// insert문 수행
			String id = orderInfo.getCustomer().getId();
			session.insert("com.my.vo.Order.insertOrderInfo", id);
			List<OrderDetail> details = orderInfo.getOrderDetails();
			for(OrderDetail detail : details) {
				session.insert("com.my.vo.Order.insertOrderDetail", detail);
			}
			
			// 커밋
			session.commit();
		} else {
			throw new AddException("추가실패");
		}
		session.close();
	}
	
	/**
	 * 아이디에 해당하는 주문정보들을 반환한다
	 * 최근 주문정보부터 저장된다. - 내림차순 정렬
	 * @param id 주문자 ID
	 * @return 
	 * @throws NotFoundException 해당 주문정보가 없다면 NotFoundException 발생
	 */
	public List<OrderInfo> selectById(String id) throws NotFoundException {
		SqlSession session = null;
		if(sqlSessionFactory != null) {
			// 설정xml을 보고 DB와 연결 (=Connection)
			session = sqlSessionFactory.openSession();
			
			// sql 실행
			List<OrderInfo> list = session.selectList("com.my.vo.Order.selectById", id);
			System.out.println("검색건수: " + list.size());
			
			if(list.size() == 0) {
				throw new NotFoundException("아이디에 해당하는 주문정보가 없습니다.");
			}
			session.close();
			return list;
		} else {	// null 인 경우
			throw new NotFoundException("추가실패");
		}
	}
	
	/**
	 * 날짜구간에 해당하는 주문정보들을 반환한다
	 * @param start 검색할 시작 날짜 (yy/MM/dd 포맷 사용)
	 * @param end	검색할 끝    날짜 (yy/MM/dd 포맷 사용)
	 * @return
	 * @throws NotFoundException
	 */
	public List<OrderInfo> selectByDate(String start, String end) throws NotFoundException {
		return null;
	}
	
	public List<OrderInfo> selectAll(){
		return null;
	}
}
