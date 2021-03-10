package com.jacaranda.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jacaranda.security.model.User;



@Entity
public class Customer implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	@Column(nullable = false)
	private String name;
	private String surname;
	private String dni;
		
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pedido> pedidos;
	
	
	@OneToOne
	@JoinColumn(name = "idUser", foreignKey = @ForeignKey(name = "FK_CUSTOMER_USER"))
	private User user;
	
	public Customer() {
		super();
		pedidos = new ArrayList<Pedido>();
	}

	public Customer(String name, String surname, String dni) {
		super();
		this.name = name;
		this.surname = surname;
		this.dni = dni;
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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", surname=" + surname + ", dni=" + dni
				+ ", pedidos=" + pedidos + ", user=" + user + "]";
	}

	

	
}
