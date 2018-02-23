package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the contrato_servicio database table.
 * 
 */
@Entity
@Table(name="contrato_servicio")
@NamedQuery(name="ContratoServicio.findAll", query="SELECT c FROM ContratoServicio c")
public class ContratoServicio extends  ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="numero_contrato")
	private long numeroContrato;


	/*@ManyToOne
	@JoinColumn(name="codigo_servicio")
	private Servicio servicio;*/
	

	private String direccion;


	@Column(name="fecha_fin")
	private Date fechaFin;

	@Column(name="fecha_inicio")
	private Date fechaInicio;

	


	@Column(name="tarifa")
	private Double tarifa;



	//bi-directional many-to-one association to Distrito
	@ManyToOne
	@JoinColumn(name="codigo_distrito")
	private Distrito distrito;

	//bi-directional many-to-one association to Poste
	@ManyToOne
	@JoinColumn(name="codigo_poste")
	private Poste poste;
	
	
	@ManyToOne
	@JoinColumn(name="codigo_cliente")
	private Cliente cliente ;
	

	@Column(name =  "codigo_tipo_domicilio")
	private  Integer codigoTipoDomicilio;
	
	@Column(name="nro_domicilio")
	private String nroDomicilio;
	
	@Column(name="dpto_int_domicilio")
	private String dptoIntDomicilio;
	
	
	@Column(name="referencia")
	private String referencia;
	
	
	@Column(name="urbanizacion")
	private String urbanizacion;
		
	
	
	@Column(name="latitud")
	private Double latitud;

	@Column(name="longitud")
	private Double longitud;
	
	 
	@OneToMany(mappedBy="contratoServicio", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private List<SolicitudServicio> solicitudes;
	
	
	
	@Transient
	private List<ContratoServicioDetalle> contratoServicioDetalleList;
	
	
	
	

	public ContratoServicio(long numeroContrato) {
		super();
		this.numeroContrato = numeroContrato;
	}

	public ContratoServicio() {
	}

	public long getNumeroContrato() {
		return this.numeroContrato;
	}

	public void setNumeroContrato(long numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	/*public Integer getCodigoServicio() {
		return this.codigoServicio;
	}

	public void setCodigoServicio(Integer codigoServicio) {
		this.codigoServicio = codigoServicio;
	}*/

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Double  getTarifa() {
		return this.tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}


	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Poste getPoste() {
		return this.poste;
	}

	public void setPoste(Poste poste) {
		this.poste = poste;
	}

	public List<SolicitudServicio> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<SolicitudServicio> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/*public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}*/


	public Integer getCodigoTipoDomicilio() {
		return codigoTipoDomicilio;
	}

	public void setCodigoTipoDomicilio(Integer codigoTipoDomicilio) {
		this.codigoTipoDomicilio = codigoTipoDomicilio;
	}

	public String getNroDomicilio() {
		return nroDomicilio;
	}

	public void setNroDomicilio(String nroDomicilio) {
		this.nroDomicilio = nroDomicilio;
	}

	public String getDptoIntDomicilio() {
		return dptoIntDomicilio;
	}

	public void setDptoIntDomicilio(String dptoIntDomicilio) {
		this.dptoIntDomicilio = dptoIntDomicilio;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getUrbanizacion() {
		return urbanizacion;
	}

	public void setUrbanizacion(String urbanizacion) {
		this.urbanizacion = urbanizacion;
	}

	
	
	
	
	
	
	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public List<ContratoServicioDetalle> getContratoServicioDetalleList() {
		return contratoServicioDetalleList;
	}

	public void setContratoServicioDetalleList(List<ContratoServicioDetalle> contratoServicioDetalleList) {
		this.contratoServicioDetalleList = contratoServicioDetalleList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (numeroContrato ^ (numeroContrato >>> 32));
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
		ContratoServicio other = (ContratoServicio) obj;
		if (numeroContrato != other.numeroContrato)
			return false;
		return true;
	}
	
	

}