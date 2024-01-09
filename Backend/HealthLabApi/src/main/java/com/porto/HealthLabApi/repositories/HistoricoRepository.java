package com.porto.HealthLabApi.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.historico.Historico;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    @Query(value = """
            SELECT h FROM Historico h 
            WHERE h.tabela = :tabela AND h.referenciaId = :referenciaId
            """)
    List<Historico> findByTabelaAndReferenciaId(Pageable paginacao, @Param("tabela") String tabela, @Param("referenciaId") Long referenciaId);
    
}
