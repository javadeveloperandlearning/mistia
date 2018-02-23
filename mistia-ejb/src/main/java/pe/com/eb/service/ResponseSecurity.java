package pe.com.eb.service;

import java.io.Serializable;
import java.util.Map;

public class ResponseSecurity  implements Serializable{
	

	/**
	 * Clase que contiene las respuestas de seguridad
	 */
	private static final long serialVersionUID = 2681891009847497309L;
	
	private int codigo;
	private String message;
	private Map<String, Object> data;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
}
