package control;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

@WebServlet("/up")
public class UpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getParameter("t"); 
		// getParameter로는 실제로 업로드된 파일을 가져올 수 없다.
		
//		InputStream is = request.getInputStream();
//		// 한글자씩 읽어 한글 깨짐을 방지
//		InputStreamReader isr = new InputStreamReader(is);
//		int readValue = -1;
//		while((readValue = isr.read()) != -1){
//			System.out.println((char)readValue);
//		}

		String saveDirectory = "files";	// WebContent 하위 폴더명
		String realPath = getServletContext().getRealPath(saveDirectory);	// servlet의 실제 tomcat 구동 경로
		String encoding = "UTF-8";
		int maxPostSize = 100 * 1024;	// 10 KB
		FileRenamePolicy policy = //new DefaultFileRenamePolicy();
				new com.my.util.MyFileRenamePolicy();
		
		MultipartRequest mr = new MultipartRequest(request, realPath, maxPostSize, encoding, policy);
		String t = mr.getParameter("t");
	}

}
