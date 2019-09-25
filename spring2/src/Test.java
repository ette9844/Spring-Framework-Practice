import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import a.First;

public class Test {

	public static void main(String[] args) {
		// String path = "beans.xml";
		String path = "beans2.xml";
		// spring container 구동
		ApplicationContext ctx = new ClassPathXmlApplicationContext(path);
		
		// ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
		
		First f = ctx.getBean("first", a.First.class);
		System.out.println(f);	// f.toString() 자동 호출
	}

}
