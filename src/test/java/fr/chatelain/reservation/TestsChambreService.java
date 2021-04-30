package fr.chatelain.reservation;

import java.net.URISyntaxException;
import java.util.UUID;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import fr.chatelain.reservation.model.ChambreService;
import fr.chatelain.reservation.model.dto.ChambreServiceDto;
import fr.chatelain.reservation.service.ChambreServiceService;
import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class TestsChambreService {

	private static final Logger LOG = LogManager.getLogger(TestsChambreService.class);

	private static final String MY_UUID = UUID.randomUUID().toString();

	private static final String MY_LIBELLE = "MonService";

	@LocalServerPort
	private int RANDOM_SERVER_PORT;

	private String baseUrl;

	private String getUrl;

	private String getUrlbyId;

	private String postUrl;

	private String putUrl;

	private String deleteUrl;

	@BeforeEach
	public void initUrl() {
		this.baseUrl = "http://localhost:" + RANDOM_SERVER_PORT + "/api/chambreServices";
		this.getUrl = baseUrl;
		this.getUrlbyId = baseUrl + "/{id}";
		this.postUrl = baseUrl;
		this.putUrl = baseUrl;
		this.deleteUrl = baseUrl + "/{id}";
	}

	@Autowired
	private ChambreServiceService service;

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	@Order(1)
	public void contructorChambreServiceSuccess(){
		ChambreService entity = service.getInstance();

		Assertions.assertNotNull(entity.getId());

		ChambreService entityWithLibelle = service.getInstance("libelle");

		Assertions.assertNotNull(entityWithLibelle.getLibelle());
	}

	@Test
	@Order(2)
	public void equalsAndHashCodeChambreServiceSuccess(){
		EqualsVerifier.simple().forClass(ChambreService.class).verify();
	}

	@Test
	@Order(3)
	public void saveChambreServiceSuccess() throws URISyntaxException {
		ChambreServiceDto entityDto = new ChambreServiceDto();
		entityDto.setId(MY_UUID);
		entityDto.setLibelleService(MY_LIBELLE);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<ChambreServiceDto> request = new HttpEntity<>(entityDto, headers);

		ResponseEntity<String> result = restTemplate.exchange(postUrl, HttpMethod.POST, request, String.class);

		Assertions.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
	}

	@Test
	@Order(4)
	public void getChambreServiceSuccess() throws URISyntaxException {
		ResponseEntity<String> result = restTemplate.exchange(getUrl, HttpMethod.GET, null, String.class);
		Assertions.assertEquals(HttpStatus.FOUND.value(), result.getStatusCodeValue());
	}

	@Test
	@Order(5)
	public void getChambreServiceByIdSuccess() throws URISyntaxException {
		ResponseEntity<String> result = restTemplate.exchange(getUrlbyId, HttpMethod.GET, null, String.class, MY_UUID);
		Assertions.assertEquals(HttpStatus.FOUND.value(), result.getStatusCodeValue());
	}

	@Test
	@Order(6)
	public void updateChambreServiceSuccess() throws URISyntaxException {
		ChambreServiceDto entityDto = new ChambreServiceDto();
		entityDto.setId(MY_UUID);
		entityDto.setLibelleService("LibelleChanged");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<ChambreServiceDto> request = new HttpEntity<>(entityDto, headers);

		ResponseEntity<String> result = restTemplate.exchange(putUrl, HttpMethod.PUT, request, String.class);

		Assertions.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	@Order(7)
	public void deleteChambreServiceSuccess() throws URISyntaxException {
		ResponseEntity<String> result = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class,
				MY_UUID);
		Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
	}

	@Test
	@Order(8)
	public void getChambreServiceByIdFailed() throws URISyntaxException {
		ResponseEntity<String> result = restTemplate.exchange(getUrlbyId, HttpMethod.GET, null, String.class, UUID.randomUUID().toString());
		Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
	}

	@Test
	@Order(9)
	public void getChambreServiceFailed() throws URISyntaxException {
		ResponseEntity<String> result = restTemplate.exchange(getUrl, HttpMethod.GET, null, String.class);
		Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
	}
}
