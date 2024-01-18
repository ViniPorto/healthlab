package com.porto.HealthLabApi.domain.layout.DTO;

import java.util.List;

import com.porto.HealthLabApi.domain.layout.Layout;

public record ResponseLayout(
    Long layoutId,
    List<ResponseLayoutCampos> layoutCampos
) {
    public ResponseLayout(Layout layout, List<ResponseLayoutCampos> layoutCampos) {
        this(layout.getId(), layoutCampos);
    }
}
