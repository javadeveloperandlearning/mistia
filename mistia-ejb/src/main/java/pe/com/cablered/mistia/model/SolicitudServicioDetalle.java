/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author javadeveloper
 */
@Entity
@Table(name = "solicitud_servicio_detalle")
public class SolicitudServicioDetalle  extends  ObjectBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SolicitudServicioDetallePK id;

    @ManyToOne
    @JoinColumn(name = "codigo_servicio", updatable = false, insertable = false)
    private Servicio servicio;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "tarifa")
    private Double tarifa;
    
    @Transient
    private  Integer cantTelevisor;

    //bi-directional one-to-one association to ContratoServicio
    @ManyToOne
    @JoinColumn(name = "numero_solicitud", updatable = false, insertable = false)
    private SolicitudServicio solicitudServicio;

    public SolicitudServicioDetalle() {
    }
   
    public SolicitudServicioDetalle(long numeroSolicitud, Integer codigoServicio) {
        this.id  = new SolicitudServicioDetallePK(numeroSolicitud, codigoServicio);
    }
    
  
    public Double getTarifa() {
        return tarifa;
    }

    public void setTarifa(Double tarifa) {
        this.tarifa = tarifa;
    }

    public SolicitudServicioDetallePK getId() {
        return this.id;
    }

    public void setId(SolicitudServicioDetallePK id) {
        this.id = id;
    }
    

    public void setId(long numeroSolicitud, Integer codigoServicio) {
        this.id = new SolicitudServicioDetallePK(numeroSolicitud, codigoServicio);
    }


    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public SolicitudServicio getSolicitudServicio() {
        return solicitudServicio;
    }

    public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
        this.solicitudServicio = solicitudServicio;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Integer getCantTelevisor() {
        return cantTelevisor;
    }

    public void setCantTelevisor(Integer cantTelevisor) {
        this.cantTelevisor = cantTelevisor;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
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
        final SolicitudServicioDetalle other = (SolicitudServicioDetalle) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
