package com.example.producto.servicio;

import com.example.producto.dto.ComentarioDto;

public interface ComentarioServicio {

    public ComentarioDto crearComentario(long publiPedidoId,ComentarioDto comentarioDto);
}
