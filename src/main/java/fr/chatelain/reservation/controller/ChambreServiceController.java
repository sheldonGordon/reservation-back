package fr.chatelain.reservation.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.chatelain.reservation.model.ChambreService;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.model.dto.ChambreServiceDto;
import fr.chatelain.reservation.service.ChambreServiceService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ChambreServiceController {

    @Autowired
    private ChambreServiceService chambreServiceService;

    @GetMapping("/")
    public String getHelloWorld() {
        ChambreService cs1 = FactoryReservation.getInstanceChambreService("test 1");
        ChambreService cs2 = FactoryReservation.getInstanceChambreService("test 2");
        ChambreService cs3 = FactoryReservation.getInstanceChambreService("test 3");
        ChambreService cs4 = FactoryReservation.getInstanceChambreService("test 4");
        chambreServiceService.save(cs1);
        chambreServiceService.save(cs2);
        chambreServiceService.save(cs3);
        chambreServiceService.save(cs4);

        return "Hello World!";
    }

    @GetMapping("/chambreServices")
    public List<ChambreServiceDto> getAllServices() {
        List<ChambreServiceDto> listChambreServiceDto = new ArrayList<>(0);
        ModelMapper modelMapper = new ModelMapper();
        chambreServiceService.findAll().stream()
                .forEach(c -> listChambreServiceDto.add(modelMapper.map(c, ChambreServiceDto.class)));
        return listChambreServiceDto;
    }

    @PostMapping("/chambreServices")
    public ChambreServiceDto saveChambreService(@RequestParam(name = "libelle") String libelle) {
        ChambreService chambreService = FactoryReservation.getInstanceChambreService(libelle);

        chambreServiceService.save(chambreService);

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(chambreService, ChambreServiceDto.class);
    }

    @DeleteMapping("/chambreServices/{id}")
    public void deleteChambreService(@PathVariable(name = "id") String id) {
        chambreServiceService.deleteById(id);
    }
}
