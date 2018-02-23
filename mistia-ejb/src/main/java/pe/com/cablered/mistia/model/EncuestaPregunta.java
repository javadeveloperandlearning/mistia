package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the encuesta_pregunta database table.
 * 
 */
@Entity
@Table(name="encuesta_pregunta")
@NamedQuery(name="EncuestaPregunta.findAll", query="SELECT e FROM EncuestaPregunta e")
public class EncuestaPregunta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EncuestaPreguntaPK id;

	private String descripcion;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modificacion")
	private String estacionModificacion;

	private Double factor;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	private Double peso;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to Encuesta
	@ManyToOne
	@JoinColumn(name="numero_encuesta", insertable = false, updatable= false)
	private Encuesta encuesta;

;

	public EncuestaPregunta() {
	}

	public EncuestaPreguntaPK getId() {
		return this.id;
	}

	public void setId(EncuestaPreguntaPK id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstacionCreacion() {
		return this.estacionCreacion;
	}

	public void setEstacionCreacion(String estacionCreacion) {
		this.estacionCreacion = estacionCreacion;
	}

	public String getEstacionModificacion() {
		return this.estacionModificacion;
	}

	public void setEstacionModificacion(String estacionModificacion) {
		this.estacionModificacion = estacionModificacion;
	}

	public Double getFactor() {
		return this.factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioModificacion() {
		return this.usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Encuesta getEncuesta() {
		return this.encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		EncuestaPregunta other = (EncuestaPregunta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}