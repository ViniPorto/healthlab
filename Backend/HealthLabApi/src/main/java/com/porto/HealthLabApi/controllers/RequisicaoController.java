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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.domain.layout.LayoutCampos;
import com.porto.HealthLabApi.domain.layout.DTO.ResponseLayoutCampos;
import com.porto.HealthLabApi.domain.requisicao.Requisicao;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExameItensResultado;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestCadastrarRequisicao;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestEditarRequisicao;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestInformarResultado;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestSolicitarRecoleta;
import com.porto.HealthLabApi.domain.requisicao.DTO.ResponseRequisicao;
import com.porto.HealthLabApi.domain.requisicao.DTO.ResponseRequisicaoExame;
import com.porto.HealthLabApi.domain.requisicao.DTO.ResponseRequisicaoExameItensResultado;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.services.LayoutService;
import com.porto.HealthLabApi.services.RequisicaoService;
import com.porto.HealthLabApi.utils.ResponseHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/requisicao")
public class RequisicaoController {
 
    @Autowired
    private RequisicaoService requisicaoService;

    @Autowired
    private LayoutService layoutService;

    @Autowired
    private ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<?> listarRequisicoes(@PageableDefault(size = 10, sort = {"id"}, direction = Direction.DESC) Pageable paginacao,
                                                @RequestParam(required = false) String pessoaNome,
                                                @RequestParam(required = false) Integer id){
        List<ResponseRequisicao> responseRequisicao = new ArrayList<>();
        var requisicoes = requisicaoService.listarRequisicoes(paginacao, pessoaNome, id);
        for(Requisicao requisicao : requisicoes){
            responseRequisicao.add(new ResponseRequisicao(requisicao, toResponseRequisicaoExames(requisicao.getRequisicaoExames())));
        }

        return responseHandler.generateResponse("Consulta realizada com sucesso", true, HttpStatus.OK, responseRequisicao);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarRequisicao(@RequestBody @Valid RequestCadastrarRequisicao dadosRequisicao, 
                                                      @AuthenticationPrincipal Usuario usuario){
        var requisicaoCadastrada = requisicaoService.cadastrarRequisicao(dadosRequisicao, usuario);

        return responseHandler.generateResponse("Cadastrado com sucesso", true, HttpStatus.CREATED, new ResponseRequisicao(requisicaoCadastrada, toResponseRequisicaoExames(requisicaoCadastrada.getRequisicaoExames())));
    }

    @PutMapping
    public ResponseEntity<?> editarRequisicao(@RequestBody @Valid RequestEditarRequisicao dadosRequisicao,
                                                   @AuthenticationPrincipal Usuario usuario){
        var requisicaoEditada = requisicaoService.editarRequisicao(dadosRequisicao, usuario);

        return responseHandler.generateResponse("Editado com sucesso", true, HttpStatus.OK, new ResponseRequisicao(requisicaoEditada, toResponseRequisicaoExames(requisicaoEditada.getRequisicaoExames())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarRequisicao(@PathVariable Long id){
        requisicaoService.deletarRequisicao(id);

        return responseHandler.generateResponse("Excluido com sucesso", true, HttpStatus.OK, null);
    }

    @PostMapping("/informarResultado")
    public ResponseEntity<?> informarResultado(@RequestBody @Valid RequestInformarResultado dadosResultado,
                                                    @AuthenticationPrincipal Usuario usuario){
        var requisicao = requisicaoService.informarResultado(dadosResultado, usuario);

        return responseHandler.generateResponse("Resultado informado com sucesso", true, HttpStatus.OK, new ResponseRequisicao(requisicao, toResponseRequisicaoExames(requisicao.getRequisicaoExames())));
    }

    @PostMapping("/informarColeta/{id}")
    public ResponseEntity<?> informarColeta(@PathVariable Long id,
                                                 @AuthenticationPrincipal Usuario usuario){
        var requisicao = requisicaoService.informarColeta(id, usuario);

        return responseHandler.generateResponse("Data e hora da coleta informadas com sucesso", true, HttpStatus.OK, new ResponseRequisicao(requisicao, toResponseRequisicaoExames(requisicao.getRequisicaoExames())));
    }

    @PostMapping("/informarTriagem/{id}")
    public ResponseEntity<?> informarTriagem(@PathVariable Long id, 
                                                  @AuthenticationPrincipal Usuario usuario){
        var requisicao = requisicaoService.informarTriagem(id, usuario);

        return responseHandler.generateResponse("Data e hora da triagem informadas com sucesso", true, HttpStatus.OK, new ResponseRequisicao(requisicao, toResponseRequisicaoExames(requisicao.getRequisicaoExames())));
    }

    @DeleteMapping("/excluirResultado/{id}")
    public ResponseEntity<?> excluirResultado(@PathVariable Long id,
                                                   @AuthenticationPrincipal Usuario usuario){
        var requisicao = requisicaoService.excluirResultado(id, usuario);

        return responseHandler.generateResponse("Resultados excluídos com sucesso", true, HttpStatus.OK, new ResponseRequisicao(requisicao, toResponseRequisicaoExames(requisicao.getRequisicaoExames())));
    }

    @DeleteMapping("/cancelarExame/{id}")
    public ResponseEntity<?> cancelarExame(@PathVariable Long id,
                                                @AuthenticationPrincipal Usuario usuario){
        var requisicao = requisicaoService.cancelarExame(id, usuario);

        return responseHandler.generateResponse("Exame cancelado com sucesso", true, HttpStatus.OK, new ResponseRequisicao(requisicao, toResponseRequisicaoExames(requisicao.getRequisicaoExames())));
    }

    @PostMapping("/liberarResultado/{id}")
    public ResponseEntity<?> liberarResultado(@PathVariable Long id, 
                                                   @AuthenticationPrincipal Usuario usuario) {
        var requisicao = requisicaoService.liberarResultado(id, usuario);
        
        return responseHandler.generateResponse("Resultado liberado com sucesso", true, HttpStatus.OK, new ResponseRequisicao(requisicao, toResponseRequisicaoExames(requisicao.getRequisicaoExames())));
    }

    @PostMapping("/solicitarRecoleta")
    public ResponseEntity<?> solicitarRecoleta(@RequestBody @Valid RequestSolicitarRecoleta dadosRecoleta, 
                                                    @AuthenticationPrincipal Usuario usuario) {
        var requisicao = requisicaoService.solicitarRecoleta(dadosRecoleta, usuario);
        
        return responseHandler.generateResponse("Solicitado recoleta com sucesso", true, HttpStatus.OK, new ResponseRequisicao(requisicao, toResponseRequisicaoExames(requisicao.getRequisicaoExames())));
    }
    
    @DeleteMapping("/cancelarLiberacao/{id}")
    public ResponseEntity<?> cancelarLiberacao(@PathVariable Long id, 
                                                    @AuthenticationPrincipal Usuario usuario){
        var requisicao = requisicaoService.cancelarLiberacao(id, usuario);

        return responseHandler.generateResponse("Cancelado liberação com sucesso", true, HttpStatus.OK, new ResponseRequisicao(requisicao, toResponseRequisicaoExames(requisicao.getRequisicaoExames())));
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
