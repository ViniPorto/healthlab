package com.porto.HealthLabApi.domain.layout;

import com.porto.HealthLabApi.domain.layout.DTO.RequestCadastrarLayoutCampos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Table(name = "LayoutCampos")
@Entity(name = "LayoutCampos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class LayoutCampos {

    public LayoutCampos(Layout layout, RequestCadastrarLayoutCampos layoutCampo) {
        this.layout = layout;
        this.tipoCampo = layoutCampo.tipoCampo();
        this.posicao = layoutCampo.posicao();
        this.altura = layoutCampo.altura();
        this.largura = layoutCampo.largura();
        if(layoutCampo.texto() != null){
            this.texto = layoutCampo.texto();
        }
        this.fonteCor = layoutCampo.fonteCor();
        this.fonteTamanho = layoutCampo.fonteTamanho();
        this.codigoCampo = layoutCampo.codigoCampo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LayoutCamposId")
    private Long id;
    @Column(name = "LayoutCamposCodigoCampo")
    private Integer codigoCampo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LayoutCamposLayoutId")
    private Layout layout;
    @Column(name = "LayoutCamposTipoCampo")
    @Enumerated(EnumType.STRING)
    private TipoCampo tipoCampo;
    @Column(name = "LayoutCamposPosicao")
    private Integer posicao;
    @Column(name = "LayoutCamposAltura")
    private Integer altura;
    @Column(name = "LayoutCamposLargura")
    private Integer largura;
    @Column(name = "LayoutCamposTexto")
    private String texto;
    @Column(name = "LayoutCamposFonteCor")
    @Enumerated(EnumType.STRING)
    private FonteCor fonteCor;
    @Column(name = "LayoutCamposFonteTamanho")
    private Integer fonteTamanho;

}
