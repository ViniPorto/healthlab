package com.porto.HealthLabApi.domain.requisicao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "RequisicaoExameItensResultado")
@Entity(name = "RequisicaoExameItensResultado")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RequisicaoExameItensResultado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RequisicaoExameItensResultadoId")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RequisicaoExameItensResultadoRequisicaoExameId")
    private RequisicaoExame requisicaoExame;
    @Column(name = "RequisicaoExameItensResultadoResultado")
    private String resultado;
    @Column(name = "RequisicaoExameItensResultadoObservacao")
    private String observacao;

}
