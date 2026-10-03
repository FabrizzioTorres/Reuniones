package com.example.reuniones.controller;

import com.example.reuniones.entity.Acuerdo;
import com.example.reuniones.service.AcuerdoService;
import com.example.reuniones.service.MiembroService;
import com.example.reuniones.service.ReunionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AcuerdoController {

    private final AcuerdoService acuerdoService;
    private final MiembroService miembroService;
    private final ReunionService reunionService;

    public AcuerdoController(
            AcuerdoService acuerdoService,
            MiembroService miembroService,
            ReunionService reunionService) {

        this.acuerdoService = acuerdoService;
        this.miembroService = miembroService;
        this.reunionService = reunionService;
    }

    @GetMapping("/acuerdos")
    public String listar(Model model) {

        model.addAttribute(
                "acuerdos",
                acuerdoService.listarTodos()
        );

        return "acuerdos/lista";
    }

    @GetMapping("/acuerdos/nuevo")
    public String nuevo(Model model) {

        model.addAttribute("acuerdo", new Acuerdo());

        model.addAttribute(
                "miembros",
                miembroService.listarTodos()
        );

        model.addAttribute(
                "reuniones",
                reunionService.listarTodas()
        );

        return "acuerdos/form";
    }

    @GetMapping("/acuerdos/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        Acuerdo acuerdo = acuerdoService
                .buscarPorId(id)
                .orElseThrow();

        model.addAttribute("acuerdo", acuerdo);

        model.addAttribute(
                "miembros",
                miembroService.listarTodos()
        );

        model.addAttribute(
                "reuniones",
                reunionService.listarTodas()
        );

        return "acuerdos/form";
    }

    @PostMapping("/acuerdos/guardar")
    public String guardar(
            Acuerdo acuerdo,
            @RequestParam Long miembroId,
            @RequestParam Long reunionId) {

        acuerdoService.guardar(
                acuerdo,
                miembroId,
                reunionId
        );

        return "redirect:/acuerdos";
    }

    @GetMapping("/acuerdos/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        acuerdoService.eliminar(id);

        return "redirect:/acuerdos";
    }
}