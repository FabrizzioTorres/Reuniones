package com.example.reuniones.controller;

import com.example.reuniones.entity.Miembro;
import com.example.reuniones.service.MiembroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MiembroController {

    private final MiembroService miembroService;

    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    @GetMapping({"/", "/miembros"})
    public String listar(Model model) {
        model.addAttribute("miembros", miembroService.listarTodos());
        return "miembros/lista";
    }

    @GetMapping("/miembros/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("miembro", new Miembro());
        return "miembros/form";
    }

    @GetMapping("/miembros/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Miembro miembro = miembroService.buscarPorId(id).orElseThrow();
        model.addAttribute("miembro", miembro);
        return "miembros/form";
    }

    @PostMapping("/miembros/guardar")
    public String guardar(Miembro miembro) {
        miembroService.guardar(miembro);
        return "redirect:/miembros";
    }

    @GetMapping("/miembros/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        miembroService.eliminar(id);
        return "redirect:/miembros";
    }
}