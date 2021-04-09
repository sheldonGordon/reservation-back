package fr.chatelain.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.chatelain.reservation.model.ChambreService;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.service.ChambreServiceService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ChambreServiceController {

    @Autowired
    private ChambreServiceService chambreServiceService;

    @GetMapping("/")
    public String getHelloWorld() {
        ChambreService CS1 = FactoryReservation.getInstanceChambreService("test 1");
        ChambreService CS2 = FactoryReservation.getInstanceChambreService("test 2");
        ChambreService CS3 = FactoryReservation.getInstanceChambreService("test 3");
        ChambreService CS4 = FactoryReservation.getInstanceChambreService("test 4");
        chambreServiceService.save(CS1);
        chambreServiceService.save(CS2);
        chambreServiceService.save(CS3);
        chambreServiceService.save(CS4);

        return "Hello World!";
    }

    @GetMapping("/chambreServices")
    public List<ChambreService> getAllServices() {
        return chambreServiceService.findAll();
    }
}
