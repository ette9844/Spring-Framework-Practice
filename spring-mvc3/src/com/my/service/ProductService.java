package com.my.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.ProductDAO;
import com.my.exception.NotFoundException;
import com.my.vo.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDAO dao;
	
	public List<Product> productList() throws NotFoundException {
		return dao.selectAll();
	}
	
	public String productDetail(String prod_no) {
		
		Product p = null;
		JSONObject jsonObj = new JSONObject();
		
		try {
			p = dao.selectByNo(prod_no);
			
			jsonObj.put("status", 1);
			JSONObject productJsonObj = new JSONObject();
			productJsonObj.put("prod_no", p.getProd_no());
			productJsonObj.put("prod_name", p.getProd_name());
			productJsonObj.put("prod_detail", p.getProd_detail());
			productJsonObj.put("prod_price", p.getProd_price());
			productJsonObj.put("prod_cate_name", p.getCategory().getCate_name());
			jsonObj.put("product", productJsonObj);
			
		} catch (NotFoundException e) {
			e.printStackTrace();
			jsonObj.put("status", -1);
		}
		
		//System.out.println(jsonObj.toString());
		// json 형태의 문자열 return
		return jsonObj.toString();
	}
}
