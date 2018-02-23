package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name="clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_cliente")
	private Integer codigoCliente;

	private String apellidos;

	@Column(name="codigo_distrito")
	private Integer codigoDistrito;

	private String direccion;

	private String dni;

	@Column(name="estacion_creacion")
	private String estacionCreacion;

	@Column(name="estacion_modifcion")
	private String estacionModifcion;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="fecha_modificacion")
	private Timestamp fechaModificacion;

	private String nombres;

	private String telefono;

	@Column(name="usuario_creacion")
	private String usuarioCreacion;

	@Column(name="usuario_modificacion")
	private String usuarioModificacion;

	//bi-directional many-to-one association to ComprobantePago
	@OneToMany(mappedBy="cliente")
	private List<ComprobantePago> comprobantePagos;

	//bi-directional many-to-one association to DocumentoCompromiso
	@OneToMany(mappedBy="cliente")
	private List<DocumentoCompromiso> documentoCompromisos;
	
	@OneToMany(mappedBy="cliente")
	private List<ContratoServicio> contratoServicios;
	
	
	

	public Cliente() {
	}

	public Cliente(Integer codigoCliente) {
		this.codigoCliente =  codigoCliente;
	}

	public Integer getCodigoCliente() {
		return this.codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getCodigoDistrito() {
		return this.codigoDistrito;
	}

	public void setCodigoDistrito(Integer codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public List<ComprobantePago> getComprobantePagos() {
		return this.comprobantePagos;
	}

	public void setComprobantePagos(List<ComprobantePago> comprobantePagos) {
		this.comprobantePagos = comprobantePagos;
	}

	public ComprobantePago addComprobantePago(ComprobantePago comprobantePago) {
		getComprobantePagos().add(comprobantePago);
		comprobantePago.setCliente(this);

		return comprobantePago;
	}

	public ComprobantePago removeComprobantePago(ComprobantePago comprobantePago) {
		getComprobantePagos().remove(comprobantePago);
		comprobantePago.setCliente(null);

		return comprobantePago;
	}

	public List<DocumentoCompromiso> getDocumentoCompromisos() {
		return this.documentoCompromisos;
	}

	public void setDocumentoCompromisos(List<DocumentoCompromiso> documentoCompromisos) {
		this.documentoCompromisos = documentoCompromisos;
	}

	public DocumentoCompromiso addDocumentoCompromiso(DocumentoCompromiso documentoCompromiso) {
		getDocumentoCompromisos().add(documentoCompromiso);
		documentoCompromiso.setCliente(this);

		return documentoCompromiso;
	}

	public DocumentoCompromiso removeDocumentoCompromiso(DocumentoCompromiso documentoCompromiso) {
		getDocumentoCompromisos().remove(documentoCompromiso);
		documentoCompromiso.setCliente(null);

		return documentoCompromiso;
	}

	public List<ContratoServicio> getContratoServicios() {
		return contratoServicios;
	}

	public void setContratoServicios(List<ContratoServicio> contratoServicios) {
		this.contratoServicios = contratoServicios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (codigoCliente == null) {
			if (other.codigoCliente != null)
				return false;
		} else if (!codigoCliente.equals(other.codigoCliente))
			return false;
		return true;
	}

	
	
	
}