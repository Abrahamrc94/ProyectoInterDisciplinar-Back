package com.jacaranda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jacaranda.entity.Pedido;
import com.jacaranda.entity.Producto;
import com.jacaranda.pedido.enumerate.Estado;
import com.jacaranda.repo.PedidoRepository;
import com.jacaranda.repo.ProductoRepository;
import com.jacaranda.service.Error.Errores;

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
	public ResponseEntity<?> updatePedido(Long id, Pedido sent) {
		Pedido p = pedidoRepository.findPedidoById(id);

		ResponseEntity<?> response = null;
		
		if(sent == null) {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(Errores.ERROR_EN_EL_PEDIDO);
		}else if(sent.getEstado().equals(Estado.ENTREGADO)) {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(Errores.ESTADO_DEL_PEDIDO);
		}else {
			updateService.updatePedido(p, sent);
			pedidoRepository.save(p);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body("Pedido actualizado");
		}
			
			return response;
	}

	//Añade una valoración a un pedido
	public ResponseEntity<?> valoraPedido(Pedido p, String valoracion) {
		
		ResponseEntity<?> response = null;
		if(p == null) {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(Errores.ERROR_EN_EL_PEDIDO);
		}else if(p.getEstado()!=(Estado.ENTREGADO)) {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(Errores.VALORACION_DEL_PEDIDO);
		}else if(p.getValoracion() != null){
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(Errores.YA_VALORADO);
		}else{
			p.setValoracion(valoracion);
			pedidoRepository.save(p);
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body("Valoracion realizada con exito");
		}
		
		return response;
		
	}

	
	//Actualiza el estado
	public Pedido avanzaEstado(Long id) {
		
		Pedido p = pedidoRepository.findPedidoById(id);
		int pos = p.getEstado().ordinal();
		p.setEstado(Estado.values()[pos + 1]);
		pedidoRepository.save(p);
		
		return p;
	}
	
}
