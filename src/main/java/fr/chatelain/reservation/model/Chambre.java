package fr.chatelain.reservation.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
public class Chambre extends AbstractEntities {

        private static final long serialVersionUID = 1365648515210158917L;

        @Column
        private String nom;

        @Column
        private int nombrePersonne;

        @Column
        private BigDecimal prix;

        @Column
        private double superficie;

        @OneToMany
        private List<Photos> photos;

        @ManyToMany
        @JoinTable(name = "chambre_services", joinColumns = {
                        @JoinColumn(name = "chambre_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
                                        @JoinColumn(name = "services_id", referencedColumnName = "id", nullable = false, updatable = false) })
        private List<ChambreService> services;

        @ManyToMany
        @JoinTable(name = "chambre_indisponibilites", joinColumns = {
                        @JoinColumn(name = "chambre_id", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
                                        @JoinColumn(name = "indisponibilites_id", referencedColumnName = "id", nullable = false, updatable = false) })
        private List<DateDebutFin> indisponibilites;
}
