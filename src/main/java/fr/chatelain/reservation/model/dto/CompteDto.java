package fr.chatelain.reservation.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompteDto {
    private String id;
    private PersonneDto personne;
    private List<RoleDto> roles;
}
