package fr.chatelain.reservation.repository.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.AbstractEntities;

public abstract class AbstractJpaRepository<T extends AbstractEntities> implements IGenericRepository<T> {

    private Class<T> clazz;

    @Autowired
    private EntityManager em;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @Override
    public Optional<T> getById(String id) throws RepositoryExeption {
        Optional<T> result = null;
        
        result = this.getRepository(clazz).findById(id);
        if(result.isPresent()){
            return result;
        }else{
            throw new RepositoryExeption(String.format("Aucun objet trouvé de type %s pour l'id %s", this.clazz.getName(), id));
        }
    }

    @Override
    public List<T> findAll() throws RepositoryExeption {
        List<T> result = new ArrayList<>(0);

        result = this.getRepository(clazz).findAll();

        if(!result.isEmpty()){
            return this.getRepository(clazz).findAll();
        }else{
            throw new RepositoryExeption(String.format("Aucun objet trouvé de type %s", this.clazz.getName()));
        }        
    }

    @Override
    public T save(T entity) throws RepositoryExeption {
        if(entity.getId() != null && !entity.getId().isEmpty()){
            return this.getRepository(clazz).save(entity);
        }else{
            throw new RepositoryExeption(String.format("Objet de type %s non enregistré", this.clazz.getName()));
        }            
    }

    @Override
    public T update(T entity) {
        return this.getRepository(clazz).saveAndFlush(entity);
    }

    @Override
    public void delete(T entity) {
        this.getRepository(clazz).delete(entity);
    }

    @Override
    public void deleteById(String entityId) {
        this.getRepository(clazz).deleteById(entityId);
    }

    // Permet l'aiguillage pour pointer sur la bonne référence
    private <T extends Serializable> SimpleJpaRepository<T, String> getRepository(Class<T> classe){
        return new SimpleJpaRepository<>(classe, em);
    }
}