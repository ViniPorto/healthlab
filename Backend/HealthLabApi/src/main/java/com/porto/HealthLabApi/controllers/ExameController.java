package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.exame.Exame;
import com.porto.HealthLabApi.domain.exame.DTO.ResponseExame;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayout;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayoutCampos;
import com.porto.HealthLabApi.domain.material.DTO.ResponseMaterial;
import com.porto.HealthLabApi.domain.metodo.DTO.ResponseMetodo;
import com.porto.HealthLabApi.domain.setor.DTO.ResponseSetor;
import com.porto.HealthLabApi.services.ExameService;
import com.porto.HealthLabApi.services.LayoutService;
import com.porto.HealthLabApi.utils.ResponseHandler;

@RestController
@RequestMapping("/exame")
public class ExameController {
    
    @Autowired
    private ExameService exameService;

    @Autowired
    private LayoutService layoutService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<Object> listarExames(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao,
                                                @RequestParam(required = false) String titulo){
        var exames = exameService.listarExames(paginacao, titulo);

        var responseExames = exames.stream().map(e -> toResponseExame(e)).toList();

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, responseExames);
    }

    private ResponseExame toResponseExame (Exame exame){
        var responseSetor = new ResponseSetor(exame.getSetor());

        var responseMaterial = new ResponseMaterial(exame.getMaterial());

        var responseMetodo = new ResponseMetodo(exame.getMetodo());

        var layoutCampos = layoutService.listarCamposLayout(exame.getLayout()).stream().map(ResponseLayoutCampos::new).toList();
        
        var responseLayout = new ResponseLayout(exame.getLayout().getId(), layoutCampos);

        return new ResponseExame(exame, responseSetor, responseMaterial, responseMetodo, responseLayout);
    }

}
