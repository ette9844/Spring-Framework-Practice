package com.my.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.PostDAO;
import com.my.exception.NotFoundException;
import com.my.vo.Post;

@Service
public class PostService {
	@Autowired
	private PostDAO dao;

	public String search(String doro) {
		List<Map<String, String>> postList = new ArrayList<Map<String, String>>();
		
		try {
			postList = dao.selectByDoro(doro);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		JSONArray jsonArr = new JSONArray();
		for(Map<String, String> postMap : postList)
		{
			// 도로명 주소가 존재할 때
			JSONObject json= new JSONObject();
			json.putAll(postMap);
			jsonArr.add(json);
		}
		return jsonArr.toString();
	}
}
