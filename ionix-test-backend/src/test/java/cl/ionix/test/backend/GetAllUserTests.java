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
class GetAllUserTests {
	
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
		
		when(repository.findAll()).thenReturn(usuarios);
		
		ResponseEntity<?> resp = api.getUsers(null);
		
		List<UserVO> body = (List<UserVO>) resp.getBody();
		
		assertEquals(200, resp.getStatusCodeValue());
		
		assertEquals("Gregorio Valenzuela", body.get(0).getName());
		assertEquals("gvalenzuelab", body.get(0).getUsername());
		assertEquals("+56942517952", body.get(0).getPhone());
		assertEquals("andresvalb@gmail.com", body.get(0).getEmail());
		
	}
	
	
	@Test
	void testNotFound() throws Exception {
		
		List<User> usuarios = new ArrayList<User>();
		
		User greg = new User();
		greg.setName("Gregorio Valenzuela");
		greg.setUsername("gvalenzuelab");
		greg.setPhone("+56942517952");
		greg.setEmail("andresvalb@gmail.com");
		
 
		
		when(repository.findAll()).thenReturn(usuarios);
		
		ResponseEntity<?> resp = api.getUsers(null);
		
		List<UserVO> body = (List<UserVO>) resp.getBody();
		
		assertEquals(404, resp.getStatusCodeValue());
		
 
	}

 

}
