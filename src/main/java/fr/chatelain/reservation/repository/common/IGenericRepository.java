package fr.chatelain.reservation.repository.common;

import java.io.Serializable;
import java.util.List;

public interface IGenericRepository<T extends Serializable> {

    T getById(final String id);

    List<T> findAll();

    void save(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final String entityId);
}