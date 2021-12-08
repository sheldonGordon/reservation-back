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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DateDebutFinService implements IGenericService<DateDebutFin> {

    private GenericJpaRepository<DateDebutFin> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<DateDebutFin> repositoryToSet) {
        this.genericJpaRepository = repositoryToSet;
    }

    public DateDebutFin getInstance() {
        return FactoryReservation.getInstanceDateDebutFin();
    }

    public DateDebutFin getInstance(LocalDate dateDebut, LocalDate dateFin) {
        return FactoryReservation.getInstanceDateDebutFin(dateDebut, dateFin);
    }

    @Override
    public Optional<DateDebutFin> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, DateDebutFin.class);
    }

    @Override
    public List<DateDebutFin> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(DateDebutFin.class);
    }

    @Override
    public DateDebutFin save(DateDebutFin entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, DateDebutFin.class);
    }

    @Override
    public DateDebutFin update(DateDebutFin entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, DateDebutFin.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, DateDebutFin.class);
    }
}
