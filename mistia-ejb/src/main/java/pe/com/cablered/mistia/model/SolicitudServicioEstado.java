package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the solicitud_servicio_estado database table.
 * 
 */
@Entity
@Table(name="solicitud_servicio_estado")
@NamedQuery(name="SolicitudServicioEstado.findAll", query="SELECT s FROM SolicitudServicioEstado s")
public class SolicitudServicioEstado extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SolicitudServicioEstadoPK id;

	@Column(name="fecha_hora")
	private Date fechaHora;

	//bi-directional many-to-one association to SolicitudServicio
	@ManyToOne
	@JoinColumn(name="numero_solicitud", updatable=false, insertable=false)
	private SolicitudServicio solicitudServicio;
	
	
	@ManyToOne
	@JoinColumn(name="codigo_estado")
	private Estado estado;
	

	public SolicitudServicioEstado() {
	}
	
	public SolicitudServicioEstado(long numeroSolicitud,Integer numeroSecuencial) {
		this.id =  new SolicitudServicioEstadoPK(numeroSolicitud, numeroSecuencial);
	} 

	public SolicitudServicioEstadoPK getId() {
		return this.id;
	}

	public void setId(SolicitudServicioEstadoPK id) {
		this.id = id;
	}

	public Date getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}


	public SolicitudServicio getSolicitudServicio() {
		return this.solicitudServicio;
	}

	public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	

}