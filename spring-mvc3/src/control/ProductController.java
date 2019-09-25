package control;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.exception.NotFoundException;
import com.my.service.ProductService;
import com.my.vo.Product;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;
	
	@RequestMapping("/productlist")
	public ModelAndView list(){
		ModelAndView mnv = new ModelAndView();
		try {
			List<Product> list = service.productList();
			mnv.addObject("products", list);
			mnv.addObject("status", 1);
		} catch(NotFoundException e) {
			mnv.addObject("status", -1);
		}
		mnv.setViewName("/productlist.jsp");
		return mnv;
	}
	
	@GetMapping("/productdetail")
	public ModelAndView productDetail(String prod_no) {
		ModelAndView mnv = new ModelAndView();
		String str = service.productDetail(prod_no);
       	
		mnv.addObject("result", str);
		mnv.setViewName("/productdetailresult.jsp");
		return mnv;
	}
}
