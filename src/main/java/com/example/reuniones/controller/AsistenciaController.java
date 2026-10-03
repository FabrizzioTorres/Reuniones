package com.example.reuniones.controller;

import com.example.reuniones.entity.Asistencia;
import com.example.reuniones.entity.EstadoAsistencia;
import com.example.reuniones.entity.Reunion;
import com.example.reuniones.service.AsistenciaService;
import com.example.reuniones.service.MiembroService;
import com.example.reuniones.service.ReunionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AsistenciaController {

    private final AsistenciaService asistenciaService;
    private final MiembroService miembroService;
    private final ReunionService reunionService;

    public AsistenciaController(
            AsistenciaService asistenciaService,
            MiembroService miembroService,
            ReunionService reunionService) {

        this.asistenciaService = asistenciaService;
        this.miembroService = miembroService;
        this.reunionService = reunionService;
    }

    @GetMapping("/asistencias")
    public String reuniones(Model model) {

        model.addAttribute(
                "reuniones",
                reunionService.listarTodas()
        );

        return "asistencias/reuniones";
    }

    @GetMapping("/asistencias/reunion/{id}")
    public String registrar(
            @PathVariable Long id,
            Model model) {

        Reunion reunion = reunionService
                .buscarPorId(id)
                .orElseThrow();

        List<Asistencia> asistencias =
                asistenciaService.listarPorReunion(id);

        Map<Long, EstadoAsistencia> estadosGuardados =
                new HashMap<>();

        for (Asistencia asistencia : asistencias) {

            estadosGuardados.put(
                    asistencia.getMiembro().getId(),
                    asistencia.getEstado()
            );
        }

        model.addAttribute("reunion", reunion);

        model.addAttribute(
                "miembros",
                miembroService.listarTodos()
        );

        model.addAttribute(
                "estados",
                EstadoAsistencia.values()
        );

        model.addAttribute(
                "estadosGuardados",
                estadosGuardados
        );

        return "asistencias/registro";
    }

    @PostMapping("/asistencias/guardar")
    public String guardar(
            @RequestParam Long reunionId,
            @RequestParam List<Long> miembroId,
            @RequestParam List<EstadoAsistencia> estado) {

        for (int i = 0; i < miembroId.size(); i++) {

            asistenciaService.guardarAsistencia(
                    reunionId,
                    miembroId.get(i),
                    estado.get(i)
            );
        }

        return "redirect:/asistencias/reunion/" + reunionId + "/lista";
    }
    @GetMapping("/asistencias/reunion/{id}/lista")
    public String verAsistencia(
            @PathVariable Long id,
            Model model) {

        Reunion reunion = reunionService
                .buscarPorId(id)
                .orElseThrow();

        List<Asistencia> asistencias =
                asistenciaService.listarPorReunion(id);

        Map<Long, EstadoAsistencia> estadosGuardados =
                new HashMap<>();

        for (Asistencia asistencia : asistencias) {

            estadosGuardados.put(
                    asistencia.getMiembro().getId(),
                    asistencia.getEstado()
            );
        }

        model.addAttribute(
                "reunion",
                reunion
        );

        model.addAttribute(
                "miembros",
                miembroService.listarTodos()
        );

        model.addAttribute(
                "estadosGuardados",
                estadosGuardados
        );

        return "asistencias/lista";
    }
}