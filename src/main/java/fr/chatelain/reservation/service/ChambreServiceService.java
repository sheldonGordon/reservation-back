package fr.chatelain.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.model.ChambreService;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.repository.ChambreServiceRepository;

@Service
public class ChambreServiceService {

    @Autowired
    private ChambreServiceRepository serviceRepository;

    public ChambreService getInstance() {
        return FactoryReservation.getInstanceService();
    }

    public List<ChambreService> findAll() {
        return serviceRepository.findAll();
    }

    public void save(ChambreService chambreService) {
        serviceRepository.save(chambreService);
    }
}
