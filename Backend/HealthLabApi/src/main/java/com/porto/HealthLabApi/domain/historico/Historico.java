package com.porto.HealthLabApi.domain.historico;

import java.time.LocalDateTime;

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

@Table(name = "Historico")
@Entity(name = "Historico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Historico {
    
    public Historico(Long referenciaId, String tabela, Usuario usuario, String acaoRealizada, LocalDateTime acaoDataHora, String dados) {
        this.referenciaId = referenciaId;
        this.tabela = tabela;
        this.usuario = usuario;
        this.acaoRealizada = acaoRealizada;
        this.acaoDataHora = acaoDataHora;
        this.dadosAtualizados = dados;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HistoricoId")
    private Long id;
    @Column(name = "HistoricoReferenciaId")
    private Long referenciaId;
    @Column(name = "HistoricoTabela")
    private String tabela;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HistoricoUsuarioId")
    private Usuario usuario;
    @Column(name = "HistoricoAcaoRealizada")
    private String acaoRealizada;
    @Column(name = "HistoricoAcaoDataHora")
    private LocalDateTime acaoDataHora;
    @Column(name = "HistoricoDadosAtualizados")
    private String dadosAtualizados;

}
