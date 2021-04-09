package fr.chatelain.reservation.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IGenericService<T> {

    T getById(final String id);

    List<T> findAll();

    void save(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final String entityId);
}
