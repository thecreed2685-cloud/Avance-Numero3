package com.topitop.kpi.repository;

import com.topitop.kpi.model.Mision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MisionRepository extends JpaRepository<Mision, Integer> {

    // Método para encontrar misiones creadas por un supervisor específico
    List<Mision> findBySupervisorIdSupervisor(Integer idSupervisor);

    // (Opcional) Método para encontrar misiones asignadas a un empleado específico
    List<Mision> findByEmpleadoAsignadoIdEmpleado(Integer idEmpleado);
}