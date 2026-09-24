package com.example.reuniones.service.impl;

import com.example.reuniones.entity.Miembro;
import com.example.reuniones.repository.MiembroRepository;
import com.example.reuniones.service.MiembroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroServiceImpl implements MiembroService {

    private final MiembroRepository miembroRepository;

    public MiembroServiceImpl(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    @Override
    public List<Miembro> listarTodos() {
        return miembroRepository.findAll();
    }

    @Override
    public Optional<Miembro> buscarPorId(Long id) {
        return miembroRepository.findById(id);
    }

    @Override
    public Miembro guardar(Miembro miembro) {
        return miembroRepository.save(miembro);
    }

    @Override
    public void eliminar(Long id) {
        miembroRepository.deleteById(id);
    }
}