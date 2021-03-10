package com.jacaranda.entity.dto;

public class CustomerDTO {

	//Atributos 
	private String name;
	private String surname;
	private String dni;
	
	
	public CustomerDTO(String name, String surname, String dni) {
		super();
		this.name = name;
		this.surname = surname;
		this.dni = dni;

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
	
	
	
	
}
