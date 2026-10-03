package com.example.reuniones.service;

import com.example.reuniones.entity.Asistencia;
import com.example.reuniones.entity.EstadoAsistencia;

import java.util.List;

public interface AsistenciaService {

    List<Asistencia> listarPorReunion(Long reunionId);

    void guardarAsistencia(
            Long reunionId,
            Long miembroId,
            EstadoAsistencia estado
    );

}