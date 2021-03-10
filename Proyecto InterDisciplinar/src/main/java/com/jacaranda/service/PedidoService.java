package com.jacaranda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jacaranda.entity.Pedido;
import com.jacaranda.entity.Producto;
import com.jacaranda.repo.PedidoRepository;
import com.jacaranda.repo.ProductoRepository;

@Service
public class PedidoService {

	//Repositorios
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProductoRepository productoRepository;
	
	// Servicios
	@Autowired
	private UpdateService updateService;
	
	//Get para un pedido por id
	public Pedido getPedidoById(Long id) {
		return pedidoRepository.findPedidoById(id);
	
	}

	//Crear un nuevo pedido
	public Pedido savePedido(Pedido sent) {
		return pedidoRepository.save(sent);
	}
	
	//Añadir un prodcuto a pedido
	public Pedido addProducto(Producto newProducto, Long idPedido) {
		// se guarda el pedido en una variable auxiliar
		Pedido auxPedido = pedidoRepository.findPedidoById(idPedido);
			
		// se guarda el producto en la BBDD
		productoRepository.save(newProducto);
				
		// se inserta el producto al pedido
		auxPedido.getProductos().add(newProducto);
				
		// se guarda el pedido en la BBDD
		pedidoRepository.save(auxPedido);
			
		return auxPedido;
		}
	
	
	
	//Actualizar un pedido
	public Pedido updatePedido(Long id, Pedido sent) {
		Pedido p = pedidoRepository.findPedidoById(id);

			updateService.updatePedido(p, sent);
			pedidoRepository.save(p);
			
			return p;
	}

	//Añade una valoración a un pedido
	public void valoraPedido(Pedido p, String valoracion) {
		
		p.setValoracion(valoracion);		
	}
	
}
