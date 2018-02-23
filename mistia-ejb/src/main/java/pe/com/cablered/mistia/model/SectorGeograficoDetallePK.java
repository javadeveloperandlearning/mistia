package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the sector_geografico_detalle database table.
 * 
 */
@Embeddable
public class SectorGeograficoDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="codigo_sector", insertable=false, updatable=false)
	private Integer codigoSector;

	@Column(name="codigo_poste", insertable=false, updatable=false)
	private Integer codigoPoste;

	public SectorGeograficoDetallePK() {
	}
	public Integer getCodigoSector() {
		return this.codigoSector;
	}
	public void setCodigoSector(Integer codigoSector) {
		this.codigoSector = codigoSector;
	}
	public Integer getCodigoPoste() {
		return this.codigoPoste;
	}
	public void setCodigoPoste(Integer codigoPoste) {
		this.codigoPoste = codigoPoste;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SectorGeograficoDetallePK)) {
			return false;
		}
		SectorGeograficoDetallePK castOther = (SectorGeograficoDetallePK)other;
		return 
			this.codigoSector.equals(castOther.codigoSector)
			&& this.codigoPoste.equals(castOther.codigoPoste);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codigoSector.hashCode();
		hash = hash * prime + this.codigoPoste.hashCode();
		
		return hash;
	}
}