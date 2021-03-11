package com.jacaranda.security.model.dto;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jacaranda.entity.Customer;
import com.jacaranda.security.model.User;
import com.jacaranda.security.model.UserRole;
import com.jacaranda.security.repo.UserRepository;

@Component
public class RegistroDTOConverter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepo;
	
	public User fromRegistroDTOToUser(RegistroDto dto) {
		User user  = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setRoles(Set.of(UserRole.USER));
		user.setCreateTime(LocalDateTime.now());
		user.setUpdateTime(LocalDateTime.now());
		user.setLastPasswordChange(LocalDateTime.now());
		user.setLocked(false);
		user.setEnabled(true);
		user.setAuthenticationAttempts(0);
		user.setPasswordPolicyExpDate(LocalDateTime.now().plusDays(180));
		return user;
		
	}
	
	
	public Customer fromRegistroDtoToCustomer(RegistroDto dto) {
		
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setSurname(dto.getSurname());
		customer.setDni(dto.getDni());
		User newUser = userRepo.findUserByUsername(dto.getUsername());
		customer.setUser(newUser);
		
		return customer;
		
	}
	
	
	
	
	public UserDTO fromUserToUserDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setUsername(user.getUsername());
		dto.setRoles(user.getRoles());
		return dto;
	}
	
}
