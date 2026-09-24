package com.example.reuniones.controller;

import com.example.reuniones.entity.Reunion;
import com.example.reuniones.service.ReunionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReunionController {

    private final ReunionService reunionService;

    public ReunionController(ReunionService reunionService) {
        this.reunionService = reunionService;
    }

    @GetMapping("/reuniones")
    public String listar(Model model) {
        model.addAttribute("reuniones", reunionService.listarTodas());
        return "reuniones/lista";
    }

    @GetMapping("/reuniones/nueva")
    public String nueva(Model model) {
        model.addAttribute("reunion", new Reunion());
        return "reuniones/form";
    }

    @GetMapping("/reuniones/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Reunion reunion = reunionService.buscarPorId(id).orElseThrow();
        model.addAttribute("reunion", reunion);
        return "reuniones/form";
    }

    @PostMapping("/reuniones/guardar")
    public String guardar(Reunion reunion) {
        reunionService.guardar(reunion);
        return "redirect:/reuniones";
    }

    @GetMapping("/reuniones/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        reunionService.eliminar(id);
        return "redirect:/reuniones";
    }
}
