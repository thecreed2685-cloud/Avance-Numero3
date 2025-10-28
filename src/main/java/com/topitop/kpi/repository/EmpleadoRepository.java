package com.topitop.kpi.repository;

import com.topitop.kpi.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    // Ejemplo: buscar por correo empresarial
    Empleado findByCorreoEmpresarial(String correoEmpresarial);

    // Ejemplo: buscar empleados por apellido
    java.util.List<Empleado> findByApellidoContainingIgnoreCase(String apellido);
}
