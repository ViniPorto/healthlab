package com.porto.HealthLabApi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.status.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, String> {

    Optional<Status> findByCodigo(String codigo);
    
}
