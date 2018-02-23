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
import pe.com.cablered.mistia.model.Cargo;
import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Tecnico;
import pe.com.cablered.mistia.model.sort.CargoComparator;
import pe.com.cablered.mistia.model.sort.DistritoComparator;
import pe.com.cablered.mistia.service.CargoService;
import pe.com.cablered.mistia.service.DistritoService;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.mistia.service.TecnicoService;

@ManagedBean(name = "tecnicobean")
@SessionScoped
public class TecnicoController implements Mantenible, Serializable {
	
	
	@Inject
	private TecnicoService tecnicoService;
	
	@Inject
	private DistritoService distritoService;
	
	@Inject
	private CargoService cargoService;
	
	private String criterio;
	
	private List<Tecnico> tecnicoList;
	
	private List<Distrito> distritoList;
	private List<Cargo> cargoList;
	
	private List<Distrito> distritoListp;
	private List<Cargo> cargoListp;
	
	private Tecnico tecnico;
	
	private Distrito distrito;
	private Cargo cargo;
	private String action = null;
	
	private String  headerpopup;

	
	@Inject
	private FacesContext facesContext;
	
	final static Logger logger = Logger.getLogger(TecnicoController.class);
	
	
	
	@PostConstruct
	public void init(){
		
		tecnicoList = tecnicoService.getTecnicoList();
		distritoList =  distritoService.getDistritoList();
		cargoList =  cargoService.getCargoList();
		
		distritoListp =  new ArrayList<Distrito>();
		cargoListp =  new ArrayList<Cargo>();
		
		distritoListp.addAll(distritoList);
		cargoListp.addAll(cargoList);
		
		
		Distrito distrito0 =  new Distrito(TODOS_VALUE, TODOS_LABEL);
		Cargo cargo0       =  new Cargo(TODOS_VALUE, TODOS_LABEL);
		distritoList.add(distrito0);
		cargoList.add(cargo0);
		
		Distrito distrito1 =  new Distrito(SELECCIONE_VALUE, SELECCIONE_LABEL);
		Cargo cargo1       =  new Cargo(SELECCIONE_VALUE, SELECCIONE_LABEL);
		distritoListp.add(distrito1);
		cargoListp.add(cargo1);
		
		
		Collections.sort(distritoList, new DistritoComparator());
		Collections.sort(cargoList, new CargoComparator());
		
		
		Collections.sort(distritoListp, new DistritoComparator());
		Collections.sort(cargoListp, new CargoComparator());
		
	}
	

	@Override
	public void mostrar() {
		
		
		criterio = (criterio==null || criterio.trim().equals(""))?null:criterio;
		tecnicoList =  tecnicoService.getTecnicoList(distrito,cargo,  criterio);
		
	}

	@Override
	public void nuevo() {
		 action =  NUEVO;
		 headerpopup = "Nuevo Técnico";
		 logger.info(" ### nuevo tecnico ### ");
		 tecnico =  new Tecnico();
		 
		
	}

	@Override
	public void editar() {
		
		action =  EDITAR;
		headerpopup = "Modificar Técnico";
		logger.info("## editando técnico ##");
		logger.info( "codigoTecnico :  "+facesContext.getExternalContext().getRequestParameterMap().get("codigoTecnico"));
		
		String _codigoTecnico =  facesContext.getExternalContext().getRequestParameterMap().get("codigoTecnico");
		if(_codigoTecnico!=null){
			Integer codigoTecnico = Integer.parseInt( _codigoTecnico );
			this.tecnico =  tecnicoService .getTecnico(codigoTecnico);
		}
		
	}

	@Override
	public void grabar() {
		
		logger.info(" grabar accion : "+action);
		try{
			
			FacesMessage msg = null;
			Response response = null;
			
			if(action.equals(NUEVO)){
			    response = tecnicoService.registrar(tecnico);
			}else if (action.equals(EDITAR)) {
				 response = tecnicoService.modificar(tecnico);
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
		
		if(object instanceof Tecnico){
			
			Tecnico tecnico =  (Tecnico)object;
			Response response = tecnicoService.eliminar(tecnico);
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


	public Tecnico getTecnico() {
		return tecnico;
	}


	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}


	public List<Tecnico> getTecnicoList() {
		return tecnicoList;
	}


	public void setTecnicoList(List<Tecnico> tecnicoList) {
		this.tecnicoList = tecnicoList;
	}


	public DistritoService getDistritoService() {
		return distritoService;
	}


	public void setDistritoService(DistritoService distritoService) {
		this.distritoService = distritoService;
	}


	public List<Distrito> getDistritoList() {
		return distritoList;
	}


	public void setDistritoList(List<Distrito> distritoList) {
		this.distritoList = distritoList;
	}


	public List<Cargo> getCargoList() {
		return cargoList;
	}


	public void setCargoList(List<Cargo> cargoList) {
		this.cargoList = cargoList;
	}


	public Distrito getDistrito() {
		return distrito;
	}


	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}


	public Cargo getCargo() {
		return cargo;
	}


	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}


	public String getCriterio() {
		return criterio;
	}


	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}


	public List<Distrito> getDistritoListp() {
		return distritoListp;
	}


	public void setDistritoListp(List<Distrito> distritoListp) {
		this.distritoListp = distritoListp;
	}


	public List<Cargo> getCargoListp() {
		return cargoListp;
	}


	public void setCargoListp(List<Cargo> cargoListp) {
		this.cargoListp = cargoListp;
	}


	public String getHeaderpopup() {
		return headerpopup;
	}


	public void setHeaderpopup(String headerpopup) {
		this.headerpopup = headerpopup;
	}


	
	
	

}
