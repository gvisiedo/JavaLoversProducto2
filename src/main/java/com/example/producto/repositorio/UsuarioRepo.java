package com.example.producto.repositorio;

import com.example.producto.entidad.UsuarioPubli;
import com.example.producto.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepo extends JpaRepository<UsuarioPubli, Long> {
    public Optional<UsuarioPubli> findByEmail(String email);

    public Optional<UsuarioPubli> findByUsernameOrEmail(String username, String email);
    public Optional<UsuarioPubli> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);


}
