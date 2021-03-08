package com.jacaranda.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jacaranda.entity.Customer;


@Repository(value="customerRepository")
public interface CustomerRepository extends CrudRepository<Customer, Integer>{

	
		// Get de un customer por nombre
		public Customer findCustomerByName(String name);

		//Busca un customer por id
		public Customer findCustomerBycustomerId(Long id);
			
			
		// Get de todos los customer ordenado por nombre
		@Query(value = "select * from customer order by name", nativeQuery = true)
		public List<Customer> findAllOrderedByName();
			
		//Borra un customer
		public void deleteCustomerByCustomerId(Long id);
			
		// Esta query pretende obtener un usuario desde el username y la contraseña, se usa para el login.
		@Query(value = "SELECT * FROM customer c WHERE c.username LIKE %?1% AND c.password LIKE %?2%", nativeQuery=true)
		Customer findByUsernameAndPassword(String username, String password);
}
