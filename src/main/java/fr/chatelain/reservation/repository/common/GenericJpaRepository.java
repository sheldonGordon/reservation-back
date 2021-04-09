package fr.chatelain.reservation.repository.common;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class GenericJpaRepository<T extends Serializable> extends AbstractJpaRepository<T> {

}