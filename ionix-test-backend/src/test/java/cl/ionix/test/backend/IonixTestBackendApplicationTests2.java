package cl.ionix.test.backend;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.ionix.test.backend.repositori.UserRepository;
import cl.ionix.test.backend.service.ApiService;
import cl.ionix.test.backend.service.model.UserVO;

@SpringBootTest
@AutoConfigureMockMvc
class IonixTestBackendApplicationTests2 {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ApiService service;

	@MockBean
	private UserRepository repository;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testPostExample() throws Exception {

		UserVO datos = new UserVO();

		datos.setName("Juan");
		datos.setUsername("jn");
		datos.setEmail("juan@gmail.com");
		datos.setPhone("123456789");

		ResponseEntity<UserVO> respuesta = new ResponseEntity<UserVO>(datos, HttpStatus.valueOf(201));

		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(datos);

		// Mockito.when(service.addUser(ArgumentMatchers.any())).thenReturn(respuesta);

		String json = mapper.writeValueAsString(datos);

		mockMvc.perform(post("/users")
				.header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString("gregorio:123123".getBytes()))
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").content(json)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", Matchers.equalTo("Juan")))
				.andExpect(jsonPath("$.username", Matchers.equalTo("jn")));
	}

}
