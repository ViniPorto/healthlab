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

import com.porto.HealthLabApi.domain.exame.Exame;
import com.porto.HealthLabApi.domain.exame.DTO.RequestCadastrarExame;
import com.porto.HealthLabApi.domain.exame.DTO.RequestEditarExame;
import com.porto.HealthLabApi.domain.exame.DTO.ResponseExame;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayout;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayoutCampos;
import com.porto.HealthLabApi.domain.material.DTO.ResponseMaterial;
import com.porto.HealthLabApi.domain.metodo.DTO.ResponseMetodo;
import com.porto.HealthLabApi.domain.setor.DTO.ResponseSetor;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.services.ExameService;
import com.porto.HealthLabApi.services.LayoutService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

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
    public ResponseEntity<?> listarExames(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao,
                                                @RequestParam(required = false) String titulo){
        var exames = exameService.listarExames(paginacao, titulo);

        var responseExames = exames.stream().map(e -> toResponseExame(e)).toList();

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, responseExames);
    }

    @GetMapping("/{idOuSigla}")
    public ResponseEntity<?> detalharExame(@PathVariable String idOuSigla){
        Exame exame = null;
        try{
            Long id = Long.parseLong(idOuSigla);
            exame = exameService.detalharPorId(id);
        }catch(Exception e){
            exame = exameService.detalharPorSigla(idOuSigla);
        }
        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, toResponseExame(exame));
    }

    @PostMapping
    public ResponseEntity<?> cadastrarExame(@RequestBody @Valid RequestCadastrarExame dadosExame,
                                                 @AuthenticationPrincipal Usuario usuario){
        var exameCadastrado = exameService.cadastrarExame(dadosExame, usuario);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, toResponseExame(exameCadastrado));
    }

    @PutMapping
    public ResponseEntity<?> editarExame(@RequestBody @Valid RequestEditarExame dadosExame,
                                              @AuthenticationPrincipal Usuario usuario){
        var exameEditado = exameService.editarExame(dadosExame, usuario);

        return responseHandler.generateResponse("Editado com sucesso", true, HttpStatus.OK, toResponseExame(exameEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarExame(@PathVariable Long id){
        exameService.deletarExame(id);

        return responseHandler.generateResponse("Deletado com sucesso", true, HttpStatus.OK, null);
    }

    @GetMapping("/principais")
    public ResponseEntity<?> listarExamesPrincipais(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao){
        var exames = exameService.listarExamesPrincipais(paginacao);

        var responseExames = exames.stream().map(e -> toResponseExame(e)).toList();

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, responseExames);
    }

    private ResponseExame toResponseExame(Exame exame){
        var responseSetor = new ResponseSetor(exame.getSetor());

        var responseMaterial = new ResponseMaterial(exame.getMaterial());

        var responseMetodo = new ResponseMetodo(exame.getMetodo());     

        var layoutCampos = layoutService.listarCamposLayout(exame.getLayout()).stream().map(ResponseLayoutCampos::new).toList();
        
        var responseLayout = new ResponseLayout(exame.getLayout(), layoutCampos);

        return new ResponseExame(exame, responseSetor, responseMaterial, responseMetodo, responseLayout);
    }

    //:D

}
