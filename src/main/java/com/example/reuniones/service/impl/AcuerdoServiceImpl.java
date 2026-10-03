package com.example.reuniones.service.impl;

import com.example.reuniones.entity.Acuerdo;
import com.example.reuniones.entity.Miembro;
import com.example.reuniones.entity.Reunion;
import com.example.reuniones.repository.AcuerdoRepository;
import com.example.reuniones.repository.MiembroRepository;
import com.example.reuniones.repository.ReunionRepository;
import com.example.reuniones.service.AcuerdoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcuerdoServiceImpl implements AcuerdoService {

    private final AcuerdoRepository acuerdoRepository;
    private final MiembroRepository miembroRepository;
    private final ReunionRepository reunionRepository;

    public AcuerdoServiceImpl(
            AcuerdoRepository acuerdoRepository,
            MiembroRepository miembroRepository,
            ReunionRepository reunionRepository) {

        this.acuerdoRepository = acuerdoRepository;
        this.miembroRepository = miembroRepository;
        this.reunionRepository = reunionRepository;
    }

    @Override
    public List<Acuerdo> listarTodos() {
        return acuerdoRepository.findAll();
    }

    @Override
    public Optional<Acuerdo> buscarPorId(Long id) {
        return acuerdoRepository.findById(id);
    }

    @Override
    public Acuerdo guardar(
            Acuerdo acuerdo,
            Long miembroId,
            Long reunionId) {

        Miembro miembro = miembroRepository
                .findById(miembroId)
                .orElseThrow();

        Reunion reunion = reunionRepository
                .findById(reunionId)
                .orElseThrow();

        acuerdo.setMiembro(miembro);
        acuerdo.setReunion(reunion);

        return acuerdoRepository.save(acuerdo);
    }

    @Override
    public void eliminar(Long id) {
        acuerdoRepository.deleteById(id);
    }
}