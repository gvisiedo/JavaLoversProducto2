package com.example.producto.seguridad;

import com.example.producto.entidad.RolPubli;
import com.example.producto.entidad.UsuarioPubli;
import com.example.producto.repositorio.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Override
    public UserDetails loadUserByUsername(String usernameOremail) throws UsernameNotFoundException {
        UsuarioPubli usuarioPubli = usuarioRepo.findByUsernameOrEmail(usernameOremail,usernameOremail).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));

        return  new User(usuarioPubli.getEmail(), usuarioPubli.getPassword(),mapearRoles(usuarioPubli.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapearRoles(Set<RolPubli> rolPublis){
        return rolPublis.stream().map(rolPubli -> new SimpleGrantedAuthority(rolPubli.getNombre())).collect(Collectors.toList());

    }
}
