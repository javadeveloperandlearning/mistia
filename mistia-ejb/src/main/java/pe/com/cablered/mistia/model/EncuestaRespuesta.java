package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the encuesta_respuesta database table.
 * 
 */
@Entity
@Table(name="encuesta_respuesta")
@NamedQuery(name="EncuestaRespuesta.findAll", query="SELECT e FROM EncuestaRespuesta e")
public class EncuestaRespuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EncuestaRespuestaPK id;

	private String descripcion;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modificacion")
	private String estacionModificacion;

	private Double  factor;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	private Double peso;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;
	
	
	
	/*@ManyToOne
	@JoinColumn(name="numero_encuesta", insertable = false, updatable= false)
	private Encuesta encuesta;*/
	

	//bi-directional many-to-one association to EncuestaSolicitudResultado
	@OneToMany(mappedBy="encuestaRespuesta", fetch=FetchType.EAGER)
	private List<EncuestaSolicitudResultado> encuestaSolicitudResultados;

	public EncuestaRespuesta() {
	}

	public EncuestaRespuestaPK getId() {
		return this.id;
	}

	public void setId(EncuestaRespuestaPK id) {
		this.id = id;
	}

	
	
	/*public Encuesta getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}*/

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

	public String getEstacionModificacion() {
		return this.estacionModificacion;
	}

	public void setEstacionModificacion(String estacionModificacion) {
		this.estacionModificacion = estacionModificacion;
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



	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
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

	public List<EncuestaSolicitudResultado> getEncuestaSolicitudResultados() {
		return this.encuestaSolicitudResultados;
	}

	public void setEncuestaSolicitudResultados(List<EncuestaSolicitudResultado> encuestaSolicitudResultados) {
		this.encuestaSolicitudResultados = encuestaSolicitudResultados;
	}

	public EncuestaSolicitudResultado addEncuestaSolicitudResultado(EncuestaSolicitudResultado encuestaSolicitudResultado) {
		getEncuestaSolicitudResultados().add(encuestaSolicitudResultado);
		encuestaSolicitudResultado.setEncuestaRespuesta(this);

		return encuestaSolicitudResultado;
	}

	public EncuestaSolicitudResultado removeEncuestaSolicitudResultado(EncuestaSolicitudResultado encuestaSolicitudResultado) {
		getEncuestaSolicitudResultados().remove(encuestaSolicitudResultado);
		encuestaSolicitudResultado.setEncuestaRespuesta(null);

		return encuestaSolicitudResultado;
	}

}