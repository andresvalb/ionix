package cl.ionix.test.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import cl.ionix.test.backend.repositori.User;
import cl.ionix.test.backend.repositori.UserRepository;
import cl.ionix.test.backend.service.ApiService;
import cl.ionix.test.backend.service.model.UserVO;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class AddUserTests {
	
	@MockBean
	private UserRepository repository;
	
	@Autowired
	private ApiService api;
	
	@Test
	void testGetUserAll() throws Exception {
		
		List<User> usuarios = new ArrayList<User>();
		
		User greg = new User();
		greg.setName("Gregorio Valenzuela");
		greg.setUsername("gvalenzuelab");
		greg.setPhone("+56942517952");
		greg.setEmail("andresvalb@gmail.com");
		
		usuarios.add(greg);
		
		when(repository.save(greg)).thenReturn(new User());
		
		UserVO uservo = new UserVO();
		uservo.setEmail(greg.getEmail());
		uservo.setName(greg.getName());
		uservo.setPhone(greg.getPhone());
		uservo.setUsername(greg.getUsername());
		
		ResponseEntity<?> resp = api.addUser(uservo);
		
		UserVO body = (UserVO) resp.getBody();
		
		assertEquals(201, resp.getStatusCodeValue());
		
		assertEquals("Gregorio Valenzuela", body.getName());
		assertEquals("gvalenzuelab", body.getUsername());
		assertEquals("+56942517952", body.getPhone());
		assertEquals("andresvalb@gmail.com", body.getEmail());
		
	}

 

}
