package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the horario_atencion database table.
 * 
 */
@Entity
@Table(name="horario_atencion")
@NamedQuery(name="HorarioAtencion.findAll", query="SELECT h FROM HorarioAtencion h")
public class HorarioAtencion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="numero_horario")
	private Integer numeroHorario;

	private String descripcion;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modificacion")
	private String estacionModificacion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to HorarioAtencionDetalle
	@OneToMany(mappedBy="horarioAtencion", fetch=FetchType.EAGER)
	private List<HorarioAtencionDetalle> horarioAtencionDetalles;

	public HorarioAtencion() {
	}

	public Integer getNumeroHorario() {
		return this.numeroHorario;
	}

	public void setNumeroHorario(Integer numeroHorario) {
		this.numeroHorario = numeroHorario;
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

	public List<HorarioAtencionDetalle> getHorarioAtencionDetalles() {
		return this.horarioAtencionDetalles;
	}

	public void setHorarioAtencionDetalles(List<HorarioAtencionDetalle> horarioAtencionDetalles) {
		this.horarioAtencionDetalles = horarioAtencionDetalles;
	}

	public HorarioAtencionDetalle addHorarioAtencionDetalle(HorarioAtencionDetalle horarioAtencionDetalle) {
		getHorarioAtencionDetalles().add(horarioAtencionDetalle);
		horarioAtencionDetalle.setHorarioAtencion(this);

		return horarioAtencionDetalle;
	}

	public HorarioAtencionDetalle removeHorarioAtencionDetalle(HorarioAtencionDetalle horarioAtencionDetalle) {
		getHorarioAtencionDetalles().remove(horarioAtencionDetalle);
		horarioAtencionDetalle.setHorarioAtencion(null);

		return horarioAtencionDetalle;
	}

}