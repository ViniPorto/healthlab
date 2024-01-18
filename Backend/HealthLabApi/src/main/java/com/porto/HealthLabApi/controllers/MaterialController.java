package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.material.DTO.RequestCadastrarMaterial;
import com.porto.HealthLabApi.domain.material.DTO.RequestEditarMaterial;
import com.porto.HealthLabApi.domain.material.DTO.ResponseMaterial;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.services.MaterialService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/material")
public class MaterialController {
    
    @Autowired
    private MaterialService materialService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<?> listarMateriais(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
                                                  @RequestParam(required = false) String nome){
        var materiais = materialService.listarMateriais(paginacao, nome).map(ResponseMaterial::new).toList();

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, materiais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharMaterial(@PathVariable Long id){
        var material = materialService.detalharMaterial(id);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, new ResponseMaterial(material));
    }

    @PostMapping
    public ResponseEntity<?> cadastrarMaterial(@RequestBody @Valid RequestCadastrarMaterial dadosMaterial,
                                                    @AuthenticationPrincipal Usuario usuario){
        var materialCadastrado = materialService.cadastrarMaterial(dadosMaterial, usuario);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, new ResponseMaterial(materialCadastrado));
    }

    @PutMapping
    public ResponseEntity<?> editarMaterial(@RequestBody @Valid RequestEditarMaterial dadosMaterial,
                                                 @AuthenticationPrincipal Usuario usuario){
        var materialEditado = materialService.editarMaterial(dadosMaterial, usuario);

        return responseHandler.generateResponse("Editado com sucesso", true, HttpStatus.OK, new ResponseMaterial(materialEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarMaterial(@PathVariable Long id){
        materialService.deletarMaterial(id);

        return responseHandler.generateResponse("Deletado com sucesso", true, HttpStatus.OK, null);
    }
    
}
