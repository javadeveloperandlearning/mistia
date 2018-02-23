package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the solicitud_servicio_horario_atencion database table.
 * 
 */
@Entity
@Table(name="solicitud_servicio_horario_atencion")
@NamedQuery(name="SolicitudServicioHorarioAtencion.findAll", query="SELECT s FROM SolicitudServicioHorarioAtencion s")
public class SolicitudServicioHorarioAtencion extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SolicitudServicioHorarioAtencionPK id;

	@Column(name="hora_fin")
	private String horaFin;

	@Column(name="hora_inicio")
	private String horaInicio;

	@Column(name="numero_dia")
	private Integer numeroDia;
	


	//bi-directional many-to-one association to SolicitudServicio
	@ManyToOne
	@JoinColumn(name="numero_solicitud", insertable = false, updatable = false)
	private SolicitudServicio solicitudServicio;

	public SolicitudServicioHorarioAtencion() {
	}

	
	
	
	
	
	public SolicitudServicioHorarioAtencion( Integer numeroDia , String horaInicio , String horaFin) {
		super();
		this.numeroDia = numeroDia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		
		
	}






	public SolicitudServicioHorarioAtencionPK getId() {
		return this.id;
	}

	public void setId(SolicitudServicioHorarioAtencionPK id) {
		this.id = id;
	}

	public void setId(Long numeroSolicitud, Integer numeroSecuencial) {
		this.id =  new SolicitudServicioHorarioAtencionPK(numeroSolicitud,   numeroSecuencial);
		
	}
	

	public String getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Integer getNumeroDia() {
		return this.numeroDia;
	}

	public void setNumeroDia(Integer numeroDia) {
		this.numeroDia = numeroDia;
	}



	public SolicitudServicio getSolicitudServicio() {
		return this.solicitudServicio;
	}

	public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
	}

}