package fr.chatelain.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.chatelain.reservation.repository.GenericRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class Service {

    @Autowired
    private GenericRepository<Service> repositoryService;

    @GetMapping("/services")
    public ResponseEntity<List<Service>> getAllServices() {
        return (ResponseEntity<List<Service>>) repositoryService.findAll();
    }
}
