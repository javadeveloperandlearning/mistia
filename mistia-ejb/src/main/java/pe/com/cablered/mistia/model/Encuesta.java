package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the encuesta database table.
 * 
 */
@Entity
@NamedQuery(name = "Encuesta.findAll", query = "SELECT e FROM Encuesta e")
public class Encuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "numero_encuesta")
	private Integer numeroEncuesta;

	private String descripcion;

	@Column(name = "estacion_creacion")
	private String estacionCreacion;

	@Column(name = "estacion_modificacion")
	private String estacionModificacion;

	@Column(name = "fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name = "fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name = "usuario_creacion")
	private String usuarioCreacion;

	@Column(name = "usuario_modificacion")
	private String usuarioModificacion;

	// bi-directional many-to-one association to EncuestaPregunta
	@OneToMany(mappedBy = "encuesta", fetch = FetchType.EAGER)
	private List<EncuestaPregunta> encuestaPreguntas;

	/*
	 * @OneToMany(mappedBy="encuesta", fetch=FetchType.EAGER) private
	 * List<EncuestaRespuesta> encuestaRespuestas;
	 */
	public Encuesta() {
	}

	public Encuesta(Integer numeroEncuesta) {
		this.numeroEncuesta = numeroEncuesta;
	}

	public Integer getNumeroEncuesta() {
		return this.numeroEncuesta;
	}

	public void setNumeroEncuesta(Integer numeroEncuesta) {
		this.numeroEncuesta = numeroEncuesta;
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

	public List<EncuestaPregunta> getEncuestaPreguntas() {
		return this.encuestaPreguntas;
	}

	public void setEncuestaPreguntas(List<EncuestaPregunta> encuestaPreguntas) {
		this.encuestaPreguntas = encuestaPreguntas;
	}

	public EncuestaPregunta addEncuestaPregunta(EncuestaPregunta encuestaPregunta) {
		getEncuestaPreguntas().add(encuestaPregunta);
		encuestaPregunta.setEncuesta(this);

		return encuestaPregunta;
	}

	public EncuestaPregunta removeEncuestaPregunta(EncuestaPregunta encuestaPregunta) {
		getEncuestaPreguntas().remove(encuestaPregunta);
		encuestaPregunta.setEncuesta(null);

		return encuestaPregunta;
	}

	/*
	 * public List<EncuestaRespuesta> getEncuestaRespuestas() { return
	 * encuestaRespuestas; }
	 * 
	 * public void setEncuestaRespuestas(List<EncuestaRespuesta>
	 * encuestaRespuestas) { this.encuestaRespuestas = encuestaRespuestas; }
	 */

}