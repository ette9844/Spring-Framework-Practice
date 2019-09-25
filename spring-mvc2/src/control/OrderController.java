package control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.exception.NotFoundException;
import com.my.service.OrderService;
import com.my.vo.Customer;
import com.my.vo.OrderDetail;
import com.my.vo.OrderInfo;
import com.my.vo.Product;

@Controller
public class OrderController {
	@Autowired
	private OrderService service;
	
	@PostMapping("/addorder")
	public ModelAndView addOrder(HttpSession session) {
		ModelAndView mnv = new ModelAndView();
		
		// 로그인 안된 사용자는 로그인 작업하기
		if(session.getAttribute("loginInfo") == null) {
			JSONObject obj = new JSONObject();
			obj.put("status", 0);	// 로그인 안된 경우
			mnv.addObject("result", obj.toString());
		} else {
			Map<Product, Integer> cart = (Map)session.getAttribute("cart");
			OrderInfo orderInfo = new OrderInfo(); // cart 내용으로 채우기
			
			// 현재 세션의 사용자 id 채우기
			Customer c = new Customer();
			c.setId((String)session.getAttribute("loginInfo"));
			orderInfo.setCustomer(c);
			
			// cart 내용 채우기
			List<OrderDetail> list = new ArrayList<OrderDetail>();
			Set<Product> keyset = cart.keySet();
			OrderDetail orders;
			for(Product product : keyset) {
				orders = new OrderDetail();
				orders.setOrder_quantity(cart.get(product));
				orders.setProduct(product);
				list.add(orders);
				System.out.println("item: " + product.getProd_name() 
					+ " " + cart.get(product) +" 개");
			}
			orderInfo.setOrderDetails(list);
			
			String result = service.add(orderInfo);
			// cart 비우기
			session.removeAttribute("cart");
			mnv.addObject("result", result);
		}
		mnv.setViewName("/result.jsp");
		return mnv;
	}
	
	@RequestMapping("/orderlist")
	public ModelAndView orderList(HttpSession session) {
		String id = (String) session.getAttribute("loginInfo");
		ModelAndView mnv = new ModelAndView();
		int status = -1;
		List<OrderInfo> orders = null;
		try {
			orders = service.findById(id);
			status = 1;
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		mnv.addObject("status", status);
		mnv.addObject("orders", orders);
		
		mnv.setViewName("/orderlist.jsp");
		return mnv;
	}
}
