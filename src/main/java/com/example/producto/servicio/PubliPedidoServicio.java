package com.example.producto.servicio;

import com.example.producto.dto.PubliPedidoDto;

import java.util.List;

public interface PubliPedidoServicio {

    public PubliPedidoDto crearPubliPedido(PubliPedidoDto publiPedidoDto);

    public List<PubliPedidoDto> obtenerTodosLosPubliPedidos(int numeroDePagina, int medidaPagina);

    public PubliPedidoDto obtenerPubliPedidoPorId(long id);

    public PubliPedidoDto actualizarPubliPedido(PubliPedidoDto publiPedidoDto,long id);

    public void eliminarPubliPedido(long id);
}
