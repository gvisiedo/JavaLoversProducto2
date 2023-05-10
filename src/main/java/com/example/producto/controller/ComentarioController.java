package com.example.producto.controller;

import com.example.producto.dto.ComentarioDto;
import com.example.producto.entidad.Comentario;
import com.example.producto.servicio.ComentarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class ComentarioController {
    @Autowired
    private ComentarioServicio comentarioServicio;

    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDto> guardarComentario(@PathVariable(value = "publicacionId")long publicacionId, @RequestBody ComentarioDto comentarioDto){
        return new ResponseEntity<>(comentarioServicio.crearComentario(publicacionId, comentarioDto), HttpStatus.CREATED);

    }


}
