package com.porto.HealthLabApi.domain.medico;

import com.porto.HealthLabApi.domain.medico.DTO.RequestCadastrarMedico;
import com.porto.HealthLabApi.domain.medico.DTO.RequestEditarMedico;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Medico")
@Entity(name = "Medico")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Medico {
    
    public Medico(@Valid RequestCadastrarMedico dadosMedico) {
        this.email = dadosMedico.email();
        this.telefone = dadosMedico.telefone();
        this.crm = dadosMedico.crm();
        this.nome = dadosMedico.nome();
        this.uf = dadosMedico.uf();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MedicoId")
    private Long id;
    @Column(name = "MedicoEmail")
    private String email;
    @Column(name = "MedicoTelefone")
    private String telefone;
    @Column(name = "MedicoCRM")
    private String crm;
    @Column(name = "MedicoNome")
    private String nome;
    @Column(name = "MedicoUF")
    @Enumerated(EnumType.STRING)
    private MedicoUF uf;

    public void atualizarInformacoes(RequestEditarMedico dadosMedico) {
        if(dadosMedico.nome() != null){
            this.nome = dadosMedico.nome();
        }
        if(dadosMedico.telefone() != null){
            this.telefone = dadosMedico.telefone();
        }
        if(dadosMedico.email() != null){
            this.email = dadosMedico.email();
        }
    }

}
