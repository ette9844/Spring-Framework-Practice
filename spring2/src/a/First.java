package a;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class First {
	private String msg;
	
	//@Autowired				// 클래스 타입 
	//private Second second;	// Second 객체가 없어서 자동주입 실패시 NoSuchBeanDefinitionException
								// Second 객체가 없어서 자동주입이 되지않아도 null로 유지하려면
								// @Autowired(required = false)로 설정한다.
								// Second 객체가 여러개여서 자동주입 실패시 NoUniqueBeanDefinitionException
	
	@Autowired
	@Qualifier("s1")
	private Second second;

	private boolean flag;

	@PostConstruct
	public void init() {
		System.out.println("init 메서드 호출됨");
		flag = true;
	}
	// 매개변수 없는 public 생성자가 compile 시에 추가됨
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "msg = " + msg + ", second.info() = " + second.info();
	}
}
