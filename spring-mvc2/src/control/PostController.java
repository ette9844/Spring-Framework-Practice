package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.PostService;

@Controller
public class PostController {
	@Autowired
	private PostService service;
	static private PostController controller = new PostController();

	@PostMapping("/searchzip")
	public ModelAndView searchZip(String doro) {
		String str = service.search(doro);
	
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);
		mnv.setViewName("/result.jsp");
		return mnv;
	}
}
