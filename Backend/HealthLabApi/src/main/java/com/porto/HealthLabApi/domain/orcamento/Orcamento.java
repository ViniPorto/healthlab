package com.porto.HealthLabApi.domain.orcamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.porto.HealthLabApi.domain.medico.Medico;
import com.porto.HealthLabApi.domain.orcamento.DTO.RequestCadastrarOrcamento;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Orcamento")
@Entity(name = "Orcamento")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Orcamento {
    
    public Orcamento(RequestCadastrarOrcamento dadosOrcamento, Pessoa pessoa, Medico medico, Usuario usuario) {
        this.usuario = usuario;
        this.pessoa = pessoa;
        this.medico = medico;
        this.data = dadosOrcamento.data();
        this.precoTotal = new BigDecimal(0);
        this.orcamentoExames = new ArrayList<OrcamentoExame>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrcamentoId")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OrcamentoUsuarioId")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OrcamentoPessoaId")
    private Pessoa pessoa;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OrcamentoMedicoId")
    private Medico medico;
    @Column(name = "OrcamentoData")
    private LocalDateTime data;
    @Column(name = "OrcamentoPrecoTotal")
    private BigDecimal precoTotal;
    @OneToMany(mappedBy = "orcamento", fetch = FetchType.EAGER)
    List<OrcamentoExame> orcamentoExames;

    public void adicionarExame(OrcamentoExame orcamentoExame) {
        this.precoTotal = this.precoTotal.add(orcamentoExame.getExame().getPreco());
        this.orcamentoExames.add(orcamentoExame);
    }

}
