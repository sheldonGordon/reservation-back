package fr.chatelain.reservation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;

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
    public List<ChambreServiceDto> getAllServices() {
        List<ChambreServiceDto> listChambreServiceDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        chambreServiceService.findAll().stream()
                .forEach(c -> listChambreServiceDto.add(modelMapper.map(c, ChambreServiceDto.class)));
        return listChambreServiceDto;
    }

    @GetMapping("/chambreServices/{id}")
    public ResponseEntity<ChambreServiceDto> getChambreService(@PathVariable(name = "id") String id) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            return new ResponseEntity<>(modelMapper.map(chambreServiceService.getById(id), ChambreServiceDto.class), 
            HttpStatus.FOUND);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new ChambreServiceDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/chambreServices")
    public ChambreServiceDto saveChambreService(@RequestBody ChambreServiceDto chambreService) {
        ModelMapper modelMapper = new ModelMapper();
        chambreServiceService.save(modelMapper.map(chambreService, ChambreService.class));
        return chambreService;
    }

    @PutMapping("/chambreServices")
    public ChambreServiceDto updateChambreService(@RequestBody ChambreServiceDto chambreService) {
        ModelMapper modelMapper = new ModelMapper();
        chambreServiceService.update(modelMapper.map(chambreService, ChambreService.class));
        return chambreService;
    }

    @DeleteMapping("/chambreServices/{id}")
    public void deleteChambreService(@PathVariable(name = "id") String id) {
        try {
            chambreServiceService.deleteById(id);
        } catch (RepositoryExeption e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @GetMapping("/chambreServices/test/")
    public void getTestServices() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ChambreServiceDto> result = restTemplate.exchange("http://localhost:8080/api/chambreServices/{id}", HttpMethod.GET, null, ChambreServiceDto.class, UUID.randomUUID().toString());
        System.err.println("************************** "+result);
    }
}
