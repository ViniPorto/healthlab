package com.porto.HealthLabApi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.setor.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

    @Query(value = """
        SELECT s FROM Setor s WHERE
        (:nome is null or s.nome LIKE %:nome%)    
        """)
    Page<Setor> findAll(Pageable paginacao, @Param("nome") String nome);
    
}
