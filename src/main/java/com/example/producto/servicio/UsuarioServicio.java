package com.example.producto.servicio;

import com.example.producto.dto.UsuarioRegistroDto;
import com.example.producto.modelo.Usuario;

public interface UsuarioServicio {

    public Usuario save(UsuarioRegistroDto registroDto);
}
