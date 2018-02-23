package pe.com.eb.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the perfiles database table.
 * 
 */
@Embeddable
public class PerfilPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cod_modu")
	private Integer codModu;

	@Column(name="cod_perf")
	private Integer codPerf;

	public PerfilPK() {
	}
	
	
	
	public PerfilPK(Integer codModu, Integer codPerf) {
		super();
		this.codModu = codModu;
		this.codPerf = codPerf;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PerfilPK)) {
			return false;
		}
		PerfilPK castOther = (PerfilPK)other;
		return 
			this.codModu.equals(castOther.codModu)
			&& this.codPerf.equals(castOther.codPerf);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codModu.hashCode();
		hash = hash * prime + this.codPerf.hashCode();
		
		return hash;
	}
}