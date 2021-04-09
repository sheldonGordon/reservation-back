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
}
