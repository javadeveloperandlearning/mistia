package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the postes database table.
 * 
 */
@Entity
@Table(name="postes")
@NamedQuery(name="Poste.findAll", query="SELECT p FROM Poste p")
public class Poste extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_poste")
	private Integer codigoPoste;

	private String descripcion;



	private BigDecimal latitud;

	private BigDecimal longitud;

	
	

	//bi-directional many-to-one association to ContratoServicio
	@OneToMany(mappedBy="poste")
	private List<ContratoServicio> contratoServicios;

	//bi-directional many-to-one association to Nodo
	@ManyToOne
	@JoinColumn(name="codigo_nodo")
	private Nodo nodo;

	//bi-directional many-to-one association to SectorGeograficoDetalle
	@OneToMany(mappedBy="poste")
	private List<SectorGeograficoDetalle> sectorGeograficoDetalles;

	//bi-directional many-to-one association to SolicitudServicio
	@OneToMany(mappedBy="poste")
	private List<SolicitudServicio> solicitudServicios;

	public Poste() {
	}
	
	
	
	
	
	public Poste(Integer codigoPoste) {
		super();
		this.codigoPoste = codigoPoste;
	}





	public Poste(Integer codigoPoste,  BigDecimal latitud, BigDecimal longitud) {
		this.codigoPoste =  codigoPoste;
		this.latitud =  latitud;
		this.longitud =  longitud;
	}	
	

	public Integer getCodigoPoste() {
		return this.codigoPoste;
	}

	public void setCodigoPoste(Integer codigoPoste) {
		this.codigoPoste = codigoPoste;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
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
		contratoServicio.setPoste(this);

		return contratoServicio;
	}

	public ContratoServicio removeContratoServicio(ContratoServicio contratoServicio) {
		getContratoServicios().remove(contratoServicio);
		contratoServicio.setPoste(null);

		return contratoServicio;
	}

	public Nodo getNodo() {
		return this.nodo;
	}

	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}
	
	
	@XmlTransient
	public List<SectorGeograficoDetalle> getSectorGeograficoDetalles() {
		return this.sectorGeograficoDetalles;
	}

	public void setSectorGeograficoDetalles(List<SectorGeograficoDetalle> sectorGeograficoDetalles) {
		this.sectorGeograficoDetalles = sectorGeograficoDetalles;
	}

	public SectorGeograficoDetalle addSectorGeograficoDetalle(SectorGeograficoDetalle sectorGeograficoDetalle) {
		getSectorGeograficoDetalles().add(sectorGeograficoDetalle);
		sectorGeograficoDetalle.setPoste(this);

		return sectorGeograficoDetalle;
	}

	public SectorGeograficoDetalle removeSectorGeograficoDetalle(SectorGeograficoDetalle sectorGeograficoDetalle) {
		getSectorGeograficoDetalles().remove(sectorGeograficoDetalle);
		sectorGeograficoDetalle.setPoste(null);

		return sectorGeograficoDetalle;
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
		solicitudServicio.setPoste(this);

		return solicitudServicio;
	}

	public SolicitudServicio removeSolicitudServicio(SolicitudServicio solicitudServicio) {
		getSolicitudServicios().remove(solicitudServicio);
		solicitudServicio.setPoste(null);

		return solicitudServicio;
	}

}