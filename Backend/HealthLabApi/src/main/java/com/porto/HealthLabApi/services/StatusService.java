package com.porto.HealthLabApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.porto.HealthLabApi.domain.status.Status;
import com.porto.HealthLabApi.repositories.StatusRepository;

@Service
public class StatusService {
    
    @Autowired
    private StatusRepository statusRepository;

    public Status detalharStatus(String codigo){
        var status = statusRepository.findByCodigo(codigo).get();

        return status;
    }

}
