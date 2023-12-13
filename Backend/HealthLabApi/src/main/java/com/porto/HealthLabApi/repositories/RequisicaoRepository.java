package com.porto.HealthLabApi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.requisicao.Requisicao;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {

    @Query(value = """
            SELECT r FROM Requisicao r WHERE
            (:pessoa is null or r.pessoa LIKE %:pessoa%)
            AND (:id is null or r.id = :id)
            """)
    Page<Requisicao> findAll(Pageable paginacao, @Param("pessoa") String pessoaNome, @Param("id") Integer id);
    
}
