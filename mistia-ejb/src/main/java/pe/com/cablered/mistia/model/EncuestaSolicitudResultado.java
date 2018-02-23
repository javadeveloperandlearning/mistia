package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the encuesta_solicitud_resultado database table.
 * 
 */
@Entity
@Table(name="encuesta_solicitud_resultado")
@NamedQuery(name="EncuestaSolicitudResultado.findAll", query="SELECT e FROM EncuestaSolicitudResultado e")
public class EncuestaSolicitudResultado extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EncuestaSolicitudResultadoPK id;

	private Double factor;

	private Double peso;

	private Double puntaje;


	//bi-directional many-to-one association to EncuestaPregunta
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="numero_encuesta", referencedColumnName="numero_encuesta",  insertable=false, updatable=false),
		@JoinColumn(name="numero_pregunta", referencedColumnName="numero_pregunta",  insertable=false, updatable=false)
		})
	private EncuestaPregunta encuestaPregunta;

	//bi-directional many-to-one association to EncuestaRespuesta
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="numero_encuesta", referencedColumnName="numero_encuesta",  insertable=false, updatable=false),
		@JoinColumn(name="numero_respuesta", referencedColumnName="numero_respuesta",  insertable=false, updatable=false)
		})
	private EncuestaRespuesta encuestaRespuesta;

	//bi-directional many-to-one association to SolicitudServicio
	@ManyToOne
	@JoinColumn(name="numero_solicitud",  insertable=false, updatable=false)
	private SolicitudServicio solicitudServicio;

	public EncuestaSolicitudResultado() {
		
		
	}

	public EncuestaSolicitudResultado( Integer numeroEncuesta,Long numeroSolicitud,Integer numeroPregunta, Integer numeroRespuesta) {
		this.id =  new EncuestaSolicitudResultadoPK(numeroEncuesta,numeroSolicitud,numeroPregunta, numeroRespuesta  );
		
	}
	
	

	public EncuestaSolicitudResultadoPK getId() {
		return this.id;
	}

	public void setId(EncuestaSolicitudResultadoPK id) {
		this.id = id;
	}

	public Double getFactor() {
		return this.factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getPuntaje() {
		return this.puntaje;
	}

	public void setPuntaje(Double puntaje) {
		this.puntaje = puntaje;
	}



	public EncuestaPregunta getEncuestaPregunta() {
		return this.encuestaPregunta;
	}

	public void setEncuestaPregunta(EncuestaPregunta encuestaPregunta) {
		this.encuestaPregunta = encuestaPregunta;
	}

	public EncuestaRespuesta getEncuestaRespuesta() {
		return this.encuestaRespuesta;
	}

	public void setEncuestaRespuesta(EncuestaRespuesta encuestaRespuesta) {
		this.encuestaRespuesta = encuestaRespuesta;
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
		EncuestaSolicitudResultado other = (EncuestaSolicitudResultado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}