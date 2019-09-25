package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.Customer;
import com.my.vo.Post;

@Repository
public class CustomerDAO {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	private String url = "jdbc:oracle:thin:@192.168.30.150:1521:xe";
	private String user = "hyejin";
	private String pw = "wow130";
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;

	// String[] selectById(String id) {} -> 분석 설계가 완벽하지 않을 경우는 이런식으로 리턴하기도 함.
	// Customer selectById(String id) {} -> 분석 설계가 완벽할 때만 빛을 발함. 계속 변경될 가능성이 있을 경우에는
	// 번거롭다.

	// Customer: DTO 클래스, VO 클래스: 자료 전달용 객체
	public Customer selectById(String id) throws NotFoundException {

		SqlSession sqlSession = sqlSessionFactory.openSession();
		Customer c = sqlSession.selectOne("com.my.vo.Customer.selectById", id);
		
		if(c == null) {
			throw new NotFoundException("아이디가 존재하지 않습니다.");
		}
		
		sqlSession.close();
		return c;
	}

	public Customer insertCustomer(Customer c) throws AddException {
		try {
			// 1) JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2) DB 연결
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("connection 성공");

			// 3) SQL 구문 송신
			String selectSQL = "INSERT INTO customer " + "VALUES(\'" + c.getId() + "\', \'" + c.getPwd() + "\', \'"
					+ c.getName() + "\', \'" + c.getAddr() + "\', \'" + c.getPost().getBuildingno() + "\')";
			pstmt = conn.prepareStatement(selectSQL);

			// 4) SQL 결과 수신
			rs = pstmt.executeQuery(selectSQL);
			System.out.println(rs);

			// 5) 결과 확인
			if (rs.next()) {
				// 가입 성공
				return c;
			}
			// 가입 실패
			throw new AddException("가입에 실패했습니다.");
		} catch (Exception e) {
			// 사용자 정의 Exception 강제 발생
			throw new AddException(e.getMessage());
		} finally {
			closeConnection();
		}
	}

	public void closeConnection() {
		// 6) 연결 닫기
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
