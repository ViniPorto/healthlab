package com.porto.HealthLabApi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.metodo.Metodo;

@Repository
public interface MetodoRepository extends JpaRepository<Metodo, Long> {

    @Query(value = """
        SELECT m FROM Metodo m WHERE
        (:nome is null or m.nome LIKE %:nome%)
            """)
    Page<Metodo> findAll(Pageable paginacao, @Param("nome") String nome);
    
}
