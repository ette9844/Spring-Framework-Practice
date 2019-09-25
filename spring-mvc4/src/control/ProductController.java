package control;

import java.io.IOException;
import java.util.List;

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
public class ProductController {
    @Autowired
	private ProductService service;
	static private ProductController controller = new ProductController();
	
	@RequestMapping("/productlist")
	public ModelAndView list() {
		List<Product> list = service.productList();			
		ModelAndView mnv  = new ModelAndView();
		mnv.addObject("list",list);
		String path = "productlist.jsp";
		mnv.setViewName(path);
		return mnv;
	}
	
	@GetMapping("/productdetail")
	public ModelAndView productdetail(String prod_no) {
		String str = service.productDetail(prod_no);
		ModelAndView mnv  = new ModelAndView();
		mnv.addObject("result",str);
		String path = "productdetailresult.jsp";
		mnv.setViewName(path);
		return mnv;	
	}
}
