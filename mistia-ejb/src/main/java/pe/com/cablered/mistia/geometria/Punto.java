package pe.com.cablered.mistia.geometria;

import java.util.Map;

public class Punto {
	
	
	
	private Integer numero;
	private double latitud ;
	private double longitud;
	private double  peso;
	private Integer  orden;
	private Map datos;
	
	
	
	public Punto() {
	
	}

	
	public Punto(double latitud, double longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public Punto(double latitud, double longitud, double peso) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
		this.peso= peso;
	}
	
	public Punto(Integer numero, double latitud, double longitud, double peso) {
		super();
		this.numero=numero;
		this.latitud = latitud;
		this.longitud = longitud;
		this.peso= peso;
	}
	
	


	public Integer getNumero() {
		return numero;
	}


	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	
	
	
	
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}



	@Override
	public String toString() {
		return "Punto [numero=" + numero + ", latitud=" + latitud + ", longitud=" + longitud + ", peso=" + peso
				+ ", orden=" + orden + "]";
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	public Integer getOrden() {
		return orden;
	}


	public void setOrden(Integer orden) {
		this.orden = orden;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punto other = (Punto) obj;
		if (Double.doubleToLongBits(latitud) != Double.doubleToLongBits(other.latitud))
			return false;
		if (Double.doubleToLongBits(longitud) != Double.doubleToLongBits(other.longitud))
			return false;
		return true;
	}


	public Map getDatos() {
		return datos;
	}


	public void setDatos(Map datos) {
		this.datos = datos;
	}



	
	
	
	
}
