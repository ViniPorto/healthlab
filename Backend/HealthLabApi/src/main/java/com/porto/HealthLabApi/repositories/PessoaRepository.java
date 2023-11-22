package com.porto.HealthLabApi.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.pessoa.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
    @Query(value = """
            SELECT p FROM Pessoa p WHERE
            (:nome is null or p.nome LIKE %:nome%)
            AND (:cpf is null or p.cpf = :cpf)
            """)
    Page<Pessoa> findAll(Pageable paginacao, @Param("nome") String nome, @Param("cpf") String cpf);

    boolean existsByCpf(String cpf);

    Optional<Pessoa> findById(Integer pessoaId);

}
