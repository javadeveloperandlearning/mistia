package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the grupo_atencion_detalle database table.
 * 
 */
@Entity
@Table(name="grupo_atencion_detalle")
@XmlRootElement
@NamedQuery(name="GrupoAtencionDetalle.findAll", query="SELECT g FROM GrupoAtencionDetalle g")
public class GrupoAtencionDetalle  extends ObjectBean implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GrupoAtencionDetallePK id;


	@Column(name="grado_prioridad")
	private BigDecimal gradoPrioridad;


	//bi-directional many-to-one association to GrupoAtencion
	@ManyToOne
	@JoinColumn(name="numero_grupo_atencion" , insertable = false, updatable= false)
	private GrupoAtencion grupoAtencion;

	//bi-directional many-to-one association to SolicitudServicio
	@ManyToOne
	@JoinColumn(name="numero_solicitud", referencedColumnName = "numero_solicitud")
	private SolicitudServicio solicitudServicio;

	public GrupoAtencionDetalle() {
		
	}
	
	
	public GrupoAtencionDetalle(GrupoAtencion grupoAtencion, long numeroSolicitud) {
		this.grupoAtencion =  grupoAtencion;
		this.solicitudServicio =   new SolicitudServicio(numeroSolicitud);
	}
	
	public GrupoAtencionDetalle(GrupoAtencion grupoAtencion,  SolicitudServicio solicitudServicio) {
		this.grupoAtencion =  grupoAtencion;
		this.solicitudServicio =  solicitudServicio;
	}
	
	
	
	public GrupoAtencionDetalle(long numeroGrupoAtencion, Integer numeroOrden,   SolicitudServicio solicitudServicio) {
		this.id =  new GrupoAtencionDetallePK(numeroGrupoAtencion, numeroOrden);
		this.solicitudServicio =  solicitudServicio;
	}
	
	public GrupoAtencionDetalle(SolicitudServicio solicitudServicio) {
		this.solicitudServicio =  solicitudServicio;
	}


	public GrupoAtencionDetallePK getId() {
		return this.id;
	}

	public void setId(GrupoAtencionDetallePK id) {
		this.id = id;
	}
	
	



	public BigDecimal getGradoPrioridad() {
		return this.gradoPrioridad;
	}

	public void setGradoPrioridad(BigDecimal gradoPrioridad) {
		this.gradoPrioridad = gradoPrioridad;
	}


	public GrupoAtencion getGrupoAtencion() {
		return this.grupoAtencion;
	}

	public void setGrupoAtencion(GrupoAtencion grupoAtencion) {
		this.grupoAtencion = grupoAtencion;
	}

	public SolicitudServicio getSolicitudServicio() {
		return this.solicitudServicio;
	}

	public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
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
		GrupoAtencionDetalle other = (GrupoAtencionDetalle) obj;
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