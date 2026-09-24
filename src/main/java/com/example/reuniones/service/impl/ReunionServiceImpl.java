package com.example.reuniones.service.impl;

import com.example.reuniones.entity.Reunion;
import com.example.reuniones.repository.ReunionRepository;
import com.example.reuniones.service.ReunionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReunionServiceImpl implements ReunionService {

    private final ReunionRepository reunionRepository;

    public ReunionServiceImpl(ReunionRepository reunionRepository) {
        this.reunionRepository = reunionRepository;
    }

    @Override
    public List<Reunion> listarTodas() {
        return reunionRepository.findAll();
    }

    @Override
    public Optional<Reunion> buscarPorId(Long id) {
        return reunionRepository.findById(id);
    }

    @Override
    public Reunion guardar(Reunion reunion) {
        return reunionRepository.save(reunion);
    }

    @Override
    public void eliminar(Long id) {
        reunionRepository.deleteById(id);
    }
}