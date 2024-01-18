package com.porto.HealthLabApi.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.layout.Layout;
import com.porto.HealthLabApi.domain.relatorios.examesMaisRealizados.DTO.ResponseRelatorioExamesMaisRealizados;
import com.porto.HealthLabApi.domain.relatorios.examesRealizadosPorPeriodo.DTO.ResponseRelatorioExamesRealizadosPorPeriodoExames;
import com.porto.HealthLabApi.domain.requisicao.Requisicao;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;

@Repository
public interface RequisicaoExameRepository extends JpaRepository<RequisicaoExame, Long> {

    List<RequisicaoExame> findByRequisicao(Requisicao requisicao);

    @Query(value = """
            SELECT COUNT(*)>0 FROM RequisicaoExame Rex
            WHERE Rex.exame.id = :exameId AND Rex.requisicao.id = :requisicaoId
            """)
    boolean existsById(@Param("exameId") Long exameId, @Param("requisicaoId") Long requisicaoId);

    @Query(value = """
            SELECT Re FROM RequisicaoExame Re
            WHERE Re.status.codigo NOT IN ('RL', 'CA') AND Re.dataHoraColeta IS NOT NULL
            """)
    List<RequisicaoExame> listarExamesNaoLiberados();

    @Query(value = """
            SELECT new com.porto.HealthLabApi.domain.relatorios.examesRealizadosPorPeriodo.DTO.ResponseRelatorioExamesRealizadosPorPeriodoExames(Re.exame.titulo, COUNT(*))  
            FROM RequisicaoExame Re
            WHERE DATE(Re.dataHoraLiberacao) = :data
            AND Re.status.codigo = 'RL'
            GROUP BY Re.exame
            """)
    List<ResponseRelatorioExamesRealizadosPorPeriodoExames> listarQuantidadeEExamesRealizadosPorPeriodo(@Param("data") LocalDate data);

    @Query(value = """
            SELECT COUNT(*) 
            FROM RequisicaoExame Re
            WHERE DATE(Re.dataHoraLiberacao) = :data
            AND Re.status.codigo = 'RL'
            """)
    Long listarQuantidadeDeExamesRealizadosNaData(@Param("data") LocalDate data);

    @Query(value = """
            SELECT new com.porto.HealthLabApi.domain.relatorios.examesMaisRealizados.DTO.ResponseRelatorioExamesMaisRealizados(Re.exame.titulo, COUNT(*))  
            FROM RequisicaoExame Re
            WHERE Re.status.codigo = 'RL'
            GROUP BY Re.exame
            """)
    List<ResponseRelatorioExamesMaisRealizados> listarExamesMaisRealizados();

    @Modifying
    @Query(value = """
            UPDATE RequisicaoExame Re
            SET Re.layout = :layout
            WHERE Re.exame.id = :exameId
            AND Re.status.codigo NOT IN ('CA', 'RL', 'RI')
            """)
    void atualizarLayoutDeExamesNaoLiberados(@Param("exameId") Long exameId, @Param("layout") Layout layout);

    @Modifying
    @Query(value = """
            UPDATE RequisicaoExame Re
            SET Re.layout = :novoLayout
            WHERE Re.id = :requisicaoExameId
            """)
    void atualizarLayoutDoExame(@Param("requisicaoExameId") Long requisicaoExameId, @Param("novoLayout") Layout novoLayout);
    
}
