/**
 * 
 */
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Pedido;
import com.jacaranda.pedido.enumerate.Estado;
import com.jacaranda.service.CustomerService;
import com.jacaranda.service.Error.Errores;



/**
 * @author estudiante
 *
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping
public class CustomerController {
	
		//Creamos los servicios
		@Autowired
		private CustomerService customerService;
	
		//Devuelve un customer segun el id
		@GetMapping("/customer/{id}")
		public ResponseEntity<?> getCustomerId(@PathVariable Long id){
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.getCustomerById(id));
		}
			
		
		//Crea un customer
		@PostMapping("/customer/sign-up")
		public ResponseEntity<?> createCustomer(@RequestBody Customer sent){
			return  ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.saveCustomer(sent));
		}
		
		//Logea a un customer
		/**
		 * 
		 * Este método recibe el username y el password en md5 del front 
		 * y comprueba si existen en la bbdd. Si existe devuelve una respuesta con el id del customer,
		 * si no devolver
		 * 
		 */
		@GetMapping("/customer/login")
		public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password){
			ResponseEntity<?> response;
			
			if (username.equals("") || password.equals("")) {
				response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Errores.PETICION_INCORRECTA);
			
			} else {
				JsonNode jwt = customerService.autenticaUsuario(username, password);
				if (jwt == null) {			
					response = ResponseEntity.status(HttpStatus.ACCEPTED).body(Errores.USUARIO_PASS_INCORRECTA);	
				} else {
					response = ResponseEntity.status(HttpStatus.ACCEPTED).body(jwt);	
				}
			}
			return response;
		}
		
		
		
		//Asigna un pedido al customer
		@PostMapping("/customer/{id}")
		public ResponseEntity<?> addPedido(@RequestBody Pedido newPedido, @PathVariable Long id) {
			
			ResponseEntity<?> response = null;
			newPedido.setEstado(Estado.RECIBIDO);
			Customer resultado = customerService.addPedido(newPedido, id);
			
			if (resultado == null) {
				response = ResponseEntity.status(HttpStatus.CONFLICT).body(Errores.INDETERMINADO);
			} else {
				response = ResponseEntity.status(HttpStatus.OK).body(resultado);
			}
			
			return response;
		}
		
		
		//Modifica un customer PUT
		@PutMapping("/customer/{id}")
		public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer sent) {
			return  ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.updateCustomer(id, sent));
		}
		
		
		//Borra un customer
		@DeleteMapping("/customer/{id}")
		public void deleteCustomer(@PathVariable Long id){
			customerService.deleteCustomer(id);
		}
}