package pe.com.cablered.seguridad.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the usuarios_perfiles database table.
 * 
 */
@Embeddable
public class UsuarioPerfilPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cod_usua", insertable=false, updatable=false)
	private String codUsua;

	@Column(name="cod_modu", insertable=false, updatable=false)	
	private Integer codModu;

	@Column(name="cod_perf", insertable=false, updatable=false)
	private Integer codPerf;

	public UsuarioPerfilPK() {
	}

	public UsuarioPerfilPK(String codUsua, Integer codModu, Integer codPerf) {
		
		this.codUsua = codUsua;
		this.codModu = codModu;
		this.codPerf = codPerf;
	}




	public String getCodUsua() {
		return this.codUsua;
	}
	public void setCodUsua(String codUsua) {
		this.codUsua = codUsua;
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
		if (!(other instanceof UsuarioPerfilPK)) {
			return false;
		}
		UsuarioPerfilPK castOther = (UsuarioPerfilPK)other;
		return 
			this.codUsua.equals(castOther.codUsua)
			&& this.codModu.equals(castOther.codModu)
			&& this.codPerf.equals(castOther.codPerf);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codUsua.hashCode();
		hash = hash * prime + this.codModu.hashCode();
		hash = hash * prime + this.codPerf.hashCode();
		
		return hash;
	}
}