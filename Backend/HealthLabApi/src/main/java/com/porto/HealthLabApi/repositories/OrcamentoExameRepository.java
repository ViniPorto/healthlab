package com.porto.HealthLabApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.orcamento.OrcamentoExame;

@Repository
public interface OrcamentoExameRepository extends JpaRepository<OrcamentoExame, Long> {

    @Query(value = """
            SELECT COUNT(*)>0 FROM OrcamentoExame Oe
            WHERE Oe.exame.id = :exameId AND Oe.orcamento.id = :orcamentoId
            """)
    boolean existsById(@Param("exameId") Long exameId, @Param("orcamentoId") Long orcamentoId);
    
}
