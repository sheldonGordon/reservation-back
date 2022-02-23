package fr.chatelain.reservation;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.*;

@Suite()
@SelectClasses({TestChambre.class, TestChambreServices.class, TestCodePromo.class, TestCompte.class, TestOption.class, TestPersonne.class, TestPhotos.class, TestRole.class, TestReservation.class})
public class TestAll {
    @Test
    public void contextLoads() {
    }
}