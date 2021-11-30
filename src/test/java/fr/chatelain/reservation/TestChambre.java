package fr.chatelain.reservation;

import fr.chatelain.reservation.model.Chambre;
import fr.chatelain.reservation.service.ChambreService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

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
        this.getUrlbyId = baseUrl + "/chambre/{id}";
        this.postUrl = baseUrl + "/chambre";
        this.putUrl = baseUrl + "/chambre";
        this.deleteUrl = baseUrl + "/chambre/{id}";
    }

    @Autowired
    private ChambreService service;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    @Order(1)
    public void contructorChambreServiceSuccess() {
        Chambre entity = service.getInstance();

        Assertions.assertNotNull(entity.getId());
        //Chambre entityFullField = service.getInstance("Nom chambre", 2, 10.00, 20, photos, services, indisponibilites);

        //Assertions.assertNotNull(entityFullField.getNom());
    }
}
