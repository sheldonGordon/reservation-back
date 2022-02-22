package fr.chatelain.reservation;

import fr.chatelain.reservation.model.CodePromo;
import fr.chatelain.reservation.model.DateDebutFin;
import fr.chatelain.reservation.model.dto.CodePromoDto;
import fr.chatelain.reservation.model.dto.DateDebutFinDto;
import fr.chatelain.reservation.service.CodePromoService;
import fr.chatelain.reservation.service.DateDebutFinService;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCodePromo {
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
        this.getUrl = baseUrl + "/codePromos";
        this.getUrlbyId = getUrl + "/{id}";
        this.postUrl = baseUrl + "/codePromo";
        this.putUrl = baseUrl + "/codePromo";
        this.deleteUrl = baseUrl + "/codePromo/{id}";
    }

    @Autowired
    private CodePromoService service;

    @Autowired
    private DateDebutFinService dateDebutFinService;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    @Order(1)
    public void contructorOptionSuccess() {
        CodePromo entity = service.getInstance();

        Assertions.assertNotNull(entity.getId());

        DateDebutFin dateDebutFinEntity = dateDebutFinService.getInstance();

        CodePromo entityFullField = service.getInstance("code", 10.5, dateDebutFinEntity);

        Assertions.assertNotNull(entityFullField.getCode());
        Assertions.assertNotNull(entityFullField.getPourcentage());
        Assertions.assertNotNull(entityFullField.getValidite());

    }

    @Test
    @Order(2)
    public void equalsAndHashCodeOptionSuccess() {
        EqualsVerifier.simple().forClass(CodePromo.class).verify();
    }

    @Test
    @Order(3)
    public void saveCodePromoSuccess() {
        CodePromoDto entityDto = new CodePromoDto();
        entityDto.setId(MY_UUID);
        entityDto.setCode("super code");
        entityDto.setPourcentage(5);
        entityDto.setValidite(new DateDebutFinDto());

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<CodePromoDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(postUrl, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(4)
    public void getCodePromoSuccess() {
        ResponseEntity<String> result = restTemplate.exchange(getUrl, HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(5)
    public void getCodePromoByIdSuccess() {
        ResponseEntity<String> result = restTemplate.exchange(getUrlbyId, HttpMethod.GET, null, String.class, MY_UUID);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(6)
    public void updateCodePromoSuccess() {
        CodePromoDto entityDto = new CodePromoDto();
        entityDto.setId(MY_UUID);
        entityDto.setCode("code modifié");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<CodePromoDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(putUrl, HttpMethod.PUT, request, String.class);

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(7)
    public void deleteCodePromoSuccess() {
        ResponseEntity<String> result = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class,
                MY_UUID);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(8)
    public void saveCodePromoFailed() {
        CodePromoDto entityDto = new CodePromoDto();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<CodePromoDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(postUrl, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(9)
    public void getCodePromoByIdFailed() {
        ResponseEntity<String> result = restTemplate.exchange(getUrlbyId, HttpMethod.GET, null, String.class,
                UUID.randomUUID().toString());
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(10)
    public void getCodePromoFailed() {
        ResponseEntity<String> result = restTemplate.exchange(getUrl, HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(11)
    public void updateCodePromoFailed() {
        CodePromoDto entityDto = new CodePromoDto();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<CodePromoDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(putUrl, HttpMethod.PUT, request, String.class);

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(12)
    public void deleteCodePromoFailed() {
        ResponseEntity<String> result = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class,
                UUID.randomUUID().toString());
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }
}
