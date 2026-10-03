package com.example.reuniones.service.impl;

import com.example.reuniones.entity.Asistencia;
import com.example.reuniones.entity.EstadoAsistencia;
import com.example.reuniones.entity.Miembro;
import com.example.reuniones.entity.Reunion;
import com.example.reuniones.repository.AsistenciaRepository;
import com.example.reuniones.repository.MiembroRepository;
import com.example.reuniones.repository.ReunionRepository;
import com.example.reuniones.service.AsistenciaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final MiembroRepository miembroRepository;
    private final ReunionRepository reunionRepository;

    public AsistenciaServiceImpl(
            AsistenciaRepository asistenciaRepository,
            MiembroRepository miembroRepository,
            ReunionRepository reunionRepository) {

        this.asistenciaRepository = asistenciaRepository;
        this.miembroRepository = miembroRepository;
        this.reunionRepository = reunionRepository;
    }

    @Override
    public List<Asistencia> listarPorReunion(Long reunionId) {

        return asistenciaRepository.findByReunionId(reunionId);
    }

    @Override
    public void guardarAsistencia(
            Long reunionId,
            Long miembroId,
            EstadoAsistencia estado) {

        Reunion reunion = reunionRepository
                .findById(reunionId)
                .orElseThrow();

        Miembro miembro = miembroRepository
                .findById(miembroId)
                .orElseThrow();

        Asistencia asistencia = asistenciaRepository
                .findByMiembroIdAndReunionId(miembroId, reunionId)
                .orElse(new Asistencia());

        asistencia.setReunion(reunion);
        asistencia.setMiembro(miembro);
        asistencia.setEstado(estado);

        asistenciaRepository.save(asistencia);
    }
}