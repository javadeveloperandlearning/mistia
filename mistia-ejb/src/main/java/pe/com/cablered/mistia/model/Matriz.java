package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the matriz database table.
 * 
 */
@Entity
@NamedQuery(name="Matriz.findAll", query="SELECT m FROM Matriz m")
public class Matriz extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_matriz")
	private Integer codigoMatriz;

	private String descripcion;
	
	private BigDecimal latitud;

	private BigDecimal longitud;
	


	//bi-directional many-to-one association to Nodo
	@OneToMany(mappedBy="matriz")
	private List<Nodo> nodos;

	public Matriz() {
	}

	
	
	public Matriz(Integer codigoMatriz, String descripcion) {
		super();
		this.codigoMatriz = codigoMatriz;
		this.descripcion = descripcion;
	}



	public Integer getCodigoMatriz() {
		return this.codigoMatriz;
	}

	public void setCodigoMatriz(Integer codigoMatriz) {
		this.codigoMatriz = codigoMatriz;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	@XmlTransient
	public List<Nodo> getNodos() {
		return this.nodos;
	}

	public void setNodos(List<Nodo> nodos) {
		this.nodos = nodos;
	}

	public Nodo addNodo(Nodo nodo) {
		getNodos().add(nodo);
		nodo.setMatriz(this);

		return nodo;
	}

	public Nodo removeNodo(Nodo nodo) {
		getNodos().remove(nodo);
		nodo.setMatriz(null);

		return nodo;
	}



	@Override
	public String toString() {
		return descripcion;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoMatriz == null) ? 0 : codigoMatriz.hashCode());
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
		Matriz other = (Matriz) obj;
		if (codigoMatriz == null) {
			if (other.codigoMatriz != null)
				return false;
		} else if (!codigoMatriz.equals(other.codigoMatriz))
			return false;
		return true;
	}
	
	
	

}