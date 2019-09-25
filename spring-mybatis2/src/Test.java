import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.vo.Customer;

public class Test {

	public static void main(String[] args) {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			//inputStream = new FileInputStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			//Blog blog = session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);
			
			// UPDATE
			// session.update("com.my.vo.Customer.updateName", "id1");
			HashMap<String, String> map = new HashMap<>();
			map.put("id", "id1");
			map.put("name", "바티스2");
			session.update("com.my.vo.Customer.updateName", map);
			
			// INSERT
//			Customer c = new Customer();
//			c.setId("idtest");
//			c.setPwd("ptest");
//			c.setName("n테스트");
//			c.setAddr("상세주소1");
//			session.insert("com.my.vo.Customer.insert", c);
//			
//			// DELETE
//			session.delete("com.my.vo.Customer.delete", "idtest");
			
			// SELECT
			Customer c1 = session.selectOne("com.my.vo.Customer.selectById", "id1");
			System.out.println(c1.getName() + ":" + c1.getPwd());
			
			int cnt = session.selectOne("com.my.vo.Customer.selectCount");
			System.out.println("총 고객 수 : " + cnt);
			
			HashMap map2 = session.selectOne("com.my.vo.Customer.selectGroup");
			System.out.println(map2.get("C1") + " : " + map2.get("C2"));
			
			List<Customer> list = session.selectList("com.my.vo.Customer.selectAll");
			System.out.println("총 고객 행수 : " + list.size());
			for(Customer c: list) {
				System.out.println(c.getId() + " : " + c.getName());
			}
			
			Customer c3 = session.selectOne("com.my.vo.Customer.selectZipcodeById", "etet");
			System.out.println(c3.getId() + " : " + c3.getPost().getZipcode());
			// c3.getPost()에서 NullPointException 발생
			System.out.println(c3.getPost());
			
			// END
			session.commit();
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
