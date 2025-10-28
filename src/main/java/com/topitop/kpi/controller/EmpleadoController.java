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

    // --- Obtener todos los empleados del supervisor ---
    @GetMapping("/supervisor/{idSupervisor}")
    public List<Empleado> listarEmpleadosPorSupervisor(@PathVariable Integer idSupervisor) {
        return empleadoService.listarEmpleadosPorSupervisor(idSupervisor);
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
    @PutMapping("/{idEmpleado}/supervisor/{idSupervisor}")
    public ResponseEntity<Empleado> actualizarEmpleado(
            @PathVariable Integer idEmpleado,
            @PathVariable Integer idSupervisor,
            @RequestBody Empleado empleadoDatos) {

        // 1. Busca al empleado que se quiere editar
        Optional<Empleado> existenteOptional = empleadoService.buscarPorId(idEmpleado);

        // 2. Si no existe, retorna 404 Not Found
        if (existenteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Empleado existente = existenteOptional.get();

        // 3. Verificación de seguridad:
        // ¿El empleado 'existente' pertenece al 'idSupervisor' que hace la petición?
        if (!existente.getSupervisor().getIdSupervisor().equals(idSupervisor)) {
            // 4. Si no coincide, es un intento no autorizado
            return ResponseEntity.status(403).body(null); // 403 Forbidden
        }

        // 5. Si todo está bien, actualiza los datos
        empleadoDatos.setIdEmpleado(idEmpleado);
        // Asegúrate de que la actualización mantenga al supervisor original
        empleadoDatos.setSupervisor(existente.getSupervisor());

        Empleado actualizado = empleadoService.guardarEmpleado(empleadoDatos);
        return ResponseEntity.ok(actualizado);
    }

    // --- Eliminar empleado por ID ---
    @DeleteMapping("/{idEmpleado}/supervisor/{idSupervisor}")
    public ResponseEntity<Void> eliminarEmpleado(
            @PathVariable Integer idEmpleado,
            @PathVariable Integer idSupervisor) {

        // 1. Busca al empleado que se quiere eliminar
        Optional<Empleado> existenteOptional = empleadoService.buscarPorId(idEmpleado);

        // 2. Si no existe, retorna 404 Not Found
        if (existenteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Empleado existente = existenteOptional.get();

        // 3. Verificación de seguridad:
        if (!existente.getSupervisor().getIdSupervisor().equals(idSupervisor)) {
            // 4. Si no es su empleado, retorna 403 Forbidden
            return ResponseEntity.status(403).build();
        }

        // 5. Si es su empleado, lo puede borrar
        empleadoService.eliminarEmpleado(idEmpleado);
        return ResponseEntity.noContent().build();
    }
}
