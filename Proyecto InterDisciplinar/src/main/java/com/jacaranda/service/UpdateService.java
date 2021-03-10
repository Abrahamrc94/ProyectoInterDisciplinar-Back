package com.jacaranda.service;

import org.springframework.stereotype.Service;

import com.jacaranda.entity.Customer;
import com.jacaranda.entity.Pedido;
import com.jacaranda.entity.Producto;

@Service
public class UpdateService {

	public void updateCustomer (Customer original, Customer sent) {
		original.setName((sent.getName() == null) ? original.getName() : sent.getName());
		original.setSurname((sent.getSurname() == null) ? original.getSurname() : sent.getSurname());
		original.setDni((sent.getDni() == null) ? original.getDni() : sent.getDni());
		original.setPedidos((sent.getPedidos() == null) ? original.getPedidos() : sent.getPedidos());

	}
	
	
	public void updatePedido (Pedido original, Pedido sent) {
		original.setEstado((sent.getEstado() == null) ? original.getEstado() : sent.getEstado());
		original.setCustomer((sent.getCustomer() == null) ? original.getCustomer() : sent.getCustomer());
		original.setTotal((sent.getTotal() == 0) ? original.getTotal() : sent.getTotal());
		original.setProductos((sent.getProductos() == null) ? original.getProductos() : sent.getProductos());
		original.setDireccion((sent.getDireccion() == null) ? original.getDireccion() : sent.getDireccion());
		original.setValoracion((sent.getValoracion() == null) ? original.getValoracion() : sent.getValoracion());
	}
	
	public void updateProducto (Producto original, Producto sent) {
		original.setNombre((sent.getNombre() == null) ? original.getNombre() : sent.getNombre());
		original.setPrecio((sent.getPrecio() == 0) ? original.getPrecio() : sent.getPrecio());
		original.setDescripcion((sent.getDescripcion() == null) ? original.getDescripcion() : sent.getDescripcion());		
	}
}
