package fr.chatelain.reservation.service;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.*;
import fr.chatelain.reservation.repository.GenericJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService implements IGenericService<Reservation> {

    private GenericJpaRepository<Reservation> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<Reservation> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public Reservation getInstance() {
        return FactoryReservation.getInstanceReservation();
    }

    public Reservation getInstance(DateDebutFin dateDebutfin, Chambre chambre, List<Option> options, Compte client, boolean annulation, CodePromo promotion) {
        return FactoryReservation.getInstanceReservation(dateDebutfin, chambre, options, client, annulation, promotion);
    }

    @Override
    public Optional<Reservation> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, Reservation.class);
    }

    @Override
    public List<Reservation> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(Reservation.class);
    }

    @Override
    public Reservation save(Reservation entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, Reservation.class);
    }

    @Override
    public Reservation update(Reservation entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, Reservation.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, Reservation.class);
    }
}
