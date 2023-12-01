package com.porto.HealthLabApi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.medico.Medico;
import com.porto.HealthLabApi.domain.requisicao.Requisicao;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExame;
import com.porto.HealthLabApi.domain.requisicao.RequisicaoExameItensResultado;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestCadastrarRequisicao;
import com.porto.HealthLabApi.domain.requisicao.DTO.RequestEditarRequisicao;
import com.porto.HealthLabApi.infra.exception.exceptions.ExameJaCadastradoException;
import com.porto.HealthLabApi.infra.exception.exceptions.RequisicaoExameComResultadoException;
import com.porto.HealthLabApi.repositories.ExameRepository;
import com.porto.HealthLabApi.repositories.MedicoRepository;
import com.porto.HealthLabApi.repositories.PessoaRepository;
import com.porto.HealthLabApi.repositories.RequisicaoExameItensResultadoRepository;
import com.porto.HealthLabApi.repositories.RequisicaoExameRepository;
import com.porto.HealthLabApi.repositories.RequisicaoRepository;
import com.porto.HealthLabApi.repositories.StatusRepository;
import com.porto.HealthLabApi.repositories.UsuarioRepository;

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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private RequisicaoExameItensResultadoRepository requisicaoExameItensResultadoRepository;

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
    public Requisicao cadastrarRequisicao(RequestCadastrarRequisicao dadosRequisicao) {
        Medico medico = null;
        if(dadosRequisicao.medicoId() != null){
            medico = medicoRepository.findById(dadosRequisicao.medicoId()).get();
        }
        var pessoa = pessoaRepository.findById(dadosRequisicao.pessoaId()).get();
        var usuario = usuarioRepository.findById(dadosRequisicao.usuarioId()).get();
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
                    throw new RequisicaoExameComResultadoException();
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
            System.out.println(exameId + " " + requisicao.getId());
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
                throw new RequisicaoExameComResultadoException();
            }
            requisicaoExameRepository.delete(requisicao.getRequisicaoExames().get(i));
        }

        requisicaoRepository.delete(requisicao);
    }

}
