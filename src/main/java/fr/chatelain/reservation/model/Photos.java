package fr.chatelain.reservation.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
public class Photos extends AbstractEntities {

    private static final long serialVersionUID = 7714218428585395888L;

    @Column(columnDefinition = "text")
    private String data;

    @Column
    private String nom;

    @Column
    private String typeMime;
}
