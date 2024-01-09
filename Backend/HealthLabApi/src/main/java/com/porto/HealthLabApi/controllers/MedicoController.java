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

import com.porto.HealthLabApi.domain.medico.DTO.RequestCadastrarMedico;
import com.porto.HealthLabApi.domain.medico.DTO.RequestEditarMedico;
import com.porto.HealthLabApi.domain.medico.DTO.ResponseMedico;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.services.MedicoService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<Object> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
                                                @RequestParam(required = false) String nome,
                                                @RequestParam(required = false) String crm){
        var medicos = medicoService.listarMedicos(paginacao, nome, crm).map(ResponseMedico::new);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, medicos);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalharMedico(@PathVariable Long id){
        var medico = medicoService.detalharMedico(id);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, new ResponseMedico(medico));
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarMedico(@RequestBody @Valid RequestCadastrarMedico dadosMedico,
                                                  @AuthenticationPrincipal Usuario usuario){
        var medicoCriado = medicoService.cadastrarMedico(dadosMedico, usuario);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, new ResponseMedico(medicoCriado));
    }

    @PutMapping
    public ResponseEntity<Object> editarMedico(@RequestBody @Valid RequestEditarMedico dadosMedico,
                                               @AuthenticationPrincipal Usuario usuario){
        var medicoEditado = medicoService.editarMedico(dadosMedico, usuario);

        return responseHandler.generateResponse("Editado com sucesso", true, HttpStatus.OK, new ResponseMedico(medicoEditado));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarMedico(@PathVariable Long id){
        medicoService.deletarMedico(id);

        return responseHandler.generateResponse("Deletado com sucesso", true, HttpStatus.OK, null);
    }
}
