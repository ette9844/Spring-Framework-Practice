import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import a.First;

@Configuration
public class Beans {
	@Bean
	public First first() {
		First f = new First();
		f.setMsg("금요일입니다.");
		return f;
	}
}
