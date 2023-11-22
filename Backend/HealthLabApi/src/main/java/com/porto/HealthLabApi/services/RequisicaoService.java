package com.porto.HealthLabApi.services;

import java.math.BigDecimal;
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
import com.porto.HealthLabApi.repositories.ExameRepository;
import com.porto.HealthLabApi.repositories.LayoutRepository;
import com.porto.HealthLabApi.repositories.MedicoRepository;
import com.porto.HealthLabApi.repositories.PessoaRepository;
import com.porto.HealthLabApi.repositories.RequisicaoExameItensResultadoRepository;
import com.porto.HealthLabApi.repositories.RequisicaoExameRepository;
import com.porto.HealthLabApi.repositories.RequisicaoRepository;
import com.porto.HealthLabApi.repositories.StatusRepository;
import com.porto.HealthLabApi.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

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
    private LayoutRepository layoutRepository;

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

        var precoTotal = new BigDecimal(0);
        var status = statusRepository.findByCodigo("CD").get();
        for(Long exameId : dadosRequisicao.examesId()){
            var exame = exameRepository.findById(exameId).get();
            var layout = layoutRepository.findById(exame.getLayout().getId()).get();
            precoTotal = precoTotal.add(exame.getPreco());

            var requisicaoExame = new RequisicaoExame(dadosRequisicao, layout, requisicao, exame, status);

            requisicaoExameRepository.save(requisicaoExame);

            requisicao.adicionarExame(requisicaoExame);
        }
        requisicao.setPretoTotal(precoTotal);

        return requisicaoRepository.save(requisicao);
    }

}
