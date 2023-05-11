package com.example.producto.servicio;

import com.example.producto.dto.ComentarioDto;

import java.util.List;

public interface ComentarioServicio {

    public ComentarioDto crearComentario(long publicacionId, ComentarioDto comentarioDto);
    public List<ComentarioDto> obtenerComentariosPorPublicacionId(long publicacionId);
    public ComentarioDto obtenerComentarioPorId(Long publicacionId, Long comentarioId);
    public ComentarioDto actualizarComentario(Long publicacionId, Long comentarioId,ComentarioDto solicitudDeComentario);

    public void eliminarComentario(Long publicacionId, Long comentarioId);

}
