package fr.chatelain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.chatelain.reservation.model.ChambreService;

@Repository
public interface ChambreServiceRepository extends JpaRepository<ChambreService, String> {

}
