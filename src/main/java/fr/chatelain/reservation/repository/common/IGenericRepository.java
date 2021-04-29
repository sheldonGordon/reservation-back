package fr.chatelain.reservation.repository.common;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import fr.chatelain.reservation.exceptions.RepositoryExeption;

public interface IGenericRepository<T extends Serializable> {

    Optional<T> getById(final String id) throws RepositoryExeption;

    List<T> findAll();

    T save(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final String entityId) throws RepositoryExeption;
}