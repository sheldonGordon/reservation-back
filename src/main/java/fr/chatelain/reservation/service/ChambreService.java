package fr.chatelain.reservation.service;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.Chambre;
import fr.chatelain.reservation.model.DateDebutFin;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.model.Photos;
import fr.chatelain.reservation.repository.GenericJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChambreService implements IGenericService<Chambre>{

    private GenericJpaRepository<Chambre> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<Chambre> repositoryToSet) {
        this.genericJpaRepository = repositoryToSet;
    }

    public Chambre getInstance() {
        return FactoryReservation.getInstanceChambre();
    }

    public Chambre getInstance(String nom, int nombrePersonne, BigDecimal prix, double superficie, List<Photos> photos, List<fr.chatelain.reservation.model.ChambreService> services, List<DateDebutFin> indisponibilites) {
        return FactoryReservation.getInstanceChambre(nom, nombrePersonne, prix, superficie, photos, services, indisponibilites);
    }

    @Override
    public Optional<Chambre> getById(String id) throws RepositoryExeption {
        return Optional.empty();
    }

    @Override
    public List<Chambre> findAll() throws RepositoryExeption {
        return null;
    }

    @Override
    public Chambre save(Chambre entity) throws RepositoryExeption {
        return null;
    }

    @Override
    public Chambre update(Chambre entity) throws RepositoryExeption {
        return null;
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {

    }
}
