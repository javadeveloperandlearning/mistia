package pe.com.cablered.mistia.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import pe.com.cablered.mistia.model.Reclamo;
import pe.com.cablered.mistia.service.AbstractSevice;
import pe.com.cablered.mistia.service.ReclamoService;


@ManagedBean(name = "reclamobean")
public class ReclamoController extends BaseController<Reclamo>  {
	
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private ReclamoService service;
	
	
	private String criterio;

	private Date fechaReclamoIni;
	private Date fechaReclamoFin;


	
	@PostConstruct
	public void init(){
			
		this.limpiar();
		
	}


	@Override
	protected FacesContext getFacesContext() {
		return facesContext;
	}

	@Override
	protected AbstractSevice getService() {
		return service;
	}


	@Override
	public void mostrar() {
				
		this.list =   service.getReclamoList (criterio, fechaReclamoIni, fechaReclamoFin);
		//this.list =   service.getReclamoList ();
		
		
	}


	@Override
	public void reset() {
		
	}


	@Override
	public void limpiar() {
		
		
		fechaReclamoIni =  new Date();
		fechaReclamoFin =  new Date();
		this.list  =  Collections.emptyList();
	}


	public String getCriterio() {
		return criterio;
	}


	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}


	public Date getFechaReclamoIni() {
		return fechaReclamoIni;
	}


	public void setFechaReclamoIni(Date fechaReclamoIni) {
		this.fechaReclamoIni = fechaReclamoIni;
	}


	public Date getFechaReclamoFin() {
		return fechaReclamoFin;
	}


	public void setFechaReclamoFin(Date fechaReclamoFin) {
		this.fechaReclamoFin = fechaReclamoFin;
	}


	
	
	
	
	
}
