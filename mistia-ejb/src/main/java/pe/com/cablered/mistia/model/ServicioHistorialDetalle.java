package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the servicio_historial_detalle database table.
 * 
 */
@Entity
@Table(name="servicio_historial_detalle")
@NamedQuery(name="ServicioHistorialDetalle.findAll", query="SELECT s FROM ServicioHistorialDetalle s")
public class ServicioHistorialDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="numero_solicitud")
	private Integer numeroSolicitud;

	@Column(name="codigo_estado")
	private Integer codigoEstado;

	@Column(name="codigo_motivo")
	private Integer codigoMotivo;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modifcion")
	private String estacionModifcion;

	private Timestamp fecha;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	public ServicioHistorialDetalle() {
	}

	public Integer getNumeroSolicitud() {
		return this.numeroSolicitud;
	}

	public void setNumeroSolicitud(Integer numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}

	public Integer getCodigoEstado() {
		return this.codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public Integer getCodigoMotivo() {
		return this.codigoMotivo;
	}

	public void setCodigoMotivo(Integer codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
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

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
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

}