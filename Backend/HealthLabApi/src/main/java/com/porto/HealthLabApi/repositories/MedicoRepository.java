package com.porto.HealthLabApi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.medico.Medico;
import com.porto.HealthLabApi.domain.medico.MedicoUF;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query(value = """
            SELECT m FROM Medico m WHERE
            (:nome is null or m.nome LIKE %:nome%)
            AND (:crm is null or m.crm = :crm)
            """)
    Page<Medico> findAll(Pageable paginacao, @Param("nome") String nome, @Param("crm") String crm);

    boolean existsByCrmAndUf(String crm, MedicoUF uf);
    
}
