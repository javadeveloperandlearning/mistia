package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the encuesta_solicitud_resultado database table.
 * 
 */
@Embeddable
public class EncuestaSolicitudResultadoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	 @Basic(optional = false)
	@Column(name="numero_encuesta")
	private Integer numeroEncuesta;

	 @Basic(optional = false)
	@Column(name="numero_solicitud")
	private Long numeroSolicitud;

	 @Basic(optional = false)
	@Column(name="numero_pregunta")
	private Integer numeroPregunta;

	 @Basic(optional = false)
	@Column(name="numero_respuesta")
	private Integer numeroRespuesta;

	public EncuestaSolicitudResultadoPK() {
	}
	
	
	public EncuestaSolicitudResultadoPK(Integer numeroEncuesta, Long numeroSolicitud, Integer numeroPregunta,
			Integer numeroRespuesta) {
		super();
		this.numeroEncuesta = numeroEncuesta;
		this.numeroSolicitud = numeroSolicitud;
		this.numeroPregunta = numeroPregunta;
		this.numeroRespuesta = numeroRespuesta;
	}





	public Integer getNumeroEncuesta() {
		return this.numeroEncuesta;
	}
	public void setNumeroEncuesta(Integer numeroEncuesta) {
		this.numeroEncuesta = numeroEncuesta;
	}
	public Long getNumeroSolicitud() {
		return this.numeroSolicitud;
	}
	public void setNumeroSolicitud(Long numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}
	public Integer getNumeroPregunta() {
		return this.numeroPregunta;
	}
	public void setNumeroPregunta(Integer numeroPregunta) {
		this.numeroPregunta = numeroPregunta;
	}
	public Integer getNumeroRespuesta() {
		return this.numeroRespuesta;
	}
	public void setNumeroRespuesta(Integer numeroRespuesta) {
		this.numeroRespuesta = numeroRespuesta;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EncuestaSolicitudResultadoPK)) {
			return false;
		}
		EncuestaSolicitudResultadoPK castOther = (EncuestaSolicitudResultadoPK)other;
		return 
			this.numeroEncuesta.equals(castOther.numeroEncuesta)
			&& this.numeroSolicitud.equals(castOther.numeroSolicitud)
			&& this.numeroPregunta.equals(castOther.numeroPregunta)
			&& this.numeroRespuesta.equals(castOther.numeroRespuesta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numeroEncuesta.hashCode();
		hash = hash * prime + this.numeroSolicitud.hashCode();
		hash = hash * prime + this.numeroPregunta.hashCode();
		hash = hash * prime + this.numeroRespuesta.hashCode();
		
		return hash;
	}
}