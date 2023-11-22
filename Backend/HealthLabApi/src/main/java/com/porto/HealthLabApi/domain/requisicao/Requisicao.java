package com.porto.HealthLabApi.domain.requisicao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.porto.HealthLabApi.domain.medico.Medico;
import com.porto.HealthLabApi.domain.pessoa.Pessoa;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestCadastrarRequisicao;
import com.porto.HealthLabApi.domain.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Requisicao")
@Entity(name = "Requisicao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Requisicao {
    
    public Requisicao(RequestCadastrarRequisicao dadosRequisicao, Medico medico, Pessoa pessoa, Usuario usuario) {
        this.medico = medico;
        this.data = LocalDateTime.now();
        this.urgente = dadosRequisicao.urgente();
        this.pessoa = pessoa;
        this.usuario = usuario;
        this.paga = dadosRequisicao.paga();
        this.pretoTotal = new BigDecimal(0);
        this.requisicaoExames = new ArrayList<RequisicaoExame>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RequisicaoId")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RequisicaoMedicoId")
    private Medico medico;
    @Column(name = "RequisicaoData")
    private LocalDateTime data;
    @Column(name = "RequisicaoUrgente")
    private boolean urgente;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RequisicaoPessoaId")
    private Pessoa pessoa;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RequisicaoUsuarioId")
    private Usuario usuario;
    @Column(name = "RequisicaoPaga")
    private boolean paga;
    @Column(name = "RequisicaoPrecoTotal")
    private BigDecimal pretoTotal;
    @OneToMany(mappedBy = "requisicao", fetch = FetchType.EAGER)
    List<RequisicaoExame> requisicaoExames;

    public void adicionarExame(RequisicaoExame requisicaoExame) {
        this.requisicaoExames.add(requisicaoExame);
    }

}
