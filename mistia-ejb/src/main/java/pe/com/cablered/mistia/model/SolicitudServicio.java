package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import pe.com.cablered.mistia.service.Response;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the solicitud_servicio database table.
 * 
 */
@Entity
@Table(name="solicitud_servicio")
@XmlRootElement
@NamedQuery(name="SolicitudServicio.findAll", query="SELECT s FROM SolicitudServicio s")
public class SolicitudServicio extends ObjectBean  implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="numero_solicitud")
	private long numeroSolicitud;
	
	@Column(name="fecha_atencion")
	private Date fechaAtencion;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_solicitud")
	private Date fechaSolicitud;

	/*@Column(name="numero_contrato")
	private BigDecimal numeroContrato;*/
	
	@Column(name="tarifa_atencion")
	private Double tarifaAtencion;

	//bi-directional many-to-one association to GrupoAtencionDetalle
	@OneToMany(mappedBy="solicitudServicio" , cascade=CascadeType.ALL)
	private List<GrupoAtencionDetalle> grupoAtencionDetalles;

	//bi-directional many-to-one association to PlanTrabajoDetalle
	
	@OneToMany(mappedBy="solicitudServicio")
	private List<PlanTrabajoDetalle> planTrabajoDetalles;
	
	
	
	@OneToMany(mappedBy="solicitudServicio")
	private List<SolicitudServicioEstado> solicitudServicioEstados;
	
	//
	@OneToMany(mappedBy="solicitudServicio")
	private List<EncuestaSolicitudResultado> encuestaSolicitudResultados;
	

	//bi-directional many-to-one association to Distrito
	@ManyToOne
	@JoinColumn(name="codigo_distrito")
	private Distrito distrito;

	//bi-directional many-to-one association to Poste
	@ManyToOne
	@JoinColumn(name="codigo_poste")
	private Poste poste;

	//bi-directional many-to-one association to TipoSolicitud
	@ManyToOne
	@JoinColumn(name="codigo_tipo_solicitud")
	private TipoSolicitud tipoSolicitud;

	
	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="codigo_estado")
	private Estado estado;
	
	
	@ManyToOne
	@JoinColumn(name="numero_contrato")
	private ContratoServicio contratoServicio;
	
	
	@Transient
	private List<SolicitudServicioHorarioAtencion> solicitudServicioHorarioAtencionList;
	
	
	


	public SolicitudServicio() {
	}
	
	
	public SolicitudServicio(long numeroSolicitud) {
		this.numeroSolicitud  = numeroSolicitud;
	}
	

	
	public SolicitudServicio(long numeroSolicitud, Date fechaAtencion, Date fechaSolicitud, Poste poste,  TipoSolicitud tipoSolicitud, ContratoServicio contratoServicio ) {
		
		this.numeroSolicitud =  numeroSolicitud; 
		this.fechaAtencion =  fechaAtencion;
		this.fechaSolicitud =  fechaSolicitud;
		this.poste =  poste;
		this.tipoSolicitud =  tipoSolicitud;
		this.contratoServicio =  contratoServicio;
	
	} 
	
	
	public SolicitudServicio(long numeroSolicitud,  Poste poste) {
		
		this.numeroSolicitud =  numeroSolicitud;
		this.poste =  poste;
		
	}

	public long getNumeroSolicitud() {
		return this.numeroSolicitud;
	}

	public void setNumeroSolicitud(long numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}



	public Date getFechaAtencion() {
		return this.fechaAtencion;
	}

	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}


	public Date getFechaSolicitud() {
		return this.fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/*
	public BigDecimal getNumeroContrato() {
		return this.numeroContrato;
	}

	public void setNumeroContrato(BigDecimal numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
*/
	public Double getTarifaAtencion() {
		return this.tarifaAtencion;
	}

	public void setTarifaAtencion(Double tarifaAtencion) {
		this.tarifaAtencion = tarifaAtencion;
	}


	
	public TipoSolicitud getTipoSolicitud() {
		return tipoSolicitud;
	}



	public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}



	@XmlTransient
	public List<GrupoAtencionDetalle> getGrupoAtencionDetalles() {
		return this.grupoAtencionDetalles;
	}

	public void setGrupoAtencionDetalles(List<GrupoAtencionDetalle> grupoAtencionDetalles) {
		this.grupoAtencionDetalles = grupoAtencionDetalles;
	}

	public GrupoAtencionDetalle addGrupoAtencionDetalle(GrupoAtencionDetalle grupoAtencionDetalle) {
		getGrupoAtencionDetalles().add(grupoAtencionDetalle);
		grupoAtencionDetalle.setSolicitudServicio(this);

		return grupoAtencionDetalle;
	}

	public GrupoAtencionDetalle removeGrupoAtencionDetalle(GrupoAtencionDetalle grupoAtencionDetalle) {
		getGrupoAtencionDetalles().remove(grupoAtencionDetalle);
		grupoAtencionDetalle.setSolicitudServicio(null);

		return grupoAtencionDetalle;
	}

	@XmlTransient
	public List<PlanTrabajoDetalle> getPlanTrabajoDetalles() {
		return this.planTrabajoDetalles;
	}

	public void setPlanTrabajoDetalles(List<PlanTrabajoDetalle> planTrabajoDetalles) {
		this.planTrabajoDetalles = planTrabajoDetalles;
	}

	public PlanTrabajoDetalle addPlanTrabajoDetalle(PlanTrabajoDetalle planTrabajoDetalle) {
		getPlanTrabajoDetalles().add(planTrabajoDetalle);
		planTrabajoDetalle.setSolicitudServicio(this);

		return planTrabajoDetalle;
	}

	public PlanTrabajoDetalle removePlanTrabajoDetalle(PlanTrabajoDetalle planTrabajoDetalle) {
		getPlanTrabajoDetalles().remove(planTrabajoDetalle);
		planTrabajoDetalle.setSolicitudServicio(null);

		return planTrabajoDetalle;
	}

	
	
	
	
	public List<SolicitudServicioEstado> getSolicitudServicioEstados() {
		return solicitudServicioEstados;
	}


	public void setSolicitudServicioEstados(List<SolicitudServicioEstado> solicitudServicioEstados) {
		this.solicitudServicioEstados = solicitudServicioEstados;
	}


	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Poste getPoste() {
		return this.poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}



	public ContratoServicio getContratoServicio() {
		return contratoServicio;
	}


	public void setContratoServicio(ContratoServicio contratoServicio) {
		this.contratoServicio = contratoServicio;
	}

	
	



	public List<EncuestaSolicitudResultado> getEncuestaSolicitudResultados() {
		return encuestaSolicitudResultados;
	}


	public void setEncuestaSolicitudResultados(List<EncuestaSolicitudResultado> encuestaSolicitudResultados) {
		this.encuestaSolicitudResultados = encuestaSolicitudResultados;
	}

	
	
	
	public List<SolicitudServicioHorarioAtencion> getSolicitudServicioHorarioAtencionList() {
		return solicitudServicioHorarioAtencionList;
	}


	public void setSolicitudServicioHorarioAtencionList(
			List<SolicitudServicioHorarioAtencion> solicitudServicioHorarioAtencionList) {
		this.solicitudServicioHorarioAtencionList = solicitudServicioHorarioAtencionList;
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
		result = prime * result + (int) (numeroSolicitud ^ (numeroSolicitud >>> 32));
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
		SolicitudServicio other = (SolicitudServicio) obj;
		if (numeroSolicitud != other.numeroSolicitud)
			return false;
		return true;
	}






}