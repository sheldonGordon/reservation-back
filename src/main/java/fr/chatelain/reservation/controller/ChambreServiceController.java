package fr.chatelain.reservation.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.ChambreService;
import fr.chatelain.reservation.model.dto.ChambreServiceDto;
import fr.chatelain.reservation.service.ChambreServiceService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ChambreServiceController {

    @Autowired
    private ChambreServiceService chambreServiceService;

    @GetMapping("/chambreServices")
    public ResponseEntity<List<ChambreServiceDto>> getAllServices() {
        List<ChambreServiceDto> listChambreServiceDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try {
            chambreServiceService.findAll()
                    .forEach(c -> listChambreServiceDto.add(modelMapper.map(c, ChambreServiceDto.class)));
            return new ResponseEntity<>(listChambreServiceDto, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/chambreServices/{id}")
    public ResponseEntity<ChambreServiceDto> getChambreService(@PathVariable(name = "id") String id) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            return new ResponseEntity<>(modelMapper.map(chambreServiceService.getById(id), ChambreServiceDto.class),
                    HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new ChambreServiceDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/chambreServices")
    public ResponseEntity<ChambreServiceDto> saveChambreService(@RequestBody ChambreServiceDto chambreService) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            ChambreService entity = modelMapper.map(chambreService, ChambreService.class);
            chambreServiceService.save(entity);
            return new ResponseEntity<>(chambreService, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new ChambreServiceDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/chambreServices")
    public ResponseEntity<ChambreServiceDto> updateChambreService(@RequestBody ChambreServiceDto chambreService) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            chambreServiceService.update(modelMapper.map(chambreService, ChambreService.class));
            return new ResponseEntity<>(chambreService, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new ChambreServiceDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/chambreServices/{id}")
    public ResponseEntity<String> deleteChambreService(@PathVariable(name = "id") String id) {
        try {
            chambreServiceService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
