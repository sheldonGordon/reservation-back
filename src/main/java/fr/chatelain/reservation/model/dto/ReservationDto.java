package fr.chatelain.reservation.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReservationDto {
    private String id;
    private DateDebutFinDto dateDebutfin;
    private ChambreDto chambre;
    private List<OptionDto> options;
    private CompteDto client;
    private boolean annulation;
    private CodePromoDto promotion;
}
