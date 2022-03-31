package fr.chatelain.reservation.controller;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.CodePromo;
import fr.chatelain.reservation.model.dto.CodePromoDto;
import fr.chatelain.reservation.service.CodePromoService;
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
public class CodePromoController {

    @Autowired
    private CodePromoService codePromoService;

    @GetMapping("/codePromos")
    public ResponseEntity<List<CodePromoDto>> getAllCodePromo(){
        List<CodePromoDto> listCodePromoDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try {
            codePromoService.findAll().forEach(c -> listCodePromoDto.add(modelMapper.map(c, CodePromoDto.class)));
            return new ResponseEntity<>(listCodePromoDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/codePromos/{id}")
    public ResponseEntity<CodePromoDto> getCodePromo(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();
        try {
            return new ResponseEntity<>(modelMapper.map(codePromoService.getById(id).get(), CodePromoDto.class), HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new CodePromoDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/codePromo")
    public ResponseEntity<CodePromoDto> saveCodePromo(@RequestBody CodePromoDto codePromo) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            CodePromo entity = modelMapper.map(codePromo, CodePromo.class);
            codePromoService.save(entity);
            return new ResponseEntity<>(codePromo, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new CodePromoDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/codePromo")
    public ResponseEntity<CodePromoDto> updateOption(@RequestBody CodePromoDto codePromo) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            codePromoService.update(modelMapper.map(codePromo, CodePromo.class));
            return new ResponseEntity<>(codePromo, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new CodePromoDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/codePromos/{id}")
    public ResponseEntity<String> deleteOption(@PathVariable(name = "id") String id) {
        try {
            codePromoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
