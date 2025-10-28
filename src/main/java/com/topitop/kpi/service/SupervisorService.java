package com.topitop.kpi.service;

import com.topitop.kpi.model.Supervisor;
import com.topitop.kpi.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    public Supervisor buscarPorCorreo(String correo) {
        return supervisorRepository.findByCorreoEmpresarial(correo);
    }
}