package cl.ionix.test.backend.service.secutity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthWebSecurityConfiguration {

	@Autowired
	private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, "/users").permitAll()
		.antMatchers(HttpMethod.POST, "/cifrado/**").permitAll().anyRequest()
				.authenticated().and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);
		return http.build();
	}

//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		
//		System.out.println(passwordEncoder().encode("123456"));
//		
//		UserDetails user = User.withUsername("user").password(passwordEncoder().encode("password")).roles("USER_ROLE")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(8);
	}
}
