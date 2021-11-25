package fr.chatelain.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.ChambreService;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.repository.common.GenericJpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ChambreServiceService implements IGenericService<ChambreService> {

    private GenericJpaRepository<ChambreService> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<ChambreService> repositoryToSet) {
        this.genericJpaRepository = repositoryToSet;
    }

    public ChambreService getInstance() {
        return FactoryReservation.getInstanceChambreService();
    }

    public ChambreService getInstance(String libelle) {
        return FactoryReservation.getInstanceChambreService(libelle);
    }

    @Override
    public Optional<ChambreService> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, ChambreService.class);
    }

    @Override
    public List<ChambreService> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(ChambreService.class);
    }

    @Override
    public ChambreService save(ChambreService entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, ChambreService.class);
    }

    @Override
    public ChambreService update(ChambreService entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, ChambreService.class);
    }

    @Override
    public void deleteById(String entityId) throws RepositoryExeption {
        genericJpaRepository.deleteById(entityId, ChambreService.class);
    }
}