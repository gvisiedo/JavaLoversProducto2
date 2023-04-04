package com.example.producto.servicio;

import com.example.producto.entidad.Pedido;
import com.example.producto.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoServicioImpl implements PedidoServicio{
    @Autowired
    private PedidoRepositorio repositorio;
    @Override
    public List<Pedido> listarTodosLosPedidos(){
        return repositorio.findAll();
    }
    @Override
    public Pedido guardarPedido(Pedido pedido){
        return repositorio.save(pedido);
    }

    @Override
    public Pedido obtenerPedidoPorId(Long id) {
        return repositorio.findById(id).get();
    }

    @Override
    public Pedido actualizarPedido(Pedido pedido) {
        return repositorio.save(pedido);
    }

    @Override
    public void eliminarPedido(Long id) {
        repositorio.deleteById(id);
    }
}
