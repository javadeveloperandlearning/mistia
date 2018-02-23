package pe.com.eb.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the perfiles_opciones database table.
 * 
 */
@Embeddable
public class PerfilOpcionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cod_modu", insertable=false, updatable=false)
	private Integer codModu;

	@Column(name="cod_perf", insertable=false, updatable=false)
	private Integer codPerf;

	@Column(name="cod_opci", insertable=false, updatable=false)
	private Integer codOpci;

	public PerfilOpcionPK() {
	}
	public Integer getCodModu() {
		return this.codModu;
	}
	public void setCodModu(Integer codModu) {
		this.codModu = codModu;
	}
	public Integer getCodPerf() {
		return this.codPerf;
	}
	public void setCodPerf(Integer codPerf) {
		this.codPerf = codPerf;
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
		if (!(other instanceof PerfilOpcionPK)) {
			return false;
		}
		PerfilOpcionPK castOther = (PerfilOpcionPK)other;
		return 
			this.codModu.equals(castOther.codModu)
			&& this.codPerf.equals(castOther.codPerf)
			&& this.codOpci.equals(castOther.codOpci);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codModu.hashCode();
		hash = hash * prime + this.codPerf.hashCode();
		hash = hash * prime + this.codOpci.hashCode();
		
		return hash;
	}
}