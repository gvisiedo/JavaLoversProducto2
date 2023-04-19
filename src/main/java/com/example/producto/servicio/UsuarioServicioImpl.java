package com.example.producto.servicio;

import com.example.producto.dto.UsuarioRegistroDto;
import com.example.producto.modelo.Rol;
import com.example.producto.modelo.Usuario;
import com.example.producto.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private UsuarioRepositorio usuarioRepositorio;
    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }
    @Override
    public Usuario save(UsuarioRegistroDto registroDto) {
       Usuario usuario=new Usuario(registroDto.getNombre(), registroDto.getApellido(), registroDto.getEmail(), registroDto.getPassword(), Arrays.asList(new Rol("ROL_USER")));
       return usuarioRepositorio.save(usuario);
    }


}
