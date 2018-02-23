package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the contrato_servicio_detalle database table.
 * 
 */
@Entity
@Table(name="contrato_servicio_detalle")
@NamedQuery(name="ContratoServicioDetalle.findAll", query="SELECT c FROM ContratoServicioDetalle c")
public class ContratoServicioDetalle  extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ContratoServicioDetallePK id;

	private Integer cantidad;

	//bi-directional one-to-one association to ContratoServicio
	@OneToOne
	@JoinColumn(name="numero_contrato", insertable = false, updatable = false)
	private ContratoServicio contratoServicio;

	public ContratoServicioDetalle() {
	}

	public ContratoServicioDetallePK getId() {
		return this.id;
	}

	public void setId(ContratoServicioDetallePK id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public ContratoServicio getContratoServicio() {
		return this.contratoServicio;
	}

	public void setContratoServicio(ContratoServicio contratoServicio) {
		this.contratoServicio = contratoServicio;
	}

}