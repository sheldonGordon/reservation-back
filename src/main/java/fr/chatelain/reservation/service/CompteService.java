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
public class CompteService implements IGenericService<Compte> {

    private GenericJpaRepository<Compte> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<Compte> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public Compte getInstance() {
        return FactoryReservation.getInstanceCompte();
    }

    public Compte getInstance(Personne personne, List<Role> roles) {
        return FactoryReservation.getInstanceCompte(personne, roles);
    }

    @Override
    public Optional<Compte> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, Compte.class);
    }

    @Override
    public List<Compte> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(Compte.class);
    }

    @Override
    public Compte save(Compte entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, Compte.class);
    }

    @Override
    public Compte update(Compte entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, Compte.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, Compte.class);
    }
}
