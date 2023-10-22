package com.porto.HealthLabApi.domain.material;

import com.porto.HealthLabApi.domain.material.DTO.RequestCadastrarMaterial;
import com.porto.HealthLabApi.domain.material.DTO.RequestEditarMaterial;

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

@Table(name = "Material")
@Entity(name = "Material")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Material {
    
    public Material(RequestCadastrarMaterial dadosMaterial) {
        this.nome = dadosMaterial.nome();
        if(dadosMaterial.descricao() != null){
            this.descricao = dadosMaterial.descricao();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaterialId")
    private Long id;
    @Column(name = "MaterialNome")
    private String nome;
    @Column(name = "MaterialDescricao")
    private String descricao;

    public void atualizarInformacoes(RequestEditarMaterial dadosMaterial) {
        if(dadosMaterial.nome() != null){
            this.nome = dadosMaterial.nome();
        }
        if(dadosMaterial.descricao() != null){
            this.descricao = dadosMaterial.descricao();
        }
    }

}
