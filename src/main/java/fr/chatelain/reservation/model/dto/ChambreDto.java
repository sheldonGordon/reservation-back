package fr.chatelain.reservation.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ChambreDto {
    private String nom;

    private int nombrePersonne;

    private BigDecimal prix;

    private double superficie;

    private List<PhotosDto> photos;

    private List<ChambreServiceDto> services;

    private List<DateDebutFinDto> indisponibilites;
}
