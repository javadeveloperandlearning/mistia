package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tipos database table.
 * 
 */
@Entity
@Table(name="tipos")
@NamedQuery(name="Tipo.findAll", query="SELECT t FROM Tipo t")
public class Tipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_tipo")
	private Integer codigoTipo;

	@Column(name="codigo_grupo")
	private Integer codigoGrupo;

	@Column(name= "descripcion")
	private String descripcion;

	@Transient
	private  Integer prioridad;
	
	

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modifcion")
	private String estacionModifcion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="ind_activo")
	private Integer indActivo;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;




	//bi-directional many-to-one association to TipoSolicitudCompetenciaDetalle
	@OneToMany(mappedBy="tipo")
	private List<TipoSolicitudCompetenciaDetalle> tipoSolicitudCompetenciaDetalles;

	public Tipo() {
	}

	public Integer getCodigoTipo() {
		return this.codigoTipo;
	}

	public void setCodigoTipo(Integer codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	public Integer getCodigoGrupo() {
		return this.codigoGrupo;
	}

	public void setCodigoGrupo(Integer codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
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

	public Integer getIndActivo() {
		return this.indActivo;
	}

	public void setIndActivo(Integer indActivo) {
		this.indActivo = indActivo;
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
	
	
	
	

	/*public List<SolicitudServicio> getSolicitudServicios1() {
		return this.solicitudServicios1;
	}

	public void setSolicitudServicios1(List<SolicitudServicio> solicitudServicios1) {
		this.solicitudServicios1 = solicitudServicios1;
	}

	public SolicitudServicio addSolicitudServicios1(SolicitudServicio solicitudServicios1) {
		getSolicitudServicios1().add(solicitudServicios1);
		solicitudServicios1.setTipo1(this);

		return solicitudServicios1;
	}

	public SolicitudServicio removeSolicitudServicios1(SolicitudServicio solicitudServicios1) {
		getSolicitudServicios1().remove(solicitudServicios1);
		solicitudServicios1.setTipo1(null);

		return solicitudServicios1;
	}

	public List<SolicitudServicio> getSolicitudServicios2() {
		return this.solicitudServicios2;
	}

	public void setSolicitudServicios2(List<SolicitudServicio> solicitudServicios2) {
		this.solicitudServicios2 = solicitudServicios2;
	}

	public SolicitudServicio addSolicitudServicios2(SolicitudServicio solicitudServicios2) {
		getSolicitudServicios2().add(solicitudServicios2);
		solicitudServicios2.setTipo2(this);

		return solicitudServicios2;
	}

	public SolicitudServicio removeSolicitudServicios2(SolicitudServicio solicitudServicios2) {
		getSolicitudServicios2().remove(solicitudServicios2);
		solicitudServicios2.setTipo2(null);

		return solicitudServicios2;
	}*/

	public List<TipoSolicitudCompetenciaDetalle> getTipoSolicitudCompetenciaDetalles() {
		return this.tipoSolicitudCompetenciaDetalles;
	}

	public void setTipoSolicitudCompetenciaDetalles(List<TipoSolicitudCompetenciaDetalle> tipoSolicitudCompetenciaDetalles) {
		this.tipoSolicitudCompetenciaDetalles = tipoSolicitudCompetenciaDetalles;
	}

	public TipoSolicitudCompetenciaDetalle addTipoSolicitudCompetenciaDetalle(TipoSolicitudCompetenciaDetalle tipoSolicitudCompetenciaDetalle) {
		getTipoSolicitudCompetenciaDetalles().add(tipoSolicitudCompetenciaDetalle);
		tipoSolicitudCompetenciaDetalle.setTipo(this);

		return tipoSolicitudCompetenciaDetalle;
	}

	public TipoSolicitudCompetenciaDetalle removeTipoSolicitudCompetenciaDetalle(TipoSolicitudCompetenciaDetalle tipoSolicitudCompetenciaDetalle) {
		getTipoSolicitudCompetenciaDetalles().remove(tipoSolicitudCompetenciaDetalle);
		tipoSolicitudCompetenciaDetalle.setTipo(null);

		return tipoSolicitudCompetenciaDetalle;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
	
	
	
	
	

}