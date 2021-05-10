package fr.chatelain.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.service.PhotosService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PhotosController {

    @Autowired
    private PhotosService photosService;

    @DeleteMapping("/photos/{id}")
    public ResponseEntity<String> deleteChambreService(@PathVariable(name = "id") String id) {
        try {
            photosService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
