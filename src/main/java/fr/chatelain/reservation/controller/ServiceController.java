package fr.chatelain.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.chatelain.reservation.model.Service;
import fr.chatelain.reservation.repository.ServiceRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ServiceController {

    @Autowired
    private ServiceRepository repositoryService;

    @GetMapping("/services")
    public List<Service> getAllServices() {
        return repositoryService.findAll();
    }
}
