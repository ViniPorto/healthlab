package com.porto.HealthLabApi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.layout.LayoutCampos;
import com.porto.HealthLabApi.domain.layout.DTO.RequestCadastrarLayout;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayout;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayoutCampos;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.services.LayoutService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/layout")
public class LayoutController {
    
    @Autowired
    private LayoutService layoutService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharLayout(@PathVariable Long id){
        var layout = layoutService.detalharLayout(id);
        var layoutCampos = layoutService.listarCamposLayout(layout);
        List<ResponseLayoutCampos> responseLayoutCampos = new ArrayList<>();
        for(LayoutCampos layoutCampo : layoutCampos){
            responseLayoutCampos.add(new ResponseLayoutCampos(layoutCampo));
        }
        var responseLayout = new ResponseLayout(layout, responseLayoutCampos);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, responseLayout);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarLayout(@RequestBody @Valid RequestCadastrarLayout dadosLayout,
                                                  @AuthenticationPrincipal Usuario usuario){
        var layoutCriado = layoutService.cadastrarLayout(dadosLayout.exameId(), usuario);
        var layoutCamposCriados = layoutService.cadastrarLayoutCampos(layoutCriado, dadosLayout);
        List<ResponseLayoutCampos> responseLayoutCampos = new ArrayList<>();
        for(LayoutCampos layout : layoutCamposCriados){
            responseLayoutCampos.add(new ResponseLayoutCampos(layout));
        }
                
        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, new ResponseLayout(layoutCriado, responseLayoutCampos));
    }

}
