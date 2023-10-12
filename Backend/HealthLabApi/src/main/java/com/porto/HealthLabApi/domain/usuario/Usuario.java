package com.porto.HealthLabApi.domain.usuario;

import com.porto.HealthLabApi.domain.usuario.DTO.RequestCadastrarUsuario;
import com.porto.HealthLabApi.domain.usuario.DTO.RequestEditarUsuario;

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
import lombok.Setter;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    
    public Usuario(@Valid RequestCadastrarUsuario dadosUsuario) {
        this.login = dadosUsuario.login();
        this.ativo = true;
        this.administrador = false;
        this.nome = dadosUsuario.nome();
        this.senha = dadosUsuario.senha();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UsuarioId")
    private Long id;
    @Column(name = "UsuarioLogin")
    private String login;
    @Column(name = "UsuarioAtivo")
    private boolean ativo;
    @Column(name = "UsuarioAdministrador")
    private boolean administrador;
    @Column(name = "UsuarioNome")
    private String nome;
    @Column(name = "UsuarioSenha")
    private String senha;

    public void atualizarInformacoes(@Valid RequestEditarUsuario dadosUsuario) {
        if(dadosUsuario.login() != null){
            this.login = dadosUsuario.login();
        }
        if(dadosUsuario.nome() != null){
            this.nome = dadosUsuario.nome();
        }
        if(dadosUsuario.senha() != null){
            this.senha = dadosUsuario.senha();
        }
    }

    public void inativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void tornarAdministrador() {
        this.administrador = true;
    }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    // }
    // @Override
    // public String getPassword() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    // }
    // @Override
    // public String getUsername() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getUsername'");
    // }
    // @Override
    // public boolean isAccountNonExpired() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    // }
    // @Override
    // public boolean isAccountNonLocked() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    // }
    // @Override
    // public boolean isCredentialsNonExpired() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    // }
    // @Override
    // public boolean isEnabled() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    // }

}
