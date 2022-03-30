package fr.chatelain.reservation.controller;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.Personne;
import fr.chatelain.reservation.model.dto.PersonneDto;
import fr.chatelain.reservation.service.PersonneService;
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
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @GetMapping("/personnes")
    public ResponseEntity<List<PersonneDto>> getAllPersonnes(){
        List<PersonneDto> listCodePromoDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try {
            personneService.findAll().forEach(c -> listCodePromoDto.add(modelMapper.map(c, PersonneDto.class)));
            return new ResponseEntity<>(listCodePromoDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/personnes/{id}")
    public ResponseEntity<PersonneDto> getPersonne(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();
        try {
            return new ResponseEntity<>(modelMapper.map(personneService.getById(id), PersonneDto.class), HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new PersonneDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/personne")
    public ResponseEntity<PersonneDto> savePersonne(@RequestBody PersonneDto personne) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Personne entity = modelMapper.map(personne, Personne.class);
            personneService.save(entity);
            return new ResponseEntity<>(personne, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new PersonneDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/personne")
    public ResponseEntity<PersonneDto> updatePersonne(@RequestBody PersonneDto personne) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            personneService.update(modelMapper.map(personne, Personne.class));
            return new ResponseEntity<>(personne, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new PersonneDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/personnes/{id}")
    public ResponseEntity<String> deletePersonne(@PathVariable(name = "id") String id) {
        try {
            personneService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
