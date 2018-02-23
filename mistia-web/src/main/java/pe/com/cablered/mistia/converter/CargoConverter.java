package pe.com.cablered.mistia.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import pe.com.cablered.mistia.model.Cargo;
import pe.com.cablered.mistia.service.CargoService;



@ManagedBean(name="cargoConverter")
@RequestScoped
public class CargoConverter implements Converter {

	@Inject
	private CargoService cargoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value !=null &&  value.trim().length()>0){
			Cargo cargo  =  cargoService.getCargo(value.trim());
			return  cargo;
			
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
