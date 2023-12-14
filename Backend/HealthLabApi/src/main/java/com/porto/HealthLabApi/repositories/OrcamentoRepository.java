package com.porto.HealthLabApi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.orcamento.Orcamento;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    @Query(value = """
            SELECT o FROM Orcamento o WHERE
            (:pessoaNome is null or o.pessoa.nome LIKE %:pessoaNome%)
            AND (:id is null or o.id = :id)
            """)
    Page<Orcamento> findAll(Pageable paginacao, @Param("pessoaNome") String pessoaNome, @Param("id") Integer id);
    
}
