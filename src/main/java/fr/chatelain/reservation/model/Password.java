package fr.chatelain.reservation.model;

import javax.persistence.Column;
import javax.persistence.Entity;

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
public class Password extends AbstractEntities {

    private static final long serialVersionUID = 6338037257230920268L;

    @Column
    private String hash;

    @Column
    private String salt;
}
