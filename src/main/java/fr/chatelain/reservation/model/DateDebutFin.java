package fr.chatelain.reservation.model;

import java.time.LocalDate;

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
public class DateDebutFin extends AbstractEntities {

    private static final long serialVersionUID = 3367530931755645512L;

    @Column
    private LocalDate dateDebut;

    @Column
    private LocalDate dateFin;
}
