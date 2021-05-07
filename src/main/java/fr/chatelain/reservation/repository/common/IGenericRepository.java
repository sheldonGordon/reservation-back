package fr.chatelain.reservation.repository.common;

import java.util.List;
import java.util.Optional;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.AbstractEntities;

public interface IGenericRepository<T extends AbstractEntities> {

    Optional<T> getById(final String id) throws RepositoryExeption;

    List<T> findAll() throws RepositoryExeption;

    T save(final T entity) throws RepositoryExeption;

    T update(final T entity) throws RepositoryExeption;

    void deleteById(final String entityId) throws RepositoryExeption;
}