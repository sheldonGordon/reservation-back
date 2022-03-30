package fr.chatelain.reservation.controller;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.Compte;
import fr.chatelain.reservation.model.dto.CompteDto;
import fr.chatelain.reservation.service.CompteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @GetMapping("/comptes")
    public ResponseEntity<List<CompteDto>> getAllCompte(){
        List<CompteDto> listCompteDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try {
            compteService.findAll().forEach(c -> listCompteDto.add(modelMapper.map(c, CompteDto.class)));
            return new ResponseEntity<>(listCompteDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/comptes/{id}")
    public ResponseEntity<CompteDto> getCompte(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();
        try {
            return new ResponseEntity<>(modelMapper.map(compteService.getById(id), CompteDto.class), HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new CompteDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/compte")
    public ResponseEntity<CompteDto> saveCompte(@RequestBody CompteDto compte) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Compte entity = modelMapper.map(compte, Compte.class);
            compteService.save(entity);
            return new ResponseEntity<>(compte, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new CompteDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/compte")
    public ResponseEntity<CompteDto> updateOption(@RequestBody CompteDto compte) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            compteService.update(modelMapper.map(compte, Compte.class));
            return new ResponseEntity<>(compte, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new CompteDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/comptes/{id}")
    public ResponseEntity<String> deleteOption(@PathVariable(name = "id") String id) {
        try {
            compteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
