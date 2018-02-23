package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the solicitud_servicio_estado database table.
 * 
 */
@Embeddable
public class SolicitudServicioEstadoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="numero_solicitud", insertable=false, updatable=false)
	private long numeroSolicitud;

	@Column(name="numero_secuencial")
	private Integer numeroSecuencial;

	public SolicitudServicioEstadoPK() {
	}
	
	public SolicitudServicioEstadoPK(long numeroSolicitud, Integer numeroSecuencial ) {
		this.numeroSolicitud  =numeroSolicitud;
		this.numeroSecuencial =  numeroSecuencial;
 
	}
	
	
	public long getNumeroSolicitud() {
		return this.numeroSolicitud;
	}
	public void setNumeroSolicitud(long numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
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
		if (!(other instanceof SolicitudServicioEstadoPK)) {
			return false;
		}
		SolicitudServicioEstadoPK castOther = (SolicitudServicioEstadoPK)other;
		return 
			(this.numeroSolicitud == castOther.numeroSolicitud)
			&& this.numeroSecuencial.equals(castOther.numeroSecuencial);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.numeroSolicitud ^ (this.numeroSolicitud >>> 32)));
		hash = hash * prime + this.numeroSecuencial.hashCode();
		
		return hash;
	}
}