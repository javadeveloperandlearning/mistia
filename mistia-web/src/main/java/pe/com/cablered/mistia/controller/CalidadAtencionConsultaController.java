package pe.com.cablered.mistia.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Cliente;
import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.service.ClienteService;
import pe.com.cablered.mistia.service.CuadrillaService;
import pe.com.cablered.mistia.service.DistritoService;
import pe.com.cablered.mistia.service.SolicitudServicioService;
import pe.com.cablered.mistia.util.ConstantBusiness;
import static pe.com.cablered.mistia.controller.ConstansView.*;

@ManagedBean(name = "caliatencon")
@SessionScoped
public class CalidadAtencionConsultaController implements Serializable{
	
	
	
	final static Logger logger = Logger.getLogger(CalidadAtencionConsultaController.class);
	
	
	@Inject
	private DistritoService distritoService;
	
	@Inject
	private CuadrillaService cuadrillaService;
	
	@Inject
	private SolicitudServicioService SolicitudServicioService;
	
	@Inject
	private ClienteService clienteService;
	
	@Inject
	private FacesContext facesContext;
	
	
	
	private String nombreCliente;
	
	private List<Distrito> distritoList;
	
	private List<Cuadrilla> cuadrillaList;
	
	private List<Cliente> clienteList;
	
	private List<Map> lista;
	
	
	// camnpos 
	private Integer codigoDistrito;
	private Long numeroCuadrilla;
	
	
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	
	
	
	@PostConstruct
	public void init(){

		cuadrillaList  = 	cuadrillaService.getCuadrillaList();
		distritoList  =  distritoService.getDistritoList();
		clienteList =  clienteService.clienteList();
		Date fecha =  Calendar.getInstance().getTime();
		fechaInicio = fecha;
		fechaFin =  fecha;
		lista =  Collections.EMPTY_LIST;
		
	}
	
	

	
	public void buscar(){	
		
		if(numeroCuadrilla!=null &&   numeroCuadrilla == 0){
			numeroCuadrilla = null;
		}
		
		
		
		if(codigoDistrito!=null &&   codigoDistrito == 0){
			
			codigoDistrito = null;
		}
		
		
		lista =  SolicitudServicioService.getSolicitudList(null, numeroCuadrilla, codigoDistrito, fechaInicio, fechaFin);
		
		
		
	}
	
	public  void limpiar(){	
		
		
		  Date fecha =  Calendar.getInstance().getTime();
		  fechaInicio = fecha;
		  fechaFin =  fecha;
		  lista =  Collections.EMPTY_LIST;
		  
		  
		
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
	
	
	public String  editar(Object object){
		try{
			
			Long  numerosolicitud =  (Long )object;
			ExternalContext ec  =  facesContext.getExternalContext();			
			HttpServletRequest request = (HttpServletRequest)ec.getRequest();
			request.setAttribute("numerosolicitud", numerosolicitud);
			
			return CALIDA_ATENCION_REGISTRAR_VIEW;
			
		}catch(Exception e){
			e.printStackTrace();
			
		} 
		
		return null;
		
	}
	
	
	public List<String> completeText(String query) {
	        /*List<String> results = new ArrayList<String>();
	        for(int i = 0; i < 10; i++) {
	            results.add(query + i);
	        }
	        return results;*/
		
			return buscarCliente(query);
	}


	
	private List<String> buscarCliente(String criterio){
		
		
		List<String> filtro =  new ArrayList();
	
		for (Cliente cliente : clienteList) {
			String nombre  =  cliente.getApellidos()+" "+cliente.getNombres();
			if(nombre.toUpperCase().startsWith(criterio.trim().toUpperCase())){
				filtro.add(cliente.getApellidos()+" "+cliente.getNombres());
				
			}
			
		}
		return filtro;
	}

	
	
	

	public String getNombreCliente() {
		return nombreCliente;
	}




	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}




	public List<Distrito> getDistritoList() {
		return distritoList;
	}




	public void setDistritoList(List<Distrito> distritoList) {
		this.distritoList = distritoList;
	}




	public List<Cuadrilla> getCuadrillaList() {
		return cuadrillaList;
	}




	public void setCuadrillaList(List<Cuadrilla> cuadrillaList) {
		cuadrillaList = cuadrillaList;
	}




	public List<Cliente> getClienteList() {
		return clienteList;
	}




	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}






	public List<Map> getLista() {
		return lista;
	}






	public void setLista(List<Map> lista) {
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




	public Integer getCodigoDistrito() {
		return codigoDistrito;
	}




	public void setCodigoDistrito(Integer codigoDistrito) {
		this.codigoDistrito = codigoDistrito;
	}




	public Long getNumeroCuadrilla() {
		return numeroCuadrilla;
	}




	public void setNumeroCuadrilla(Long numeroCuadrilla) {
		this.numeroCuadrilla = numeroCuadrilla;
	}
	
	
	
	

}
