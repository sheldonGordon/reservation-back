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
public class Role extends AbstractEntities {

    private static final long serialVersionUID = 3994768534377368475L;

    @Column
    private String libelle;
}
