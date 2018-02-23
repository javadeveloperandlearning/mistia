package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the conceptos database table.
 * 
 */
@Entity
@Table(name="conceptos")
@NamedQuery(name="Concepto.findAll", query="SELECT c FROM Concepto c")
public class Concepto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_concepto")
	private Integer codigoConcepto;

	private String descripcion;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modifcion")
	private String estacionModifcion;

	@Column(name="fecha_anulacion")
	private Timestamp fechaAnulacion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_generacion")
	private Timestamp fechaGeneracion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	private BigDecimal importe;

	@Column(name="solicitud_servicio")
	private BigDecimal solicitudServicio;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to ComprobantePagoDetalle
	@OneToMany(mappedBy="concepto")
	private List<ComprobantePagoDetalle> comprobantePagoDetalles;

	public Concepto() {
	}

	public Integer getCodigoConcepto() {
		return this.codigoConcepto;
	}

	public void setCodigoConcepto(Integer codigoConcepto) {
		this.codigoConcepto = codigoConcepto;
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

	public Timestamp getFechaAnulacion() {
		return this.fechaAnulacion;
	}

	public void setFechaAnulacion(Timestamp fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaGeneracion() {
		return this.fechaGeneracion;
	}

	public void setFechaGeneracion(Timestamp fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Timestamp getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public BigDecimal getSolicitudServicio() {
		return this.solicitudServicio;
	}

	public void setSolicitudServicio(BigDecimal solicitudServicio) {
		this.solicitudServicio = solicitudServicio;
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

	public List<ComprobantePagoDetalle> getComprobantePagoDetalles() {
		return this.comprobantePagoDetalles;
	}

	public void setComprobantePagoDetalles(List<ComprobantePagoDetalle> comprobantePagoDetalles) {
		this.comprobantePagoDetalles = comprobantePagoDetalles;
	}

	public ComprobantePagoDetalle addComprobantePagoDetalle(ComprobantePagoDetalle comprobantePagoDetalle) {
		getComprobantePagoDetalles().add(comprobantePagoDetalle);
		comprobantePagoDetalle.setConcepto(this);

		return comprobantePagoDetalle;
	}

	public ComprobantePagoDetalle removeComprobantePagoDetalle(ComprobantePagoDetalle comprobantePagoDetalle) {
		getComprobantePagoDetalles().remove(comprobantePagoDetalle);
		comprobantePagoDetalle.setConcepto(null);

		return comprobantePagoDetalle;
	}

}