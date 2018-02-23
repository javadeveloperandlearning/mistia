package pe.com.eb.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the modulos database table.
 * 
 */
@Entity
@Table(name="seguridad.modulos")
@NamedQuery(name="Modulo.findAll", query="SELECT m FROM Modulo m")
public class Modulo extends ObjectBean  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1631652317191514494L;

	@Id
	@Column(name="cod_modu")
	private Integer codModu;

	@Column(name="des_modu")
	private String desModu;

	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name="cod_esta"  ,nullable= false, updatable= false )
	EstadoRegistro estadoRegistro;


	//bi-directional many-to-many association to Opcion
	@ManyToMany
	@JoinTable(name="modulos"
	, joinColumns={@JoinColumn(name="cod_modu")}, inverseJoinColumns={

			}
		)
	private List<Opcion> opciones;

	public Modulo() {
	}
	
	public Modulo(Integer codModu){
		this.codModu = codModu;
	}
	
	

	public Modulo(Integer codModu, String desModu) {
		this.codModu = codModu;
		this.desModu = desModu;
	}

	public Integer getCodModu() {
		return this.codModu;
	}

	public void setCodModu(Integer codModu) {
		this.codModu = codModu;
	}


	
	public String getDesModu() {
		return this.desModu;
	}

	public void setDesModu(String desModu) {
		this.desModu = desModu;
	}

	

	public List<Opcion> getOpciones() {
		return this.opciones;
	}

	public void setOpciones(List<Opcion> opciones) {
		this.opciones = opciones;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codModu == null) ? 0 : codModu.hashCode());
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
		Modulo other = (Modulo) obj;
		if (codModu == null) {
			if (other.codModu != null)
				return false;
		} else if (!codModu.equals(other.codModu))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  getDesModu();
	}
	

}