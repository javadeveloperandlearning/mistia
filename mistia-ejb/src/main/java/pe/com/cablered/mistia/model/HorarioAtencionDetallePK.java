package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the horario_atencion_detalle database table.
 * 
 */
@Embeddable
public class HorarioAtencionDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="numero_horario", insertable=false, updatable=false)
	private Integer numeroHorario;

	@Column(name="numero_secuencial")
	private Integer numeroSecuencial;

	public HorarioAtencionDetallePK() {
	}
	public Integer getNumeroHorario() {
		return this.numeroHorario;
	}
	public void setNumeroHorario(Integer numeroHorario) {
		this.numeroHorario = numeroHorario;
	}
	public Integer getNumeroSecuencial() {
		return this.numeroSecuencial;
	}
	public void setNumeroSecuencial(Integer numeroSecuencial) {
		this.numeroSecuencial = numeroSecuencial;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HorarioAtencionDetallePK)) {
			return false;
		}
		HorarioAtencionDetallePK castOther = (HorarioAtencionDetallePK)other;
		return 
			this.numeroHorario.equals(castOther.numeroHorario)
			&& this.numeroSecuencial.equals(castOther.numeroSecuencial);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numeroHorario.hashCode();
		hash = hash * prime + this.numeroSecuencial.hashCode();
		
		return hash;
	}
}