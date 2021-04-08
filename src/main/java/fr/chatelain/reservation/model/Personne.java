package fr.chatelain.reservation.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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
public class Personne extends AbstractEntities {

    private static final long serialVersionUID = -4725443028041336733L;

    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String mail;
    @Column
    private String telephone;
    @Enumerated(EnumType.STRING)
    private Civilite civilite;
    @Column
    private String adresse;
    @Column
    private LocalDate anniversaire;

}
