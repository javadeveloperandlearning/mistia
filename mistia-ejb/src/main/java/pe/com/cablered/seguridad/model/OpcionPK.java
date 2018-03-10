package pe.com.cablered.seguridad.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the opciones database table.
 * 
 */
@Embeddable
public class OpcionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cod_modu")
	private Integer codModu;

	@Column(name="cod_opci")
	private Integer codOpci;

	public OpcionPK() {
	}
	public Integer getCodModu() {
		return this.codModu;
	}
	public void setCodModu(Integer codModu) {
		this.codModu = codModu;
	}
	public Integer getCodOpci() {
		return this.codOpci;
	}
	public void setCodOpci(Integer codOpci) {
		this.codOpci = codOpci;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OpcionPK)) {
			return false;
		}
		OpcionPK castOther = (OpcionPK)other;
		return 
			this.codModu.equals(castOther.codModu)
			&& this.codOpci.equals(castOther.codOpci);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codModu.hashCode();
		hash = hash * prime + this.codOpci.hashCode();
		
		return hash;
	}
}