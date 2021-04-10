package fr.chatelain.reservation.repository.common;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public class GenericJpaRepository<T extends Serializable> extends AbstractJpaRepository<T> {

}