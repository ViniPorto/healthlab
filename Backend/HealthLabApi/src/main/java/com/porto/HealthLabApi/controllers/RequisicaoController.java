package com.porto.HealthLabApi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porto.HealthLabApi.services.RequisicaoService;
import com.porto.HealthLabApi.utils.ResponseHandler;

@RestController
@RequestMapping("/requisicao")
public class RequisicaoController {
 
    @Autowired
    private RequisicaoService requisicaoService;

    @Autowired
    private ResponseHandler responseHandler;

    
}
