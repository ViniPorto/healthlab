package com.porto.HealthLabApi.domain.bioquimico;

import com.porto.HealthLabApi.domain.bioquimico.DTO.RequestCadastrarBioquimico;
import com.porto.HealthLabApi.domain.bioquimico.DTO.RequestEditarBioquimico;
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
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Bioquimico")
@Entity(name = "Bioquimico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Bioquimico {
    
    public Bioquimico(@Valid RequestCadastrarBioquimico dadosBioquimico, Usuario usuario) {
        this.nome = dadosBioquimico.nome();
        this.usuario = usuario;
        this.assinatura = dadosBioquimico.assinatura();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BioquimicoId")
    private Long id;
    @Column(name = "BioquimicoNome")
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BioquimicoUsuarioId")
    private Usuario usuario;
    @Column(name = "BioquimicoAssinatura")
    private String assinatura; //no banco fica salvo como longtext -> front envia como data URI

    public void atualizarInformacoes(RequestEditarBioquimico dadosBioquimico) {
        if(dadosBioquimico.nome() != null){
            this.nome = dadosBioquimico.nome();
        }
        if(dadosBioquimico.assinatura() != null){
            this.assinatura = dadosBioquimico.assinatura();
        }
    }

}
