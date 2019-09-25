package control;

import java.io.IOException;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.BoardService;
import com.my.service.CustomerService;
import com.my.vo.Customer;
import com.my.vo.Post;
//@Controller
@RestController //@Controller + @ResponseBody
public class CustomerController {
    @Autowired
	private CustomerService service;
	//static private CustomerController controller = new CustomerController();
	
	@PostMapping("/dupchk")
	//@ResponseBody
//	public Map<String, Integer> dupchk(String id){
//		return service.dupchk(id);
//	}
	public ModelAndView dupchk(String id) {
		String str = service.dupchk(id);			
		ModelAndView mnv  = new ModelAndView();
		mnv.addObject("result",str);
		String path = "result.jsp";
		//mnv.setViewName("/result.jsp");
		mnv.setViewName(path);
		return mnv;
	}
	
	
//	public String dupchk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String id = request.getParameter("id");		
//
//		CustomerService service = new CustomerService();
//		String str = service.dupchk(id);
//
//		request.setAttribute("result", str);
//		String path = "/result.jsp";
//		return path;
//	}
	
	
	@PostMapping("/join")
	//@ResponseBody
	public Map<String, Integer> join(@ModelAttribute("c")Customer c, String buildingno,
							@RequestParam(required=false,defaultValue = "0")int age,@RequestParam("addr2") String addr) {
		Post post = new Post();
		post.setBuildingno(buildingno);
		c.setPost(post);
		c.setAddr(addr);
		return service.join(c);	
	}
		
//	@PostMapping("/join")
//	public ModelAndView join(@ModelAttribute("c")Customer c, String buildingno,
//							@RequestParam(required=false,defaultValue = "0")int age,@RequestParam("addr2") String addr) {
//		System.out.println("age="+age);
//		System.out.println(c.getId()+":"+ buildingno+":"+ c.getName());
//		Post post = new Post();
//		post.setBuildingno(buildingno);
//		c.setPost(post);
//		c.setAddr(addr);
//		String str = service.join(c);	
//		String path = "/result.jsp";
//		ModelAndView mnv = new ModelAndView();
//		mnv.addObject("result",str);
//		mnv.setViewName(path);
//		return mnv;
//	}
	
//	public String join(String id, String pwd,String name, String buildingno,String addr2) {
//		Customer c = new Customer();
//		c.setId(id);
//		c.setPwd(pwd);
//		c.setName(name);
//		Post post = new Post();
//		post.setBuildingno(buildingno);
//		c.setPost(post);
//		c.setAddr(addr2);
//		
////		String str = service.join(c);		
////		request.setAttribute("result", str);
//		String path = "/result.jsp";
//		return path;
//	}
	
//	public String join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String id = request.getParameter("id");
//		String pwd = request.getParameter("pwd");
//		String name = request.getParameter("name");
//		String buildingno = request.getParameter("buildingno");
//		String addr = request.getParameter("addr2");
//		Customer c = new Customer();
//		System.out.println(id + ":" + pwd + ":" + name + ":" + buildingno + ":" + addr);
//		c.setId(id);
//		c.setPwd(pwd);
//		c.setName(name);
//		Post post = new Post();
//		post.setBuildingno(buildingno);
//		c.setPost(post);
//		c.setAddr(addr);
//		
//		String str = service.join(c);		
//		request.setAttribute("result", str);
//		String path = "/result.jsp";
//		return path;
//	}

	@PostMapping("/login")
	//@ResponseBody //응답내용을 알리기위함  viewer역할 없애기위함
	/*1.반환값을 jackson라이브러리 활용하여 json문자열로 변환시킨다.*/
	/*2.응답형식을 application/json타입으로 설정한다.*/
	public Map<String, Object> login(String id, 
							  String pwd,
							  HttpSession session) {
		//HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		Map<String,Object> map = service.login(id, pwd);
		if((Integer)map.get("status") == 1) {
			session.setAttribute("loginInfo", id);
		}
		return map;
	}
	
//	@PostMapping("/login")
//	@ResponseBody
	//string타입 반환
	//반환값을 응답
//	public String login(String id, String pwd, HttpSession session) {
//		String str = service.login(id, pwd);
//		JSONParser parser = new JSONParser();
//		try {
//			Object obj = parser.parse(str);
//			JSONObject jsonObj = (JSONObject)obj;
//			if((Long)jsonObj.get("status") == 1) {//로그인 성공!
//				session.setAttribute("loginInfo", id);
//			}
//		}catch (ParseException e) {
//			e.printStackTrace();
//		}
//		//request.setAttribute("result", str);
//		//String path = "/result.jsp";
//		//return path;
//		
//		return str;
//	}
	
		
		
//	@RequestMapping("/login")
//	public ModelAndView login(String id, 
//							  String pwd,
//							  HttpSession session) {
//		//HttpSession session = request.getSession();
//		session.removeAttribute("loginInfo");
//		String str = service.login(id, pwd);
//		JSONParser parser = new JSONParser();
//		try {
//			Object obj = parser.parse(str);
//			JSONObject jsonObj = (JSONObject)obj;
//			if((Long)jsonObj.get("status") == 1) {//로그인 성공!
//				session.setAttribute("loginInfo", id);
//			}
//		}catch (ParseException e) {
//			e.printStackTrace();
//		}
//		//request.setAttribute("result", str);
//		//String path = "/result.jsp";
//		//return path;
//		ModelAndView mnv  = new ModelAndView();
//		mnv.addObject("result",str);
//		mnv.setViewName("/result.jsp");
//		return mnv;
//	}
	
	
	
	
//	public String login(String id, String pwd,HttpServletRequest request){
//		HttpSession session = request.getSession();
//		session.removeAttribute("loginInfo");
//		String str = service.login(id, pwd);
//		/*--로그인성공시 HttpSession객체의 속성으로 추가 --*/
//		JSONParser parser = new JSONParser();
//		try {
//			Object obj = parser.parse(str);
//			JSONObject jsonObj = (JSONObject)obj;
//			if((Long)jsonObj.get("status") == 1) {//로그인 성공!
//				session.setAttribute("loginInfo", id);
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		/*-------*/
//		request.setAttribute("result", str);
//		String path = "/result.jsp";
//		return path;
//	}
	
	
	
	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpSession session) {
		session.invalidate();	
		
		
	}
	
	
//	@RequestMapping("/logout")
//	public ModelAndView logout(HttpSession session) {
//		session.invalidate();	
//		
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("status",9999);
//		ModelAndView mnv  = new ModelAndView();
//		mnv.addObject("result",jsonObj.toString());
//		mnv.setViewName("/result.jsp");
//		return mnv;
//	}
//	public String logout(HttpServletRequest request,HttpSession session){
//		//HttpSession session = request.getSession();
//		session.invalidate();		
//		//???
//		//return "-1";
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("status",-1);
//		request.setAttribute("result", jsonObj.toString());
//		return "/result.jsp"; //SpringMVC에서는 뷰가 반드시 필요
//	}
}
