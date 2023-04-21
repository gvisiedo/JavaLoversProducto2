package com.example.producto.servicio;

import com.example.producto.dto.UsuarioRegistroDto;
import com.example.producto.modelo.Rol;
import com.example.producto.modelo.Usuario;
import com.example.producto.repositorio.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

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
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioRepositorio.findByEmail(username);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }

        return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
    }



}
