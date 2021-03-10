package com.jacaranda.security.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jacaranda.entity.Customer;
import com.jacaranda.entity.dto.DtoConverter;
import com.jacaranda.repo.CustomerRepository;
import com.jacaranda.security.model.User;
import com.jacaranda.security.model.dto.RegistroDTOConverter;
import com.jacaranda.security.model.dto.RegistroDto;
import com.jacaranda.security.model.dto.UserDTO;
import com.jacaranda.security.repo.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private CustomerRepository custoRepo;
	
	@Autowired
	private RegistroDTOConverter converter;
	
	@Autowired
	private DtoConverter customerConverter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
	}
	
	
	
	public UserDetails loadUserById(Long idUser) throws AuthenticationException {
		return repository.findById(idUser)
				.orElseThrow(()-> new AuthenticationException("Id/username not found"));
	}	
	
	
	public UserDTO createNewUser(RegistroDto dto) {
		
		//Obtenemos el User y lo guardamos
		User nuevoUser = converter.fromRegistroDTOToUser(dto);
		repository.save(nuevoUser);
		
		
		//Obtenemos el Customer y lo guardamos
		Customer nuevoCustomer = converter.fromRegistroDtoToCustomer(dto);
		custoRepo.save(nuevoCustomer);
		return converter.fromUserToUserDTO(nuevoUser);
	}
	

}
