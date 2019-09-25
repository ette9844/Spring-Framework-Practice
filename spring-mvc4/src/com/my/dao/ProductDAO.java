package com.my.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.vo.Customer;
import com.my.vo.Post;
import com.my.vo.Product;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;

@Repository
public class ProductDAO {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	public List<Product> selectAll() throws NotFoundException{
		List<Product> list;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList("com.my.vo.Product.selectAll");
		sqlSession.close();
		if(list==null) {
			throw new NotFoundException("상품목록이 없습니다.");
		}
		return list;
	}
	public Product selectByNo(String prod_no) throws NotFoundException{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Product p = sqlSession.selectOne("com.my.vo.Product.selectByNo", prod_no);
		System.out.println();
		if(p==null) {
			throw new NotFoundException("상품이 존재하지 않습니다.");
		}
		sqlSession.close();
		return p;
	}
}