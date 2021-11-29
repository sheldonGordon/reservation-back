package fr.chatelain.reservation.controller;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.Chambre;
import fr.chatelain.reservation.model.dto.ChambreDto;
import fr.chatelain.reservation.model.dto.ChambreServiceDto;
import fr.chatelain.reservation.service.ChambreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ChambreController {

    @Autowired
    private ChambreService chambreService;

    @GetMapping("/chambres")
    public ResponseEntity<List<ChambreDto>> getAllChambre(){
        List<ChambreDto> listeChambreDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try{
            chambreService.findAll().forEach(c -> listeChambreDto.add(modelMapper.map(c, ChambreDto.class)));
            return new ResponseEntity<>(listeChambreDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/chambres/{id}")
    public ResponseEntity<ChambreDto> getChambre(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();
        try{
            return new ResponseEntity<>(modelMapper.map(chambreService.getById(id), ChambreDto.class), HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ChambreDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/chambre")
    public ResponseEntity<ChambreDto> saveChambre(@RequestBody ChambreDto chambre) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Chambre entity = modelMapper.map(chambre, Chambre.class);
            chambreService.save(entity);
            return new ResponseEntity<>(chambre, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new ChambreDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/chambre")
    public ResponseEntity<ChambreDto> updateChambre(@RequestBody ChambreDto chambre) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            chambreService.update(modelMapper.map(chambre, Chambre.class));
            return new ResponseEntity<>(chambre, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new ChambreDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/chambre/{id}")
    public ResponseEntity<String> deleteChambre(@PathVariable(name = "id") String id) {
        try {
            chambreService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
