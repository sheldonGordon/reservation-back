package fr.chatelain.reservation.model;

public class FactoryReservation {

    private FactoryReservation() {
    }

    public static ChambreService getInstanceService() {
        return new ChambreService();
    }

    public static ChambreService getInstanceService(String libelle) {
        return new ChambreService(libelle);
    }
}
