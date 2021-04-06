package fr.chatelain.reservation.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Compte extends AbstractEntities {

    private static final long serialVersionUID = 1L;

    @OneToOne
    private Personne personne;

    @OneToOne
    private Password password;

    @ManyToMany
    private List<Role> roles;

    public Compte() {
        super();
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

}
