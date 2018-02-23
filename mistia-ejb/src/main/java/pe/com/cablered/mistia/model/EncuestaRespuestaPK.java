package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the encuesta_respuesta database table.
 * 
 */
@Embeddable
public class EncuestaRespuestaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="numero_encuesta")
	private Integer numeroEncuesta;

	@Column(name="numero_respuesta")
	private Integer numeroRespuesta;

	public EncuestaRespuestaPK() {
	}
	public Integer getNumeroEncuesta() {
		return this.numeroEncuesta;
	}
	public void setNumeroEncuesta(Integer numeroEncuesta) {
		this.numeroEncuesta = numeroEncuesta;
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
		if (!(other instanceof EncuestaRespuestaPK)) {
			return false;
		}
		EncuestaRespuestaPK castOther = (EncuestaRespuestaPK)other;
		return 
			this.numeroEncuesta.equals(castOther.numeroEncuesta)
			&& this.numeroRespuesta.equals(castOther.numeroRespuesta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numeroEncuesta.hashCode();
		hash = hash * prime + this.numeroRespuesta.hashCode();
		
		return hash;
	}
}