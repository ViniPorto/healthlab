package com.porto.HealthLabApi.domain.orcamento;

import com.porto.HealthLabApi.domain.exame.Exame;

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

@Table(name = "OrcamentoExame")
@Entity(name = "OrcamentoExame")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrcamentoExame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrcamentoExameId")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrcamentoExameOrcamentoId")
    private Orcamento orcamento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrcamentoExameExameId")
    private Exame exame;
    
}
