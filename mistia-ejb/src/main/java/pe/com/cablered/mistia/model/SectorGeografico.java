package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the sector_geografico database table.
 * 
 */
@Entity
@Table(name="sector_geografico")
@NamedQuery(name="SectorGeografico.findAll", query="SELECT s FROM SectorGeografico s")
public class SectorGeografico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_sector")
	private Integer codigoSector;

	private String descripcion;

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

	//bi-directional many-to-one association to SectorGeograficoDetalle
	@OneToMany(mappedBy="sectorGeografico")
	private List<SectorGeograficoDetalle> sectorGeograficoDetalles;

	public SectorGeografico() {
	}

	public Integer getCodigoSector() {
		return this.codigoSector;
	}

	public void setCodigoSector(Integer codigoSector) {
		this.codigoSector = codigoSector;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public List<SectorGeograficoDetalle> getSectorGeograficoDetalles() {
		return this.sectorGeograficoDetalles;
	}

	public void setSectorGeograficoDetalles(List<SectorGeograficoDetalle> sectorGeograficoDetalles) {
		this.sectorGeograficoDetalles = sectorGeograficoDetalles;
	}

	public SectorGeograficoDetalle addSectorGeograficoDetalle(SectorGeograficoDetalle sectorGeograficoDetalle) {
		getSectorGeograficoDetalles().add(sectorGeograficoDetalle);
		sectorGeograficoDetalle.setSectorGeografico(this);

		return sectorGeograficoDetalle;
	}

	public SectorGeograficoDetalle removeSectorGeograficoDetalle(SectorGeograficoDetalle sectorGeograficoDetalle) {
		getSectorGeograficoDetalles().remove(sectorGeograficoDetalle);
		sectorGeograficoDetalle.setSectorGeografico(null);

		return sectorGeograficoDetalle;
	}

}