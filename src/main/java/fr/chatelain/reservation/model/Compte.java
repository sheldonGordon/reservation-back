package fr.chatelain.reservation.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
public class Compte extends AbstractEntities {

    private static final long serialVersionUID = 8527285856951457997L;

    @OneToOne
    private Personne personne;

    @OneToOne
    private Password password;

    @ManyToMany
    private List<Role> roles;
}
