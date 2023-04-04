package com.example.producto;

import com.example.producto.entidad.Pedido;
import com.example.producto.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProductoApplication.class, args);
	}

	@Autowired
	private PedidoRepositorio repositorio;

	@Override
	public void run(String... args) throws Exception{
		Pedido pedido1 = new Pedido("Pepe","Ramirez","mail@mail.com");
		repositorio.save(pedido1);

		Pedido pedido2 = new Pedido("Lola","Perez","mail1@mail.com");
		repositorio.save(pedido2);
}
}
