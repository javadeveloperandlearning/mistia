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
import pe.com.cablered.mistia.model.sort.MatrizComparator;
import pe.com.cablered.mistia.service.MatrizService;
import pe.com.cablered.mistia.service.NodosService;
import pe.com.cablered.mistia.service.Response;

@ManagedBean(name = "nodo")
@SessionScoped
public class NodoController  implements Serializable, Mantenible{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -32348959599327908L;

	@Inject
	private NodosService nodosService;
	
	@Inject
	private MatrizService  matrizService;
	
	
	@Inject
	private FacesContext facesContext;
	
	private List<Matriz> matrizList;
	
	private List<Nodo> nodoList;
	
	
	
	private Nodo nodo;
	private Matriz matriz;	
	private String criterio ;
	private String action = null;
	
	final static Logger logger = Logger.getLogger(NodoController.class);
	
	@PostConstruct
	public void init(){
	
		
		// lista de matrices
		matrizList =  matrizService.getMatrizList();
		if(matrizList==null){
			matrizList = new ArrayList<>();
		}
		Matriz matriz0  = new Matriz(SELECCIONE_VALUE, SELECCIONE_LABEL);
		matrizList.add(matriz0);
		
		Collections.sort(matrizList, new MatrizComparator());
		// 
		nodo =  new Nodo();
		nodo.setMatriz(new Matriz());
		
		mostrar();
		
	}
	
	
	@Override
	public void mostrar() {
		
		Integer codigoMatriz =  (matriz==null?null:matriz.getCodigoMatriz());
		criterio = (criterio==null || criterio.trim().equals(""))?null:criterio;
		setNodoList(nodosService.getNodoList(codigoMatriz,  criterio));
	}

	@Override
	public void nuevo() {
		 action =  NUEVO;
		 logger.info(" ### nuevo nodo ### ");
		 nodo =  new Nodo();
		 
		
	}

	@Override
	public void editar() {
		
		action =  EDITAR;
		logger.info("## editando ##");
		logger.info( "codigoNodo :  "+facesContext.getExternalContext().getRequestParameterMap().get("codigoNodo"));
		
		String _codigoNodo =  facesContext.getExternalContext().getRequestParameterMap().get("codigoNodo");
		if(_codigoNodo!=null){
			Integer codigoNodo = Integer.parseInt(_codigoNodo);
			this.nodo =  nodosService.getNodo(codigoNodo);
		}
	}

	@Override
	public void grabar() {
		
		logger.info(" grabar accion : "+action);
		try{
			
			FacesMessage msg = null;
			Response response = null;
			if(action.equals(NUEVO)){
			
			    response = 	nodosService.registrar(nodo);
			

			}else if (action.equals(EDITAR)) {
				
				 response = nodosService.modificar(nodo);
				 
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
		
		if(object instanceof Nodo){
			
			Nodo nodo =  (Nodo)object;
			Response response = nodosService.eliminar(nodo);
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

	public List<Matriz> getMatrizList() {
		return matrizList;
	}

	public void setMatrizList(List<Matriz> matrizList) {
		this.matrizList = matrizList;
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


	
	
	
	
	

}
