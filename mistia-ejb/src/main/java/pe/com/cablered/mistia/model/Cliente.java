package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the clientes database table.
 *
 */
@Entity
@Table(name = "clientes")
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
public class Cliente extends ObjectBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codigo_cliente")
    private Integer codigoCliente;

    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Column(name = "codigo_distrito")
    private Integer codigoDistrito;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "referencia")
    private String referencia;

    //private String dni;
    @Column(name = "estacion_creacion")
    private String estacionCreacion;

    @Column(name = "estacion_modificacion")
    private String estacionModificacion;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Timestamp fechaModificacion;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;

    @Column(name = "tipo_documento")
    private Integer tipoDocumento;

    @Column(name = "documento_identidad")
    private String documentoIdentidad;

    @Column(name = "nombre_razon_social")
    private String nombreRazonSocial;

    @Column(name = "telefono_movil")
    private String telefonoMovil;

    @Column(name = "sexo")
    private Integer sexo;

    @Column(name = "email")
    private String email;

    @Column(name = "numero_ruc")
    private String numeroRuc;

    //bi-directional many-to-one association to ComprobantePago
    @OneToMany(mappedBy = "cliente")
    private List<ComprobantePago> comprobantePagos;

    //bi-directional many-to-one association to DocumentoCompromiso
    @OneToMany(mappedBy = "cliente")
    private List<DocumentoCompromiso> documentoCompromisos;

    @OneToMany(mappedBy = "cliente")
    private List<ContratoServicio> contratoServicios;

   //@OneToMany(mappedBy = "cliente")
    @Transient
    private List<ClienteDireccion> clienteDireccions;

    public Cliente() {
    }

    public Cliente(Integer codigoCliente, String apellidoMaterno, String apellidoPaterno, Integer codigoDistrito, String direccion, String referencia, String estacionCreacion, String estacionModificacion, Timestamp fechaCreacion, Timestamp fechaModificacion, String nombres, String telefono, String usuarioCreacion, String usuarioModificacion, Integer tipoDocumento, String documentoIdentidad, String nombreRazonSocial, String telefonoMovil, Integer sexo, String email, String numeroRuc) {
        this.codigoCliente = codigoCliente;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.codigoDistrito = codigoDistrito;
        this.direccion = direccion;
        this.referencia = referencia;
        this.estacionCreacion = estacionCreacion;
        this.estacionModificacion = estacionModificacion;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.nombres = nombres;
        this.telefono = telefono;
        this.usuarioCreacion = usuarioCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.tipoDocumento = tipoDocumento;
        this.documentoIdentidad = documentoIdentidad;
        this.nombreRazonSocial = nombreRazonSocial;
        this.telefonoMovil = telefonoMovil;
        this.sexo = sexo;
        this.email = email;
        this.numeroRuc = numeroRuc;
    }
    
    
    

    public Cliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getCodigoCliente() {
        return this.codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    //@Transient
    public String getApellidos() {
        return apellidoPaterno + " " + apellidoMaterno;

    }

    /*
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }*/

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

    /*public String getDni() {
		return this.dni;
    }

    public void setDni(String dni) {
		this.dni = dni;
    }
     */
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

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroRuc() {
        return numeroRuc;
    }

    public void setNumeroRuc(String numeroRuc) {
        this.numeroRuc = numeroRuc;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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
    
    //@XmlTransient
    public List<ClienteDireccion> getClienteDireccions() {
        return clienteDireccions;
    }

    public void setClienteDireccions(List<ClienteDireccion> clienteDireccions) {
        this.clienteDireccions = clienteDireccions;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Cliente other = (Cliente) obj;
        if (codigoCliente == null) {
            if (other.codigoCliente != null) {
                return false;
            }
        } else if (!codigoCliente.equals(other.codigoCliente)) {
            return false;
        }
        return true;
    }

}
