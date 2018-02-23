package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the plan_trabajo_detalle database table.
 * 
 */
@Embeddable
public class PlanTrabajoDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="numero_plan_trabajo", insertable=false, updatable=false)
	private long numeroPlanTrabajo;

	@Column(name="numero_secuencial")
	private Integer numeroSecuencial;

	public PlanTrabajoDetallePK() {
	}
	
	
	public PlanTrabajoDetallePK(long numeroPlanTrabajo,Integer numeroSecuencial ) {
		this.numeroPlanTrabajo =  numeroPlanTrabajo;
		this.numeroSecuencial =  numeroSecuencial;
	}
	
	
	
	public long getNumeroPlanTrabajo() {
		return this.numeroPlanTrabajo;
	}
	public void setNumeroPlanTrabajo(long numeroPlanTrabajo) {
		this.numeroPlanTrabajo = numeroPlanTrabajo;
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
		if (!(other instanceof PlanTrabajoDetallePK)) {
			return false;
		}
		PlanTrabajoDetallePK castOther = (PlanTrabajoDetallePK)other;
		return 
			(this.numeroPlanTrabajo == castOther.numeroPlanTrabajo)
			&& this.numeroSecuencial.equals(castOther.numeroSecuencial);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.numeroPlanTrabajo ^ (this.numeroPlanTrabajo >>> 32)));
		hash = hash * prime + this.numeroSecuencial.hashCode();
		
		return hash;
	}


	@Override
	public String toString() {
		return "PlanTrabajoDetallePK [numeroPlanTrabajo=" + numeroPlanTrabajo + ", numeroSecuencial=" + numeroSecuencial
				+ "]";
	}
	
	
}