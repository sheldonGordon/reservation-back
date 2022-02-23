package fr.chatelain.reservation.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FactoryReservation {

    private FactoryReservation() {
    }

    public static ChambreService getInstanceChambreService() {
        return new ChambreService();
    }

    public static ChambreService getInstanceChambreService(String libelle) {
        return new ChambreService(libelle);
    }

    public static Photos getInstancePhotos() {
        return new Photos();
    }

    public static Photos getInstancePhotos(String data, String nom, String typeMime) {
        return new Photos(data, nom, typeMime);
    }

    public static Chambre getInstanceChambre() {
        return new Chambre();
    }

    public static Chambre getInstanceChambre(String nom, int nombrePersonne, BigDecimal prix, double superficie, List<Photos> photos, List<ChambreService> services, List<DateDebutFin> indisponibilites) {
        return new Chambre(nom, nombrePersonne, prix, superficie, photos, services, indisponibilites);
    }

    public static DateDebutFin getInstanceDateDebutFin() {
        return new DateDebutFin();
    }

    public static DateDebutFin getInstanceDateDebutFin(LocalDate dateDebut, LocalDate dateFin) {
        return new DateDebutFin(dateDebut, dateFin);
    }

    public static Option getInstanceOption() {
        return new Option();
    }

    public static Option getInstanceOption(String libelle, double prix) {
        return new Option(libelle, prix);
    }

    public static CodePromo getInstanceCodePromo() {
        return new CodePromo();
    }

    public static CodePromo getInstanceCodePromo(String code, double pourcentage, DateDebutFin validite) {
        return new CodePromo(code, pourcentage, validite);
    }

    public static Personne getInstancePersonne() {
        return new Personne();
    }

    public static Personne getInstancePersonne(String nom, String prenom) {
        return new Personne(nom, prenom);
    }

    public static Role getInstanceRole() {
        return new Role();
    }

    public static Role getInstanceRole(String libelle) {
        return new Role(libelle);
    }

    public static Compte getInstanceCompte() {
        return new Compte();
    }

    public static Compte getInstanceCompte(Personne personne, List<Role> roles) {
        return new Compte(personne, roles);
    }

    public static Reservation getInstanceReservation() {
        return new Reservation();
    }

    public static Reservation getInstanceReservation(DateDebutFin dateDebutfin, Chambre chambre, List<Option> options, Compte client, boolean annulation, CodePromo promotion) {
        return new Reservation(dateDebutfin, chambre, options, client, annulation, promotion);
    }
}
