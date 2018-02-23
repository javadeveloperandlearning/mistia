package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the encuesta_pregunta database table.
 * 
 */
@Embeddable
public class EncuestaPreguntaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="numero_encuesta", insertable=false, updatable=false)
	private Integer numeroEncuesta;

	@Column(name="numero_pregunta")
	private Integer numeroPregunta;

	public EncuestaPreguntaPK() {
	}
	public Integer getNumeroEncuesta() {
		return this.numeroEncuesta;
	}
	public void setNumeroEncuesta(Integer numeroEncuesta) {
		this.numeroEncuesta = numeroEncuesta;
	}
	public Integer getNumeroPregunta() {
		return this.numeroPregunta;
	}
	public void setNumeroPregunta(Integer numeroPregunta) {
		this.numeroPregunta = numeroPregunta;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EncuestaPreguntaPK)) {
			return false;
		}
		EncuestaPreguntaPK castOther = (EncuestaPreguntaPK)other;
		return 
			this.numeroEncuesta.equals(castOther.numeroEncuesta)
			&& this.numeroPregunta.equals(castOther.numeroPregunta);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numeroEncuesta.hashCode();
		hash = hash * prime + this.numeroPregunta.hashCode();
		
		return hash;
	}
}