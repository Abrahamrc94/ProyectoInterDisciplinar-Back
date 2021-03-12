package com.jacaranda.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jacaranda.pedido.enumerate.Estado;

@Entity
public class Pedido implements Serializable{

	@ManyToOne
	@JoinColumn(name = "customerId", foreignKey = @ForeignKey(name = "Customer_ID_FK"))
	@JsonIgnore
	private Customer customer;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;
	private int total;//El precio total del pedido
	private String direccion;
	private String valoracion;//La valoracion solo sera posible cuando el estado del pedido sea "Entregado"
	
	
	@Enumerated(EnumType.STRING)
	private Estado estado;//El estado en el que se encuentra el pedido actualmente
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Producto> productos;
	
	
	public Pedido() {
		super();
	}


	public Pedido(Customer customer, Long id, int total, String direccion, String valoracion) {
		super();
		this.customer = customer;
		this.id = id;
		this.total = total;
		this.direccion = direccion;
		this.valoracion = valoracion;
		productos = new ArrayList<Producto>();
	}


	public Long getId() {
		return id;
	}


	public void setId_(Long id) {
		this.id = id;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}

	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String dirección) {
		this.direccion = dirección;
	}


	public String getValoracion() {
		return valoracion;
	}


	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}
	
	
	
}
