package com.porto.HealthLabApi.domain.motivoRecoleta;

import com.porto.HealthLabApi.domain.motivoRecoleta.DTO.RequestCadastrarMotivoRecoleta;
import com.porto.HealthLabApi.domain.motivoRecoleta.DTO.RequestEditarMotivoRecoleta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "MotivoRecoleta")
@Entity(name = "MotivoRecoleta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MotivoRecoleta {

    public MotivoRecoleta(@Valid RequestCadastrarMotivoRecoleta dadosMotivoRecoleta) {
        this.nome = dadosMotivoRecoleta.nome();
        if(dadosMotivoRecoleta.descricao() != null){
            this.descricao = dadosMotivoRecoleta.descricao();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MotivoRecoletaId")
    private Long id;
    @Column(name = "MotivoRecoletaNome")
    private String nome;
    @Column(name = "MotivoRecoletaDescricao")
    private String descricao;

    public void atualizarInformacoes(RequestEditarMotivoRecoleta dadosMotivoRecoleta) {
        if(dadosMotivoRecoleta.nome() != null){
            this.nome = dadosMotivoRecoleta.nome();
        }
        if(dadosMotivoRecoleta.descricao() != null){
            this.descricao = dadosMotivoRecoleta.descricao();
        }
    }

}
