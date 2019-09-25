package com.my.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.OrderDAO;
import com.my.exception.AddException;
import com.my.vo.OrderInfo;
import com.my.exception.NotFoundException;

@Service
public class OrderService {
	@Autowired
	private OrderDAO dao;

	public String add(OrderInfo info) {
		int status = -1;
		try {
			dao.insert(info);
			status = 1;
		} catch (AddException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		String str = jsonObj.toString();
		return str;
	}

	public List<OrderInfo> findById(String id) throws NotFoundException {
		return dao.selectById(id);
	}
}
