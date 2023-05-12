package com.example.producto.servicio;

import com.example.producto.dto.ComentarioDto;
import com.example.producto.entidad.Comentario;
import com.example.producto.entidad.PubliPedido;
import com.example.producto.excepciones.PubliPedidoAppException;
import com.example.producto.excepciones.ResourceNotFoundException;
import com.example.producto.repositorio.ComentarioRepositorio;
import com.example.producto.repositorio.PubliPedidoRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ComentarioServicioImpl implements ComentarioServicio{
    @Autowired
    private ModelMapper modelMapper;

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

    @Override
    public List<ComentarioDto> obtenerComentariosPorPublicacionId(long publicacionId) {
        List<Comentario> comentarios = comentarioRepositorio.findByPublicacionId(publicacionId);
        return comentarios.stream().map(comentario -> mapearDTO(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDto obtenerComentarioPorId(Long publicacionId, Long comentarioId) {
        PubliPedido publiPedido = publiPedidoRepo.findById(publicacionId).orElseThrow(()-> new ResourceNotFoundException("","PubliPedido","id",publicacionId));
        Comentario comentario = comentarioRepositorio.findById(comentarioId).orElseThrow(()-> new ResourceNotFoundException("","Comentario","id",comentarioId));

        if(!comentario.getPubliPedido().getId().equals(publiPedido.getId())){
            throw new PubliPedidoAppException(HttpStatus.BAD_REQUEST,"El comentario no pertenece a la publicación");
        }
        return mapearDTO(comentario);
    }

    @Override
    public ComentarioDto actualizarComentario(Long publicacionId, Long comentarioId,ComentarioDto solicitudDeComentario) {
        PubliPedido publiPedido = publiPedidoRepo.findById(publicacionId).orElseThrow(()-> new ResourceNotFoundException("","PubliPedido","id",publicacionId));
        Comentario comentario = comentarioRepositorio.findById(comentarioId).orElseThrow(()-> new ResourceNotFoundException("","Comentario","id",comentarioId));

        if(!comentario.getPubliPedido().getId().equals(publiPedido.getId())){
            throw new PubliPedidoAppException(HttpStatus.BAD_REQUEST,"El comentario no pertenece a la publicación");
        }
        comentario.setNombre(solicitudDeComentario.getNombre());
        comentario.setEmail(solicitudDeComentario.getEmail());
        comentario.setCuerpo(solicitudDeComentario.getCuerpo());

        Comentario comentarioActualizado = comentarioRepositorio.save(comentario);
        return mapearDTO(comentarioActualizado);
    }

    @Override
    public void eliminarComentario(Long publicacionId, Long comentarioId) {
        PubliPedido publiPedido = publiPedidoRepo.findById(publicacionId).orElseThrow(()-> new ResourceNotFoundException("","PubliPedido","id",publicacionId));
        Comentario comentario = comentarioRepositorio.findById(comentarioId).orElseThrow(()-> new ResourceNotFoundException("","Comentario","id",comentarioId));

        if(!comentario.getPubliPedido().getId().equals(publiPedido.getId())){
            throw new PubliPedidoAppException(HttpStatus.BAD_REQUEST,"El comentario no pertenece a la publicación");
        }
        comentarioRepositorio.delete(comentario);
    }


    private ComentarioDto mapearDTO(Comentario comentario){
        ComentarioDto comentarioDto = modelMapper.map(comentario, ComentarioDto.class);

        return comentarioDto;
    }
    private Comentario mapearEntidad(ComentarioDto comentarioDto){
        Comentario comentario = modelMapper.map(comentarioDto, Comentario.class);
        return comentario;
    }
}
