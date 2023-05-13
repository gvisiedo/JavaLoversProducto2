package com.example.producto.repositorio;

import com.example.producto.entidad.RolPubli;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepo extends JpaRepository<RolPubli, Long> {

    public Optional<RolPubli> findByNombre(String nombre);

}
