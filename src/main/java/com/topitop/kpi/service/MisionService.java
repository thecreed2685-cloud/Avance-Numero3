package com.topitop.kpi.service;

import com.topitop.kpi.model.Mision;
import com.topitop.kpi.repository.MisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MisionService {

    @Autowired
    private MisionRepository misionRepository;

    // Listar misiones por supervisor
    public List<Mision> listarMisionesPorSupervisor(Integer idSupervisor) {
        return misionRepository.findBySupervisorIdSupervisor(idSupervisor);
    }

    // Crear o actualizar una misión
    public Mision guardarMision(Mision mision) {
        // Aquí podrías añadir lógica extra, como validar fechas, etc.
        return misionRepository.save(mision);
    }

    // Buscar misión por ID
    public Optional<Mision> buscarPorId(Integer id) {
        return misionRepository.findById(id);
    }

    // Eliminar misión por ID
    public void eliminarMision(Integer id) {
        misionRepository.deleteById(id);
    }
}