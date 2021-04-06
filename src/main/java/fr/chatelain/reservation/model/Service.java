package fr.chatelain.reservation.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Service extends AbstractEntities {

    private static final long serialVersionUID = 1L;

    @Column
    private String libelle;

    public Service() {
        super();
    }

    public Service(String libelle) {
        super();
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

}
