package com.topitop.kpi.controller;

import com.topitop.kpi.model.Mision;
import com.topitop.kpi.service.MisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // Importar Optional

@RestController
@RequestMapping("/api/misiones")
@CrossOrigin(origins = "*") // Permite peticiones desde el front
public class MisionController {

    @Autowired
    private MisionService misionService;

    // --- Endpoint para CREAR una nueva misión ---
    @PostMapping
    public ResponseEntity<Mision> crearMision(@RequestBody Mision mision) {
        // Asumiendo que el frontend envía el supervisor y empleadoAsignado correctos
        Mision nuevaMision = misionService.guardarMision(mision);
        return ResponseEntity.ok(nuevaMision);
    }

    // --- Endpoint para OBTENER misiones por supervisor ---
    @GetMapping("/supervisor/{idSupervisor}")
    public List<Mision> listarMisionesPorSupervisor(@PathVariable Integer idSupervisor) {
        return misionService.listarMisionesPorSupervisor(idSupervisor);
    }

    // --- Endpoint NUEVO para OBTENER una misión por su ID ---
    // Necesario para la función cargarMisionParaEditar del frontend
    @GetMapping("/{idMision}")
    public ResponseEntity<Mision> obtenerMisionPorId(@PathVariable Integer idMision) {
        Optional<Mision> mision = misionService.buscarPorId(idMision);
        // Usamos la forma corta que sí funciona bien aquí
        return mision.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // --- Endpoint para ACTUALIZAR una misión (CORREGIDO) ---
    @PutMapping("/{idMision}/supervisor/{idSupervisor}")
    public ResponseEntity<Mision> actualizarMision(
            @PathVariable Integer idMision,
            @PathVariable Integer idSupervisor,
            @RequestBody Mision misionDatos) {

        // 1. Buscar la misión existente
        Optional<Mision> existenteOptional = misionService.buscarPorId(idMision);

        // 2. Si no existe, 404 Not Found
        if (existenteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Mision existente = existenteOptional.get();

        // 3. Verificación: Solo el supervisor que la creó puede editarla
        // Asegurarse de que el supervisor exista antes de comparar IDs
        if (existente.getSupervisor() == null || !existente.getSupervisor().getIdSupervisor().equals(idSupervisor)) {
            return ResponseEntity.status(403).body(null); // 403 Forbidden
        }

        // 4. Actualizamos los campos permitidos
        existente.setNombreMision(misionDatos.getNombreMision());
        existente.setDescripcion(misionDatos.getDescripcion());
        existente.setObjetivo(misionDatos.getObjetivo());
        existente.setFechaInicio(misionDatos.getFechaInicio());
        existente.setFechaFin(misionDatos.getFechaFin());
        existente.setEstado(misionDatos.getEstado());
        existente.setPuntajeMision(misionDatos.getPuntajeMision());
        existente.setEmpleadoAsignado(misionDatos.getEmpleadoAsignado()); // Actualiza el empleado asignado

        // 5. Guardar y devolver
        Mision actualizada = misionService.guardarMision(existente);
        return ResponseEntity.ok(actualizada);
    }

    // --- Endpoint para ELIMINAR una misión (CORREGIDO) ---
    @DeleteMapping("/{idMision}/supervisor/{idSupervisor}")
    public ResponseEntity<Void> eliminarMision(
            @PathVariable Integer idMision,
            @PathVariable Integer idSupervisor) {

        // 1. Buscar la misión existente
        Optional<Mision> existenteOptional = misionService.buscarPorId(idMision);

        // 2. Si no existe, 404 Not Found
        if (existenteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Mision existente = existenteOptional.get();

        // 3. Verificación: Solo el supervisor que la creó puede borrarla
        if (existente.getSupervisor() == null || !existente.getSupervisor().getIdSupervisor().equals(idSupervisor)) {
            return ResponseEntity.status(403).build(); // 403 Forbidden
        }

        // 4. Eliminar
        misionService.eliminarMision(idMision);
        return ResponseEntity.noContent().build(); // OK, sin contenido
    }
}
