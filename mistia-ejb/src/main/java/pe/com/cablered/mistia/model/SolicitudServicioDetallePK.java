/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author javadeveloper
 */
@Embeddable
public class SolicitudServicioDetallePK implements Serializable {

    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "numero_solicitud", insertable = false, updatable = false)
    private long numeroSolicitud;

    @Column(name = "codigo_servicio", insertable = false, updatable = false)
    private Integer codigoServicio;

    public SolicitudServicioDetallePK() {
    }

    public SolicitudServicioDetallePK(long numeroSolicitud, Integer codigoServicio) {
        this.numeroSolicitud = numeroSolicitud;
        this.codigoServicio = codigoServicio;
    }

    public Integer getCodigoServicio() {
        return this.codigoServicio;
    }

    public void setCodigoServicio(Integer codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public long getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(long numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final SolicitudServicioDetallePK other = (SolicitudServicioDetallePK) obj;
        if (this.numeroSolicitud != other.numeroSolicitud) {
            return false;
        }
        if (!Objects.equals(this.codigoServicio, other.codigoServicio)) {
            return false;
        }
        return true;
    }
    
    

   

}
