package fr.chatelain.reservation.model.dto;

import lombok.Data;

@Data
public class CodePromoDto {
    private String id;
    private String code;
    private double pourcentage;
    private DateDebutFinDto validite;
}
