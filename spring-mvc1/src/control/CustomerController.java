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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
//	public String login(String id, String pwd, HttpServletRequest request) {
//
//	    HttpSession session = request.getSession();
//	    session.removeAttribute("loginInfo"); // 초기화
//	      
//	    String str = service.login(id, pwd);
//	    JSONParser parser = new JSONParser();
//	    try {
//	       	Object obj = parser.parse(str);
//	       	JSONObject jsonObj = (JSONObject) obj;
//	        if ((Long) jsonObj.get("status") == 1) {// 로그인 성공
//	        	session.setAttribute("loginInfo", id);
//	        }
//	    } catch (ParseException e) {
//	    	e.printStackTrace();
//	    }
//	    request.setAttribute("result", str); // request의 attribute에 저장 -> forward시 필요한 공유객체 생성
//
//	    String path = "/result.jsp";
//	    return path;
//	}
	
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
//	public String logout(HttpServletRequest request, HttpSession session){
//		session.invalidate();
//		
//		// 원래 viewer가 없던 logout
//		// Spring MVC 구조에서는 return 받은 결과를 viewer로 사용하기 때문에 jsp 경로를 return 해줘야한다
//		// 나중에는 responseBody를 사용하여 json 형태로 return 할 수 있다.
//		// return "-1";
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("status", 9999);	// 의미없는 값
//		request.setAttribute("result", jsonObj.toString());
//		return "/result.jsp";
//	}
	
	@RequestMapping("/join")
	public ModelAndView join(HttpServletRequest request){

		// 요청 데이터 수신
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String addr2 = request.getParameter("addr2");
		String buildingno = request.getParameter("buildingno");

		// Customer 객체 생성
		Customer c = new Customer();
		c.setId(id);
		c.setPwd(pwd);
		c.setName(name);
		c.setAddr(addr2);

		Post p = new Post();
		p.setBuildingno(buildingno);
		c.setPost(p);

		String str = service.join(c);

		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);
		mnv.setViewName("/result.jsp");
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
