import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import a.First;

public class Test {

	public static void main(String[] args) {
		// xml 파일 구동
		String path = "beans.xml";
		ApplicationContext ctx;	// Spring Container: Spring에서 쓰일 객체관리자
		
		// 클래스패스(bin경로) 기준으로 xml 찾아오기
		ctx = new ClassPathXmlApplicationContext(path);
		First first = ctx.getBean("first-set", a.First.class);
		System.out.println(first);
		System.out.println(first.getNum());
		System.out.println(first.getSecond().info());
		// 첫번째 인자: 찾고자 하는 객체의 name이나 id로 찾기
		// 두번째 인자: 찾아온 객체가 a.First.class 타입으로 다운 캐스팅이 가능한지 물어보고 다운 캐스팅
		
		First first1 = ctx.getBean("first-set", a.First.class);
		System.out.println(first1);	// 싱글톤으로 관리되기 때문에 둘다 같은 객체를 참조하게 됨
		
		// 클래스는 바뀌지 않고 xml 파일만 바꿔서 다르게 동작 시킬 수 있다.
		// xml에서 property 변경
		
		System.out.println("-------------------------------");
		
		// 생성자 방식 DI
		First first2 = ctx.getBean("first-con", a.First.class);
		System.out.println(first2.getNum());
		System.out.println(first2.getMsg());
		
		// first1과 first2의 비교
		// id에 해당하는 객체는 하나씩 만들어지지만, id가 서로 다르면 다른 객체
		System.out.println(first1 == first2); 	// false
		
		// 두 객체가 ref 하는 second 객체는 같은 객체이기 때문에 true
		System.out.println(first1.getSecond() == first2.getSecond()); 	// true
		
		First first3 = ctx.getBean("first-list", a.First.class);
		System.out.println(first3.getList());
		
		First first4 = ctx.getBean("first-map", a.First.class);
		System.out.println(first4.getMap());
	}

}
