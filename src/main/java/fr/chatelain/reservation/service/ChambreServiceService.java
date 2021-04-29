package fr.chatelain.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.ChambreService;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.repository.common.GenericJpaRepository;

@Service
public class ChambreServiceService implements IGenericService<ChambreService> {

    private GenericJpaRepository<ChambreService> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<ChambreService> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
        genericJpaRepository.setClazz(ChambreService.class);
    }

    public ChambreService getInstance() {
        return FactoryReservation.getInstanceChambreService();
    }

    public ChambreService getInstance(String libelle) {
        return FactoryReservation.getInstanceChambreService(libelle);
    }

    @Override
    public Optional<ChambreService> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id);
    }

    @Override
    public List<ChambreService> findAll() {
        return genericJpaRepository.findAll();
    }

    @Override
    public ChambreService save(ChambreService entity) {
        return genericJpaRepository.save(entity);
    }

    @Override
    public ChambreService update(ChambreService entity) {
        return genericJpaRepository.update(entity);
    }

    @Override
    public void delete(ChambreService entity) {
        genericJpaRepository.delete(entity);
    }

    @Override
    public void deleteById(String entityId) throws RepositoryExeption {
        genericJpaRepository.deleteById(entityId);
    }
}
