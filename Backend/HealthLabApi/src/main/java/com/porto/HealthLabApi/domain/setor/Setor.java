package com.porto.HealthLabApi.domain.setor;

import com.porto.HealthLabApi.domain.setor.DTO.RequestCadastrarSetor;
import com.porto.HealthLabApi.domain.setor.DTO.RequestEditarSetor;

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

@Table(name = "Setor")
@Entity(name = "Setor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Setor {
    
    public Setor(@Valid RequestCadastrarSetor dadosSetor) {
        this.nome = dadosSetor.nome();
        if(dadosSetor.descricao() != null){
            this.descricao = dadosSetor.descricao();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SetorId")
    private Long id;
    @Column(name = "SetorNome")
    private String nome;
    @Column(name = "SetorDescricao")
    private String descricao;

    public void atualizarInformacoes(RequestEditarSetor dadosSetor) {
        if(dadosSetor.nome() != null){
            this.nome = dadosSetor.nome();
        }
        if(dadosSetor.descricao() != null){
            this.descricao = dadosSetor.descricao();
        }
    }

}
