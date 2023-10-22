package com.porto.HealthLabApi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.material.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    @Query(value = """
        SELECT m FROM Material m WHERE
        (:nome is null or m.nome LIKE %:nome%)
            """)
    Page<Material> findAll(Pageable paginacao, @Param("nome") String nome);
    
}
