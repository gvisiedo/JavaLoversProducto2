package com.example.producto.controller;

import com.example.producto.dto.ComentarioDto;
import com.example.producto.entidad.Comentario;
import com.example.producto.servicio.ComentarioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ComentarioController {
    @Autowired
    private ComentarioServicio comentarioServicio;

    @GetMapping("publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDto> listarComentariosPorPublicacionId(@PathVariable(value = "publicacionId")Long publicacionId){
        return comentarioServicio.obtenerComentariosPorPublicacionId(publicacionId);
    }
    @GetMapping("/publicaciones/{publicacionId}/comentarios/{id}")
    public ResponseEntity<ComentarioDto> obtenerComentarioPorId(@PathVariable(value = "publicacionId")Long publicacionId,@PathVariable(value = "id")long comentarioId){
      ComentarioDto comentarioDto = comentarioServicio.obtenerComentarioPorId(publicacionId,comentarioId);
      return new ResponseEntity<ComentarioDto>(comentarioDto, HttpStatus.OK);
    }
    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDto> guardarComentario(@PathVariable(value = "publicacionId")long publicacionId,@Valid @RequestBody ComentarioDto comentarioDto){
        return new ResponseEntity<>(comentarioServicio.crearComentario(publicacionId, comentarioDto), HttpStatus.CREATED);

    }
    @PutMapping("/publicaciones/{publicacionId}/comentarios/{id}")
    public ResponseEntity<ComentarioDto> actualizarComentario(@PathVariable(value = "publicacionId")Long publicacionId,@PathVariable(value = "id")long comentarioId,@Valid @RequestBody ComentarioDto comentarioDto) {
        ComentarioDto comentarioActualizado = comentarioServicio.actualizarComentario(publicacionId, comentarioId, comentarioDto);
        return new ResponseEntity<>(comentarioActualizado, HttpStatus.OK);

    }
    @DeleteMapping("/publicaciones/{publicacionId}/comentarios/{id}")
    public ResponseEntity<String> eliminarComentario(@PathVariable(value = "publicacionId")Long publicacionId,@PathVariable(value = "id")long comentarioId){
        comentarioServicio.eliminarComentario(publicacionId,comentarioId);
        return new ResponseEntity<>("Comentario eliminado",HttpStatus.OK);
    }
    }
