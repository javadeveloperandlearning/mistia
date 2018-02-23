package pe.com.cablered.mistia.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.service.DistritoService;




@ManagedBean(name="distritoConverter")
@RequestScoped
public class DistritoConverter implements Converter {
	
	
	
	@Inject
	private DistritoService distritoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value !=null &&  value.trim().length()>0){
			Distrito distrito  =  distritoService.getDistrito(value.trim());
			return  distrito;
			
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
