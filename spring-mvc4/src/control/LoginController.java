package control;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@RequestMapping("/a") //get,post둘다 원할시 //String타입이 가능
	//@GetMapping("/a")
	//@PostMapping("/a")
//	public void a() {
//		System.out.println("LoginController의 a()호출됨");
//	}
	
	// http://localhost:8080/spring-mvc1/a?id=aaa&pwd=bbb요청
	public String a(String id, @RequestParam("pwd") String p) {
		System.out.println("LoginController의 a()호출됨");
		System.out.println("id=" + id + "p=" + p);
		return "/result.jsp"; //View이름ㄴ
	}
	
//	public void a() { //view이름이 "/WEB-INF/a.jsp";와 같은 효과를 갖는 view
//		System.out.println("LoginController의 a()호출됨");
		
		/*
		 *<mvc:jsp prefix=" /" suffix=".jsp" />
		 *return "/a.jsp" 
		 */
	
}
