package fr.chatelain.reservation;

import fr.chatelain.reservation.model.Compte;
import fr.chatelain.reservation.model.Option;
import fr.chatelain.reservation.model.Personne;
import fr.chatelain.reservation.model.Role;
import fr.chatelain.reservation.model.dto.CompteDto;
import fr.chatelain.reservation.model.dto.OptionDto;
import fr.chatelain.reservation.model.dto.PersonneDto;
import fr.chatelain.reservation.service.CompteService;
import fr.chatelain.reservation.service.OptionService;
import fr.chatelain.reservation.service.PersonneService;
import fr.chatelain.reservation.service.RoleService;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCompte {
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
        this.getUrl = baseUrl + "/comptes";
        this.getUrlbyId = getUrl + "/{id}";
        this.postUrl = baseUrl + "/compte";
        this.putUrl = baseUrl + "/compte";
        this.deleteUrl = getUrl + "/{id}";
    }

    @Autowired
    private CompteService service;

    @Autowired
    private PersonneService personneService;

    @Autowired
    private RoleService roleService;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    @Order(1)
    public void contructorCompteSuccess() {
        Compte entity = service.getInstance();

        Assertions.assertNotNull(entity.getId());

        Personne entityPersonne = personneService.getInstance("nom", "prenom");
        Role entityRole1 = roleService.getInstance("role 1");
        Role entityRole2 = roleService.getInstance("role 2");
        List<Role> listRoles = new ArrayList<>();
        listRoles.add(entityRole1);
        listRoles.add(entityRole2);

        Compte entityFullField = service.getInstance(entityPersonne, listRoles);

        Assertions.assertNotNull(entityFullField.getPersonne());
        Assertions.assertNotNull(entityFullField.getRoles());

    }

    @Test
    @Order(2)
    public void equalsAndHashCodeCompteSuccess() {
        EqualsVerifier.simple().forClass(Compte.class).verify();
    }

    @Test
    @Order(3)
    public void saveCompteSuccess() {
        CompteDto entityDto = new CompteDto();

        entityDto.setId(MY_UUID);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<CompteDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(postUrl, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(HttpStatus.CREATED.value(), result.getStatusCodeValue());
    }


    @Test
    @Order(4)
    public void getCompteSuccess() {
        ResponseEntity<String> result = restTemplate.exchange(getUrl, HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(5)
    public void getCompteByIdSuccess() {
        ResponseEntity<String> result = restTemplate.exchange(getUrlbyId, HttpMethod.GET, null, String.class, MY_UUID);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(6)
    public void updateCompteSuccess() {
        CompteDto entityDto = new CompteDto();
        entityDto.setId(MY_UUID);
        PersonneDto personneDto = new PersonneDto();
        entityDto.setPersonne(personneDto);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<CompteDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(putUrl, HttpMethod.PUT, request, String.class);

        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(7)
    public void deleteCompteSuccess() {
        ResponseEntity<String> result = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class,
                MY_UUID);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(8)
    public void saveCompteFailed() {
        CompteDto entityDto = new CompteDto();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<CompteDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(postUrl, HttpMethod.POST, request, String.class);

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(9)
    public void getCompteByIdFailed() {
        ResponseEntity<String> result = restTemplate.exchange(getUrlbyId, HttpMethod.GET, null, String.class,
                UUID.randomUUID().toString());
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(10)
    public void getCompteFailed() {
        ResponseEntity<String> result = restTemplate.exchange(getUrl, HttpMethod.GET, null, String.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(11)
    public void updateCompteFailed() {
        CompteDto entityDto = new CompteDto();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<CompteDto> request = new HttpEntity<>(entityDto, headers);

        ResponseEntity<String> result = restTemplate.exchange(putUrl, HttpMethod.PUT, request, String.class);

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }

    @Test
    @Order(12)
    public void deleteComtpeFailed() {
        ResponseEntity<String> result = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class,
                UUID.randomUUID().toString());
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getStatusCodeValue());
    }
}
