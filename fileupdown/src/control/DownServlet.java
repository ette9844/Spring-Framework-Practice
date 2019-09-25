package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/down")
public class DownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) 요청 전달 데이터 fileName 얻기
		String realPath = getServletContext().getRealPath("files");
		String fileName = request.getParameter("fileName");
		
		// 2) files 경로에 있는 fileName에 해당하는 파일 내용 읽어오기 -- FileInputStream
		File file = new File(realPath, fileName);
		FileInputStream fis = new FileInputStream(file);
		
		// 다운로드를 위해 설정해야 할 헤더 옵션
		response.setHeader("Content-Type", "application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+fileName+";");
		OutputStream os = response.getOutputStream();
		
		int readValue = -1;
		while((readValue = fis.read()) != -1) {
			System.out.print((char)readValue);
			os.write(readValue);
		}
		os.flush();
		os.close();
	}
}