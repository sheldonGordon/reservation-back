package fr.chatelain.reservation.model;

import java.math.BigDecimal;
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
        return new Photos(data,nom,typeMime);
    }

    public static Chambre getInstanceChambre(){
        return new Chambre();
    }

    public static Chambre getInstanceChambre(String nom, int nombrePersonne, BigDecimal prix, double superficie, List<Photos> photos, List<ChambreService> services, List<DateDebutFin> indisponibilites){
        return new Chambre(nom, nombrePersonne, prix, superficie, photos, services, indisponibilites);
    }
}
