package cl.ionix.test.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import cl.ionix.test.backend.repositori.User;
import cl.ionix.test.backend.repositori.UserRepository;
import cl.ionix.test.backend.service.ApiService;
import cl.ionix.test.backend.service.model.ResponseCifrar;
import cl.ionix.test.backend.service.model.UserVO;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class ServiceCifradoTests {
	
	@MockBean
	private UserRepository repository;
	
	@Autowired
	private ApiService api;
	
	@Test
	void testCifrarOk() throws Exception {
		
		
		ResponseEntity<?> resp = api.cifrado("1-9");
		
		ResponseCifrar body = (ResponseCifrar) resp.getBody();
		
		assertEquals(200, resp.getStatusCodeValue());
		assertEquals(0, body.getResponseCode());
		assertEquals("OK",body.getDescription());
		assertNotNull(body.getElapsedTime());
		
		assertNotNull(body.getResult());
		
		assertEquals(3,body.getResult().getRegisterCount());
		
 
		
	}
	
	
	@Test
	void testCifrarError() throws Exception {
		
		
		ResponseEntity<?> resp = api.cifrado("1-8");
		
		ResponseCifrar body = (ResponseCifrar) resp.getBody();
		
		assertEquals(400, resp.getStatusCodeValue());
		assertEquals(0, body.getResponseCode());
		assertEquals("OK",body.getDescription());
		assertNotNull(body.getElapsedTime());
		
		assertNotNull(body.getResult());
		
		assertEquals(3,body.getResult().getRegisterCount());
		
 
		
	}
	
	 

 

}
