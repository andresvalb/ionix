package cl.ionix.test.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
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
class DeleteUserTests {
	
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
		
		
		UserRepository mockrepo = mock(UserRepository.class);
		
		when(repository.findByEmail(greg.getEmail())).thenReturn(usuarios);
		
		doNothing().when(mockrepo).deleteUsers("andresvalb@gmail.com");
		
		ResponseEntity resp = api.delete("andresvalb@gmail.com");
 
		assertEquals(202, resp.getStatusCodeValue());
		
	}
	
	
	@Test
	void testNotFound() throws Exception {
		
		List<User> usuarios = new ArrayList<User>();
		
		User greg = new User();
		greg.setName("Gregorio Valenzuela");
		greg.setUsername("gvalenzuelab");
		greg.setPhone("+56942517952");
		greg.setEmail("andresvalb@gmail.com");
			
		
		UserRepository mockrepo = mock(UserRepository.class);
		
		when(repository.findByEmail(greg.getEmail())).thenReturn(usuarios);
		
		doNothing().when(mockrepo).deleteUsers("andresvalb@gmail.com");
		
		ResponseEntity resp = api.delete("andresvalb@gmail.com");
 
		assertEquals(404, resp.getStatusCodeValue());
		
	}

 

}
