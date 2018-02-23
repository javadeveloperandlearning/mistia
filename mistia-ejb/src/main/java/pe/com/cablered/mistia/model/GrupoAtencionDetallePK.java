package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the grupo_atencion_detalle database table.
 * 
 */
@Embeddable
public class GrupoAtencionDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="numero_grupo_atencion", insertable=false, updatable=false)
	private long numeroGrupoAtencion;

	@Column(name="numero_orden")
	private Integer numeroOrden;

	public GrupoAtencionDetallePK() {
	}
	
	public GrupoAtencionDetallePK(long numeroGrupoAtencion, Integer numeroOrden) {
		this.numeroGrupoAtencion =  numeroGrupoAtencion;
		this.numeroOrden =  numeroOrden;
	}
	
	
	public long getNumeroGrupoAtencion() {
		return this.numeroGrupoAtencion;
	}
	public void setNumeroGrupoAtencion(long numeroGrupoAtencion) {
		this.numeroGrupoAtencion = numeroGrupoAtencion;
	}
	public Integer getNumeroOrden() {
		return this.numeroOrden;
	}
	public void setNumeroOrden(Integer numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GrupoAtencionDetallePK)) {
			return false;
		}
		GrupoAtencionDetallePK castOther = (GrupoAtencionDetallePK)other;
		return 
			(this.numeroGrupoAtencion == castOther.numeroGrupoAtencion)
			&& this.numeroOrden.equals(castOther.numeroOrden);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.numeroGrupoAtencion ^ (this.numeroGrupoAtencion >>> 32)));
		hash = hash * prime + this.numeroOrden.hashCode();
		
		return hash;
	}
}