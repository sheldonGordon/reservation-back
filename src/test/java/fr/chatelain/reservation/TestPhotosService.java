package fr.chatelain.reservation;

import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.Photos;
import fr.chatelain.reservation.service.PhotosService;
import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class TestPhotosService {

	private static final String MY_UUID = UUID.randomUUID().toString();

	@LocalServerPort
	private int RANDOM_SERVER_PORT;

	private String baseUrl;

	private String deleteUrl;

	@BeforeEach
	public void initUrl() {
		this.baseUrl = "http://localhost:" + RANDOM_SERVER_PORT + "/api/photos";
		this.deleteUrl = baseUrl + "/{id}";
	}

	@Autowired
	private PhotosService service;

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	@Order(1)
	public void contructorPhotosSuccess() {
		Photos entity = service.getInstance();

		Assertions.assertNotNull(entity.getId());

		Random random = ThreadLocalRandom.current();
		byte[] r = new byte[256]; 
		random.nextBytes(r);
		String dataPhotos = Base64.getEncoder().encodeToString(r);

		Photos entityFullField = service.getInstance(dataPhotos, "NomPhotos", "typeMimePhotos");

		try {
			entity.setId(MY_UUID);
			service.save(entity);
		} catch (RepositoryExeption e) {
			e.printStackTrace();
		}

		Assertions.assertNotNull(entityFullField.getData());
		Assertions.assertNotNull(entityFullField.getNom());
		Assertions.assertNotNull(entityFullField.getTypeMime());

	}

	@Test
	@Order(2)
	public void equalsAndHashCodePhotosSuccess() {
		EqualsVerifier.simple().forClass(Photos.class).verify();
	}

	@Test
	@Order(3)
	public void deletePhotosSuccess() {
		ResponseEntity<String> result = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class,
				MY_UUID);
		Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
	}

	@Test
	@Order(4)
	public void deletePhotosFailed() {
		ResponseEntity<String> result = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class,
				UUID.randomUUID().toString());
		Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
	}
}
