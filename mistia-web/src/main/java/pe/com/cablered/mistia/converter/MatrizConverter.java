package pe.com.cablered.mistia.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import pe.com.cablered.mistia.model.Matriz;
import pe.com.cablered.mistia.service.MatrizService;
import pe.com.eb.model.Modulo;



@ManagedBean(name="matrizConverter")
@RequestScoped
public class MatrizConverter implements Converter{
	
	
	@Inject
	MatrizService matrizService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		
		if(value !=null &&  value.trim().length()>0){
			Matriz matriz  =  matrizService.getMatriz(value.trim());
			return  matriz;
			
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
