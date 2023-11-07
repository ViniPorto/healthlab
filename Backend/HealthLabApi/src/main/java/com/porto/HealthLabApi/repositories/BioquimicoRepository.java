package com.porto.HealthLabApi.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.bioquimico.Bioquimico;

@Repository
public interface BioquimicoRepository extends JpaRepository<Bioquimico, Long> {

    @Query(value = """
            SELECT b FROM Bioquimico b WHERE
            (:nome is null or b.nome LIKE %:nome%)
            """)
    List<Bioquimico> findAll(Pageable paginacao, @Param("nome") String nome);
    
}
