package control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.vo.Product;

@Controller
public class CartController {
	
	@PostMapping("/addcart")
	public ModelAndView addCart(Product p, 
								int quantity, 
								HttpSession session) {
		Map<Product, Integer> map = (Map<Product, Integer>) session.getAttribute("cart");
		if(map != null) {
			Integer q = map.get(p);
			// 조회
			if(q != null) {
				// 기존 수량이 있을 경우 누적
				quantity += q;
			}
			map.put(p, quantity);
		} else {
			map = new HashMap<Product, Integer>();
			map.put(p, quantity);
		}
		System.out.println(map);
		session.removeAttribute("cart");	// 삭제
		session.setAttribute("cart", map);	// 갱신
		return null;
	}
	
}
