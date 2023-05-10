package com.example.producto.servicio;

import com.example.producto.dto.PubliPedidoDto;
import com.example.producto.dto.PubliPedidoRespuesta;

import java.util.List;

public interface PubliPedidoServicio {

    public PubliPedidoDto crearPubliPedido(PubliPedidoDto publiPedidoDto);

    public PubliPedidoRespuesta obtenerTodosLosPubliPedidos(int numeroDePagina, int medidaPagina, String ordenarPor,String sortDir);

    public PubliPedidoDto obtenerPubliPedidoPorId(long id);

    public PubliPedidoDto actualizarPubliPedido(PubliPedidoDto publiPedidoDto,long id);

    public void eliminarPubliPedido(long id);
}
