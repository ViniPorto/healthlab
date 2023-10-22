package com.porto.HealthLabApi.domain.metodo;

import com.porto.HealthLabApi.domain.metodo.DTO.RequestCadastrarMetodo;
import com.porto.HealthLabApi.domain.metodo.DTO.RequestEditarMetodo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Metodo")
@Entity(name = "Metodo")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Metodo {

    public Metodo(RequestCadastrarMetodo dadosMetodo) {
        this.nome = dadosMetodo.nome();
        if(dadosMetodo.descricao() != null){
            this.descricao = dadosMetodo.descricao();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MetodoId")
    private Long id;
    @Column(name = "MetodoNome")
    private String nome;
    @Column(name = "MetodoDescricao")
    private String descricao;

    public void atualizarInformacoes(RequestEditarMetodo dadosMetodo) {
        if(dadosMetodo.nome() != null){
            this.nome = dadosMetodo.nome();
        }
        if(dadosMetodo.descricao() != null){
            this.descricao = dadosMetodo.descricao();
        }
    }

}
