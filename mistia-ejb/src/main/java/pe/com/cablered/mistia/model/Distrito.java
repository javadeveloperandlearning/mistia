package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the distritos database table.
 * 
 */
@Entity
@Table(name="distritos")
@NamedQuery(name="Distrito.findAll", query="SELECT d FROM Distrito d")
public class Distrito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_distrito")
	private Integer codigoDistrito;

	private String descripcion;

	@Column(name="estacion_creacion")
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
	private String usuarioModificacion;

	//bi-directional many-to-one association to ContratoServicio
	@OneToMany(mappedBy="distrito")
	private List<ContratoServicio> contratoServicios;

	//bi-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name="codigo_provincia")
	private Provincia provincia;

	//bi-directional many-to-one association to SolicitudServicio
	@OneToMany(mappedBy="distrito")
	private List<SolicitudServicio> solicitudServicios;

	//bi-directional many-to-one association to Tecnico
	@OneToMany(mappedBy="distrito")
	private List<Tecnico> tecnicos;

	public Distrito() {
	}

	
	public Distrito(Integer codigoDistrito) {
		super();
		this.codigoDistrito = codigoDistrito;
	}



	public Distrito(Integer codigoDistrito, String descripcion) {
		super();
		this.codigoDistrito = codigoDistrito;
		this.descripcion = descripcion;
	}






	public Integer getCodigoDistrito() {
		return this.codigoDistrito;
	}

	public void setCodigoDistrito(Integer codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
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
	}

	@XmlTransient
	public List<ContratoServicio> getContratoServicios() {
		return this.contratoServicios;
	}

	public void setContratoServicios(List<ContratoServicio> contratoServicios) {
		this.contratoServicios = contratoServicios;
	}

	public ContratoServicio addContratoServicio(ContratoServicio contratoServicio) {
		getContratoServicios().add(contratoServicio);
		contratoServicio.setDistrito(this);

		return contratoServicio;
	}

	public ContratoServicio removeContratoServicio(ContratoServicio contratoServicio) {
		getContratoServicios().remove(contratoServicio);
		contratoServicio.setDistrito(null);

		return contratoServicio;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	@XmlTransient
	public List<SolicitudServicio> getSolicitudServicios() {
		return this.solicitudServicios;
	}

	public void setSolicitudServicios(List<SolicitudServicio> solicitudServicios) {
		this.solicitudServicios = solicitudServicios;
	}

	public SolicitudServicio addSolicitudServicio(SolicitudServicio solicitudServicio) {
		getSolicitudServicios().add(solicitudServicio);
		solicitudServicio.setDistrito(this);

		return solicitudServicio;
	}

	public SolicitudServicio removeSolicitudServicio(SolicitudServicio solicitudServicio) {
		getSolicitudServicios().remove(solicitudServicio);
		solicitudServicio.setDistrito(null);

		return solicitudServicio;
	}

	@XmlTransient
	public List<Tecnico> getTecnicos() {
		return this.tecnicos;
	}

	public void setTecnicos(List<Tecnico> tecnicos) {
		this.tecnicos = tecnicos;
	}

	public Tecnico addTecnico(Tecnico tecnico) {
		getTecnicos().add(tecnico);
		tecnico.setDistrito(this);

		return tecnico;
	}

	@XmlTransient
	public Tecnico removeTecnico(Tecnico tecnico) {
		getTecnicos().remove(tecnico);
		tecnico.setDistrito(null);

		return tecnico;
	}
	
	@Override
	public String toString() {
		return this.getDescripcion();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoDistrito == null) ? 0 : codigoDistrito.hashCode());
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
		Distrito other = (Distrito) obj;
		if (codigoDistrito == null) {
			if (other.codigoDistrito != null)
				return false;
		} else if (!codigoDistrito.equals(other.codigoDistrito))
			return false;
		return true;
	}
	
	
	

}