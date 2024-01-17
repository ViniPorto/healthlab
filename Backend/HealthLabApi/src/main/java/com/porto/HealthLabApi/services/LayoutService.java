package com.porto.HealthLabApi.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.historico.Historico;
import com.porto.HealthLabApi.domain.layout.Layout;
import com.porto.HealthLabApi.domain.layout.LayoutCampos;
import com.porto.HealthLabApi.domain.layout.DTO.RequestCadastrarLayout;
import com.porto.HealthLabApi.domain.layout.DTO.RequestCadastrarLayoutCampos;
import com.porto.HealthLabApi.domain.usuario.Usuario;
import com.porto.HealthLabApi.repositories.ExameRepository;
import com.porto.HealthLabApi.repositories.HistoricoRepository;
import com.porto.HealthLabApi.repositories.LayoutCamposRepository;
import com.porto.HealthLabApi.repositories.LayoutRepository;

import jakarta.transaction.Transactional;

@Service
public class LayoutService {
    
    @Autowired
    private LayoutRepository layoutRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired 
    private LayoutCamposRepository layoutCamposRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    public Layout detalharLayout(Long id) {
        return layoutRepository.findById(id).get();
    }

    public List<LayoutCampos> listarCamposLayout(Layout layout) {
        return layoutCamposRepository.findByLayout(layout);
    }

    @Transactional
    public Layout cadastrarLayout(Long exameId, Usuario usuario) {
        var exame = exameRepository.findById(exameId).get();
        var layout = new Layout();

        exame.alterarLayout(layout);

        exameRepository.save(exame);
        layoutRepository.save(layout);

        historicoRepository.save(new Historico(layout.getId(), "LAYOUT", usuario, "CADATRO", LocalDateTime.now(), gerarDados(layout)));

        return layout;
    }

    @Transactional
    public List<LayoutCampos> cadastrarLayoutCampos(Layout layout, RequestCadastrarLayout dadosLayout) {
        List<RequestCadastrarLayoutCampos> layoutCampos = dadosLayout.campos();
        List<LayoutCampos> layoutCamposCriados = new ArrayList<>();
        for(RequestCadastrarLayoutCampos layoutCampo : layoutCampos){
            var novoLayoutCampo = new LayoutCampos(layout, layoutCampo);
            layoutCamposCriados.add(layoutCamposRepository.save(novoLayoutCampo));
        }
        return layoutCamposCriados;
    }

    private String gerarDados(Layout layout){
        return "ID: " + layout.getId();
    }


}
