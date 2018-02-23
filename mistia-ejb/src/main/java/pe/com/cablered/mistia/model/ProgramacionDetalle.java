package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;



import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the programacion_detalle database table.
 * 
 */
@Entity
@Table(name="programacion_detalle")
@NamedQuery(name="ProgramacionDetalle.findAll", query="SELECT p FROM ProgramacionDetalle p")
public class ProgramacionDetalle extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProgramacionDetallePK id;
	

	//@Column(name="numero_plan_trabajo")
	//private Long numeroPlanTrabajo;

	
	//bi-directional many-to-one association to PlanTrabajo
	@ManyToOne
	@JoinColumn(name="numero_plan_trabajo")
	private PlanTrabajo planTrabajo;

	//bi-directional many-to-one association to Programacion
	@ManyToOne
	@JoinColumn(name="numero_programacion", insertable = false, updatable= false)
	private Programacion programacion;

	public ProgramacionDetalle() {
	}

	public ProgramacionDetallePK getId() {
		return this.id;
	}
	
	public ProgramacionDetalle(long numeroProgramacion, Integer numeroSecuencial) {
		this.id =  new ProgramacionDetallePK(numeroProgramacion, numeroSecuencial);
	} 

	public void setId(ProgramacionDetallePK id) {
		this.id = id;
	}



	public Programacion getProgramacion() {
		return this.programacion;
	}

	public void setProgramacion(Programacion programacion) {
		this.programacion = programacion;
	}

	public PlanTrabajo getPlanTrabajo() {
		return planTrabajo;
	}

	public void setPlanTrabajo(PlanTrabajo planTrabajo) {
		this.planTrabajo = planTrabajo;
	}
	
	
	

}