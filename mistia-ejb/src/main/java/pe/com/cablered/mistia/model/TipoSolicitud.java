package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tipo_solicitud database table.
 * 
 */
@Entity
@Table(name="tipo_solicitud")
@NamedQuery(name="TipoSolicitud.findAll", query="SELECT t FROM TipoSolicitud t")
public class TipoSolicitud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="codigo_tipo_solicitud")
	private Integer codigoTipoSolicitud;

	private String descripcion;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modificacion")
	private String estacionModificacion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="ind_activo")
	private Integer indActivo;

	private Integer prioridad;

	@Column(name="tiempo_ejecucion")
	private Integer tiempoEjecucion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;
	
	
	@Column(name="abreviatura")
	private String abreviatura;
	
	@Column(name="tarifa")
	private Double tarifa;
	

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	//bi-directional many-to-one association to SolicitudServicio
	@OneToMany(mappedBy="tipoSolicitud")
	private List<SolicitudServicio> solicitudServicios;

	public TipoSolicitud() {
	}
	
	
	

	public TipoSolicitud(Integer codigoTipoSolicitud) {
		super();
		this.codigoTipoSolicitud = codigoTipoSolicitud;
	}

	public Integer getCodigoTipoSolicitud() {
		return this.codigoTipoSolicitud;
	}

	public void setCodigoTipoSolicitud(Integer codigoTipoSolicitud) {
		this.codigoTipoSolicitud = codigoTipoSolicitud;
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

	public String getEstacionModificacion() {
		return this.estacionModificacion;
	}

	public void setEstacionModificacion(String estacionModificacion) {
		this.estacionModificacion = estacionModificacion;
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

	public Integer getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

	public Integer getTiempoEjecucion() {
		return this.tiempoEjecucion;
	}

	public void setTiempoEjecucion(Integer tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
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

	@XmlTransient
	public List<SolicitudServicio> getSolicitudServicios() {
		return this.solicitudServicios;
	}

	public void setSolicitudServicios(List<SolicitudServicio> solicitudServicios) {
		this.solicitudServicios = solicitudServicios;
	}

	public Double getTarifa() {
		return tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}



}