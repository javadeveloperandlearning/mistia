package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the tipos database table.
 *
 */
@Entity
@Table(name = "tipos")
@NamedQuery(name = "Tipo.findAll", query = "SELECT t FROM Tipo t")
public class Tipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codigo_tipo")
    private Integer codigoTipo;

    @Column(name = "codigo_grupo")
    private Integer codigoGrupo;

    @Column(name = "descripcion")
    private String descripcion;

    @Transient
    private Integer prioridad;

    @Column(name = "estacion_creacion")
    private String estacionCreacion;

    @Column(name = "estacion_modifcion")
    private String estacionModifcion;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Timestamp fechaModificacion;

    @Column(name = "ind_activo")
    private Integer indActivo;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;


    
    @OneToMany(mappedBy = "tipoDocumento")
    private List<Cliente> clienteList;

    public Tipo() {

    }

    public Tipo(Integer codigoTipo) {
        this.codigoTipo =  codigoTipo;
    }

    public Tipo(Integer codigoTipo, String descripcion) {
        this.codigoTipo = codigoTipo;
        this.descripcion = descripcion;
    }

    public Integer getCodigoTipo() {
        return this.codigoTipo;
    }

    public void setCodigoTipo(Integer codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public Integer getCodigoGrupo() {
        return this.codigoGrupo;
    }

    public void setCodigoGrupo(Integer codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
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

    public Integer getIndActivo() {
        return this.indActivo;
    }

    public void setIndActivo(Integer indActivo) {
        this.indActivo = indActivo;
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



    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }
    
    

}
