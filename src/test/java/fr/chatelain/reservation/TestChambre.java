package fr.chatelain.reservation;

import fr.chatelain.reservation.model.Chambre;
import fr.chatelain.reservation.model.DateDebutFin;
import fr.chatelain.reservation.model.Photos;
import fr.chatelain.reservation.model.dto.ChambreDto;
import fr.chatelain.reservation.model.dto.PhotosDto;
import fr.chatelain.reservation.service.ChambreService;
import fr.chatelain.reservation.service.ChambreServicesService;
import fr.chatelain.reservation.service.DateDebutFinService;
import fr.chatelain.reservation.service.PhotosService;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestChambre {

    private static final String MY_UUID = UUID.randomUUID().toString();

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
        this.baseUrl = "http://localhost:" + RANDOM_SERVER_PORT + "/api";
        this.getUrl = baseUrl + "/chambres";
        this.getUrlbyId = getUrl + "/{id}";
        this.postUrl = baseUrl + "/chambre";
        this.putUrl = baseUrl + "/chambre";
        this.deleteUrl = baseUrl + "/chambre/{id}";
    }

    @Autowired
    private ChambreService service;

    @Autowired
    private PhotosService photosService;

    @Autowired
    private ChambreServicesService chambreService;

    @Autowired
    private DateDebutFinService dateDebutFinService;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    @Order(1)
    public void contructorChambreServiceSuccess() {
        Chambre entity = service.getInstance();

        Assertions.assertNotNull(entity.getId());

        Photos photosEntity1 = photosService.getInstance();
        Photos photosEntity2 = photosService.getInstance();
        Photos photosEntity3 = photosService.getInstance();
        List<Photos> listPhotosEntity = new ArrayList<Photos>();
        listPhotosEntity.add(photosEntity1);
        listPhotosEntity.add(photosEntity2);
        listPhotosEntity.add(photosEntity3);

        fr.chatelain.reservation.model.ChambreService chambreServiceEntity1 = chambreService.getInstance();
        fr.chatelain.reservation.model.ChambreService chambreServiceEntity2 = chambreService.getInstance();
        List<fr.chatelain.reservation.model.ChambreService> listChambreService = new ArrayList<fr.chatelain.reservation.model.ChambreService>();
        listChambreService.add(chambreServiceEntity1);
        listChambreService.add(chambreServiceEntity2);

        DateDebutFin dateDebutFinEntity1 = dateDebutFinService.getInstance();
        DateDebutFin dateDebutFinEntity2 = dateDebutFinService.getInstance();
        List<DateDebutFin> listDateDebutFin = new ArrayList<DateDebutFin>();
        listDateDebutFin.add(dateDebutFinEntity1);
        listDateDebutFin.add(dateDebutFinEntity2);

        Chambre entityFullField = service.getInstance("Nom chambre", 2, new BigDecimal(10.52), 20, listPhotosEntity, listChambreService, listDateDebutFin);

        Assertions.assertNotNull(entityFullField.getNom());
        Assertions.assertNotNull(entityFullField.getNombrePersonne());
        Assertions.assertNotNull(entityFullField.getPrix());
        Assertions.assertNotNull(entityFullField.getSuperficie());
        Assertions.assertNotNull(entityFullField.getPhotos());
        Assertions.assertNotNull(entityFullField.getServices());
        Assertions.assertNotNull(entityFullField.getIndisponibilites());
    }

    @Test
    @Order(2)
    public void equalsAndHashCodeChambreSuccess() {
        EqualsVerifier.simple().forClass(Chambre.class).verify();
    }

    @Test
    @Order(3)
    public void saveChambreSuccess() {
        ChambreDto entityDto = new ChambreDto();
        entityDto.setId(MY_UUID);
        entityDto.setNom("Nom de ma chambre");
        entityDto.setNombrePersonne(2);
        entityDto.setPrix(new BigDecimal(10.50));

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<ChambreDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(postUrl, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(4)
    public void getChambreSuccess() {
        ResponseEntity<String> result = restTemplate.exchange(getUrl, HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(5)
    public void getChambreByIdSuccess() {
        ResponseEntity<String> result = restTemplate.exchange(getUrlbyId, HttpMethod.GET, null, String.class, MY_UUID);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(6)
    public void updateChambreSuccess() {
        ChambreDto entityDto = new ChambreDto();
        entityDto.setId(MY_UUID);
        entityDto.setNom("Nom de ma chambre modifié");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<ChambreDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(putUrl, HttpMethod.PUT, request, String.class);

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(7)
    public void deleteChambreSuccess() {
        ResponseEntity<String> result = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class,
                MY_UUID);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(8)
    public void saveChambreFailed() {
        ChambreDto entityDto = new ChambreDto();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<ChambreDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(postUrl, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(9)
    public void getChambreByIdFailed() {
        ResponseEntity<String> result = restTemplate.exchange(getUrlbyId, HttpMethod.GET, null, String.class,
                UUID.randomUUID().toString());
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(10)
    public void getChambreFailed() {
        ResponseEntity<String> result = restTemplate.exchange(getUrl, HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(11)
    public void updateChambreFailed() {
        ChambreDto entityDto = new ChambreDto();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<ChambreDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(putUrl, HttpMethod.PUT, request, String.class);

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(12)
    public void deleteChambreFailed() {
        ResponseEntity<String> result = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class,
                UUID.randomUUID().toString());
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }
}
