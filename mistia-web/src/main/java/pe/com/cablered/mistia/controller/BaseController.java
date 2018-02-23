package pe.com.cablered.mistia.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pe.com.cablered.mistia.model.ObjectBean;
import pe.com.cablered.mistia.service.AbstractSevice;
import pe.com.cablered.mistia.service.Response;

public abstract class BaseController<T extends ObjectBean> {

	protected String action;
	protected List<T> list;
	protected ObjectBean object;
	private String headerpopup;
	
	
	public static final  String NUEVO = "1";
	public static final String EDITAR = "2";
	
	public static final  String NUEVO_KEY = "NUEVO";
	public static final String EDITAR_KEY = "EDITAR";
	public static final String SELECCIONE_LABEL = "--Seleccione--"; 
	public static final int SELECCIONE_VALUE = 0; 
	
	
	public static final String TODOS_LABEL = "--Todos--"; 
	public static final int TODOS_VALUE = 0; 
	
	
	protected  abstract FacesContext getFacesContext();
	protected abstract  AbstractSevice getService();
	
	/*public abstract ObjectBean getObject();

	public abstract void setObject(ObjectBean object) ;*/
	

	public void nuevo() {
		action = NUEVO;
	}

	public void editar(ObjectBean object) {
		action = EDITAR;
		if (object != null && list != null) {
			int idx = list.indexOf(object);
			if (idx != -1) {
				//this.object = (ObjectBean) list.get(idx);
				setObject((ObjectBean) list.get(idx));
			}
		}
	}

	public void grabar() {
		try {

			FacesMessage msg = null;
			Response response = null;

			if (object != null) {
				
				if (action.equals(NUEVO)) {
					response = getService().registrar(object);
				} else if (action.equals(EDITAR)) {
					response = getService().modificar(object);
				}
				
				if(response!=null && response.getCodigo()==Response.OK){
					mostrar();
				}
				
				mostrarMensaje(response);
			} else {
				mostrarMensaje(new Response(Response.ERROR, "Objeto no válido"));
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void eliminar(ObjectBean object) {

		if (object != null) {
			Response response = getService().eliminar(object);
			if(response!=null && response.getCodigo()==Response.OK){
				mostrar();
			}
			mostrarMensaje(response);
			
		} else {

			mostrarMensaje(new Response(Response.ERROR, "Objeto no válido"));
		}

	}

	public abstract void mostrar();
	
	public abstract void reset();
	
	public abstract void limpiar();

	public void mostrarMensaje(Response response) {
		FacesMessage msg = new FacesMessage(response.getMensaje());
		if (response != null && response.getCodigo() == Response.OK) {
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		} else {
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			RequestContext.getCurrentInstance().
            addCallbackParam("notValid", true);
			RequestContext.getCurrentInstance().
            addCallbackParam("mensaje", response.getMensaje());
		}
		getFacesContext().addMessage(null, msg);
	}



	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public ObjectBean getObject() {
		return object;
	}

	public void setObject(ObjectBean object) {
		this.object = object;
	}
	


	public String getHeaderpopup() {
		return headerpopup;
	}


	public void setHeaderpopup(String headerpopup) {
		this.headerpopup = headerpopup;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
