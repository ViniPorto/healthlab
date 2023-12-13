package com.porto.HealthLabApi.domain.agendamentoColeta;

import java.time.LocalDateTime;

import com.porto.HealthLabApi.domain.agendamentoColeta.DTO.RequestCadastrarAgendamentoColeta;
import com.porto.HealthLabApi.domain.pessoa.Pessoa;
import com.porto.HealthLabApi.domain.requisicao.Requisicao;
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

@Table(name = "AgendamentoColeta")
@Entity(name = "AgendamentoColeta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AgendamentoColeta {
    
    public AgendamentoColeta(RequestCadastrarAgendamentoColeta dadosAgendamentoColeta, Pessoa pessoa, Requisicao requisicao, Usuario usuario) {
        this.dataHoraColeta = dadosAgendamentoColeta.dataHoraColeta();
        this.pessoa = pessoa;
        this.requisicao = requisicao;
        this.usuario = usuario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AgendamentoColetaId")
    private Long id;
    @Column(name = "AgendamentoColetaDataHoraColeta")
    private LocalDateTime dataHoraColeta;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AgendamentoColetaPessoaId")
    private Pessoa pessoa;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AgendamentoColetaRequisicaoId")
    private Requisicao requisicao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AgendamentoColetaUsuarioId")
    private Usuario usuario;
    
}
