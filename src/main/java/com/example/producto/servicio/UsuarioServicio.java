package com.example.producto.servicio;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.producto.dto.UsuarioRegistroDto;
import com.example.producto.modelo.Usuario;

public interface UsuarioServicio extends UserDetailsService{

    public Usuario save(UsuarioRegistroDto registroDto);

    public List<Usuario> listarUsuarios();
}
