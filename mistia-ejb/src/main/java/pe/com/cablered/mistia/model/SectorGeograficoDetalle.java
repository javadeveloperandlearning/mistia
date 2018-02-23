package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the sector_geografico_detalle database table.
 * 
 */
@Entity
@Table(name="sector_geografico_detalle")
@NamedQuery(name="SectorGeograficoDetalle.findAll", query="SELECT s FROM SectorGeograficoDetalle s")
public class SectorGeograficoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SectorGeograficoDetallePK id;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modifcion")
	private String estacionModifcion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to Poste
	@ManyToOne
	@JoinColumn(name="codigo_poste", insertable= false, updatable=false)
	private Poste poste;

	//bi-directional many-to-one association to SectorGeografico
	@ManyToOne
	@JoinColumn(name="codigo_sector", insertable= false, updatable=false)
	private SectorGeografico sectorGeografico;

	public SectorGeograficoDetalle() {
	}

	public SectorGeograficoDetallePK getId() {
		return this.id;
	}

	public void setId(SectorGeograficoDetallePK id) {
		this.id = id;
	}

	public String getEstacionCreacion() {
		return this.estacionCreacion;
	}

	public void setEstacionCreacion(String estacionCreacion) {
		this.estacionCreacion = estacionCreacion;
	}

	public String getEstacionModifcion() {
		return this.estacionModifcion;
	}

	public void setEstacionModifcion(String estacionModifcion) {
		this.estacionModifcion = estacionModifcion;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioCreacion() {
		return this.usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioModificacion() {
		return this.usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Poste getPoste() {
		return this.poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public SectorGeografico getSectorGeografico() {
		return this.sectorGeografico;
	}

	public void setSectorGeografico(SectorGeografico sectorGeografico) {
		this.sectorGeografico = sectorGeografico;
	}

}