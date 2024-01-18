package com.porto.HealthLabApi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.exame.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {

    @Query(value = """
            SELECT e FROM Exame e WHERE
            (:titulo is null or e.titulo LIKE %:titulo%)
            """)
    List<Exame> findAll(Pageable paginacao, @Param("titulo") String titulo);

    Exame findBySigla(String idOuSigle);

    boolean existsBySigla(String sigla);

    Optional<Exame> findById(Integer exameId);

    List<Exame> findByPrincipalTrue(Pageable paginacao);
    
}

