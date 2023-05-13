package com.example.producto.controller;

import com.example.producto.dto.LoginDto;
import com.example.producto.dto.RegistroDto;
import com.example.producto.entidad.RolPubli;
import com.example.producto.entidad.UsuarioPubli;
import com.example.producto.modelo.Usuario;
import com.example.producto.repositorio.RolRepo;
import com.example.producto.repositorio.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private RolRepo rolRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/iniciarsesion")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail()
        , loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Ha iniciado sesion", HttpStatus.OK);

    }
    @PostMapping("registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDto registroDto){
        if (usuarioRepo.existsByUsername(registroDto.getUsername())){
            return new ResponseEntity<>("Ese usuario ya existe", HttpStatus.BAD_REQUEST);
        }
        if (usuarioRepo.existsByEmail(registroDto.getEmail())){
            return new ResponseEntity<>("Ese email ya existe", HttpStatus.BAD_REQUEST);
        }
        UsuarioPubli usuarioPubli = new UsuarioPubli();
        usuarioPubli.setNombre(registroDto.getNombre());
        usuarioPubli.setUsername(registroDto.getUsername());
        usuarioPubli.setEmail(registroDto.getEmail());
        usuarioPubli.setPassword(passwordEncoder.encode(registroDto.getPassword()));

        RolPubli rolPubli = rolRepo.findByNombre("ROLE_ADMIN").get();
        usuarioPubli.setRoles(Collections.singleton(rolPubli));

        usuarioRepo.save(usuarioPubli);
        return new ResponseEntity<>("Usuario Registrado con exito", HttpStatus.OK);

    }
}
