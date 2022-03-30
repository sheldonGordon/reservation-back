package fr.chatelain.reservation.controller;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.Option;
import fr.chatelain.reservation.model.dto.OptionDto;
import fr.chatelain.reservation.service.OptionService;
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
public class OptionController {

    @Autowired
    private OptionService optionService;

    @GetMapping("/options")
    public ResponseEntity<List<OptionDto>> getAllOptions(){
        List<OptionDto> listOptionDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try {
            optionService.findAll().forEach(c -> listOptionDto.add(modelMapper.map(c, OptionDto.class)));
            return new ResponseEntity<>(listOptionDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/options/{id}")
    public ResponseEntity<OptionDto> getOption(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();
        try {
            return new ResponseEntity<>(modelMapper.map(optionService.getById(id), OptionDto.class), HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new OptionDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/option")
    public ResponseEntity<OptionDto> saveOption(@RequestBody OptionDto option) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Option entity = modelMapper.map(option, Option.class);
            optionService.save(entity);
            return new ResponseEntity<>(option, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new OptionDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/option")
    public ResponseEntity<OptionDto> updateOption(@RequestBody OptionDto option) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            optionService.update(modelMapper.map(option, Option.class));
            return new ResponseEntity<>(option, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new OptionDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/options/{id}")
    public ResponseEntity<String> deleteOption(@PathVariable(name = "id") String id) {
        try {
            optionService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
