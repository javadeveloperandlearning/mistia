package pe.com.cablered.mistia.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the cuadrillas_detalle database table.
 * 
 */
@Entity
@Table(name="cuadrillas_detalle")
@NamedQuery(name="CuadrillasDetalle.findAll", query="SELECT c FROM CuadrillasDetalle c")
public class CuadrillasDetalle extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CuadrillasDetallePK id;
	
	@Column(name="grado_asignacion")
	private BigDecimal gradoAsignacion;

	/*@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modifcion")
	private String estacionModifcion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;*/

	//bi-directional many-to-one association to Cuadrilla
	@ManyToOne(optional =false)
	@JoinColumn(name="numero_cuadrilla",  referencedColumnName = "numero_cuadrilla", insertable =false, updatable=false)
	private Cuadrilla cuadrilla;

	//bi-directional many-to-one association to Tecnico
	@ManyToOne
	@JoinColumn(name="codigo_tecnico")
	private Tecnico tecnico;

	public CuadrillasDetalle() {
	
	}
	
	public CuadrillasDetalle(  Long numeroCuadrilla, Integer numeroSecuencia ) {
	
		this.id =  new CuadrillasDetallePK(numeroCuadrilla,numeroSecuencia);
	}
	
	

	public CuadrillasDetallePK getId() {
		return this.id;
	}

	public void setId(CuadrillasDetallePK id) {
		this.id = id;
	}

	public BigDecimal getGradoAsignacion() {
		return this.gradoAsignacion;
	}

	public void setGradoAsignacion(BigDecimal gradoAsignacion) {
		this.gradoAsignacion = gradoAsignacion;
	}
	
	/*public String getEstacionCreacion() {
		return this.estacionCreacion;
	}

	public void setEstacionCreacion(String estacionCreacion) {
		this.estacionCreacion = estacionCreacion;
	}

	public String getEstacionModifcion() {
		return this.estacionModifcion;
	}

	public void setEstacionModifcion(String estacionModifcion) {
		this.estacionModifcion = estacionModifcion;
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
	}*/

	public Cuadrilla getCuadrilla() {
		return this.cuadrilla;
	}

	public void setCuadrilla(Cuadrilla cuadrilla) {
		this.cuadrilla = cuadrilla;
	}

	public Tecnico getTecnico() {
		return this.tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
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
		CuadrillasDetalle other = (CuadrillasDetalle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}