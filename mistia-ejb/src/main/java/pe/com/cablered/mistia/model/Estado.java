package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the estados database table.
 * 
 */
@Entity
@Table(name="estados")
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_estado")
	private Integer codigoEstado;

	@Column(name="codigo_grupo")
	private Integer codigoGrupo;

	private String descripcion;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modifcion")
	private String estacionModifcion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="ind_activo")
	private Integer indActivo;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to Programacion
	@OneToMany(mappedBy="estado")
	private List<Programacion> programacions;
	
	
	@OneToMany(mappedBy="estado")
	private List<SolicitudServicio> solicitudes;
	
	
	@OneToMany(mappedBy="estado")
	private List<SolicitudServicioEstado> SolicitudServicioEstados;


	public Estado() {
	}
	
	public Estado(Integer codigoEstado) {
		 this.codigoEstado =  codigoEstado;
		 
	}

	
	public Estado(Integer codigoEstado, String descripcion) {
		 this.codigoEstado =  codigoEstado;
		 this.descripcion =  descripcion;
	}

	public Integer getCodigoEstado() {
		return this.codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public Integer getCodigoGrupo() {
		return this.codigoGrupo;
	}

	public void setCodigoGrupo(Integer codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
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

	public Integer getIndActivo() {
		return this.indActivo;
	}

	public void setIndActivo(Integer indActivo) {
		this.indActivo = indActivo;
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
	public List<Programacion> getProgramacions() {
		return this.programacions;
	}

	public void setProgramacions(List<Programacion> programacions) {
		this.programacions = programacions;
	}

	@XmlTransient
	public List<SolicitudServicio> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<SolicitudServicio> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Programacion addProgramacion(Programacion programacion) {
		getProgramacions().add(programacion);
		programacion.setEstado(this);

		return programacion;
	}

	public Programacion removeProgramacion(Programacion programacion) {
		getProgramacions().remove(programacion);
		programacion.setEstado(null);

		return programacion;
	}

	public List<SolicitudServicioEstado> getSolicitudServicioEstados() {
		return SolicitudServicioEstados;
	}

	public void setSolicitudServicioEstados(List<SolicitudServicioEstado> solicitudServicioEstados) {
		SolicitudServicioEstados = solicitudServicioEstados;
	}
	
	

}