package com.porto.HealthLabApi.domain.requisicao;

import java.time.LocalDateTime;
import java.util.List;

import com.porto.HealthLabApi.domain.bioquimico.Bioquimico;
import com.porto.HealthLabApi.domain.exame.Exame;
import com.porto.HealthLabApi.domain.layout.Layout;
import com.porto.HealthLabApi.domain.motivoRecoleta.MotivoRecoleta;
import com.porto.HealthLabApi.domain.status.Status;

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

@Table(name = "RequisicaoExame")
@Entity(name = "RequisicaoExame")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RequisicaoExame {
    
    public RequisicaoExame(Layout layout, Requisicao requisicao, Exame exame, Status status) {
        this.layout = layout;
        this.requisicao = requisicao;
        this.exame = exame;
        this.status = status;
        this.dataHoraInclusao = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RequisicaoExameId")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RequisicaoExameLayoutVigenteId")
    private Layout layout;
    @Column(name = "RequisicaoExameDataHoraColeta")
    private LocalDateTime dataHoraColeta;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RequisicaoExameMotivoRecoletaId")
    private MotivoRecoleta motivoRecoleta;
    @Column(name = "RequisicaoExameImpresso")
    private boolean exameImpresso;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RequisicaoExameBioquimicoId")
    private Bioquimico bioquimico;
    @Column(name = "RequisicaoExameBioquimicoAssinatura")
    private String bioquimicoAssinatura;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RequisicaoExameRequisicaoId")
    private Requisicao requisicao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RequisicaoExameExameId")
    private Exame exame;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RequisicaoExameStatusCodigo")
    private Status status;
    @Column(name = "RequisicaoExameDataHoraInclusao")
    private LocalDateTime dataHoraInclusao;
    @Column(name = "RequisicaoExameDataHoraTriagem")
    private LocalDateTime dataHoraTriagem;
    @OneToMany(mappedBy = "requisicaoExame", fetch = FetchType.EAGER)
    List<RequisicaoExameItensResultado> itensResultado;

    public void deletarItensResultados() {
        this.itensResultado.clear();
    }

    public void adicionarItemResultado(RequisicaoExameItensResultado itemResultado) {
        this.itensResultado.add(itemResultado);
    }

    public void atualizarStatus(Status status) {
        this.status = status;
    }
}
