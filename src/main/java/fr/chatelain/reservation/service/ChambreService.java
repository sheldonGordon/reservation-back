package fr.chatelain.reservation.service;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.Chambre;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ChambreService implements IGenericService<Chambre>{
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
