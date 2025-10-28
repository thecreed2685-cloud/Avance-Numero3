package com.topitop.kpi.controller;

import com.topitop.kpi.model.Supervisor;
import com.topitop.kpi.service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    // --- ENDPOINT PARA ACTUALIZAR PERFIL DE SUPERVISOR ---
    @PutMapping("/{idSupervisor}")
    public ResponseEntity<Supervisor> actualizarPerfilSupervisor(
            @PathVariable Integer idSupervisor,
            @RequestBody Supervisor datosActualizados) {

        // 1. Busca el supervisor existente
        Optional<Supervisor> supervisorOptional = supervisorService.buscarPorId(idSupervisor);

        // 2. Si no existe, 404 Not Found
        if (supervisorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Supervisor supervisorExistente = supervisorOptional.get();

        // 3. Actualiza SOLO los campos permitidos
        supervisorExistente.setNombre(datosActualizados.getNombre());
        supervisorExistente.setApellido(datosActualizados.getApellido());
        supervisorExistente.setFechaNacimiento(datosActualizados.getFechaNacimiento());
        supervisorExistente.setTelefono(datosActualizados.getTelefono());
        supervisorExistente.setPuesto(datosActualizados.getPuesto());
        supervisorExistente.setTiempoTrabajo(datosActualizados.getTiempoTrabajo()); // Asumiendo que se puede editar
        supervisorExistente.setEsContrato(datosActualizados.getEsContrato());   // Asumiendo que se puede editar
        supervisorExistente.setFotoUrl(datosActualizados.getFotoUrl());

        // 4. Guarda los cambios
        Supervisor supervisorGuardado = supervisorService.guardarSupervisor(supervisorExistente);

        // 5. Devuelve el supervisor actualizado
        return ResponseEntity.ok(supervisorGuardado);
    }
}