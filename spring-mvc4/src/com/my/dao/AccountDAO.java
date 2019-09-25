package com.my.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	public String account() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("no", "101");
		map.put("amount", 10);
		int rowCnt1 = sqlSession.update("com.my.vo.Account.withdraw", map);
		if(rowCnt1 == 0) {
			// UncheckedException 발생
			throw new RuntimeException("출금오류");
		}
		
		map = new HashMap<>();
		map.put("no", "999");
		map.put("amount", 10);
		int rowCnt2 = sqlSession.update("com.my.vo.Account.deposit", map);
		if(rowCnt2 == 0) {
			// UncheckedException 발생
			throw new RuntimeException("입금오류");			
		}
		return "계좌이체";
	}
}
