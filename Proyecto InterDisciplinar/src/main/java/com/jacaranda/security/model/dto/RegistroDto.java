package com.jacaranda.security.model.dto;

public class RegistroDto {

	//Atributos
	private String nombre;
	private String apellido;
	private String dni;
	private String username;
	private String password;
	
	
	public RegistroDto(String nombre, String apellido, String dni, String username, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.username = username;
		this.password = password;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
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
