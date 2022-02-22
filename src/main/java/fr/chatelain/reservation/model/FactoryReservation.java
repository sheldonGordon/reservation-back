package fr.chatelain.reservation.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FactoryReservation {

    private FactoryReservation() {
    }

    public static ChambreService getInstanceChambreService() { return new ChambreService(); }

    public static ChambreService getInstanceChambreService(String libelle) {
        return new ChambreService(libelle);
    }

    public static Photos getInstancePhotos() {
        return new Photos();
    }

    public static Photos getInstancePhotos(String data, String nom, String typeMime) {
        return new Photos(data,nom,typeMime);
    }

    public static Chambre getInstanceChambre(){
        return new Chambre();
    }

    public static Chambre getInstanceChambre(String nom, int nombrePersonne, BigDecimal prix, double superficie, List<Photos> photos, List<ChambreService> services, List<DateDebutFin> indisponibilites){
        return new Chambre(nom, nombrePersonne, prix, superficie, photos, services, indisponibilites);
    }

    public static DateDebutFin getInstanceDateDebutFin() { return new DateDebutFin(); }

    public static DateDebutFin getInstanceDateDebutFin(LocalDate dateDebut, LocalDate dateFin) {
        return new DateDebutFin(dateDebut, dateFin);
    }

    public static Option getInstanceOption(){
        return new Option();
    }

    public static Option getInstanceOption(String libelle, double prix){
        return new Option(libelle,prix);
    }

    public static CodePromo getInstanceCodePromo() { return new CodePromo(); }

    public static CodePromo getInstanceCodePromo(String code, double pourcentage, DateDebutFin validite) { return new CodePromo(code, pourcentage, validite); }

}
