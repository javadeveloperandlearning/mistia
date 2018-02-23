package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tipo_solicitud_competencia_detalle database table.
 * 
 */
@Embeddable
public class TipoSolicitudCompetenciaDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_tipo_solicitud", insertable=false, updatable=false)
	private Integer codigoTipoSolicitud;

	@Column(name="codigo_competencia", insertable=false, updatable=false)
	private Integer codigoCompetencia;

	public TipoSolicitudCompetenciaDetallePK() {
	}
	public Integer getCodigoTipoSolicitud() {
		return this.codigoTipoSolicitud;
	}
	public void setCodigoTipoSolicitud(Integer codigoTipoSolicitud) {
		this.codigoTipoSolicitud = codigoTipoSolicitud;
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
		if (!(other instanceof TipoSolicitudCompetenciaDetallePK)) {
			return false;
		}
		TipoSolicitudCompetenciaDetallePK castOther = (TipoSolicitudCompetenciaDetallePK)other;
		return 
			this.codigoTipoSolicitud.equals(castOther.codigoTipoSolicitud)
			&& this.codigoCompetencia.equals(castOther.codigoCompetencia);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoTipoSolicitud.hashCode();
		hash = hash * prime + this.codigoCompetencia.hashCode();
		
		return hash;
	}
}