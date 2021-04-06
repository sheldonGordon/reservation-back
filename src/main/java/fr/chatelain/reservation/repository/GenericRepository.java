package fr.chatelain.reservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<T> extends JpaRepository<T, String> {

    Optional<T> findById(String id);
}
