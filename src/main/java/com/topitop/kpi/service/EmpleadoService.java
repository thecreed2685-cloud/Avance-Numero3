package com.topitop.kpi.service;

import com.topitop.kpi.model.Empleado;
import com.topitop.kpi.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // --- Listar todos los empleados ---
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    // --- Buscar empleado por ID ---
    public Optional<Empleado> buscarPorId(Integer id) {
        return empleadoRepository.findById(id);
    }

    // --- Buscar empleado por correo ---
    public Empleado buscarPorCorreo(String correo) {
        return empleadoRepository.findByCorreoEmpresarial(correo);
    }

    // --- Registrar o actualizar empleado ---
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // --- Eliminar empleado por ID ---
    public void eliminarEmpleado(Integer id) {
        empleadoRepository.deleteById(id);
    }
}
