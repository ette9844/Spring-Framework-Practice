package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.CustomerService;
import com.my.vo.Customer;
import com.my.vo.Post;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@RequestMapping("/login")
	public ModelAndView login(String id
							, String pwd
							, HttpSession session) {
		session.removeAttribute("loginInfo"); // 초기화
		
		String str = service.login(id, pwd);
		JSONParser parser = new JSONParser();
		try {
	       	Object obj = parser.parse(str);
	       	JSONObject jsonObj = (JSONObject) obj;
	        if ((Long) jsonObj.get("status") == 1) {// 로그인 성공
	        	session.setAttribute("loginInfo", id);
	        }
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);		// request.setAttribute 와 동일 효과
		mnv.setViewName("/result.jsp");
		return mnv;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpSession session){
		session.invalidate();

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", 9999);	// 의미없는 값
		//request.setAttribute("result", jsonObj.toString());
		//return "/result.jsp";
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", jsonObj.toString());
		mnv.setViewName("/result.jsp");
		return mnv;
	}
	
//	매개변수를 통해 요청 값을 받아오는 방식
//	@RequestMapping("/join")
//	public ModelAndView join(String id
//						   , String pwd
//						   , String name
//						   , String addr2
//						   , String buildingno) {
//		// Customer 객체 생성
//		Customer c = new Customer();
//		c.setId(id);
//		c.setPwd(pwd);
//		c.setName(name);
//		c.setAddr(addr2);
//
//		Post p = new Post();
//		p.setBuildingno(buildingno);
//		c.setPost(p);
	
	// Customer 객체의 setter 메서드를 통해 자동 전달되는 방식 ( 매개변수가 확 줄어듬 ) 
	// Customer c: command 객체
	@RequestMapping("/join")
	public ModelAndView join(@ModelAttribute("c") Customer c, 
			String buildingno, 
			@RequestParam("addr2") String addr) {
//		System.out.println(c.getId() + " : " 
//				+ c.getPwd() + " : " 
//				+ c.getName());
		
		Post post = new Post();
		post.setBuildingno(buildingno);
		c.setPost(post);
		c.setAddr(addr);
		
		String str = service.join(c);
		String path = "/result.jsp";

		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);
		mnv.setViewName(path);
		return mnv;
	}
	
	@PostMapping("/dupchk")
	public ModelAndView dupchk(HttpServletRequest request){
		// 요청 데이터 수신
		String id = request.getParameter("id");
		String str = service.dupchk(id);

		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);
		mnv.setViewName("/result.jsp");
		return mnv;
	}
}
