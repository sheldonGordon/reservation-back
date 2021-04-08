package fr.chatelain.reservation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodePromo extends AbstractEntities {

    private static final long serialVersionUID = 8505905252780920773L;

    @Column
    private String code;

    @Column
    private double pourcentage;

    @OneToOne
    private DateDebutFin validite;

}
