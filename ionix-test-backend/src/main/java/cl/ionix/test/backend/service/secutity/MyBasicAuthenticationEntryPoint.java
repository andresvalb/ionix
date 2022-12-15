package cl.ionix.test.backend.service.secutity;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void afterPropertiesSet() {
		setRealmName("spring");
		super.afterPropertiesSet();
	}
}