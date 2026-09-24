package com.example.reuniones.service;

import com.example.reuniones.entity.Miembro;

import java.util.List;
import java.util.Optional;

public interface MiembroService {

    List<Miembro> listarTodos();

    Optional<Miembro> buscarPorId(Long id);

    Miembro guardar(Miembro miembro);

    void eliminar(Long id);
}