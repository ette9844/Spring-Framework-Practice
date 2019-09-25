package com.my.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.OrderDAO;
import com.my.vo.OrderInfo;
import com.studybb.exception.AddException;
import com.studybb.exception.NotFoundException;

/**
 * 직영점 용 주문 서비스
 * @author Administrator
 *
 */
@Service("orderService")
public class OrderServiceDirect implements OrderService {

	@Autowired
	private OrderDAO dao;	// orderDAO interface와 has-a 관계
	
	// interface를 통해 dao 개발자 service 개발자의 병행 개발이 가능해짐
	
	@Override
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

	@Override		// 부모 interface에서 throws 한 exception만 throws 가능
	public List<OrderInfo> findById(String id) throws NotFoundException {
		return dao.selectById(id);
	}

}
