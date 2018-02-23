package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the plan_trabajo_detalle database table.
 * 
 */
@Entity
@Table(name="plan_trabajo_detalle")
@XmlRootElement
public class PlanTrabajoDetalle extends ObjectBean implements Serializable, Cloneable {
	
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PlanTrabajoDetallePK id;

	@Column(name="cod_motivo")
	private Integer codMotivo;
	
	@Column(name="hora_fin")
	private Date horaFin;

	@Column(name="hora_inicio")
	private Date horaInicio;

	private String observacion;
	
	@Column(name="grado_prioridad")
	private BigDecimal gradoPrioridad;
	
	
	@Column(name="ind_atnd")
	private Integer indAtnd;
	
	//codigo_estado
	@Column(name="codigo_estado")
	private Integer codigoEstado;
	
	@Transient
	private long numeroPlanTrabajo;
	
	@Transient
	private Integer numeroSecuencial;
	
	//bi-directional many-to-one association to PlanTrabajo
	@ManyToOne
	@JoinColumn(name="numero_plan_trabajo" , insertable = false, updatable= false)
	private PlanTrabajo planTrabajo;

	//bi-directional many-to-one association to SolicitudServicio
	@ManyToOne
	@JoinColumn(name="numero_solicitud")
	private SolicitudServicio solicitudServicio;

	public PlanTrabajoDetalle() {
	}
	
	public PlanTrabajoDetalle(SolicitudServicio solicitudServicio){
		this.solicitudServicio = solicitudServicio;
		
	}
	
	
	public PlanTrabajoDetalle( long numeroPlanTrabajo,Integer numeroSecuencial ){
		this.id =  new PlanTrabajoDetallePK(numeroPlanTrabajo, numeroSecuencial);
	}

	
	
	
	public PlanTrabajoDetalle( long numeroPlanTrabajo,Integer numeroSecuencial ,  SolicitudServicio solicitudServicio){
		this.id =  new PlanTrabajoDetallePK(numeroPlanTrabajo, numeroSecuencial);
		this.solicitudServicio = solicitudServicio;
	}
	
	public long getNumeroPlanTrabajo() {
		return numeroPlanTrabajo;
	}

	public void setNumeroPlanTrabajo(long numeroPlanTrabajo) {
		this.numeroPlanTrabajo = numeroPlanTrabajo;
	}

	public Integer getNumeroSecuencial() {
		return numeroSecuencial;
	}

	public void setNumeroSecuencial(Integer numeroSecuencial) {
		this.numeroSecuencial = numeroSecuencial;
	}

	public PlanTrabajoDetallePK getId() {
		return this.id;
	}

	public void setId(PlanTrabajoDetallePK id) {
		this.id = id;
	}
	
	

	public Integer getCodMotivo() {
		return this.codMotivo;
	}

	public void setCodMotivo(Integer codMotivo) {
		this.codMotivo = codMotivo;
	}



	public BigDecimal getGradoPrioridad() {
		return this.gradoPrioridad;
	}

	public void setGradoPrioridad(BigDecimal gradoPrioridad) {
		this.gradoPrioridad = gradoPrioridad;
	}

	public Date getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}



	public PlanTrabajo getPlanTrabajo() {
		return this.planTrabajo;
	}

	public void setPlanTrabajo(PlanTrabajo planTrabajo) {
		this.planTrabajo = planTrabajo;
	}

	public SolicitudServicio getSolicitudServicio() {
		return this.solicitudServicio;
	}

	public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
	}
	
	
	
	public Integer getIndAtnd() {
		return indAtnd;
	}

	public void setIndAtnd(Integer indAtnd) {
		this.indAtnd = indAtnd;
	}

	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}
	
	
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PlanTrabajoDetalle other = (PlanTrabajoDetalle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

}