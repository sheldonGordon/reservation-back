package fr.chatelain.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.model.Photos;
import fr.chatelain.reservation.repository.GenericJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PhotosService implements IGenericService<Photos> {

    private GenericJpaRepository<Photos> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<Photos> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public Photos getInstance() {
        return FactoryReservation.getInstancePhotos();
    }

    public Photos getInstance(String data, String nom, String typeMime) {
        return FactoryReservation.getInstancePhotos(data, nom, typeMime);
    }

    @Override
    public void deleteById(String entityId) throws RepositoryExeption {
        genericJpaRepository.deleteById(entityId, Photos.class);
    }

    @Override
    public Photos update(Photos entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, Photos.class);
    }

    @Override
    public Optional<Photos> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, Photos.class);
    }

    @Override
    public List<Photos> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(Photos.class);
    }

    @Override
    public Photos save(Photos entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, Photos.class);
    }
    
}