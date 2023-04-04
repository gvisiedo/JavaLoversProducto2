package com.example.producto.controller;

import com.example.producto.entidad.Pedido;
import com.example.producto.repositorio.PedidoRepositorio;
import com.example.producto.servicio.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PedidoController {
    @Autowired
    private PedidoServicio servicio;

    @GetMapping({"/pedidos","/"})
    public String listarPedidos(Model modelo){
        modelo.addAttribute("pedidos", servicio.listarTodosLosPedidos());
        return "pedidos";
    }
    @GetMapping("pedidos/nuevo")
    public String mostrarFormularioRegistrarPedido(Model modelo){
        Pedido pedido = new Pedido();
        modelo.addAttribute("pedido", pedido);
        return "crear_pedido";
    }
    @PostMapping("/pedidos")
    public String guardarPedido(@ModelAttribute("pedido")Pedido pedido){
        servicio.guardarPedido(pedido);
        return "redirect:/pedidos";

    }
    @GetMapping("/pedidos/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model modelo){
        modelo.addAttribute("pedido", servicio.obtenerPedidoPorId(id));
        return "editar_pedido";
    }
    @PostMapping("/pedidos/{id}")
    public String actualizarPedido(@PathVariable Long id,@ModelAttribute("pedido")Pedido pedido,Model modelo){
        Pedido pedidoExistente = servicio.obtenerPedidoPorId(id);
        pedidoExistente.setId(id);
        pedidoExistente.setNombre(pedido.getNombre());
        pedidoExistente.setApellido(pedido.getApellido());
        pedidoExistente.setEmail(pedido.getEmail());
        servicio.actualizarPedido(pedidoExistente);
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/{id}")
    public String eliminarPedido(@PathVariable Long id){
        servicio.eliminarPedido(id);
        return "redirect:/pedidos";
    }

}
