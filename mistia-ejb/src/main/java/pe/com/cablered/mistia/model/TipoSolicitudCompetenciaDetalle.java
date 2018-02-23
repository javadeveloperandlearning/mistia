package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tipo_solicitud_competencia_detalle database table.
 * 
 */
@Entity
@Table(name="tipo_solicitud_competencia_detalle")
@NamedQuery(name="TipoSolicitudCompetenciaDetalle.findAll", query="SELECT t FROM TipoSolicitudCompetenciaDetalle t")
public class TipoSolicitudCompetenciaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TipoSolicitudCompetenciaDetallePK id;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modifcion")
	private String estacionModifcion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="grado_competencia")
	private Integer gradoCompetencia;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to Competencia
	@ManyToOne
	@JoinColumn(name="codigo_competencia" , insertable = false, updatable= false)
	private Competencia competencia;

	//bi-directional many-to-one association to Tipo
	@ManyToOne
	@JoinColumn(name="codigo_tipo_solicitud" , insertable = false, updatable= false)
	private Tipo tipo;

	public TipoSolicitudCompetenciaDetalle() {
	}

	public TipoSolicitudCompetenciaDetallePK getId() {
		return this.id;
	}

	public void setId(TipoSolicitudCompetenciaDetallePK id) {
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

	public Integer getGradoCompetencia() {
		return this.gradoCompetencia;
	}

	public void setGradoCompetencia(Integer gradoCompetencia) {
		this.gradoCompetencia = gradoCompetencia;
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

	public Competencia getCompetencia() {
		return this.competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}