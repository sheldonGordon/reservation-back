package fr.chatelain.reservation.repository;

import org.springframework.stereotype.Repository;

import fr.chatelain.reservation.model.AbstractEntities;

@Repository
public class GenericJpaRepository<T extends AbstractEntities> extends AbstractJpaRepository<T> {

}