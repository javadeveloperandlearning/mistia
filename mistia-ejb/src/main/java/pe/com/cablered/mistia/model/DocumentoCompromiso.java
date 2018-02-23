package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the documento_compromiso database table.
 * 
 */
@Entity
@Table(name="documento_compromiso")
@NamedQuery(name="DocumentoCompromiso.findAll", query="SELECT d FROM DocumentoCompromiso d")
public class DocumentoCompromiso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="numero_compromiso")
	private long numeroCompromiso;

	private String detalle;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modifcion")
	private String estacionModifcion;

	private Timestamp fecha;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="codigo_cliente")
	private Cliente cliente;

	public DocumentoCompromiso() {
	}

	public long getNumeroCompromiso() {
		return this.numeroCompromiso;
	}

	public void setNumeroCompromiso(long numeroCompromiso) {
		this.numeroCompromiso = numeroCompromiso;
	}

	public String getDetalle() {
		return this.detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
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

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
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

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}