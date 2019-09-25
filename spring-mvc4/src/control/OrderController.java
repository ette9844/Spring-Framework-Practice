package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.OrderService;
import com.my.service.ProductService;
import com.my.vo.Customer;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
import com.my.vo.Product;
@Controller
public class OrderController {
	@Autowired
	private OrderService service;
	@PostMapping("/addorder")
	public ModelAndView addOrder(HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		//로그인 안된 사용자는 로그인 작업하기
		System.out.println(session.getAttribute("loginInfo"));
		if(session.getAttribute("loginInfo") == null) {
			JSONObject obj = new JSONObject();
			obj.put("status", 0);//로그인이 안된 경우
			mnv.addObject("result", obj.toString());
		}else {
			Map<Product,Integer> cart = (Map)session.getAttribute("cart");
			
			OrderInfo orderinfo = new OrderInfo();//cart내용으로 채우기
			Customer customer = new Customer();
			
			customer.setId((String)session.getAttribute("loginInfo"));
			orderinfo.setCustomer(customer);
			
			List<OrderLine> list = new ArrayList<>();
			OrderLine orderlines = new OrderLine();
			for(Product p : cart.keySet()) {
				orderlines = new OrderLine();
				orderlines.setProduct(p);;
				orderlines.setOrder_quantity(cart.get(p));
				list.add(orderlines);
			}
			
			orderinfo.setOrderLines(list);
			
			
			String result = service.add(orderinfo);
			//cart비우기
			session.removeAttribute("cart");
			 mnv.addObject("result", result);
		}
		mnv.setViewName("/result.jsp");
		return mnv;
	}
	

	   
}
