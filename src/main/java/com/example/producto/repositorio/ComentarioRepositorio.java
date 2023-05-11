package com.example.producto.repositorio;

import com.example.producto.entidad.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepositorio extends JpaRepository<Comentario, Long> {

    public List<Comentario> findByPublicacionId(long publicaionId);

}
