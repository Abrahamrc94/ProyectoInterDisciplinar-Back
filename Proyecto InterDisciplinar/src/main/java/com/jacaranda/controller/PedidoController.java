package com.jacaranda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Pedido;
import com.jacaranda.entity.Producto;
import com.jacaranda.service.PedidoService;
import com.jacaranda.service.Error.Errores;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping
public class PedidoController {

	
	//Creamos los servicios
	@Autowired
	private PedidoService pedidoService;
	
	//Devuelve un pedido segun el id
	@GetMapping("/pedido/{id}")
	public ResponseEntity<?> getPedidoId(@PathVariable Long id){
		return ResponseEntity.ok(pedidoService.getPedidoById(id));
	}
		
	
	//Crea un pedido
	@PostMapping("/pedido")
	public Pedido createPedido(@RequestBody Pedido sent){
		return pedidoService.savePedido(sent);
	}
	
	
	//Asigna un producto al pedido
	@PostMapping("/pedido/{id}")
	public ResponseEntity<?> addProducto(@RequestBody Producto newProducto, @PathVariable Long id) {
				
		ResponseEntity<?> response = null;
		Pedido resultado = pedidoService.addProducto(newProducto, id);
				
		if (resultado == null) {
			response = ResponseEntity.status(HttpStatus.CONFLICT).body(Errores.INDETERMINADO);
		} else {
			response = ResponseEntity.status(HttpStatus.OK).body(resultado);
		}
				
		return response;
	}
	
	
	//Modifica un pedido PUT
	@PutMapping(path = "/pedido/{id}")
	public ResponseEntity<?> updatePedido(@PathVariable Long id, @RequestBody Pedido sent) {
		return pedidoService.updatePedido(id, sent);
	}
	
}
