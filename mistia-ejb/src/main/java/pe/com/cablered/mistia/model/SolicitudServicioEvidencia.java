/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author javadeveloper
 */
@Entity
@Table(name = "solicitud_servicio_evidencia")
public class SolicitudServicioEvidencia extends ObjectBean implements Serializable {

    @EmbeddedId
    private SolicitudServicioEvidenciaPK id;

    //bi-directional many-to-one association to SolicitudServicio
    @ManyToOne
    @JoinColumn(name = "numero_solicitud", updatable = false, insertable = false)
    private SolicitudServicio solicitudServicio;

    @Transient
    private int numeroSecuencial;

    @Transient
    private String file;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ruta")
    private String ruta;

    @Column(name = "ruta_equipo_movil")
    private String rutaEquipoMovil;

    public SolicitudServicioEvidencia() {

    }

    public SolicitudServicioEvidencia(long numeroSolicitud, Integer numeroSecuencial) {
        this.id = new SolicitudServicioEvidenciaPK(numeroSolicitud, numeroSecuencial);
    }

    public SolicitudServicio getSolicitudServicio() {
        return solicitudServicio;
    }

    public void setSolicitudServicio(SolicitudServicio solicitudServicio) {
        this.solicitudServicio = solicitudServicio;
    }

    public int getNumeroSecuencial() {
        return numeroSecuencial;
    }

    public void setNumeroSecuencial(int numeroSecuencial) {
        this.numeroSecuencial = numeroSecuencial;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRutaEquipoMovil() {
        return rutaEquipoMovil;
    }

    public void setRutaEquipoMovil(String rutaEquipoMovil) {
        this.rutaEquipoMovil = rutaEquipoMovil;
    }

    public SolicitudServicioEvidenciaPK getId() {
        return id;
    }

    public void setId(SolicitudServicioEvidenciaPK id) {
        this.id = id;
    }

    public void setId(long numeroSolicitud, Integer numeroSecuencial) {
        this.id =  new SolicitudServicioEvidenciaPK(numeroSolicitud, numeroSecuencial);
    }

}
