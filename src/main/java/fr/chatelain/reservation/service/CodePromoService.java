package fr.chatelain.reservation.service;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.CodePromo;
import fr.chatelain.reservation.model.DateDebutFin;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.repository.GenericJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CodePromoService implements IGenericService<CodePromo> {

    private GenericJpaRepository<CodePromo> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<CodePromo> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public CodePromo getInstance() {
        return FactoryReservation.getInstanceCodePromo();
    }

    public CodePromo getInstance(String code, double pourcentage, DateDebutFin validite) {
        return FactoryReservation.getInstanceCodePromo(code, pourcentage, validite);
    }

    @Override
    public Optional<CodePromo> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, CodePromo.class);
    }

    @Override
    public List<CodePromo> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(CodePromo.class);
    }

    @Override
    public CodePromo save(CodePromo entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, CodePromo.class);
    }

    @Override
    public CodePromo update(CodePromo entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, CodePromo.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, CodePromo.class);
    }
}
