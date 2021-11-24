package fr.chatelain.reservation.repository.common;

import java.util.List;
import java.util.Optional;

import fr.chatelain.reservation.exceptions.RepositoryExeption;
import fr.chatelain.reservation.model.AbstractEntities;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericRepository<T extends AbstractEntities> {

    Optional<T> getById(final String id, Class<T> type) throws RepositoryExeption;

    List<T> findAll(Class<T> type) throws RepositoryExeption;

    T save(final T entity, Class<T> type) throws RepositoryExeption;

    T update(final T entity, Class<T> type) throws RepositoryExeption;

    void deleteById(final String id, Class<T> type) throws RepositoryExeption;
}