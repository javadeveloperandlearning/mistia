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

import pe.com.cablered.mistia.model.Matriz;
import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.model.Poste;
import pe.com.cablered.mistia.model.sort.MatrizComparator;
import pe.com.cablered.mistia.model.sort.NodoComparator;
import pe.com.cablered.mistia.service.MatrizService;
import pe.com.cablered.mistia.service.NodosService;
import pe.com.cablered.mistia.service.PosteService;
import pe.com.cablered.mistia.service.Response;

@ManagedBean(name = "poste")
@SessionScoped
public class PosteController  implements Serializable, Mantenible{


	private static final long serialVersionUID = -32348959599327908L;

	@Inject
	private NodosService nodosService;
	
	@Inject
	private PosteService posteService;
	

	
	@Inject
	private FacesContext facesContext;
	

	private List<Nodo> nodoList;
	private List<Poste> posteList;
	
	
	
	private Nodo nodo;
	private Poste poste;
	private Matriz matriz;	
	private String criterio ;
	private String action = null;
	
	final static Logger logger = Logger.getLogger(PosteController.class);
	
	@PostConstruct
	public void init(){
		
		
		
		
		 
		// lista de matrices
		nodoList =  nodosService.getNodoList();
		if(nodoList==null){
			nodoList = new ArrayList<>();
		}
		Nodo  nodo0  = new Nodo(SELECCIONE_VALUE, SELECCIONE_LABEL);
		nodoList.add(nodo0);
		
		Collections.sort(nodoList, new NodoComparator());
		// 
	

		// lista de matrices
		
		
		posteList =  posteService.getPosteList();
		poste = new Poste();
		nodo =  new Nodo();
		poste.setNodo(new Nodo());
		mostrar();
		
	}
	
	
	@Override
	public void mostrar() {
		
		Integer codigoNodo =  (nodo==null?null:nodo.getCodigoNodo());
		criterio = (criterio==null || criterio.trim().equals(""))?null:criterio;
		posteList =  posteService.getPosteList(codigoNodo, criterio);
		
	}

	@Override
	public void nuevo() {
		 action =  NUEVO;
		 logger.info(" ### nuevo nodo ### ");
		 poste =  new Poste();
		 
		
	}

	@Override
	public void editar() {
		
		action =  EDITAR;
		logger.info("## editando ##");
		logger.info( "codigoPoste :  "+facesContext.getExternalContext().getRequestParameterMap().get("codigoPoste"));
		
		Integer codigoPoste = Integer.parseInt(  facesContext.getExternalContext().getRequestParameterMap().get("codigoPoste") );
		this.poste =  posteService.getPoste(codigoPoste);
		
	}

	@Override
	public void grabar() {
		
		logger.info(" grabar accion : "+action);
		try{
			
			FacesMessage msg = null;
			Response response = null;
			
			if(action.equals(NUEVO)){
			    response = posteService.registrar(poste);
			}else if (action.equals(EDITAR)) {
				 response = posteService.modificar(poste);
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
		
		if(object instanceof Poste){
			
			Poste nodo =  (Poste)object;
			Response response = posteService.eliminar(nodo);
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
	

	public Nodo getNodo() {
		return nodo;
	}

	public void setNodo(Nodo nodo) {
		this.nodo = nodo;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}


	public String getCriterio() {
		return criterio;
	}


	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}


	public List<Nodo> getNodoList() {
		return nodoList;
	}


	public void setNodoList(List<Nodo> nodoList) {
		this.nodoList = nodoList;
	}


	public List<Poste> getPosteList() {
		return posteList;
	}


	public void setPosteList(List<Poste> posteList) {
		this.posteList = posteList;
	}


	public Poste getPoste() {
		return poste;
	}


	public void setPoste(Poste poste) {
		this.poste = poste;
	}


	
	
	
	
	

}
