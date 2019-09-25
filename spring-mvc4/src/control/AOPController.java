package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.dao.AccountDAO;

@Controller
public class AOPController {  // controller가 dao 작업을 직접함, test는 이런식으로 간단하게 진행할 수 있다
	
	@Autowired

	private AccountDAO dao;
	
	@GetMapping("/account")
	@ResponseBody	// view단을 안거치고 controller가 직접 응답
	//@Transactional(propagation=Propagation.REQUIRED)	// 선언적 트랜잭션의 annotation
	public String account(Model model) {
		return "redirect:/b.do";	// 리다이렉트
	}
	/*
	 Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		try {
			// jdbc에서는 auto commit이 된다.
			// 실패되었지만 sql Exception이 발생하지도 않고 무시가 되어버린다.
			// auto commit이 되기 때문에 입금과 출금이 맞지 않게 된다.
			
			con = dataSource.getConnection();
			con.setAutoCommit(false);	// auto commit 해제
			// 계좌 이체 작업
			String sql1 = "UPDATE account "
					+ "SET balance = balance-10 "
					+ "WHERE no = ?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setString(1, "101"); 	// 101번 계좌에서 10원 출금
			int rowCnt1 = pstmt1.executeUpdate();	// 처리된 건수 받기
			
			String sql2 = "UPDATE account "
					+ "SET balance = balance+10 "
					+ "WHERE no = ?";
			pstmt2 = con.prepareStatement(sql2);
			//pstmt2.setString(1, "102"); 	// 102번 계좌에서 10원 입금
			pstmt2.setString(1, "999");	// 없는 계좌
			int rowCnt2 = pstmt2.executeUpdate();	// 처리된 건수 받기
			
			if(rowCnt1 == 1 && rowCnt2 == 1) {
				// 정상 처리
				con.commit();
			} else {
				con.rollback();
			}
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	  */
}
