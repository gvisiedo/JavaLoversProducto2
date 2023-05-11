package com.example.producto.controller;

import com.example.producto.dto.PubliPedidoDto;
import com.example.producto.dto.PubliPedidoRespuesta;
import com.example.producto.servicio.PubliPedidoServicio;
import com.example.producto.utilities.AppConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PubliPedidoController {
    @Autowired
    private PubliPedidoServicio publiPedidoServicio;

    @GetMapping
    public PubliPedidoRespuesta listarPubliPedido(@RequestParam(value = "pageNo", defaultValue = AppConstantes.NUMERO_DE_PAGINA_POR_DEFECTO, required = false)int numeroDePagina, @RequestParam(value = "pageSize",defaultValue = AppConstantes.MEDIDA_DE_PAGINA_POR_DEFECTO,required = false)int medidaPagina, @RequestParam(value = "sortBy",defaultValue = AppConstantes.ORDENAR_POR_DEFECTO,required = false)String ordenarPor, @RequestParam(value = "sortDir",defaultValue = AppConstantes.ORDENAR_DIRECCION_POR_DEFECTO,required = false)String sortDir){
        return publiPedidoServicio.obtenerTodosLosPubliPedidos(numeroDePagina,medidaPagina,ordenarPor,sortDir);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PubliPedidoDto> obtenerPubliPedidoPorId(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(publiPedidoServicio.obtenerPubliPedidoPorId(id));

    }

    @PostMapping
    public ResponseEntity<PubliPedidoDto> guardarPubliPedido(@RequestBody PubliPedidoDto publiPedidoDto){
        return new ResponseEntity<>(publiPedidoServicio.crearPubliPedido(publiPedidoDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PubliPedidoDto> actualizarPubliPedidoPorId(@RequestBody PubliPedidoDto publiPedidoDto,@PathVariable(name = "id")long id){
        PubliPedidoDto publiPedidoRespuesta = publiPedidoServicio.actualizarPubliPedido(publiPedidoDto, id);
        return new ResponseEntity<>(publiPedidoRespuesta, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPubliPedido(@PathVariable(name = "id")long id){
        publiPedidoServicio.eliminarPubliPedido(id);
        return new ResponseEntity<>("PubliPedido eliminado", HttpStatus.OK);
    }


}
