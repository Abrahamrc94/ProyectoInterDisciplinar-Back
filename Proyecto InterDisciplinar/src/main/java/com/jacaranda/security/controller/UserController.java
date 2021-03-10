package com.jacaranda.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jacaranda.security.model.dto.RegistroDto;
import com.jacaranda.security.model.dto.UserDTO;
import com.jacaranda.security.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/sign-up")
	public ResponseEntity<UserDTO> signUp(@RequestBody RegistroDto dto) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(dto));
		}catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO dto){
		// Created only to retrieve the Bearer token once authenticated
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
}
