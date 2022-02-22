package fr.chatelain.reservation.service;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.model.Personne;
import fr.chatelain.reservation.repository.GenericJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonneService implements IGenericService<Personne> {

    private GenericJpaRepository<Personne> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<Personne> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public Personne getInstance() {
        return FactoryReservation.getInstancePersonne();
    }

    public Personne getInstance(String nom, String prenom) {
        return FactoryReservation.getInstancePersonne(nom, prenom);
    }
    @Override
    public Optional<Personne> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, Personne.class);
    }

    @Override
    public List<Personne> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(Personne.class);
    }

    @Override
    public Personne save(Personne entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, Personne.class);
    }

    @Override
    public Personne update(Personne entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, Personne.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, Personne.class);
    }
}
