package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the tecnico_competencia_detalle database table.
 * 
 */
@Entity
@Table(name="tecnico_competencia_detalle")
@NamedQuery(name="TecnicoCompetenciaDetalle.findAll", query="SELECT t FROM TecnicoCompetenciaDetalle t")
public class TecnicoCompetenciaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TecnicoCompetenciaDetallePK id;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modifcion")
	private String estacionModifcion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="grado_competencia")
	private BigDecimal gradoCompetencia;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to Competencia
	@ManyToOne
	@JoinColumn(name="codigo_competencia", insertable = false, updatable= false)
	private Competencia competencia;

	//bi-directional many-to-one association to Tecnico
	@ManyToOne
	@JoinColumn(name="codigo_tecnico" , insertable = false, updatable= false)
	private Tecnico tecnico;

	public TecnicoCompetenciaDetalle() {
	}

	public TecnicoCompetenciaDetallePK getId() {
		return this.id;
	}

	public void setId(TecnicoCompetenciaDetallePK id) {
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

	public BigDecimal getGradoCompetencia() {
		return this.gradoCompetencia;
	}

	public void setGradoCompetencia(BigDecimal gradoCompetencia) {
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

	public Tecnico getTecnico() {
		return this.tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

}