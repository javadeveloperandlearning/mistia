package pe.com.cablered.mistia.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the reclamos database table.
 * 
 */
@Entity
@Table(name="reclamos")
@NamedQuery(name="Reclamo.findAll", query="SELECT r FROM Reclamo r")
public class Reclamo extends ObjectBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="numero_reclamo")
	private long numeroReclamo;

	private String descripcion;
	
	@Column(name="necesidad")
	private String necesidad; //<---
	
	@Column(name="solucion")
	private String  solucion;//<---
	

	@ManyToOne
	@JoinColumn(name="codigo_estado")
	private Estado estado; //<---
	
	@Column(name="plazo_atencion_horas")
	private Integer plazoAtencionHoras; //<---
	
	
	@Column(name="fecha_reclamo")
	private Timestamp fechaReclamo; // fecha registro

	
	@Column(name="fecha_cierre")
	private Timestamp fechaCierre; // fecha atencion
	



	//bi-directional many-to-one association to Motivo
	@ManyToOne
	@JoinColumn(name="codigo_motivo")
	private Motivo motivo;

	public Reclamo() {
	}

	public long getNumeroReclamo() {
		return this.numeroReclamo;
	}

	public void setNumeroReclamo(long numeroReclamo) {
		this.numeroReclamo = numeroReclamo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Timestamp getFechaCierre() {
		return this.fechaCierre;
	}

	public void setFechaCierre(Timestamp fechaCierre) {
		this.fechaCierre = fechaCierre;
	}



	public Timestamp getFechaReclamo() {
		return this.fechaReclamo;
	}

	public void setFechaReclamo(Timestamp fechaReclamo) {
		this.fechaReclamo = fechaReclamo;
	}



	public Motivo getMotivo() {
		return this.motivo;
	}

	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	public String getNecesidad() {
		return necesidad;
	}

	public void setNecesidad(String necesidad) {
		this.necesidad = necesidad;
	}

	public String getSolucion() {
		return solucion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}



	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	
	
	
	
	
	
}