package com.jacaranda.service.Error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Errores implements Serializable{
		
	PETICION_INCORRECTA("Se ha realizado una petición incorrecta. ERROR 1"),
	EXISTE_NICK_EMAIL("Ya existe el nickname o el email. ERROR 2"),
	USUARIO_PASS_INCORRECTA("Usuario o contraseña incorrecta. ERROR 3"),
	INDETERMINADO("Error indeterminado. ERROR 999999");
	
	
	private final String mensaje;

	private Errores(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
	
	
	
}
