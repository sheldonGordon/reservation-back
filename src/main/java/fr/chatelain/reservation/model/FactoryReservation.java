package fr.chatelain.reservation.model;

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
}
