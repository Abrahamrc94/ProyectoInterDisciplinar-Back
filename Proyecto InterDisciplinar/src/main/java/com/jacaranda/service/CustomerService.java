package com.jacaranda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Pedido;
import com.jacaranda.repo.CustomerRepository;
import com.jacaranda.repo.PedidoRepository;

@Service
public class CustomerService {

	//Repositorios
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	
	// Servicios
	@Autowired
	private UpdateService updateService;
	

	private ObjectMapper mapper = new ObjectMapper();
	
	
	public CustomerService(CustomerRepository customerRepository, PedidoRepository pedidoReposiroty) {
		this.customerRepository=customerRepository;
		this.pedidoRepository = pedidoReposiroty;
		
	}
	
	//Get para un customer por id
	public Customer getCustomerById(Long id) {
		return customerRepository.findCustomerBycustomerId(id);
	
	}
	
	//Crear un nuevo customer
	public Customer saveCustomer(Customer sent) {
		return customerRepository.save(sent);
	}
	
	
	// Autenticacion de usuario, devolviendo jwt creado a partir de un usuario.
		public JsonNode autenticaUsuario(String username, String password) {
			JsonNode jwt = null;
			try {
				jwt = mapper.readTree(new String("{}"));
				Customer customerAutenticado = customerRepository.findByUsernameAndPassword(username, password);
				((ObjectNode) jwt).put("id", customerAutenticado.getId());

			} catch (Exception e) {
				return null;
			}
			return jwt;
		}
	
	
	
	
	
	
	//Asignar un pedido
	public Customer addPedido(Pedido newPedido, Long idCustomer) {
		// se guarda el cliente en una variable auxiliar
		Customer auxCustomer = customerRepository.findCustomerBycustomerId(idCustomer);
		
		// se inserta el id de cliente en el pedido
		newPedido.setCustomer(auxCustomer);
			
		// se guarda el pedido en la BBDD
		pedidoRepository.save(newPedido);
			
		// se inserta el pedido al cliente
		auxCustomer.getPedidos().add(newPedido);
			
		// se guarda el cliente en la BBDD
		customerRepository.save(auxCustomer);
		
		return auxCustomer;
	}
	
	
	
	//Actualziar un customer
	public ResponseEntity<?> updateCustomer(Long id, Customer sent) {
		Customer c = customerRepository.findCustomerBycustomerId(id);
		ResponseEntity<?> response;
		if (c == null) {
			response = ResponseEntity.notFound().build();
		} else {
			updateService.updateCustomer(c, sent);
			response = ResponseEntity.ok(customerRepository.save(c));
		}
		return response;
	}
	
	
	//Borrar un customer
	public void deleteCustomer(Long id) {
		customerRepository.deleteCustomerByCustomerId(id);
	}
	
}
