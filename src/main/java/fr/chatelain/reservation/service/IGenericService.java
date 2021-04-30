package fr.chatelain.reservation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.ChambreService;

@Transactional
public interface IGenericService<T> {

    Optional<ChambreService> getById(final String id) throws RepositoryExeption;

    List<T> findAll() throws RepositoryExeption;

    T save(final T entity) throws RepositoryExeption;

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final String entityId) ;
}
