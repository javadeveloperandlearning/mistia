package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the programacion_detalle database table.
 * 
 */
@Embeddable
public class ProgramacionDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="numero_programacion")
	private long numeroProgramacion;

	@Column(name="numero_secuencial")
	private Integer numeroSecuencial;

	public ProgramacionDetallePK() {
	}
	
	
	public ProgramacionDetallePK(long numeroProgramacion, Integer numeroSecuencial) {
	
		this.numeroProgramacion =  numeroProgramacion;
		this.numeroSecuencial =  numeroSecuencial;
		
		
	}
	public long getNumeroProgramacion() {
		return this.numeroProgramacion;
	}
	public void setNumeroProgramacion(long numeroProgramacion) {
		this.numeroProgramacion = numeroProgramacion;
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
		if (!(other instanceof ProgramacionDetallePK)) {
			return false;
		}
		ProgramacionDetallePK castOther = (ProgramacionDetallePK)other;
		return 
			(this.numeroProgramacion == castOther.numeroProgramacion)
			&& this.numeroSecuencial.equals(castOther.numeroSecuencial);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.numeroProgramacion ^ (this.numeroProgramacion >>> 32)));
		hash = hash * prime + this.numeroSecuencial.hashCode();
		
		return hash;
	}
}