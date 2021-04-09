package fr.chatelain.reservation.repository.common;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractJpaRepository<T extends Serializable> implements IGenericRepository<T> {

    private Class<T> clazz;

    @Autowired
    private EntityManager em;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @Override
    public T getById(String id) {
        return em.find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<T> q = cb.createQuery(clazz);
        Root<T> classe = q.from(clazz);

        q.select(classe);

        TypedQuery<T> query = em.createQuery(q);

        return query.getResultList();
    }

    @Override
    public void save(T entity) {
        em.persist(entity);
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public void deleteById(String entityId) {
        T entity = getById(entityId);
        delete(entity);
    }
}