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
public class SolicitudServicioEvidenciaPK implements Serializable{

    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    @Column(name = "numero_solicitud", insertable = false, updatable = false)
    private long numeroSolicitud;

    @Column(name = "numero_secuencial")
    private Integer numeroSecuencial;

    public SolicitudServicioEvidenciaPK() {
    }

    public SolicitudServicioEvidenciaPK(long numeroSolicitud, Integer numeroSecuencial) {
        this.numeroSolicitud = numeroSolicitud;
        this.numeroSecuencial = numeroSecuencial;

    }

    public long getNumeroSolicitud() {
        return this.numeroSolicitud;
    }

    public void setNumeroSolicitud(long numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Integer getNumeroSecuencial() {
        return this.numeroSecuencial;
    }

    public void setNumeroSecuencial(Integer numeroSecuencial) {
        this.numeroSecuencial = numeroSecuencial;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SolicitudServicioEvidenciaPK)) {
            return false;
        }
        SolicitudServicioEvidenciaPK castOther = (SolicitudServicioEvidenciaPK) other;
        return (this.numeroSolicitud == castOther.numeroSolicitud)
                && this.numeroSecuencial.equals(castOther.numeroSecuencial);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (int) (this.numeroSolicitud ^ (this.numeroSolicitud >>> 32));
        hash = 53 * hash + Objects.hashCode(this.numeroSecuencial);
        return hash;
    }

}
