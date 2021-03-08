package com.jacaranda.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Customer implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	@Column(nullable = false)
	private String name;
	private String surname;
	private String dni;
	@Column(nullable = false)
	private String username;// Un nick para el usuario
	@Column(nullable = false)
	private String password;
		
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pedido> pedidos;
	
	
	
	
	public Customer() {
		super();
	}

	public Customer(String name, String surname, String dni, Long id, String username, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.customerId = id;
		this.username = username;
		this.password = password;
		pedidos = new ArrayList<Pedido>();
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
	
	public Long getId() {
		return customerId;
	}

	public void setId(Long id) {
		this.customerId = id;
	}
	

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
