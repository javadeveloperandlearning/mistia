package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the programacion database table.
 * 
 */
@Entity
@NamedQuery(name="Programacion.findAll", query="SELECT p FROM Programacion p")
public class Programacion extends  ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="numero_programacion")
	private long numeroProgramacion;

	@Column(name="cantidad_asignada")
	private Integer cantidadAsignada;

	@Column(name="cantidad_atendidas")
	private Integer cantidadAtendidas;

	@Column(name="cantidad_candelada")
	private Integer cantidadCandelada;

	@Column(name="cantidad_cuadrillas")
	private Integer cantidadCuadrillas;

	@Column(name="cantidad_pendiente")
	private Integer cantidadPendiente;

	@Column(name="cantidad_suspendidas")
	private Integer cantidadSuspendidas;
	
	
	@Column(name="fecha_programacion")
	private Date fechaProgramacion;
	
	

	
	
	

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="codigo_estado")
	private Estado estado;

	//bi-directional many-to-one association to ProgramacionDetalle
	@OneToMany(mappedBy="programacion")
	private List<ProgramacionDetalle> programacionDetalles;

	public Programacion() {
	}
	
	

	public Programacion(long numeroProgramacion) {
		super();
		this.numeroProgramacion = numeroProgramacion;
	}



	public long getNumeroProgramacion() {
		return this.numeroProgramacion;
	}

	public void setNumeroProgramacion(long numeroProgramacion) {
		this.numeroProgramacion = numeroProgramacion;
	}

	public Integer getCantidadAsignada() {
		return this.cantidadAsignada;
	}

	public void setCantidadAsignada(Integer cantidadAsignada) {
		this.cantidadAsignada = cantidadAsignada;
	}

	public Integer getCantidadAtendidas() {
		return this.cantidadAtendidas;
	}

	public void setCantidadAtendidas(Integer cantidadAtendidas) {
		this.cantidadAtendidas = cantidadAtendidas;
	}

	public Integer getCantidadCandelada() {
		return this.cantidadCandelada;
	}

	public void setCantidadCandelada(Integer cantidadCandelada) {
		this.cantidadCandelada = cantidadCandelada;
	}

	public Integer getCantidadCuadrillas() {
		return this.cantidadCuadrillas;
	}

	public void setCantidadCuadrillas(Integer cantidadCuadrillas) {
		this.cantidadCuadrillas = cantidadCuadrillas;
	}

	public Integer getCantidadPendiente() {
		return this.cantidadPendiente;
	}

	public void setCantidadPendiente(Integer cantidadPendiente) {
		this.cantidadPendiente = cantidadPendiente;
	}

	public Integer getCantidadSuspendidas() {
		return this.cantidadSuspendidas;
	}

	public void setCantidadSuspendidas(Integer cantidadSuspendidas) {
		this.cantidadSuspendidas = cantidadSuspendidas;
	}



	public Date getFechaProgramacion() {
		return this.fechaProgramacion;
	}

	public void setFechaProgramacion(Date fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<ProgramacionDetalle> getProgramacionDetalles() {
		return this.programacionDetalles;
	}

	public void setProgramacionDetalles(List<ProgramacionDetalle> programacionDetalles) {
		this.programacionDetalles = programacionDetalles;
	}

	public ProgramacionDetalle addProgramacionDetalle(ProgramacionDetalle programacionDetalle) {
		getProgramacionDetalles().add(programacionDetalle);
		programacionDetalle.setProgramacion(this);

		return programacionDetalle;
	}

	public ProgramacionDetalle removeProgramacionDetalle(ProgramacionDetalle programacionDetalle) {
		getProgramacionDetalles().remove(programacionDetalle);
		programacionDetalle.setProgramacion(null);

		return programacionDetalle;
	}

}