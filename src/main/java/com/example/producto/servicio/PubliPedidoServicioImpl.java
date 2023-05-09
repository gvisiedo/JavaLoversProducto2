package com.example.producto.servicio;

import com.example.producto.dto.PubliPedidoDto;
import com.example.producto.entidad.PubliPedido;
import com.example.producto.repositorio.PubliPedidoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PubliPedidoServicioImpl implements PubliPedidoServicio{

    @Autowired
    private PubliPedidoRepo publiPedidoRepo;
    @Override
    public PubliPedidoDto crearPubliPedido(PubliPedidoDto publiPedidoDto) {
        //Convertimos DTO a entidad
        PubliPedido publiPedido= new PubliPedido();
        publiPedido.setTitulo(publiPedidoDto.getTitulo());
        publiPedido.setDescripcion(publiPedidoDto.getDescripcion());
        publiPedido.setContenido(publiPedidoDto.getContenido());

        PubliPedido nuevoPubliPedido = publiPedidoRepo.save(publiPedido);

        //Convertimos de entidad a DTO

        PubliPedidoDto publiPedidoRespuesta = new PubliPedidoDto();
        publiPedidoRespuesta.setId(nuevoPubliPedido.getId());
        publiPedidoRespuesta.setTitulo(nuevoPubliPedido.getTitulo());
        publiPedidoRespuesta.setDescripcion(nuevoPubliPedido.getDescripcion());
        publiPedidoRespuesta.setContenido(nuevoPubliPedido.getContenido());
        return publiPedidoRespuesta;
    }
}
