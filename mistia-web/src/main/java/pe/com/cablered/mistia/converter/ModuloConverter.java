package pe.com.cablered.mistia.converter;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import pe.com.cablered.seguridad.model.Modulo;
import pe.com.cablered.seguridad.service.ModuloService;



@ManagedBean(name="moduloConverter")
@RequestScoped
public class ModuloConverter implements Converter{
	
	@EJB
	ModuloService  moduloManager;

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if(value !=null &&  value.trim().length()>0){
			
			Modulo modulo  =  moduloManager.getModulo(value.trim());
			return  modulo;
			
		}else{
			return null;
		}		
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if(object !=null){
			return object.toString();
		}else{
			return null;	
		}
	}

}
