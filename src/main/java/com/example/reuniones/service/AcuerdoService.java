package com.example.reuniones.service;

import com.example.reuniones.entity.Acuerdo;

import java.util.List;
import java.util.Optional;

public interface AcuerdoService {

    List<Acuerdo> listarTodos();

    Optional<Acuerdo> buscarPorId(Long id);

    Acuerdo guardar(Acuerdo acuerdo, Long miembroId, Long reunionId);

    void eliminar(Long id);
}