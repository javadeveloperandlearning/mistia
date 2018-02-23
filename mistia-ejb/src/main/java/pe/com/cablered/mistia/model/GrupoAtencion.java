package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the grupo_atencion database table.
 * 
 */
@Entity
@Table(name="grupo_atencion")
@XmlRootElement
@NamedQuery(name="GrupoAtencion.findAll", query="SELECT g FROM GrupoAtencion g")
public class GrupoAtencion extends ObjectBean  implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="numero_grupo_atencion")
	private long numeroGrupoAtencion;

	private String descripcion;
	
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_programacion")
	private Date fechaProgramacion;

	
	@Column(name="latitud_central")
	private Double latitudCentral;
	
	@Column(name="longitud_central")
	private Double longitudCentral;
	
	
	@Column(name="color")
	private String color;
	
	@Column(name="radio")
	private Double radio;
	
	@Column(name="area")
	private Double area;
	
	

	//bi-directional many-to-one association to GrupoAtencionDetalle
	@OneToMany(mappedBy="grupoAtencion")
	private List<GrupoAtencionDetalle> grupoAtencionDetalles;
	
	
	//bi-directional many-to-one association to PlanTrabajo
	@OneToMany(mappedBy="grupoAtencion")
	private List<PlanTrabajo> planTrabajos;
	
	

	public GrupoAtencion() {
	}
	
	
	public GrupoAtencion(long numeroGrupoAtencion, String descripcion) {
		
		this.numeroGrupoAtencion =  numeroGrupoAtencion;
		this.descripcion =  descripcion;
				
	}

	

	public long getNumeroGrupoAtencion() {
		return this.numeroGrupoAtencion;
	}

	public void setNumeroGrupoAtencion(long numeroGrupoAtencion) {
		this.numeroGrupoAtencion = numeroGrupoAtencion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

	public Date getFechaProgramacion() {
		return this.fechaProgramacion;
	}

	public void setFechaProgramacion(Date fechaProgramacion) {
		this.fechaProgramacion = fechaProgramacion;
	}

	
	@XmlTransient
	public List<GrupoAtencionDetalle> getGrupoAtencionDetalles() {
		return this.grupoAtencionDetalles;
	}

	public void setGrupoAtencionDetalles(List<GrupoAtencionDetalle> grupoAtencionDetalles) {
		this.grupoAtencionDetalles = grupoAtencionDetalles;
	}
	
	


	public Double getLatitudCentral() {
		return latitudCentral;
	}


	public void setLatitudCentral(Double latitudCentral) {
		this.latitudCentral = latitudCentral;
	}


	public Double getLongitudCentral() {
		return longitudCentral;
	}


	public void setLongitudCentral(Double longitudCentral) {
		this.longitudCentral = longitudCentral;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}

	

	public Double getRadio() {
		return radio;
	}


	public void setRadio(Double radio) {
		this.radio = radio;
	}

	
	

	public Double getArea() {
		return area;
	}


	public void setArea(Double area) {
		this.area = area;
	}


	public GrupoAtencionDetalle addGrupoAtencionDetalle(GrupoAtencionDetalle grupoAtencionDetalle) {
		getGrupoAtencionDetalles().add(grupoAtencionDetalle);
		grupoAtencionDetalle.setGrupoAtencion(this);

		return grupoAtencionDetalle;
	}

	public GrupoAtencionDetalle removeGrupoAtencionDetalle(GrupoAtencionDetalle grupoAtencionDetalle) {
		getGrupoAtencionDetalles().remove(grupoAtencionDetalle);
		grupoAtencionDetalle.setGrupoAtencion(null);

		return grupoAtencionDetalle;
	}
	
	@XmlTransient
	public List<PlanTrabajo> getPlanTrabajos() {
		return this.planTrabajos;
	}

	public void setPlanTrabajos(List<PlanTrabajo> planTrabajos) {
		this.planTrabajos = planTrabajos;
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
		result = prime * result + (int) (numeroGrupoAtencion ^ (numeroGrupoAtencion >>> 32));
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
		GrupoAtencion other = (GrupoAtencion) obj;
		if (numeroGrupoAtencion != other.numeroGrupoAtencion)
			return false;
		return true;
	}
	
	
	
	
	
	

}