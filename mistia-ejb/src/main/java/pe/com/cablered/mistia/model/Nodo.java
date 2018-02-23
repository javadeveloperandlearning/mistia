package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the nodos database table.
 * 
 */
@Entity
@Table(name="nodos")
@NamedQuery(name="Nodo.findAll", query="SELECT n FROM Nodo n")
public class Nodo extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_nodo")
	private Integer codigoNodo;

	private String descripcion;
	
	
	private BigDecimal latitud;

	private BigDecimal longitud;	

	//bi-directional many-to-one association to Matriz
	@ManyToOne
	@JoinColumn(name="codigo_matriz")
	private Matriz matriz;

	//bi-directional many-to-one association to Poste
	@OneToMany(mappedBy="nodo")
	private List<Poste> postes;

	public Nodo() {
	
	}

	
	
	
	
	public Nodo(Integer codigoNodo, String descripcion) {
		super();
		this.codigoNodo = codigoNodo;
		this.descripcion = descripcion;
	}





	public Integer getCodigoNodo() {
		return this.codigoNodo;
	}

	public void setCodigoNodo(Integer codigoNodo) {
		this.codigoNodo = codigoNodo;
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



	public Matriz getMatriz() {
		return this.matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	@XmlTransient
	public List<Poste> getPostes() {
		return this.postes;
	}

	public void setPostes(List<Poste> postes) {
		this.postes = postes;
	}

	public Poste addPoste(Poste poste) {
		getPostes().add(poste);
		poste.setNodo(this);

		return poste;
	}

	public Poste removePoste(Poste poste) {
		getPostes().remove(poste);
		poste.setNodo(null);

		return poste;
	}

	
	
	@Override
	public String toString() {
		return this.descripcion;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoNodo == null) ? 0 : codigoNodo.hashCode());
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
		Nodo other = (Nodo) obj;
		if (codigoNodo == null) {
			if (other.codigoNodo != null)
				return false;
		} else if (!codigoNodo.equals(other.codigoNodo))
			return false;
		return true;
	}
	
	
}