package com.topitop.kpi.controller;

import com.topitop.kpi.model.Empleado;
import com.topitop.kpi.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(origins = "*") // Permite peticiones desde el front
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    // --- Obtener todos los empleados ---
    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }

    // --- Obtener empleado por ID ---
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id) {
        Optional<Empleado> empleado = empleadoService.buscarPorId(id);
        return empleado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // --- Buscar empleado por correo ---
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Empleado> buscarPorCorreo(@PathVariable String correo) {
        Empleado empleado = empleadoService.buscarPorCorreo(correo);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- Crear nuevo empleado ---
    @PostMapping
    public ResponseEntity<Empleado> crearEmpleado(@RequestBody Empleado empleado) {
        Empleado nuevoEmpleado = empleadoService.guardarEmpleado(empleado);
        return ResponseEntity.ok(nuevoEmpleado);
    }

    // --- Actualizar empleado existente ---
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        Optional<Empleado> existente = empleadoService.buscarPorId(id);
        if (existente.isPresent()) {
            empleado.setIdEmpleado(id);
            Empleado actualizado = empleadoService.guardarEmpleado(empleado);
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- Eliminar empleado por ID ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Integer id) {
        Optional<Empleado> existente = empleadoService.buscarPorId(id);
        if (existente.isPresent()) {
            empleadoService.eliminarEmpleado(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
