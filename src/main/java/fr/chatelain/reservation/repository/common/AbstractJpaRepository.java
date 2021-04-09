package fr.chatelain.reservation.repository.common;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractJpaRepository<T extends Serializable> {

    private Class<T> clazz;

    @Autowired
    private EntityManager em;

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T getById(String id) {
        return em.find(clazz, id);
    }

    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<T> q = cb.createQuery(clazz);
        Root<T> classe = q.from(clazz);

        q.select(classe);

        TypedQuery<T> query = em.createQuery(q);

        return query.getResultList();
    }

    public void save(T entity) {
        em.persist(entity);
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public void delete(T entity) {
        em.remove(entity);
    }

    public void deleteById(String entityId) {
        T entity = getById(entityId);
        delete(entity);
    }
}