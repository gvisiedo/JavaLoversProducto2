package com.example.producto.servicio;

import com.example.producto.entidad.Pedido;

import java.util.List;

public interface PedidoServicio {
    public List<Pedido> listarTodosLosPedidos();

    public Pedido guardarPedido(Pedido pedido);

    public Pedido obtenerPedidoPorId(Long id);

    public Pedido actualizarPedido(Pedido pedido);

    public void eliminarPedido(Long id);
}
