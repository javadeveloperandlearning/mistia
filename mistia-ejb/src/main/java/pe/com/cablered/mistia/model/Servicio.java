package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the servicios database table.
 * 
 */
@Entity
@Table(name="servicios")
@NamedQuery(name="Servicio.findAll", query="SELECT s FROM Servicio s")
public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_servicio")
	private Integer codigoServicio;

	private String descripcion;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modificacion")
	private String estacionModificacion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	@Column(name="tarifa")
	private Double tarifa;
	
	@Column(name="velo_inter_mbps")
	private Integer veloInterMbps;
	
	@Column(name="tipo_paquete_cable")
	private Integer tipoPaqueteCable;	
	
	@Column(name="cantidad_televisor")
	private Integer cantidadTelevisor;
	
	

	@Column(name="tipo_servicio")
	private Integer tipoServicio;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;
	
	
	/*@OneToMany(mappedBy="servicio")
	private List<ContratoServicio> ContratoServicios;
*/
	public Servicio() {
	}

	public Integer getCodigoServicio() {
		return this.codigoServicio;
	}

	public void setCodigoServicio(Integer codigoServicio) {
		this.codigoServicio = codigoServicio;
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



	public String getEstacionModificacion() {
		return estacionModificacion;
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

	public Double getTarifa() {
		return this.tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}

	public Integer getTipoServicio() {
		return this.tipoServicio;
	}

	public void setTipoServicio(Integer tipoServicio) {
		this.tipoServicio = tipoServicio;
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
/*
	public List<ContratoServicio> getContratoServicios() {
		return ContratoServicios;
	}

	public void setContratoServicios(List<ContratoServicio> contratoServicios) {
		ContratoServicios = contratoServicios;
	}*/

	public Integer getVeloInterMbps() {
		return veloInterMbps;
	}

	public void setVeloInterMbps(Integer veloInterMbps) {
		this.veloInterMbps = veloInterMbps;
	}

	public Integer getTipoPaqueteCable() {
		return tipoPaqueteCable;
	}

	public void setTipoPaqueteCable(Integer tipoPaqueteCable) {
		this.tipoPaqueteCable = tipoPaqueteCable;
	}

	public Integer getCantidadTelevisor() {
		return cantidadTelevisor;
	}

	public void setCantidadTelevisor(Integer cantidadTelevisor) {
		this.cantidadTelevisor = cantidadTelevisor;
	}
	
	

}