package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.setor.DTO.RequestCadastrarSetor;
import com.porto.HealthLabApi.domain.setor.DTO.RequestEditarSetor;
import com.porto.HealthLabApi.domain.setor.DTO.ResponseSetor;
import com.porto.HealthLabApi.services.SetorService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/setor")
public class SetorController {
    
    @Autowired
    private SetorService setorService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<Object> listarSetores(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
                                                @RequestParam(required = false) String nome){
        var setores = setorService.listarSetores(paginacao, nome).map(ResponseSetor::new);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, setores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalharSetor(@PathVariable Long id){
        var setor = setorService.detalharSetor(id);

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, setor);
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarSetor(@RequestBody @Valid RequestCadastrarSetor dadosSetor){
        var setorCadastrado = setorService.cadastrarSetor(dadosSetor);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, new ResponseSetor(setorCadastrado));
    }

    @PutMapping
    public ResponseEntity<Object> editarSetor(@RequestBody @Valid RequestEditarSetor dadosSetor){
        var setorEditado = setorService.editarSetor(dadosSetor);

        return responseHandler.generateResponse("Editado com sucesso", true, HttpStatus.OK, new ResponseSetor(setorEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarSetor(@PathVariable Long id){
        setorService.deletarSetor(id);

        return responseHandler.generateResponse("Deletado com sucesso", true, HttpStatus.OK, null);
    }

}
