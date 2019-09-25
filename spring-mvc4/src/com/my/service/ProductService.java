package com.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dao.ProductDAO;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.Customer;
import com.my.vo.Product;
@Service
public class ProductService {
	@Autowired
	private ProductDAO dao;
	public ProductService() {
		dao = new ProductDAO();
	}
	public List<Product> productList() {
			List<Product> list = null;
			try {
				list = dao.selectAll();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	public String productDetail(String prod_no) {
		Map<String,Object>map = new HashMap<>();
		try {
			Product p = dao.selectByNo(prod_no);
			map.put("status",1);
			map.put("prod_no",p.getProd_no());
			map.put("prod_name",p.getProd_name());
			map.put("prod_price",p.getProd_price());
			map.put("prod_detail",p.getProd_detail());
			map.put("cate_name",p.getCategory().getCate_name());
		}catch(NotFoundException e) {
			map.put("status",-1);		
			e.printStackTrace();
			
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(map);
		}catch(JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
		
//		JSONObject jsonObj = new JSONObject();
//		try { 
//			Product p = dao.selectByNo(prod_no);
//			int status = 1;
//			jsonObj.put("status", status);
//			jsonObj.put("prod_no",p.getProd_no());
//			jsonObj.put("prod_name", p.getProd_name());
//			jsonObj.put("prod_price",p.getProd_price());
//			jsonObj.put("prod_detail", p.getProd_detail());
//			jsonObj.put("cate_name",p.getCategory().getCate_name());
//		} catch (NotFoundException e) {
//			int status = -1;
//			jsonObj.put("status", status);
//			e.printStackTrace();
//		}
//		String str = jsonObj.toString();
//		
//		System.out.println(str);
//		return str;
	}

}
