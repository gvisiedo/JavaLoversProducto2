package com.example.producto.servicio;

import com.example.producto.dto.PubliPedidoDto;
import com.example.producto.entidad.PubliPedido;
import com.example.producto.excepciones.ResourceNotFoundException;
import com.example.producto.repositorio.PubliPedidoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PubliPedidoServicioImpl implements PubliPedidoServicio{

    @Autowired
    private PubliPedidoRepo publiPedidoRepo;
    @Override
    public PubliPedidoDto crearPubliPedido(PubliPedidoDto publiPedidoDto) {
       PubliPedido publiPedido = mapearEntidad(publiPedidoDto);
       PubliPedido nuevoPubliPedido = publiPedidoRepo.save(publiPedido);
       PubliPedidoDto publiPedidoRespuesta = mapearDTO(nuevoPubliPedido);
       return publiPedidoRespuesta;

    }
    @Override
    public List<PubliPedidoDto> obtenerTodosLosPubliPedidos(int numeroDePagina,int medidaPagina){
        Pageable pageable = PageRequest.of(numeroDePagina, medidaPagina);
        Page<PubliPedido> publiPedidos = publiPedidoRepo.findAll(pageable);
        List<PubliPedido> listaDePubliPedidos = publiPedidos.getContent();
       return listaDePubliPedidos.stream().map(publiPedido -> mapearDTO(publiPedido)).collect(Collectors.toList());
    }

    @Override
    public PubliPedidoDto obtenerPubliPedidoPorId(long id) {
        PubliPedido publiPedido = publiPedidoRepo.
                findById(id).orElseThrow(()-> new ResourceNotFoundException("","PubliPedido","id",id));
        return mapearDTO(publiPedido);
    }

    @Override
    public PubliPedidoDto actualizarPubliPedido(PubliPedidoDto publiPedidoDto, long id) {
        PubliPedido publiPedido = publiPedidoRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("","PubliPedido","id", id));
        publiPedido.setTitulo(publiPedidoDto.getTitulo());
        publiPedido.setDescripcion(publiPedidoDto.getDescripcion());
        publiPedido.setContenido(publiPedidoDto.getContenido());
        PubliPedido publiPedidoActualizado = publiPedidoRepo.save(publiPedido);

        return mapearDTO(publiPedidoActualizado);
    }

    @Override
    public void eliminarPubliPedido(long id) {
        PubliPedido publiPedido = publiPedidoRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("","PubliPedido","id", id));
        publiPedidoRepo.delete(publiPedido);
    }

    //convierte entidad a DTO
    private PubliPedidoDto mapearDTO(PubliPedido publiPedido){
        PubliPedidoDto publiPedidoDto = new PubliPedidoDto();
        publiPedidoDto.setId(publiPedido.getId());
        publiPedidoDto.setTitulo(publiPedido.getTitulo());
        publiPedidoDto.setDescripcion(publiPedido.getDescripcion());
        publiPedidoDto.setContenido(publiPedido.getContenido());
        return publiPedidoDto;
    }
    //convierte de DTo a entidad
    private PubliPedido mapearEntidad(PubliPedidoDto publiPedidoDto){
        PubliPedido publiPedido = new PubliPedido();

        publiPedido.setTitulo(publiPedidoDto.getTitulo());
        publiPedido.setDescripcion(publiPedidoDto.getDescripcion());
        publiPedido.setContenido(publiPedidoDto.getContenido());
        return publiPedido;
    }
}
