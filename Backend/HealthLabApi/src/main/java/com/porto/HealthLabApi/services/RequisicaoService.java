package com.porto.HealthLabApi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.medico.Medico;
import com.porto.HealthLabApi.domain.orcamento.OrcamentoExame;
import com.porto.HealthLabApi.domain.requisicao.Requisicao;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExameItensResultado;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestCadastrarRequisicao;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestEditarRequisicao;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestInformarResultado;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestInformarResultadoRequisicaoExameItensResultado;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestSolicitarRecoleta;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.infra.exception.exceptions.ExameJaCadastradoException;
import com.porto.HealthLabApi.infra.exception.exceptions.RequisicaoExameComResultadoException;
import com.porto.HealthLabApi.infra.exception.exceptions.StatusInvalidoParaRealizarOperacao;
import com.porto.HealthLabApi.infra.exception.exceptions.UsuarioNaoBioquimicoException;
import com.porto.HealthLabApi.repositories.BioquimicoRepository;
import com.porto.HealthLabApi.repositories.ExameRepository;
import com.porto.HealthLabApi.repositories.MedicoRepository;
import com.porto.HealthLabApi.repositories.MotivoRecoletaRepository;
import com.porto.HealthLabApi.repositories.OrcamentoRepository;
import com.porto.HealthLabApi.repositories.PessoaRepository;
import com.porto.HealthLabApi.repositories.RequisicaoExameItensResultadoRepository;
import com.porto.HealthLabApi.repositories.RequisicaoExameRepository;
import com.porto.HealthLabApi.repositories.RequisicaoRepository;
import com.porto.HealthLabApi.repositories.StatusRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class RequisicaoService {
    
    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @Autowired
    private RequisicaoExameRepository requisicaoExameRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private BioquimicoRepository bioquimicoRepository;

    @Autowired
    private MotivoRecoletaRepository motivoRecoletaRepository;

    @Autowired
    private RequisicaoExameItensResultadoRepository requisicaoExameItensResultadoRepository;

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    public Page<Requisicao> listarRequisicoes(Pageable paginacao, String pessoaNome, Integer id) {
        return requisicaoRepository.findAll(paginacao, pessoaNome, id);
    }

    public List<RequisicaoExame> listarRequisicaoExames(Requisicao requisicao) {
        return requisicaoExameRepository.findByRequisicao(requisicao);
    }

    public List<RequisicaoExameItensResultado> listarRequisicaoExameItens(RequisicaoExame requisicaoExame) {
        return requisicaoExameItensResultadoRepository.findByRequisicaoExame(requisicaoExame);
    }

    @Transactional
    public Requisicao cadastrarRequisicao(RequestCadastrarRequisicao dadosRequisicao, Usuario usuario) {
        Medico medico = null;
        if(dadosRequisicao.medicoId() != null){
            medico = medicoRepository.findById(dadosRequisicao.medicoId()).get();
        }
        var pessoa = pessoaRepository.findById(dadosRequisicao.pessoaId()).get();
        var requisicao = new Requisicao(dadosRequisicao, medico, pessoa, usuario);

        requisicaoRepository.save(requisicao);
        
        var status = statusRepository.findByCodigo("CD").get();
        for(Long exameId : dadosRequisicao.examesId()){
            if(requisicaoExameRepository.existsById(exameId, requisicao.getId())){
                throw new ExameJaCadastradoException();
            }
            var exame = exameRepository.findById(exameId).get();
            var layout = exame.getLayout();

            var requisicaoExame = new RequisicaoExame(layout, requisicao, exame, status);

            requisicaoExameRepository.save(requisicaoExame);

            requisicao.adicionarExame(requisicaoExame);
        }

        return requisicaoRepository.save(requisicao);
    }

    @Transactional
    public Requisicao editarRequisicao(@Valid RequestEditarRequisicao dadosRequisicao) {
        var requisicao = requisicaoRepository.findById(dadosRequisicao.requisicaoId()).get();

        var examesId = dadosRequisicao.examesId();

        var requisicaoExamesRemover = new ArrayList<RequisicaoExame>();
        var examesRemover = new ArrayList<Long>();

        //lógica: se o exame da minha lista de exames da requisição não está na nova lista, verificar se existe resultado informado -> se existir, lançar exceção, senão exclui da lista de exames
        for(RequisicaoExame requisicaoExame : requisicao.getRequisicaoExames()){
            if(!examesId.contains(requisicaoExame.getExame().getId())){
                if(!requisicaoExame.getItensResultado().isEmpty()){
                    throw new RequisicaoExameComResultadoException("Excluir exame");
                }
                //se não tem resultado, então pode excluir da lista de exames
                requisicaoExamesRemover.add(requisicaoExame);
            }else{ //se contem o exame, então retira o id da lista de examesId
                examesRemover.add(requisicaoExame.getExame().getId());
            }
        }

        requisicaoExamesRemover.forEach(re -> requisicao.removerExame(re));
        requisicaoExamesRemover.forEach(re -> requisicaoExameRepository.delete(re));
        examesRemover.forEach(e -> examesId.remove(e));

        var status = statusRepository.findByCodigo("CD").get();

        for(Long exameId : examesId){
            if(requisicaoExameRepository.existsById(exameId, requisicao.getId())){
                throw new ExameJaCadastradoException();
            }
            var exame = exameRepository.findById(exameId).get();
            var layout = exame.getLayout();
            var requisicaoExame = new RequisicaoExame(layout, requisicao, exame, status);
            
            requisicaoExameRepository.save(requisicaoExame);

            requisicao.adicionarExame(requisicaoExame);
        }

        return requisicaoRepository.save(requisicao);
    }

    @Transactional
    public void deletarRequisicao(Long id) {
        var requisicao = requisicaoRepository.findById(id).get();

        for(int i = 0; i < requisicao.getRequisicaoExames().size(); i++){
            if(!requisicao.getRequisicaoExames().get(i).getItensResultado().isEmpty()){
                throw new RequisicaoExameComResultadoException("Deletar requisição");
            }
            requisicaoExameRepository.delete(requisicao.getRequisicaoExames().get(i));
        }

        requisicaoRepository.delete(requisicao);
    }

    @Transactional
    public Requisicao informarResultado(RequestInformarResultado dadosResultado) {
        var requisicaoExame = requisicaoExameRepository.findById(dadosResultado.requisicaoExameId()).get();

        //verificar se status do exame é apto para receber resultado -> apenas é possível informar resultado caso status seja material triado ou resultado informado
        if(!(requisicaoExame.getStatus().getCodigo().equals("MT") || requisicaoExame.getStatus().getCodigo().equals("RI"))){
            throw new StatusInvalidoParaRealizarOperacao("Informar Resultado - Status: " + requisicaoExame.getStatus().getNome());
        }

        //deletar todos os resultados
        requisicaoExameItensResultadoRepository.deleteByRequisicaoExame(requisicaoExame);
        requisicaoExame.deletarItensResultados();

        //cadastrar o que veio de resultado
        for(RequestInformarResultadoRequisicaoExameItensResultado requestItensResultado : dadosResultado.camposResultado()){
            var itemResultado = new RequisicaoExameItensResultado(requestItensResultado, requisicaoExame);
            requisicaoExameItensResultadoRepository.save(itemResultado);
            requisicaoExame.adicionarItemResultado(itemResultado);
        }

        var status = statusRepository.findByCodigo("RI").get();

        requisicaoExame.atualizarStatus(status); //atualizando status para resultado informado

        requisicaoExameRepository.save(requisicaoExame);

        return requisicaoExame.getRequisicao();
    }

    @Transactional
    public Requisicao informarColeta(Long id) {
        var requisicaoExame = requisicaoExameRepository.findById(id).get();
        var status = statusRepository.findByCodigo("MC").get();

        if(!(requisicaoExame.getStatus().getCodigo().equals("CD") || requisicaoExame.getStatus().getCodigo().equals("SR"))){ //só pode informar data e hora de coleta se o status do exame for exame cadastrado
            throw new StatusInvalidoParaRealizarOperacao("Informar Coleta - Status: " + requisicaoExame.getStatus().getNome());
        }

        if(requisicaoExame.getStatus().getCodigo().equals("SR")){
            requisicaoExame.setMotivoRecoleta(null);
        }

        requisicaoExame.atualizarDataHoraColeta();
        requisicaoExame.atualizarStatus(status);

        requisicaoExameRepository.save(requisicaoExame);

        return requisicaoExame.getRequisicao();
    }

    @Transactional
    public Requisicao informarTriagem(Long id) {
        var requisicaoExame = requisicaoExameRepository.findById(id).get();
        var status = statusRepository.findByCodigo("MT").get();

        if(!requisicaoExame.getStatus().getCodigo().equals("MC")){
            throw new StatusInvalidoParaRealizarOperacao("Informar Triagem - Status: " + requisicaoExame.getStatus().getNome());
        }

        requisicaoExame.atualizarDataHoraTriagem();
        requisicaoExame.atualizarStatus(status);

        requisicaoExameRepository.save(requisicaoExame);

        return requisicaoExame.getRequisicao();
    }

    @Transactional
    public Requisicao excluirResultado(Long id) {
        var requisicaoExame = requisicaoExameRepository.findById(id).get();
        var status = statusRepository.findByCodigo("MT").get();

        if(!requisicaoExame.getStatus().getCodigo().equals("RI")){
            throw new StatusInvalidoParaRealizarOperacao("Excluir Resultado - Status: " + requisicaoExame.getStatus().getNome());
        }

        requisicaoExameItensResultadoRepository.deleteByRequisicaoExame(requisicaoExame);
        requisicaoExame.deletarItensResultados();
        requisicaoExame.atualizarStatus(status);

        requisicaoExameRepository.save(requisicaoExame);

        return requisicaoExame.getRequisicao();
    }

    @Transactional
    public Requisicao cancelarExame(Long id) {
        var requisicaoExame = requisicaoExameRepository.findById(id).get();
        var status = statusRepository.findByCodigo("CA").get();

        if(requisicaoExame.getStatus().getCodigo() == "CA" || requisicaoExame.getStatus().getCodigo() == "RL"){
            throw new StatusInvalidoParaRealizarOperacao("Cancelar Exame - Status: " + requisicaoExame.getStatus().getNome());
        }

        requisicaoExameItensResultadoRepository.deleteByRequisicaoExame(requisicaoExame);
        requisicaoExame.deletarItensResultados();
        requisicaoExame.atualizarStatus(status);

        requisicaoExameRepository.save(requisicaoExame);

        return requisicaoExame.getRequisicao();
    }

    @Transactional
    public Requisicao liberarResultado(Long id, Usuario usuario) {
        var requisicaoExame = requisicaoExameRepository.findById(id).get();
        var status = statusRepository.findByCodigo("RL").get();

        if(!bioquimicoRepository.existsByUsuario(usuario)){
            throw new UsuarioNaoBioquimicoException();
        }

        var bioquimico = bioquimicoRepository.findByUsuario(usuario);

        if(!requisicaoExame.getStatus().getCodigo().equals("RI")){
            throw new StatusInvalidoParaRealizarOperacao("Liberar Resultado - Status: " + requisicaoExame.getStatus().getNome());
        }

        requisicaoExame.atualizarStatus(status);
        requisicaoExame.setBioquimico(bioquimico);
        requisicaoExame.setBioquimicoAssinatura(bioquimico.getAssinatura());

        requisicaoExameRepository.save(requisicaoExame);

        return requisicaoExame.getRequisicao();
    }

    @Transactional
    public Requisicao solicitarRecoleta(RequestSolicitarRecoleta dadosRecoleta, Usuario usuario) {
        var requisicaoExame = requisicaoExameRepository.findById(dadosRecoleta.requisicaoExameId()).get();
        var status = statusRepository.findByCodigo("SR").get();
        var motivoRecoleta = motivoRecoletaRepository.findById(dadosRecoleta.motivoRecoletaId()).get();

        if(!bioquimicoRepository.existsByUsuario(usuario)){
            throw new UsuarioNaoBioquimicoException();
        }

        if(!(requisicaoExame.getStatus().getCodigo().equals("RI") || requisicaoExame.getStatus().getCodigo().equals("MT"))){
            throw new StatusInvalidoParaRealizarOperacao("Solicitar Recoleta - Status: " + requisicaoExame.getStatus().getNome());
        }

        requisicaoExame.atualizarStatus(status);
        requisicaoExame.setMotivoRecoleta(motivoRecoleta);

        requisicaoExameRepository.save(requisicaoExame);

        return requisicaoExame.getRequisicao();
    }

    @Transactional
    public Requisicao cancelarLiberacao(Long id, Usuario usuario) {
        var requisicaoExame = requisicaoExameRepository.findById(id).get();
        var status = statusRepository.findByCodigo("RI").get();

        if(!bioquimicoRepository.existsByUsuario(usuario)){
            throw new UsuarioNaoBioquimicoException();
        }

        if(!requisicaoExame.getStatus().getCodigo().equals("RL")){
            throw new StatusInvalidoParaRealizarOperacao("Cancelar Liberação - Status: " + requisicaoExame.getStatus().getNome());
        }

        requisicaoExame.atualizarStatus(status);
        requisicaoExame.setBioquimico(null);
        requisicaoExame.setBioquimicoAssinatura(null);

        requisicaoExameRepository.save(requisicaoExame);

        return requisicaoExame.getRequisicao();
    }


    public Requisicao converterOrcamentoEmRequisicao(Long id, Usuario usuario) {
        var orcamento = orcamentoRepository.findById(id).get();

        var requisicao = new Requisicao(orcamento, usuario);

        requisicaoRepository.save(requisicao);
        
        var status = statusRepository.findByCodigo("CD").get();
        for(OrcamentoExame orcamentoExame : orcamento.getOrcamentoExames()){
            var requisicaoExame = new RequisicaoExame(orcamentoExame.getExame().getLayout(), requisicao, orcamentoExame.getExame(), status);

            requisicaoExameRepository.save(requisicaoExame);

            requisicao.adicionarExame(requisicaoExame);
        }

        return requisicaoRepository.save(requisicao);
    }

}
