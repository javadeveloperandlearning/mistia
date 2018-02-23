package pe.com.cablered.mistia.statistics;

import java.util.Comparator;

public class Frecuencia {
	
	
	private Integer numero;
	private Limite limite;
	private Integer cantidad;
	
	
	public Frecuencia() {
	
	}
	
	public Frecuencia(Integer numero) {
		this.numero = numero;
	}
	
	
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	
	
	public Limite getLimite() {
		return limite;
	}
	public void setLimite(Limite limite) {
		this.limite = limite;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "Frecuencia [numero=" + numero + ", limite=" + limite + ", cantidad=" + cantidad + "]";
	}
	
}


