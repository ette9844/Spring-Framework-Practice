import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.my.dao.OrderDAO;
import com.my.vo.Customer;
import com.my.vo.OrderDetail;
import com.my.vo.OrderInfo;
import com.my.vo.Product;
import com.studybb.exception.AddException;
import com.studybb.exception.NotFoundException;

public class OrderTest {

	public static void main(String[] args) {
		OrderDAO dao = new OrderDAO();
		String id = "id1";
		
		try {
			List<OrderInfo> list = dao.selectById(id);
			for(OrderInfo info: list) {
				int order_no = info.getOrder_no();
				Timestamp order_time = info.getOrder_time();
				System.out.println("주문기본정보: " 
						+ order_no + ", " 
						+ order_time);
				List<OrderDetail> details = info.getOrderDetails();
				for(OrderDetail detail: details) {
					Product product = detail.getProduct();
					String p_no = product.getProd_no();
					String p_name = product.getProd_name();
					int p_price = product.getProd_price();
					int quantity = detail.getOrder_quantity();
					System.out.println("주문상세정보: " 
							+ p_no + ", " 
							+ p_name+", " 
							+ p_price + ", " 
							+ quantity);
				}
			}
			System.out.println("------------------------------");
		} catch(NotFoundException e) {
			System.out.println(e.getMessage());	// 출력
		}
		
		OrderInfo info = new OrderInfo();
		Customer c = new Customer();
		c.setId(id);
		info.setCustomer(c);			// 주문자
		List<OrderDetail> orderDetails = new ArrayList<>();
		
//		OrderDetail detail = new OrderDetail();
//		Product p = new Product();
//		p.setProd_no("10001");
//		detail.setProduct(p);			// 주문상품번호
//		detail.setOrder_quantity(2);	// 주문 수량
//		orderDetails.add(detail);
//		
//		detail = new OrderDetail();
//		p = new Product();	// new 키워드로 새 객체 생성을 하지 않으면 이전 객체 덮어쓰기가 되어버림
//		p.setProd_no("10003");
//		detail.setProduct(p);			// 주문상품번호
//		detail.setOrder_quantity(3);	// 주문 수량
//		orderDetails.add(detail);
//		
//		info.setOrderDetails(orderDetails);
//		try {
//			dao.insert(info);
//			System.out.println("주문 추가 성공!");
//		} catch (AddException e) {
//			e.printStackTrace();
//		}
	}

}
