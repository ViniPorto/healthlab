package com.porto.HealthLabApi.domain.layout.DTO;

import com.porto.HealthLabApi.domain.layout.FonteCor;
import com.porto.HealthLabApi.domain.layout.LayoutCampos;
import com.porto.HealthLabApi.domain.layout.TipoCampo;

public record ResponseLayoutCampos(
    Long layoutCamposId,
    TipoCampo layoutCamposTipoCampo,
    Integer layoutCamposAltura,
    Integer layoutCamposLargura,
    String layoutCamposTexto,
    FonteCor layoutCamposFonteCor,
    Integer layoutCamposFonteTamanho,
    Integer layoutCamposPosicao
) {
    public ResponseLayoutCampos(LayoutCampos layoutCampos){
        this(layoutCampos.getId(), layoutCampos.getTipoCampo(), layoutCampos.getAltura(), layoutCampos.getLargura(), layoutCampos.getTexto(), layoutCampos.getFonteCor(), layoutCampos.getFonteTamanho(), layoutCampos.getPosicao());
    }

}
