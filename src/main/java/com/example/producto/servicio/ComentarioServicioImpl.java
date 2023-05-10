package com.example.producto.servicio;

import com.example.producto.dto.ComentarioDto;
import com.example.producto.entidad.Comentario;
import com.example.producto.entidad.PubliPedido;
import com.example.producto.excepciones.ResourceNotFoundException;
import com.example.producto.repositorio.ComentarioRepositorio;
import com.example.producto.repositorio.PubliPedidoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{

    @Autowired
    private ComentarioRepositorio comentarioRepositorio;
    @Autowired
    private PubliPedidoRepo publiPedidoRepo;
    @Override
    public ComentarioDto crearComentario(long publiPedidoId, ComentarioDto comentarioDto) {
        Comentario comentario = mapearEntidad(comentarioDto);
        PubliPedido publiPedido = publiPedidoRepo.findById(publiPedidoId).orElseThrow(()-> new ResourceNotFoundException("","PubliPedido","id",publiPedidoId));
        comentario.setPubliPedido(publiPedido);
        Comentario nuevoComentario = comentarioRepositorio.save(comentario);
        return mapearDTO(nuevoComentario);
    }
    private ComentarioDto mapearDTO(Comentario comentario){
        ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setId(comentario.getId());
        comentarioDto.setNombre(comentario.getNombre());
        comentarioDto.setEmail(comentario.getEmail());
        comentarioDto.setCuerpo(comentario.getCuerpo());

        return comentarioDto;
    }
    private Comentario mapearEntidad(ComentarioDto comentarioDto){
        Comentario comentario = new Comentario();
        comentario.setId(comentarioDto.getId());
        comentario.setNombre(comentarioDto.getNombre());
        comentario.setEmail(comentarioDto.getEmail());
        comentario.setCuerpo(comentarioDto.getCuerpo());
        return comentario;
    }
}
