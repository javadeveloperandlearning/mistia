package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the horario_atencion_detalle database table.
 * 
 */
@Entity
@Table(name="horario_atencion_detalle")
@NamedQuery(name="HorarioAtencionDetalle.findAll", query="SELECT h FROM HorarioAtencionDetalle h")
public class HorarioAtencionDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HorarioAtencionDetallePK id;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modificacion")
	private String estacionModificacion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="hora_fin")
	private String horaFin;

	@Column(name="hora_inicio")
	private String horaInicio;

	private String rango;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to HorarioAtencion
	@ManyToOne
	@JoinColumn(name="numero_horario", insertable =  false, updatable =  false)
	private HorarioAtencion horarioAtencion;

	public HorarioAtencionDetalle() {
	}

	public HorarioAtencionDetallePK getId() {
		return this.id;
	}

	public void setId(HorarioAtencionDetallePK id) {
		this.id = id;
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

	public String getRango() {
		return this.rango;
	}

	public void setRango(String rango) {
		this.rango = rango;
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

	public HorarioAtencion getHorarioAtencion() {
		return this.horarioAtencion;
	}

	public void setHorarioAtencion(HorarioAtencion horarioAtencion) {
		this.horarioAtencion = horarioAtencion;
	}

}