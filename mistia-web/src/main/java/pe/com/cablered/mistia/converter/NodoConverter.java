package pe.com.cablered.mistia.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import pe.com.cablered.mistia.model.Matriz;
import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.service.MatrizService;
import pe.com.cablered.mistia.service.NodosService;



@ManagedBean(name="nodoConverter")
@RequestScoped
public class NodoConverter implements Converter{
	
	@Inject
	private NodosService nodosService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value !=null &&  value.trim().length()>0){
			Nodo nodo  =  nodosService.getNodo(value.trim());
			return  nodo;
			
		}else{
			return null;
		}
	}

	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object !=null){
			return object.toString();
		}else{
			return null;	
		}
	}

}
