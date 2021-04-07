package fr.chatelain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.chatelain.reservation.model.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, String> {
    
}
