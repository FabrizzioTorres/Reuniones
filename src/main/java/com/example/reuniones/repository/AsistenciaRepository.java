package com.example.reuniones.repository;

import com.example.reuniones.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AsistenciaRepository
        extends JpaRepository<Asistencia, Long> {

    List<Asistencia> findByReunionId(Long reunionId);

    Optional<Asistencia> findByMiembroIdAndReunionId(
            Long miembroId,
            Long reunionId
    );
}