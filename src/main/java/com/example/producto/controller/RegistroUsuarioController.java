package com.example.producto.controller;

import com.example.producto.dto.UsuarioRegistroDto;
import com.example.producto.servicio.UsuarioServicio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioController {
    private UsuarioServicio usuarioServicio;

    public RegistroUsuarioController(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }
    @ModelAttribute("usuario")
    public UsuarioRegistroDto retornarNuevoUsuarioRegistroDto(){
        return new UsuarioRegistroDto();
    }
    @GetMapping
    public String mostrarFormularioDeRegistro(){
        return "registro";
    }
    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDto registroDto){
        usuarioServicio.save(registroDto);
        return "redirect:/registro?exito";
    }
}
