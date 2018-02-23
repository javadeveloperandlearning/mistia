package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the contrato_servicio_detalle database table.
 * 
 */
@Embeddable
public class ContratoServicioDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="numero_contrato", insertable=false, updatable=false)
	private long numeroContrato;

	@Column(name="codigo_servicio", insertable=false, updatable=false)
	private Integer codigoServicio;

	public ContratoServicioDetallePK() {
	}
	public long getNumeroContrato() {
		return this.numeroContrato;
	}
	public void setNumeroContrato(long numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public Integer getCodigoServicio() {
		return this.codigoServicio;
	}
	public void setCodigoServicio(Integer codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ContratoServicioDetallePK)) {
			return false;
		}
		ContratoServicioDetallePK castOther = (ContratoServicioDetallePK)other;
		return 
			(this.numeroContrato == castOther.numeroContrato)
			&& this.codigoServicio.equals(castOther.codigoServicio);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.numeroContrato ^ (this.numeroContrato >>> 32)));
		hash = hash * prime + this.codigoServicio.hashCode();
		
		return hash;
	}
}