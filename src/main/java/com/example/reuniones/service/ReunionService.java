package com.example.reuniones.service;

import com.example.reuniones.entity.Reunion;

import java.util.List;
import java.util.Optional;

public interface ReunionService {

    List<Reunion> listarTodas();

    Optional<Reunion> buscarPorId(Long id);

    Reunion guardar(Reunion reunion);

    void eliminar(Long id);
}