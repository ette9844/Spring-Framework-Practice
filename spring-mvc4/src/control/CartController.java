package control;

import java.io.IOException;
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
import org.springframework.web.servlet.ModelAndView;

import com.my.service.ProductService;
import com.my.vo.Product;
@Controller
public class CartController {
	@RequestMapping("/addcart")
	   public ModelAndView addCart(Product p, //커맨드객체
	                        Integer quantity, 
	                        HttpSession session){
	      System.out.println("p의 정보" + p.getProd_no()+" "+p.getProd_name()+" "+ p.getProd_price()+" "+quantity);
	      Map<Product, Integer> cart = (Map)session.getAttribute("cart");
	      System.out.println(cart);
	      if(cart==null) {
	         cart = new HashMap<>();
	         session.setAttribute("cart", cart);
	      };
	      
	      Integer oldQuantity = cart.get(p);
	      if(oldQuantity!=null) {
	         quantity += oldQuantity;
	      }
	      
	      cart.put(p, quantity);

	      JSONObject jsonObj = new JSONObject();
	   
	      
	      session.setAttribute("cart", cart);
	      ModelAndView mnv = new ModelAndView();
	      JSONObject jsonObject = new JSONObject();
	      jsonObject.put("status", 1);
	      mnv.addObject("result", jsonObject.toString());
	      mnv.setViewName("/result.jsp");
	      return mnv;
	   }
	
	
	@RequestMapping("/cartlist")
	public String cartList() {
		return "/cartlist.jsp";
	}	
}
