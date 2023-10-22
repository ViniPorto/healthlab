package com.porto.HealthLabApi.domain.material.DTO;

import com.porto.HealthLabApi.domain.material.Material;

public record ResponseMaterial(
    Long materialId,
    String materialNome,
    String materialDescricao
) {
    public ResponseMaterial(Material material){
        this(material.getId(), material.getNome(), material.getDescricao());
    }
}
