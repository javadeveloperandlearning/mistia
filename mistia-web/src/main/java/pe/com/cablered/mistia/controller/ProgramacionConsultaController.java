package pe.com.cablered.mistia.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.BubbleChartSeries;

import pe.com.cablered.mistia.model.Estado;
import pe.com.cablered.mistia.model.Programacion;
import pe.com.cablered.mistia.service.ProgramacionService;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.mistia.util.ConstantBusiness;






@ManagedBean(name = "progcon")
@SessionScoped
public class ProgramacionConsultaController {
	
	

	final static Logger logger = Logger.getLogger(ProgramacionConsultaController.class);
	
	@Inject
	private ProgramacionService programacionService;
	
	@Inject
	private FacesContext facesContext;
	
	
	private List<Programacion> lista;	
	private Date  fechaInicio;
	private Date  fechaFin;
	
	/*@ManagedProperty("#{proggene}")
	private ProgramacionRest proggene;*/
	
	
	@PostConstruct
	public void init(){
		
			limpiar();
	 
			buscar();
			
	}

	
	public void buscar(){
		
		System.out.println("fechaInicio "+fechaInicio);
		System.out.println("fechaFin "+fechaFin);
		
		long _time = fechaFin.getTime() - fechaInicio.getTime();
		
		if (_time < 0) {
			FacesMessage msg = new FacesMessage("Ingrese un rango de fecha no válido");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, msg);
			
			lista =  Collections.EMPTY_LIST;
			
			return;
		}
		// tres meses
		_time = (fechaFin.getTime() - fechaInicio.getTime());
		long _limit = 1000 * 60 * 60 * 24 * 90L;
		if (_time > _limit) {
			FacesMessage msg = new FacesMessage("El rango de Fecha no debe ser mayor a 3 a meses");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, msg);
			lista =  Collections.EMPTY_LIST;
			return;
		}
		
		
		
		logger.info(" ### buscar ### ");
		this.lista  =  programacionService.getProgramacionList(fechaInicio, fechaFin);
		
		if(lista.size()==0){
			FacesMessage msg = new FacesMessage("No existen registros");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			facesContext.addMessage(null, msg);
			
		}
		for (Programacion programacion : lista) {
			logger.info(" estado"+programacion.getEstado().getCodigoEstado());
		}
		
		logger.info("Cantidad de elementos :"+lista.size());
	}
	
	
	public  void limpiar(){
		  Date fecha =  Calendar.getInstance().getTime();
		  fechaInicio = fecha;
		  fechaFin =  fecha;
		  lista =  Collections.EMPTY_LIST;
	}
	
	public void editar(Object object){
		logger.info(" ## editar ##");
		
		try{
			//proggene.clearCache();
			Programacion p =  (Programacion)object;
			ExternalContext ec  =  facesContext.getExternalContext();
			HttpServletRequest request =    (HttpServletRequest) ec.getRequest();
			HttpSession session =  request.getSession(false);
			
			session.setAttribute("numeroProgramacion", p.getNumeroProgramacion());
			session.setAttribute(ConstantBusiness.ACCION_PROGRAMACION, ConstantBusiness.ACCION_EDITA_PROGRAMACION);
			session.setAttribute("ACCION_NUEVA_PROGRAMACION", ConstantBusiness.ACCION_NUEVA_PROGRAMACION);
			session.setAttribute("ACCION_EDITA_PROGRAMACION", ConstantBusiness.ACCION_EDITA_PROGRAMACION);
		
			
			/*
			 
			 ec.getSessionMap().put("numeroProgramacion",p.getNumeroProgramacion());
			ec.getSessionMap().put(ConstantBusiness.ACCION_PROGRAMACION, ConstantBusiness.ACCION_EDITA_PROGRAMACION);
			ec.getSessionMap().put("ACCION_NUEVA_PROGRAMACION",ConstantBusiness.ACCION_NUEVA_PROGRAMACION);
			ec.getSessionMap().put("ACCION_EDITA_PROGRAMACION",ConstantBusiness.ACCION_EDITA_PROGRAMACION);
			
			*/
			
			
			
			ec.redirect(ec.getRequestContextPath()+ ConstansView.GENERAR_PROGRAMACION);
			
			
			
			
		}catch(Exception e ){
			logger.info(e);
			e.printStackTrace();
		} 
		
	}
	
	public void anular(Object object){
		
		Programacion p =  (Programacion)object;
		
		p.setEstado( new Estado(ConstantBusiness.ESTADO_PROGRAMACION_ANULADO,
								ConstantBusiness.DESCRIPCION_ESTADO_PROGRAMACION_ANULADO));
		
		
		Response response =  programacionService.actualizar(p);
		
		
		
		
	/*	
		FacesMessage msg = new FacesMessage("Anulando Programación");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		facesContext.addMessage(null, msg);*/
	}
	
	public void generarProgramacion(){
		
	}
	
	
	public void salir(){
		
		limpiar();
		
		logger.info("metodo: salir");
		try {
			ExternalContext ec = facesContext.getExternalContext();
			ec.redirect(ec.getRequestContextPath()+ ConstansView.PRINCIPAL_VIEW);
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	public String getIncon(Object object){
		
		Programacion p  =  (Programacion)object;
		String icon = ""; 
		if(p!=null &&  (p.getEstado().getCodigoEstado().equals(4))){
			icon = "ui-icon-search";
		}else if(p!=null &&  (p.getEstado().getCodigoEstado().equals(1))){
			icon = "ui-icon-pencil";
		}
		return icon;
	}
	
	public void sendGenerarProgramacion(){
		logger.info("metodo: generar");
		try {
			
			//proggene.clearCache();
			ExternalContext ec = facesContext.getExternalContext();
			HttpServletRequest request =    (HttpServletRequest) ec.getRequest();
			HttpSession session =  request.getSession(false);
			session.setAttribute(ConstantBusiness.ACCION_PROGRAMACION, ConstantBusiness.ACCION_NUEVA_PROGRAMACION);
			session.setAttribute("ACCION_NUEVA_PROGRAMACION", ConstantBusiness.ACCION_NUEVA_PROGRAMACION);
			session.setAttribute("ACCION_EDITA_PROGRAMACION", ConstantBusiness.ACCION_EDITA_PROGRAMACION);
			
			/*
			ec.getSessionMap().put(ConstantBusiness.ACCION_PROGRAMACION,ConstantBusiness.ACCION_NUEVA_PROGRAMACION);
			ec.getSessionMap().put("ACCION_NUEVA_PROGRAMACION",ConstantBusiness.ACCION_NUEVA_PROGRAMACION);
			ec.getSessionMap().put("ACCION_EDITA_PROGRAMACION",ConstantBusiness.ACCION_EDITA_PROGRAMACION);
			*/
			
			ec.redirect(ec.getRequestContextPath()+ ConstansView.GENERAR_PROGRAMACION);
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	


	
	
	/*
	
	public void setProggene(ProgramacionRest proggene) {
		this.proggene = proggene;
	}
*/

	public List<Programacion> getLista() {
		return lista;
	}

	public void setLista(List<Programacion> lista) {
		this.lista = lista;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
	

}
