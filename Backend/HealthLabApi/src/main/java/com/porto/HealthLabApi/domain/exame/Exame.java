package com.porto.HealthLabApi.domain.exame;

import java.math.BigDecimal;

import com.porto.HealthLabApi.domain.exame.DTO.RequestCadastrarExame;
import com.porto.HealthLabApi.domain.layout.Layout;
import com.porto.HealthLabApi.domain.material.Material;
import com.porto.HealthLabApi.domain.metodo.Metodo;
import com.porto.HealthLabApi.domain.setor.Setor;

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

@Table(name = "Exame")
@Entity(name = "Exame")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Exame {
    
    public Exame(RequestCadastrarExame dadosExame, Layout layout, Setor setor,
        Metodo metodo, Material material) {
        this.setor = setor;
        this.layout = layout;
        this.principal = dadosExame.principal();
        this.titulo = dadosExame.titulo();
        this.sigla = dadosExame.sigla().toUpperCase();
        this.tempoExecucaoUrgente = dadosExame.tempoExecucaoUrgente();
        this.metodo = metodo;
        this.material = material;
        if(dadosExame.descricao() != null){
            this.descricao = dadosExame.descricao();
        }
        this.preco = dadosExame.preco();
        this.tempoExecucaoNormal = dadosExame.tempoExecucaoNormal();
    }   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExameId")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ExameSetorId")
    private Setor setor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ExameLayoutId")
    private Layout layout;
    @Column(name = "ExamePrincipal")
    private boolean principal;
    @Column(name = "ExameTitulo")
    private String titulo;
    @Column(name = "ExameSigla")
    private String sigla;
    @Column(name = "ExameTempoExecucaoUrgente")
    private Integer tempoExecucaoUrgente;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ExameMetodoId")
    private Metodo metodo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ExameMaterialId")
    private Material material;
    @Column(name = "ExameDescricao")
    private String descricao;
    @Column(name = "ExamePreco")
    private BigDecimal preco;
    @Column(name = "ExameTempoExecucaoNormal")
    private Integer tempoExecucaoNormal;

}
