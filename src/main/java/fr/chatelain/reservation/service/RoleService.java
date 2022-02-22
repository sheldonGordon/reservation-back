package fr.chatelain.reservation.service;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.FactoryReservation;
import fr.chatelain.reservation.model.Role;
import fr.chatelain.reservation.repository.GenericJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService implements IGenericService<Role> {

    private GenericJpaRepository<Role> genericJpaRepository;

    @Autowired
    public void setGenericJpaRepository(GenericJpaRepository<Role> repositoryToSet) {
        genericJpaRepository = repositoryToSet;
    }

    public Role getInstance() {
        return FactoryReservation.getInstanceRole();
    }

    public Role getInstance(String libelle) {
        return FactoryReservation.getInstanceRole(libelle);
    }

    @Override
    public Optional<Role> getById(String id) throws RepositoryExeption {
        return genericJpaRepository.getById(id, Role.class);
    }

    @Override
    public List<Role> findAll() throws RepositoryExeption {
        return genericJpaRepository.findAll(Role.class);
    }

    @Override
    public Role save(Role entity) throws RepositoryExeption {
        return genericJpaRepository.save(entity, Role.class);
    }

    @Override
    public Role update(Role entity) throws RepositoryExeption {
        return genericJpaRepository.update(entity, Role.class);
    }

    @Override
    public void deleteById(String id) throws RepositoryExeption {
        genericJpaRepository.deleteById(id, Role.class);
    }
}
