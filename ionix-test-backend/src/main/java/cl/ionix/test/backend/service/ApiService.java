package cl.ionix.test.backend.service;

import cl.ionix.test.backend.repositori.User;
import cl.ionix.test.backend.repositori.UserRepository;
import cl.ionix.test.backend.service.model.ResponseCifrar;
import cl.ionix.test.backend.service.model.ResponseExterna;
import cl.ionix.test.backend.service.model.ResultCifrar;
import cl.ionix.test.backend.service.model.UserVO;
import cl.ionix.test.backend.service.util.Cifrar;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.remote.client.HttpHeaderInterceptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiService {

	@Autowired
	private UserRepository repository;

	Logger logger = LoggerFactory.getLogger(ApiService.class);

	@PostMapping("/users")
	public ResponseEntity<UserVO> addUser(@RequestBody UserVO datos) {

		logger.info("Entrada al metodo addUser.");

		User usuario = new User();

		usuario.setName(datos.getName());
		usuario.setUsername(datos.getUsername());
		usuario.setEmail(datos.getEmail());
		usuario.setPhone(datos.getPhone());

		repository.save(usuario);

		return new ResponseEntity<>(datos, HttpStatus.valueOf(201));
	}

	@GetMapping("/users")
	public ResponseEntity<?> getUsers(@RequestParam(required = false, name = "email") String email) {

		logger.info("Entrada al metodo getUsers.");

		List<UserVO> salida = new ArrayList<>();

		// recupero los usuarios.
		List<User> usuarios;

		// si viene el email busco por ese.
		if (email != null) {
			usuarios = (List<User>) repository.findByEmail(email);
		} else {
			usuarios = (List<User>) repository.findAll();
		}

		// si no existen datos.
		if (usuarios.isEmpty()) {
			return new ResponseEntity(null, HttpStatus.valueOf(404));
		}

		// Preparo salida.
		usuarios.forEach(data -> {
			UserVO user = new UserVO();
			user.setUsername(data.getUsername());
			user.setName(data.getName());
			user.setEmail(data.getEmail());
			user.setPhone(data.getPhone());
			salida.add(user);
		});

		return new ResponseEntity(salida, HttpStatus.valueOf(200));
	}

	@DeleteMapping("/users/{email}")
	public ResponseEntity delete(@PathVariable String email) {

		// recupero los usuarios.
		List<User> usuarios = (List<User>) repository.findByEmail(email);

		// si no existen datos.
		if (usuarios.isEmpty()) {
			return new ResponseEntity(null, HttpStatus.valueOf(404));
		}

		repository.deleteUsers(email);
		return new ResponseEntity(null, HttpStatus.valueOf(202));
	}

	@PostMapping("/cifrado/{rut}")
	public ResponseEntity<?> cifrado(@PathVariable String rut) {

		long start = System.currentTimeMillis();

		logger.info("Entrada al metodo cifrado.");

		String encryptedValue = Cifrar.rutCifrado(rut);

		String urlServiceExterno = "https://my.api.mockaroo.com/test-tecnico/search/" + encryptedValue;

		RestTemplate rest = new RestTemplate();

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

		interceptors.add(new HttpHeaderInterceptor("X-API-Key", "f2f719e0"));

		rest.setInterceptors(interceptors);

		ResponseEntity<ResponseExterna> result = rest.getForEntity(urlServiceExterno, ResponseExterna.class);

		ResponseCifrar respuesta = new ResponseCifrar();

		respuesta.setDescription("OK");
		respuesta.setResponseCode(0);
		respuesta.setElapsedTime(System.currentTimeMillis() - start);
		ResultCifrar resul = new ResultCifrar();
		resul.setRegisterCount(result.getBody().getResult().getItems().size());
		respuesta.setResult(resul);

		System.out.println(urlServiceExterno);
		System.out.println(result);

		return new ResponseEntity(respuesta, HttpStatus.valueOf(200));
	}

}
