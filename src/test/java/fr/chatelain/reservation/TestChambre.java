package fr.chatelain.reservation;

import fr.chatelain.reservation.model.Chambre;
import fr.chatelain.reservation.model.DateDebutFin;
import fr.chatelain.reservation.model.Photos;
import fr.chatelain.reservation.service.ChambreService;
import fr.chatelain.reservation.service.ChambreServicesService;
import fr.chatelain.reservation.service.DateDebutFinService;
import fr.chatelain.reservation.service.PhotosService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
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
        this.getUrlbyId = baseUrl + "/chambre/{id}";
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
}
