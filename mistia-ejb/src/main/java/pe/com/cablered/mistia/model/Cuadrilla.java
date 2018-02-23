package pe.com.cablered.mistia.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the cuadrillas database table.
 *
 * 
 //@XmlRootElement
 */


@Entity
@Table(name="cuadrillas")
@NamedQuery(name="Cuadrilla.findAll", query="SELECT c FROM Cuadrilla c")
@XmlRootElement
public class Cuadrilla extends ObjectBean implements Serializable {
	

	@Id
	@Column(name="numero_cuadrilla")
	private Long numeroCuadrilla;
	
	@Column(name="nombre")
	private String nombre;


	@Column(name="grado_asignacion")
	private BigDecimal gradoAsignacion;
	
	@Column(name="fecha_programacion")
	private Timestamp fechaProgramacion;
	
	

	//bi-directional many-to-one association to Vehiculo
	@ManyToOne(optional=false)
	@JoinColumn(name="placa_vehiculo", referencedColumnName = "placa_vehiculo")
	private Vehiculo vehiculo;

	//bi-directional many-to-one association to CuadrillasDetalle
	@OneToMany(mappedBy="cuadrilla", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<CuadrillasDetalle> cuadrillasDetalles;
	
	
	//bi-directional many-to-one association to PlanTrabajo
	@OneToMany( mappedBy="cuadrilla")
	private List<PlanTrabajo> planTrabajos;


	public Cuadrilla() {
	}

	public Cuadrilla(Long numeroCuadrilla) {
		this.numeroCuadrilla =  numeroCuadrilla;
	}
	
	public Long getNumeroCuadrilla() {
		return this.numeroCuadrilla;
	}

	public void setNumeroCuadrilla(Long numeroCuadrilla) {
		this.numeroCuadrilla = numeroCuadrilla;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public Timestamp getFechaProgramacion() {
		return this.fechaProgramacion;
	}

	public void setFechaProgramacion(Timestamp fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}

	public BigDecimal getGradoAsignacion() {
		return this.gradoAsignacion;
	}

	public void setGradoAsignacion(BigDecimal gradoAsignacion) {
		this.gradoAsignacion = gradoAsignacion;
	}



	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	@XmlTransient
	public List<CuadrillasDetalle> getCuadrillasDetalles() {
		return this.cuadrillasDetalles;
	}

	public void setCuadrillasDetalles(List<CuadrillasDetalle> cuadrillasDetalles) {
		this.cuadrillasDetalles = cuadrillasDetalles;
	}

	public CuadrillasDetalle addCuadrillasDetalle(CuadrillasDetalle cuadrillasDetalle) {
		getCuadrillasDetalles().add(cuadrillasDetalle);
		cuadrillasDetalle.setCuadrilla(this);

		return cuadrillasDetalle;
	}

	public CuadrillasDetalle removeCuadrillasDetalle(CuadrillasDetalle cuadrillasDetalle) {
		getCuadrillasDetalles().remove(cuadrillasDetalle);
		cuadrillasDetalle.setCuadrilla(null);
		return cuadrillasDetalle;
	}

	public void removeCuadrillasDetalle( Integer numeroSecuencia) {
		getCuadrillasDetalles().remove( new CuadrillasDetalle(this.getNumeroCuadrilla(), numeroSecuencia));
	}
	
	
	@XmlTransient
	public List<PlanTrabajo> getPlanTrabajos() {
		return this.planTrabajos;
	}

	public void setPlanTrabajos(List<PlanTrabajo> planTrabajos) {
		this.planTrabajos = planTrabajos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (numeroCuadrilla ^ (numeroCuadrilla >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuadrilla other = (Cuadrilla) obj;
		if (numeroCuadrilla != other.numeroCuadrilla)
			return false;
		return true;
	}



}