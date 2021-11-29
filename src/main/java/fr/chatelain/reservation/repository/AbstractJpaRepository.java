package fr.chatelain.reservation.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.AbstractEntities;

public abstract class AbstractJpaRepository<T extends AbstractEntities> implements IGenericRepository<T> {

    @Autowired
    private EntityManager em;

    @Override
    public Optional<T> getById(String id, Class<T> type) throws RepositoryExeption {
        Optional<T> result = this.getRepository(type).findById(id);
        if (result.isPresent()) {
            return result;
        } else {
            throw new RepositoryExeption(
                    String.format("Aucun objet trouvé de type %s pour l'id %s", type.getName(), id));
        }
    }

    @Override
    public List<T> findAll(Class<T> type) throws RepositoryExeption {
        List<T> result = this.getRepository(type).findAll();
        if (!result.isEmpty()) {
            return this.getRepository(type).findAll();
        } else {
            throw new RepositoryExeption(String.format("Aucun objet trouvé de type %s", type.getName()));
        }
    }

    @Override
    public T save(T entity, Class<T> type) throws RepositoryExeption {
        if (entity.getId() != null && !entity.getId().isEmpty()) {
            return this.getRepository(type).save(entity);
        } else {
            throw new RepositoryExeption(String.format("Objet de type %s non enregistré", type.getName()));
        }
    }

    @Override
    public T update(T entity, Class<T> type) throws RepositoryExeption {
        if (entity.getId() != null && !entity.getId().isEmpty()) {
            return this.getRepository(type).saveAndFlush(entity);
        } else {
            throw new RepositoryExeption(String.format("Objet de type %s non mis à jour", type.getName()));
        }
    }

    @Override
    public void deleteById(String entityId, Class<T> type) throws RepositoryExeption {
        try {
            this.getRepository(type).deleteById(entityId);
        } catch (Exception e) {
            throw new RepositoryExeption(String.format("Objet de type %s non supprimé", type.getName()));
        }
    }

    // Permet l'aiguillage pour pointer sur la bonne référence
    private <T extends Serializable> SimpleJpaRepository<T, String> getRepository(Class<T> type) {
        return new SimpleJpaRepository<T, String>(type, em);
    }
}