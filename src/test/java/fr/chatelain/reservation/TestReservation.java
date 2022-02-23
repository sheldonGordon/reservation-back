package fr.chatelain.reservation;

import fr.chatelain.reservation.model.*;
import fr.chatelain.reservation.model.dto.ChambreDto;
import fr.chatelain.reservation.service.*;
import fr.chatelain.reservation.service.ChambreService;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestReservation {

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
        this.getUrl = baseUrl + "/reservations";
        this.getUrlbyId = getUrl + "/{id}";
        this.postUrl = baseUrl + "/reservation";
        this.putUrl = baseUrl + "/reservation";
        this.deleteUrl = getUrl + "/{id}";
    }

    @Autowired
    private ReservationService service;

    @Autowired
    private DateDebutFinService dateDebutFinService;

    @Autowired
    private ChambreService chambreService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private CompteService compteService;

    @Autowired
    private CodePromoService codePromoService;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    @Order(1)
    public void contructorReservationSuccess() {
        Reservation entity = service.getInstance();

        Assertions.assertNotNull(entity.getId());

        DateDebutFin entityDateDebutFin = dateDebutFinService.getInstance();
        Chambre entityChambre = chambreService.getInstance();
        Option entityOption1 = optionService.getInstance();
        Option entityOption2 = optionService.getInstance();
        Option entityOption3 = optionService.getInstance();
        List<Option> listEntityOption = new ArrayList<>();
        listEntityOption.add(entityOption1);
        listEntityOption.add(entityOption2);
        listEntityOption.add(entityOption3);
        Compte entityCompte = compteService.getInstance();
        CodePromo entityCodePromo = codePromoService.getInstance();

        Reservation entityFullField = service.getInstance(entityDateDebutFin, entityChambre, listEntityOption, entityCompte, false, entityCodePromo);

        Assertions.assertNotNull(entityFullField.getDateDebutfin());
        Assertions.assertNotNull(entityFullField.getChambre());
        Assertions.assertNotNull(entityFullField.getOptions());
        Assertions.assertNotNull(entityFullField.getClient());
        Assertions.assertNotNull(entityFullField.getPromotion());
    }

    @Test
    @Order(2)
    public void equalsAndHashCodeReservationSuccess() {
        EqualsVerifier.simple().forClass(Reservation.class).verify();
    }

    @Test
    @Order(3)
    public void saveReservationSuccess() {
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
