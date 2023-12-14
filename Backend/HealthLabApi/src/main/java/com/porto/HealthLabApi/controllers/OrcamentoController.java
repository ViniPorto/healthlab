package com.porto.HealthLabApi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayoutCampos;
import com.porto.HealthLabApi.domain.orcamento.Orcamento;
import com.porto.HealthLabApi.domain.orcamento.OrcamentoExame;
import com.porto.HealthLabApi.domain.orcamento.DTO.ResponseOrcamento;
import com.porto.HealthLabApi.domain.orcamento.DTO.ResponseOrcamentoExame;
import com.porto.HealthLabApi.services.LayoutService;
import com.porto.HealthLabApi.services.OrcamentoService;
import com.porto.HealthLabApi.utils.ResponseHandler;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {
    
    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private LayoutService layoutService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<Object> listarOrcamentos(@PageableDefault(size = 10, sort = {"data"}, direction = Direction.DESC) Pageable paginacao,
                                                @RequestParam(required = false) String pessoaNome,
                                                @RequestParam(required = false) Integer id){
        List<ResponseOrcamento> responseOrcamento = new ArrayList<>();                                            
        var orcamentos = orcamentoService.listarOrcamentos(paginacao, pessoaNome, id);
        for(Orcamento orcamento : orcamentos){
            for(OrcamentoExame orcamentoExame : orcamento.getOrcamentoExames()){
                List<ResponseLayoutCampos> responseLayoutCampos = new ArrayList<>();
                layoutService.listarCamposLayout(orcamentoExame.getExame().getLayout()).forEach(lc -> responseLayoutCampos.add(new ResponseLayoutCampos(lc)));
                responseOrcamento.add(new ResponseOrcamento(orcamento, toResponseOrcamentoExames(orcamento.getOrcamentoExames(), responseLayoutCampos)));
            }
        }
        
        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, responseOrcamento);
    }

    private List<ResponseOrcamentoExame> toResponseOrcamentoExames(List<OrcamentoExame> orcamentoExames, List<ResponseLayoutCampos> responseLayoutCampos ){
        List<ResponseOrcamentoExame> responseOrcamentoExame = new ArrayList<>();
        for(OrcamentoExame orcamentoExame : orcamentoExames){
            responseOrcamentoExame.add(new ResponseOrcamentoExame(orcamentoExame, responseLayoutCampos));
        }
        return responseOrcamentoExame;
    }
}
