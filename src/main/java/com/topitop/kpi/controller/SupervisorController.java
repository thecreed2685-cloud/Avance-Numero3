package com.topitop.kpi.controller;

import com.topitop.kpi.model.Supervisor;
import com.topitop.kpi.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supervisores")
@CrossOrigin(origins = "*")
public class SupervisorController {

    @Autowired
    private SupervisorService supervisorService;

    // Endpoint para el login: busca un supervisor por su correo
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Supervisor> buscarPorCorreo(@PathVariable String correo) {
        Supervisor supervisor = supervisorService.buscarPorCorreo(correo);
        if (supervisor != null) {
            return ResponseEntity.ok(supervisor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}