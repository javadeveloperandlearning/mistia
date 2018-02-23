package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the comprobante_pago_detalle database table.
 * 
 */
@Embeddable
public class ComprobantePagoDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="numero_comp_pago")
	private long numeroCompPago;

	@Column(name="codigo_concepto", insertable=false, updatable=false)
	private Integer codigoConcepto;

	public ComprobantePagoDetallePK() {
	}
	public long getNumeroCompPago() {
		return this.numeroCompPago;
	}
	public void setNumeroCompPago(long numeroCompPago) {
		this.numeroCompPago = numeroCompPago;
	}
	public Integer getCodigoConcepto() {
		return this.codigoConcepto;
	}
	public void setCodigoConcepto(Integer codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ComprobantePagoDetallePK)) {
			return false;
		}
		ComprobantePagoDetallePK castOther = (ComprobantePagoDetallePK)other;
		return 
			(this.numeroCompPago == castOther.numeroCompPago)
			&& this.codigoConcepto.equals(castOther.codigoConcepto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.numeroCompPago ^ (this.numeroCompPago >>> 32)));
		hash = hash * prime + this.codigoConcepto.hashCode();
		
		return hash;
	}
}