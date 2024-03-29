package com.porto.HealthLabApi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.layout.LayoutCampos;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayoutCampos;
import com.porto.HealthLabApi.domain.orcamento.Orcamento;
import com.porto.HealthLabApi.domain.orcamento.OrcamentoExame;
import com.porto.HealthLabApi.domain.orcamento.DTO.RequestCadastrarOrcamento;
import com.porto.HealthLabApi.domain.orcamento.DTO.ResponseOrcamento;
import com.porto.HealthLabApi.domain.orcamento.DTO.ResponseOrcamentoExame;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExameItensResultado;
import com.porto.HealthLabApi.domain.requisicao.DTO.ResponseRequisicao;
import com.porto.HealthLabApi.domain.requisicao.DTO.ResponseRequisicaoExame;
import com.porto.HealthLabApi.domain.requisicao.DTO.ResponseRequisicaoExameItensResultado;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.services.LayoutService;
import com.porto.HealthLabApi.services.OrcamentoService;
import com.porto.HealthLabApi.services.RequisicaoService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {
    
    @Autowired
    private OrcamentoService orcamentoService;

    @Autowired
    private LayoutService layoutService;

    @Autowired
    private RequisicaoService requisicaoService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<?> listarOrcamentos(@PageableDefault(size = 10, sort = {"data"}, direction = Direction.DESC) Pageable paginacao, 
                                                @RequestParam(required = false) String pessoaNome,
                                                @RequestParam(required = false) Integer id){
        List<ResponseOrcamento> responseOrcamento = new ArrayList<>();                                            
        orcamentoService.listarOrcamentos(paginacao, pessoaNome, id).forEach(orcamento -> responseOrcamento.add(toResponseOrcamento(orcamento)));
        
        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, responseOrcamento);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarOrcamento(@RequestBody @Valid RequestCadastrarOrcamento dadosOrcamento, @AuthenticationPrincipal Usuario usuario){
        var orcamentoCadastrado = orcamentoService.cadastrarOrcamento(dadosOrcamento, usuario);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.OK, toResponseOrcamento(orcamentoCadastrado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarOrcamento(@PathVariable Long id){
        orcamentoService.deletarOrcamento(id);

        return responseHandler.generateResponse("Deletado com sucesso", true, HttpStatus.OK, null);
    }

    @PostMapping("/converterEmRequisicao/{id}")
    public ResponseEntity<?> converterEmRequisicao(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario){
        var requisicaoCadastrada = requisicaoService.converterOrcamentoEmRequisicao(id, usuario);

        return responseHandler.generateResponse("Convertido em requisição com sucesso", true, HttpStatus.CREATED, new ResponseRequisicao(requisicaoCadastrada, toResponseRequisicaoExames(requisicaoCadastrada.getRequisicaoExames())));
    }

    private ResponseOrcamento toResponseOrcamento(Orcamento orcamento){
        List<ResponseOrcamentoExame> responseOrcamentosExames = new ArrayList<>();
        for(OrcamentoExame orcamentoExame : orcamento.getOrcamentoExames()){
            List<ResponseLayoutCampos> responseLayoutCampos = new ArrayList<>();
            layoutService.listarCamposLayout(orcamentoExame.getExame().getLayout()).forEach(lc -> responseLayoutCampos.add(new ResponseLayoutCampos(lc)));
            responseOrcamentosExames.add(new ResponseOrcamentoExame(orcamentoExame, responseLayoutCampos));
        }
        return new ResponseOrcamento(orcamento, responseOrcamentosExames);
    }

    private List<ResponseRequisicaoExame> toResponseRequisicaoExames(List<RequisicaoExame> requisicaoExames){
        List<ResponseRequisicaoExame> responseRequisicaoExames = new ArrayList<>();
        for(RequisicaoExame requisicaoExame : requisicaoExames){
            List<ResponseRequisicaoExameItensResultado> responseItensResultado = new ArrayList<>();
            if(requisicaoExame.getItensResultado() != null){
                for(RequisicaoExameItensResultado itensResultado : requisicaoExame.getItensResultado()){
                    responseItensResultado.add(new ResponseRequisicaoExameItensResultado(itensResultado));
                }
            }
            List<ResponseLayoutCampos> responseLayoutCampos = new ArrayList<>();
            if(layoutService.listarCamposLayout(requisicaoExame.getLayout()) != null){
                for(LayoutCampos layoutCampo : layoutService.listarCamposLayout(requisicaoExame.getLayout())){
                    responseLayoutCampos.add(new ResponseLayoutCampos(layoutCampo));
                }
            }
            responseRequisicaoExames.add(new ResponseRequisicaoExame(requisicaoExame, responseItensResultado, responseLayoutCampos));
        }
        return responseRequisicaoExames;
    }
}
