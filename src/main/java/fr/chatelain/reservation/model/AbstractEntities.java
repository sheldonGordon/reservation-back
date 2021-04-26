package fr.chatelain.reservation.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EqualsAndHashCode(of = "id")
public abstract class AbstractEntities implements Serializable {

    private static final long serialVersionUID = 5601493523785739869L;

    protected AbstractEntities() {
        this.id = UUID.randomUUID().toString();
    }

    @Id
    @Getter
    @Setter
    private String id;
}