package fr.chatelain.reservation.service;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.model.Option;
import fr.chatelain.reservation.model.Photos;
import fr.chatelain.reservation.repository.GenericJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OptionService implements IGenericService<Option> {

    private GenericJpaRepository<Option> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<Option> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public Option getInstance() {
        return FactoryReservation.getInstanceOption();
    }

    public Option getInstance(String libelle, double prix) {
        return FactoryReservation.getInstanceOption(libelle, prix);
    }

    @Override
    public Optional<Option> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, Option.class);
    }

    @Override
    public List<Option> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(Option.class);
    }

    @Override
    public Option save(Option entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, Option.class);
    }

    @Override
    public Option update(Option entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, Option.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, Option.class);
    }
}
