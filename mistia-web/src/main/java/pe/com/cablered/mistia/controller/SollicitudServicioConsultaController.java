package pe.com.cablered.mistia.controller;

import static pe.com.cablered.mistia.controller.ConstansView.CALIDA_ATENCION_REGISTRAR_VIEW;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.EstadoDao;
import pe.com.cablered.mistia.model.Cliente;
import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Estado;
import pe.com.cablered.mistia.model.TipoSolicitud;
import pe.com.cablered.mistia.service.ClienteService;
import pe.com.cablered.mistia.service.EstadoService;
import pe.com.cablered.mistia.service.SolicitudServicioService;
import pe.com.cablered.mistia.service.TipoSolicitudService;
import pe.com.cablered.mistia.util.ConstantBusiness;

@ManagedBean(name="soliservcon")
@SessionScoped
public class SollicitudServicioConsultaController implements Serializable {



	final static Logger logger = Logger.getLogger(SollicitudServicioConsultaController.class);
	
	private Integer codigoCliente;
	private Integer codigoTipoSolicitud;
	private Integer codigoEstado;
	
	private Date fechaInicio;
	private Date fechaFin;
	private List<Map> lista;
	private List<Cliente> clienteList;
	private List<TipoSolicitud> tipoSolicitudList;
	private List<Estado> estadoList;
	
	
	@ManagedProperty("#{soliservgene}")
	private SolicitudServicioGenerarController  soliservgene;

	
	@Inject
	private SolicitudServicioService SolicitudServicioService;
	
	@Inject
	private ClienteService clienteService;
	
	@Inject
	private TipoSolicitudService tipoSolicitudService;
	
	
	@Inject
	private EstadoService estadoService;
	
	@Inject
	private FacesContext facesContext;
	
	private String nombreCliente;
	
	
	
	@PostConstruct
	public void init(){

		clienteList =  clienteService.clienteList();
		tipoSolicitudList = tipoSolicitudService.getTipoSolicitudList();
		estadoList =  estadoService.getEstadoList(ConstantBusiness.GRUPO_ESTADO_SOLICITUD_SERVICIO);
		Date fecha =  Calendar.getInstance().getTime();
		fechaInicio = fecha;
		fechaFin =  fecha;
		lista =  Collections.EMPTY_LIST;
		
	}
	
	

	public void buscar(){		
		
		logger.info(" metodo : buscar ");
		logger.info(" nombreCliente : " + nombreCliente);
		
		if(codigoTipoSolicitud!=null && codigoTipoSolicitud==ConstantBusiness.CODIGO_TODOS){
			codigoTipoSolicitud = null;
		}
		
		if(codigoEstado!=null && codigoEstado==ConstantBusiness.CODIGO_TODOS){
			codigoEstado = null;
		}
		
		Integer codigoClient = null; 
		if(nombreCliente!=null){
			for (Cliente cliente : clienteList) {
				String  nomclie =  cliente.getApellidos().trim()+" "+cliente.getNombres().trim();
				logger.info(" buscando cliente : "+nomclie);
				if(nomclie.trim().equals(nombreCliente.trim())){
					codigoClient =   cliente.getCodigoCliente();
					break ;
				}
			}
		}
		
		logger.info(" codigoClient : " + codigoClient);
		lista =  SolicitudServicioService.getSolicitudList(codigoClient, codigoTipoSolicitud, codigoEstado, fechaInicio, fechaFin);
		
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
	
	
	
	public void nuevo(){

		try {
			
			soliservgene.init();
			ExternalContext ec = facesContext.getExternalContext();
			ec.redirect(ec.getRequestContextPath()+ ConstansView.SOLICITUD_SERVICIO_GENERAR_VIEW);
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

	
	
	

	public List<TipoSolicitud> getTipoSolicitudList() {
		return tipoSolicitudList;
	}



	public void setTipoSolicitudList(List<TipoSolicitud> tipoSolicitudList) {
		this.tipoSolicitudList = tipoSolicitudList;
	}



	public String getNombreCliente() {
		return nombreCliente;
	}

	
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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



	public List<Estado> getEstadoList() {
		return estadoList;
	}



	public void setEstadoList(List<Estado> estadoList) {
		this.estadoList = estadoList;
	}



	public Integer getCodigoTipoSolicitud() {
		return codigoTipoSolicitud;
	}



	public void setCodigoTipoSolicitud(Integer codigoTipoSolicitud) {
		this.codigoTipoSolicitud = codigoTipoSolicitud;
	}



	public Integer getCodigoEstado() {
		return codigoEstado;
	}



	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}



	public void setSoliservgene(SolicitudServicioGenerarController soliservgene) {
		this.soliservgene = soliservgene;
	}	

	
	
	
}
