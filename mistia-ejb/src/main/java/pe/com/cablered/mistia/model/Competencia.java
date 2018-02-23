package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the competencias database table.
 * 
 */
@Entity
@Table(name="competencias")
@NamedQuery(name="Competencia.findAll", query="SELECT c FROM Competencia c")
public class Competencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_competencia")
	private Integer codigoCompetencia;

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

	@Column(name="valor_maximo")
	private BigDecimal valorMaximo;

	@Column(name="valor_minimo")
	private BigDecimal valorMinimo;

	//bi-directional many-to-one association to TecnicoCompetenciaDetalle
	@OneToMany(mappedBy="competencia")
	private List<TecnicoCompetenciaDetalle> tecnicoCompetenciaDetalles;

	//bi-directional many-to-one association to TipoSolicitudCompetenciaDetalle
	@OneToMany(mappedBy="competencia")
	private List<TipoSolicitudCompetenciaDetalle> tipoSolicitudCompetenciaDetalles;

	public Competencia() {
	}

	public Integer getCodigoCompetencia() {
		return this.codigoCompetencia;
	}

	public void setCodigoCompetencia(Integer codigoCompetencia) {
		this.codigoCompetencia = codigoCompetencia;
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

	public BigDecimal getValorMaximo() {
		return this.valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public BigDecimal getValorMinimo() {
		return this.valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public List<TecnicoCompetenciaDetalle> getTecnicoCompetenciaDetalles() {
		return this.tecnicoCompetenciaDetalles;
	}

	public void setTecnicoCompetenciaDetalles(List<TecnicoCompetenciaDetalle> tecnicoCompetenciaDetalles) {
		this.tecnicoCompetenciaDetalles = tecnicoCompetenciaDetalles;
	}

	public TecnicoCompetenciaDetalle addTecnicoCompetenciaDetalle(TecnicoCompetenciaDetalle tecnicoCompetenciaDetalle) {
		getTecnicoCompetenciaDetalles().add(tecnicoCompetenciaDetalle);
		tecnicoCompetenciaDetalle.setCompetencia(this);

		return tecnicoCompetenciaDetalle;
	}

	public TecnicoCompetenciaDetalle removeTecnicoCompetenciaDetalle(TecnicoCompetenciaDetalle tecnicoCompetenciaDetalle) {
		getTecnicoCompetenciaDetalles().remove(tecnicoCompetenciaDetalle);
		tecnicoCompetenciaDetalle.setCompetencia(null);

		return tecnicoCompetenciaDetalle;
	}

	public List<TipoSolicitudCompetenciaDetalle> getTipoSolicitudCompetenciaDetalles() {
		return this.tipoSolicitudCompetenciaDetalles;
	}

	public void setTipoSolicitudCompetenciaDetalles(List<TipoSolicitudCompetenciaDetalle> tipoSolicitudCompetenciaDetalles) {
		this.tipoSolicitudCompetenciaDetalles = tipoSolicitudCompetenciaDetalles;
	}

	public TipoSolicitudCompetenciaDetalle addTipoSolicitudCompetenciaDetalle(TipoSolicitudCompetenciaDetalle tipoSolicitudCompetenciaDetalle) {
		getTipoSolicitudCompetenciaDetalles().add(tipoSolicitudCompetenciaDetalle);
		tipoSolicitudCompetenciaDetalle.setCompetencia(this);

		return tipoSolicitudCompetenciaDetalle;
	}

	public TipoSolicitudCompetenciaDetalle removeTipoSolicitudCompetenciaDetalle(TipoSolicitudCompetenciaDetalle tipoSolicitudCompetenciaDetalle) {
		getTipoSolicitudCompetenciaDetalles().remove(tipoSolicitudCompetenciaDetalle);
		tipoSolicitudCompetenciaDetalle.setCompetencia(null);

		return tipoSolicitudCompetenciaDetalle;
	}

}