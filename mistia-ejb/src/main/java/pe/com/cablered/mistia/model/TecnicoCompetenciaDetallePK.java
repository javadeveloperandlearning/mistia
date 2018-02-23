package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tecnico_competencia_detalle database table.
 * 
 */
@Embeddable
public class TecnicoCompetenciaDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_tecnico", insertable=false, updatable=false)
	private Integer codigoTecnico;

	@Column(name="codigo_competencia", insertable=false, updatable=false)
	private Integer codigoCompetencia;

	public TecnicoCompetenciaDetallePK() {
	}
	public Integer getCodigoTecnico() {
		return this.codigoTecnico;
	}
	public void setCodigoTecnico(Integer codigoTecnico) {
		this.codigoTecnico = codigoTecnico;
	}
	public Integer getCodigoCompetencia() {
		return this.codigoCompetencia;
	}
	public void setCodigoCompetencia(Integer codigoCompetencia) {
		this.codigoCompetencia = codigoCompetencia;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TecnicoCompetenciaDetallePK)) {
			return false;
		}
		TecnicoCompetenciaDetallePK castOther = (TecnicoCompetenciaDetallePK)other;
		return 
			this.codigoTecnico.equals(castOther.codigoTecnico)
			&& this.codigoCompetencia.equals(castOther.codigoCompetencia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoTecnico.hashCode();
		hash = hash * prime + this.codigoCompetencia.hashCode();
		
		return hash;
	}
}