package pe.com.cablered.mistia.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import pe.com.cablered.mistia.model.Vehiculo;
import pe.com.cablered.mistia.model.VehiculoMarca;
import pe.com.cablered.mistia.service.VehiculoMarcaService;
import pe.com.cablered.mistia.service.VehiculoService;



@ManagedBean(name="vehiculoConverter")
@RequestScoped
public class VehiculoConverter implements Converter {

	
	@Inject
	private VehiculoService  vehiculoService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value !=null &&  value.trim().length()>0){
			Vehiculo vehiculo  =  vehiculoService.getVehiculo(value.trim());
			return  vehiculo;
			
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
