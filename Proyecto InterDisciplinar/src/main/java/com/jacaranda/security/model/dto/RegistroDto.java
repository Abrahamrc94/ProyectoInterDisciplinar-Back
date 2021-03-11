package com.jacaranda.security.model.dto;

public class RegistroDto {

	//Atributos
	private String name;
	private String surname;
	private String dni;
	private String username;
	private String password;
	
	
	public RegistroDto(String name, String surname, String dni, String username, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.username = username;
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
