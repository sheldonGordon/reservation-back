package fr.chatelain.reservation.controller;

import fr.chatelain.reservation.model.Photos;
import fr.chatelain.reservation.model.dto.PhotosDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.service.PhotosService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PhotosController {

    @Autowired
    private PhotosService photosService;

    @GetMapping("/photos")
    public ResponseEntity<List<PhotosDto>> getAllPhotos() {
        List<PhotosDto> listPhotosDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try {
            photosService.findAll()
                    .forEach(c -> listPhotosDto.add(modelMapper.map(c, PhotosDto.class)));
            return new ResponseEntity<>(listPhotosDto, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/photos/{id}")
    public ResponseEntity<PhotosDto> getPhotos(@PathVariable(name = "id") String id) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            return new ResponseEntity<>(modelMapper.map(photosService.getById(id), PhotosDto.class),
                    HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new PhotosDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/photos")
    public ResponseEntity<PhotosDto> savePhotos(@RequestBody PhotosDto photos) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Photos entity = modelMapper.map(photos, Photos.class);
            photosService.save(entity);
            return new ResponseEntity<>(photos, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new PhotosDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/photos")
    public ResponseEntity<PhotosDto> updatePhotos(@RequestBody PhotosDto photos) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            photosService.update(modelMapper.map(photos, Photos.class));
            return new ResponseEntity<>(photos, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new PhotosDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/photos/{id}")
    public ResponseEntity<String> deletePhotos(@PathVariable(name = "id") String id) {
        try {
            photosService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
