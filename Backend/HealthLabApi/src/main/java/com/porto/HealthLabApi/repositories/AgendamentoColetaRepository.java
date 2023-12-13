package com.porto.HealthLabApi.repositories;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.agendamentoColeta.AgendamentoColeta;

@Repository
public interface AgendamentoColetaRepository extends JpaRepository<AgendamentoColeta, Long> {

    @Query(value = """
            SELECT a FROM AgendamentoColeta a WHERE
            (:nome is null or a.pessoa.nome LIKE %:nome%)
            AND (:requisicaoId is null or a.requisicao.id = :requisicaoId)
            """)
    Page<AgendamentoColeta> findAll(Pageable paginacao, @Param("nome") String nome, @Param("requisicaoId") Long requisicaoId);

    boolean existsByDataHoraColeta(LocalDateTime dataHoraColeta);
    
}
