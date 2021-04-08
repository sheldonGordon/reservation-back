package fr.chatelain.reservation.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
public class Reservation extends AbstractEntities {

    private static final long serialVersionUID = -1872170167391711254L;

    @OneToOne
    private DateDebutFin dateDebutfin;

    @ManyToOne
    private Chambre chambre;

    @ManyToMany
    private List<Option> options;

    @ManyToOne
    private Compte client;

    @Column(columnDefinition = "boolean default false")
    private boolean annulation;

    @ManyToOne
    private CodePromo promotion;
}
