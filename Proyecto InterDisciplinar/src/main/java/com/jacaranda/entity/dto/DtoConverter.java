package com.jacaranda.entity.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jacaranda.entity.Customer;
import com.jacaranda.repo.CustomerRepository;

@Component
public class DtoConverter {
	
	
	//Repository
	@Autowired
	private CustomerRepository customerRepo;
	
	//Obtener un Customer del DTO
	public Customer fromCustomerDtoToCustomer(CustomerDTO cusDto) {
		
		return customerRepo.findCustomerByName(cusDto.getName());	
	}
	
	//Obtener un DTO del Customer
	public CustomerDTO fromCustomerToCustomerDTO(Customer customer) {
		
		CustomerDTO custoDto = new CustomerDTO(customer.getName(), customer.getSurname(), customer.getDni());
		
		return custoDto;
	}
	
}
