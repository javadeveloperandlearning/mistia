package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the plan_trabajo database table.
 * 
 */
@Entity
@Table(name="plan_trabajo")
@XmlRootElement
@NamedQuery(name="PlanTrabajo.findAll", query="SELECT p FROM PlanTrabajo p")
public class PlanTrabajo extends ObjectBean implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="numero_plan_trabajo")
	private long numeroPlanTrabajo;

	@Column(name="fecha_programacion")
	private Timestamp fechaProgramacion;

	
	


	//bi-directional many-to-one association to Cuadrilla
	@ManyToOne(optional = false)
	@JoinColumn(name="numero_cuadrilla",  referencedColumnName = "numero_cuadrilla")
	private Cuadrilla cuadrilla;

	//bi-directional many-to-one association to GrupoAtencion
	@ManyToOne(optional = false)
	@JoinColumn(name="numero_grupo_atencion",referencedColumnName = "numero_grupo_atencion" )
	private GrupoAtencion grupoAtencion;

	//bi-directional many-to-one association to PlanTrabajoDetalle
	@OneToMany(mappedBy="planTrabajo" )
	private List<PlanTrabajoDetalle> planTrabajoDetalles;
	
	
	
	//bi-directional many-to-one association to ProgramacionDetalle
	@OneToMany(mappedBy="planTrabajo" , fetch =FetchType.LAZY)
	private List<ProgramacionDetalle> programacionDetalles;

	public PlanTrabajo() {
	}
	
	public PlanTrabajo(long numeroPlanTrabajo) {
		this.numeroPlanTrabajo =  numeroPlanTrabajo;
	} 

	public long getNumeroPlanTrabajo() {
		return this.numeroPlanTrabajo;
	}

	public void setNumeroPlanTrabajo(long numeroPlanTrabajo) {
		this.numeroPlanTrabajo = numeroPlanTrabajo;
	}

	
	public Timestamp getFechaProgramacion() {
		return this.fechaProgramacion;
	}

	public void setFechaProgramacion(Timestamp fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}



	public Cuadrilla getCuadrilla() {
		return this.cuadrilla;
	}

	public void setCuadrilla(Cuadrilla cuadrilla) {
		this.cuadrilla = cuadrilla;
	}

	public GrupoAtencion getGrupoAtencion() {
		return this.grupoAtencion;
	}

	public void setGrupoAtencion(GrupoAtencion grupoAtencion) {
		this.grupoAtencion = grupoAtencion;
	}

	@XmlTransient
	public List<PlanTrabajoDetalle> getPlanTrabajoDetalles() {
		/*if(this.planTrabajoDetalles==null){
			this.planTrabajoDetalles  = new ArrayList<PlanTrabajoDetalle>();
		}*/
		return this.planTrabajoDetalles;
	}

	public void setPlanTrabajoDetalles(List<PlanTrabajoDetalle> planTrabajoDetalles) {
		this.planTrabajoDetalles = planTrabajoDetalles;
	}

	public PlanTrabajoDetalle addPlanTrabajoDetalle(PlanTrabajoDetalle planTrabajoDetalle) {
		getPlanTrabajoDetalles().add(planTrabajoDetalle);
		planTrabajoDetalle.setPlanTrabajo(this);

		return planTrabajoDetalle;
	}

	public PlanTrabajoDetalle removePlanTrabajoDetalle(PlanTrabajoDetalle planTrabajoDetalle) {
		getPlanTrabajoDetalles().remove(planTrabajoDetalle);
		planTrabajoDetalle.setPlanTrabajo(null);

		return planTrabajoDetalle;
	}
	
	@XmlTransient
	public List<ProgramacionDetalle> getProgramacionDetalles() {
		return programacionDetalles;
	}

	public void setProgramacionDetalles(List<ProgramacionDetalle> programacionDetalles) {
		this.programacionDetalles = programacionDetalles;
	}
	
	
	@Override
	public Object clone(){
		Object object  = null;
		try{
			object = super.clone();
		}catch(CloneNotSupportedException e ){
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (numeroPlanTrabajo ^ (numeroPlanTrabajo >>> 32));
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
		PlanTrabajo other = (PlanTrabajo) obj;
		if (numeroPlanTrabajo != other.numeroPlanTrabajo)
			return false;
		return true;
	}
	
	
	
	
}