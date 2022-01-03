package fr.chatelain.reservation.model.dto;

import lombok.Data;

@Data
public class OptionDto {
    private String id;
    private String libelle;
    private double prix;
}
