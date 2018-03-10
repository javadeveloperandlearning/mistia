package pe.com.cablered.mistia.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import pe.com.cablered.mistia.model.VehiculoMarca;
import pe.com.cablered.mistia.model.VehiculoModelo;
import pe.com.cablered.mistia.service.VehiculoMarcaService;
import pe.com.cablered.mistia.service.VehiculoModeloService;




@ManagedBean(name="vehiculoMarcaConverter")
@RequestScoped
public class VehiculoMarcaConverter implements Converter {

	@Inject
	private VehiculoMarcaService  vehiculoMarcaService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value !=null &&  value.trim().length()>0){
			VehiculoMarca vehiculoMarca  =  vehiculoMarcaService.getVehiculoMarca(value.trim());
			return  vehiculoMarca;
			
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
