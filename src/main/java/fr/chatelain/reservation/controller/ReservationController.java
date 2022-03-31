package fr.chatelain.reservation.controller;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.Reservation;
import fr.chatelain.reservation.model.dto.ReservationDto;
import fr.chatelain.reservation.service.ReservationService;
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
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationDto>> getAllReservations(){
        List<ReservationDto> listReservationDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        try {
            reservationService.findAll().forEach(c -> listReservationDto.add(modelMapper.map(c, ReservationDto.class)));
            return new ResponseEntity<>(listReservationDto, HttpStatus.OK);
        }catch (RepositoryExeption e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable(name = "id") String id){
        ModelMapper modelMapper = new ModelMapper();
        try {
            return new ResponseEntity<>(modelMapper.map(reservationService.getById(id).get(), ReservationDto.class), HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new ReservationDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/reservation")
    public ResponseEntity<ReservationDto> saveReservation(@RequestBody ReservationDto reservation) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            Reservation entity = modelMapper.map(reservation, Reservation.class);
            reservationService.save(entity);
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new ReservationDto(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/reservation")
    public ResponseEntity<ReservationDto> updateReservation(@RequestBody ReservationDto reservation) {
        ModelMapper modelMapper = new ModelMapper();
        try {
            reservationService.update(modelMapper.map(reservation, Reservation.class));
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(new ReservationDto(), HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable(name = "id") String id) {
        try {
            reservationService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RepositoryExeption e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
