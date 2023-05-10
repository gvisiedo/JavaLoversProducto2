package com.example.producto.repositorio;

import com.example.producto.entidad.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {
}
