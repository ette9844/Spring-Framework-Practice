import java.util.HashMap;

import com.my.vo.Product;

public class Test {

	public static void main(String[] args) {
		HashMap<Product, Integer> map;
		map = new HashMap<>();
		
		Product p1 = new Product();
		p1.setProd_no("10001");
		int quantity = 3;	// 수량
		
		// 조회
		Product p3 = new Product();
		p3.setProd_no("10001");
		Integer inte = map.get(p3);	// null
		if(inte != null) {
			// 기존 수량이 있을 경우 누적
			quantity += inte;
		}
		// 추가
		map.put(p1, quantity);
		
		Product p2 = new Product();
		p1.setProd_no("10001");
		quantity = 2;	// 수량
		
		// 조회
		p3.setProd_no("10001");
		inte = map.get(p3);	// null
		if(inte != null) {
			// 기존 수량이 있을 경우 누적
			quantity += inte;
		}
		// 추가
		map.put(p2, quantity);
		
		System.out.println(map);	// 강제로 키를 중복시킨다.
	}

}
