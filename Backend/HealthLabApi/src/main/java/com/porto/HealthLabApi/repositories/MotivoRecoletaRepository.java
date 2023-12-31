package com.porto.HealthLabApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.motivoRecoleta.MotivoRecoleta;

@Repository
public interface MotivoRecoletaRepository extends JpaRepository<MotivoRecoleta, Long> {
    
}
