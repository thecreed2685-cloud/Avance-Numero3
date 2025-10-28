package com.topitop.kpi.repository;

import com.topitop.kpi.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    // Ejemplo: buscar por correo empresarial
    Empleado findByCorreoEmpresarial(String correoEmpresarial);


    // Busca todos los empleados que pertenecen a un supervisor específico
    List<Empleado> findBySupervisorIdSupervisor(Integer idSupervisor);
}
