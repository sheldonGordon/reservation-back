package fr.chatelain.reservation.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateDebutFinDto {
    private LocalDate dateDebut;

    private LocalDate dateFin;
}
