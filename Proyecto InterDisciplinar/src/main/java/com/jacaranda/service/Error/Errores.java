package com.jacaranda.service.Error;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Errores implements Serializable{
		
	PETICION_INCORRECTA("Se ha realizado una petición incorrecta. ERROR 1"),
	EXISTE_NICK_EMAIL("Ya existe el nickname o el email. ERROR 2"),
	USUARIO_PASS_INCORRECTA("Usuario o contraseña incorrecta. ERROR 3"),
	ERROR_EN_EL_PEDIDO("Ha ocurrido algún error con los datos del pedido"),
	ESTADO_DEL_PEDIDO("El pedido no puede modificarse ahora debido a su estado"),
	VALORACION_DEL_PEDIDO("El pedido no puede valorarse porque aún no ha terminado"),
	YA_VALORADO("Este pedido ya ha sido valorado"),
	INDETERMINADO("Error indeterminado. ERROR 999999");
	
	
	private final String mensaje;

	private Errores(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
	
	
	
}
