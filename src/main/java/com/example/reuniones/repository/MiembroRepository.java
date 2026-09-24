package com.example.reuniones.repository;

import com.example.reuniones.entity.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiembroRepository extends JpaRepository<Miembro, Long> {
}