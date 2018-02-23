package pe.com.cablered.mistia.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.service.CuadrillaService;
import pe.com.cablered.mistia.service.SolicitudServicioService;

@ManagedBean(name="proggene0")
@SessionScoped
public class ProgramacionGenerarController {
	
	
	

	private List<SolicitudServicio> solicitudList;
	private List<Cuadrilla> cuadrillaList;
	
	final static Logger logger = Logger.getLogger(ProgramacionGenerarController.class);
	
	@Inject
	private CuadrillaService cuadrillaService;
	
	@Inject
	private SolicitudServicioService solicitudServicioService;
	
	
	@PostConstruct
	public void init(){
		
		
		Date fecha =  Calendar.getInstance().getTime();
		logger.info(" cargando cuadrillas");
		cuadrillaList =  cuadrillaService.getCuadrillaList(fecha);
		logger.info(" cargando solicitudes");
		//solicitudList =solicitudServicioService.getSolicitudList(0);

	}

	
	
	
	
	public void consultarPlanTrabajo(){
		
		
	}
	
	public void salir(){
		
		
	}
	
	


	public List<SolicitudServicio> getSolicitudList() {
		return solicitudList;
	}



	public void setSolicitudList(List<SolicitudServicio> solicitudList) {
		this.solicitudList = solicitudList;
	}



	public List<Cuadrilla> getCuadrillaList() {
		return cuadrillaList;
	}



	public void setCuadrillaList(List<Cuadrilla> cuadrillaList) {
		this.cuadrillaList = cuadrillaList;
	}

	
	
	
	
	

}
