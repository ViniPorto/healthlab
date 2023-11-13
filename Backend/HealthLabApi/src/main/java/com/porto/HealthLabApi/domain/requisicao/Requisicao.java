package com.porto.HealthLabApi.domain.requisicao;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.porto.HealthLabApi.domain.medico.Medico;
import com.porto.HealthLabApi.domain.pessoa.Pessoa;
import com.porto.HealthLabApi.domain.usuario.Usuario;

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

@Table(name = "Requisicao")
@Entity(name = "Requisicao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Requisicao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RequisicaoId")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RequisicaoMedicoId")
    private Medico medico;
    @Column(name = "RequisicaoData")
    private LocalDate data;
    @Column(name = "RequisicaoUrgente")
    private Boolean urgente;
    @Column(name = "RequisicaoPessoaId")
    private Pessoa pessoa;
    @Column(name = "RequisicaoUsuarioId")
    private Usuario usuario;
    @Column(name = "RequisicaoPaga")
    private Boolean paga;
    @Column(name = "RequisicaoPrecoTotal")
    private BigDecimal pretoTotal;

}
