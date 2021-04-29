package fr.chatelain.reservation.repository.common;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import fr.chatelain.reservation.exceptions.RepositoryExeption;

public abstract class AbstractJpaRepository<T extends Serializable> implements IGenericRepository<T> {

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
    public List<T> findAll() {
        return this.getRepository(clazz).findAll();
    }

    @Override
    public T save(T entity) {
        return this.getRepository(clazz).save(entity);
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