package a;

import org.springframework.stereotype.Component;

@Component(value = "s1")
public class Second2 implements Second {

	@Override
	public String info() {
		return "Second2 객체입니다";
	}

}
