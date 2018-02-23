package pe.com.cablered.mistia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.model.Vehiculo;
import pe.com.cablered.mistia.model.VehiculoMarca;
import pe.com.cablered.mistia.model.VehiculoModelo;
import pe.com.cablered.mistia.model.sort.VehiculoMarcaComparator;
import pe.com.cablered.mistia.model.sort.VehiculoModeloComparator;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.mistia.service.VehiculoMarcaService;
import pe.com.cablered.mistia.service.VehiculoModeloService;
import pe.com.cablered.mistia.service.VehiculoService;

@ManagedBean(name = "vehiculobean")
@SessionScoped
public class VehiculoController  implements Serializable, Mantenible{

	private static final long serialVersionUID = -32348959599327908L;

	@Inject
	private  VehiculoService vehiculoService;
	
	@Inject
	private VehiculoMarcaService vehiculoMarcaService;
	
	@Inject
	private VehiculoModeloService vehiculoModeloService;
	
	// detalle 
	private List<Vehiculo> vehiculoList;
	
	// combos
	private List<VehiculoMarca> vehiculoMarcaList;
	
	private List<VehiculoModelo> vehiculoModeloList;
		
	@Inject
	private FacesContext facesContext;


	private Vehiculo vehiculo; 
	private VehiculoModelo vehiculoModelo;
	private VehiculoMarca vehiculoMarca;
	
	
	private String criterio ;
	private String action = null;
	
	final static Logger logger = Logger.getLogger(VehiculoController.class);
	
	@PostConstruct
	public void init(){
	
		// lista de matrices
		vehiculoMarcaList =  vehiculoMarcaService.getVehiculoMarcaList();
		vehiculoModeloList = vehiculoModeloService.getVehiculoModeloList();
		
		if(vehiculoMarcaList==null){
			vehiculoMarcaList = new ArrayList<>();
		}

		if(vehiculoModeloList==null){
			vehiculoModeloList = new ArrayList<>();
		}
		
		
		VehiculoMarca vehiculoMarca  = new VehiculoMarca(SELECCIONE_VALUE, SELECCIONE_LABEL);
		vehiculoMarcaList.add(vehiculoMarca);
		
		VehiculoModelo vehiculoModelo =  new VehiculoModelo(SELECCIONE_VALUE, SELECCIONE_LABEL);
		vehiculoModeloList.add(vehiculoModelo);
		
		Collections.sort(vehiculoMarcaList, new VehiculoMarcaComparator());
		Collections.sort(vehiculoModeloList, new VehiculoModeloComparator());

		vehiculo =  new Vehiculo();
		
		mostrar();
		
	}
	
	
	@Override
	public void mostrar() {
		criterio = (criterio==null || criterio.trim().equals(""))?null:criterio;
		vehiculoList = vehiculoService.getVehiculoList(vehiculoMarca, vehiculoModelo, criterio);
		
	}

	@Override
	public void nuevo() {
		 action =  NUEVO;
		 logger.info(" ### nuevo vehiculo ### ");
		 
		 
		
	}

	@Override
	public void editar() {
		
		action =  EDITAR;
		logger.info("## editando ##");
		logger.info( "placaVehiculo :  "+facesContext.getExternalContext().getRequestParameterMap().get("placaVehiculo"));
		String placaVehiculo =  facesContext.getExternalContext().getRequestParameterMap().get("placaVehiculo");
		
		if(placaVehiculo!=null && vehiculoList!=null){
			int idx =   vehiculoList.indexOf(new Vehiculo(placaVehiculo));
			if(idx!=-1){
				vehiculo =  vehiculoList.get(idx);
			}
		}
		
	}

	@Override
	public void grabar() {
		
		logger.info(" grabar accion : "+action);
		try{
			
			FacesMessage msg = null;
			Response response = null;
			if(action.equals(NUEVO)){
			    response = vehiculoService.registrar(vehiculo);
			}else if (action.equals(EDITAR)) {
				 response = vehiculoService.modificar(vehiculo);
			}
			
			if(response!=null){
				msg = new FacesMessage(response.getMensaje());
				if(response.getCodigo()== Response.OK){
					mostrar();	
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
				}else{
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				}
				facesContext.addMessage(null, msg);	
			}
	
		}catch( Exception e ){
			logger.info(e);
			e.printStackTrace();
			
		}
	}

	@Override
	public void eliminar(Object object) {
		logger.info("## eliminar ## ");
		
		if(object instanceof Vehiculo){
			
			Vehiculo nodo =  (Vehiculo)object;
			Response response = vehiculoService.eliminar(nodo);
			logger.info(response.toString());
			FacesMessage msg = new FacesMessage(response.getMensaje());
			if(response!=null && response.getCodigo()== Response.OK){
				mostrar();	
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
			}else{
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			}
			
			facesContext.addMessage(null, msg);	
		}
	
	}
	

	




	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	

	public String getCriterio() {
		return criterio;
	}


	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}


	public VehiculoController(List<Vehiculo> vehiculoList) {
		super();
		this.vehiculoList = vehiculoList;
	}


	public List<Vehiculo> getVehiculoList() {
		return vehiculoList;
	}


	public void setVehiculoList(List<Vehiculo> vehiculoList) {
		this.vehiculoList = vehiculoList;
	}


	public List<VehiculoMarca> getVehiculoMarcaList() {
		return vehiculoMarcaList;
	}


	public void setVehiculoMarcaList(List<VehiculoMarca> vehiculoMarcaList) {
		this.vehiculoMarcaList = vehiculoMarcaList;
	}


	public List<VehiculoModelo> getVehiculoModeloList() {
		return vehiculoModeloList;
	}


	public void setVehiculoModeloList(List<VehiculoModelo> vehiculoModeloList) {
		this.vehiculoModeloList = vehiculoModeloList;
	}

}
