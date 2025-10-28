package com.topitop.kpi.repository;

import com.topitop.kpi.model.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {

    Supervisor findByCorreoEmpresarial(String correoEmpresarial);
}