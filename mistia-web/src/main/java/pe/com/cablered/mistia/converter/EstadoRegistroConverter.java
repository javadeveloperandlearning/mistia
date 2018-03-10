package pe.com.cablered.mistia.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import pe.com.cablered.seguridad.model.EstadoRegistro;
import pe.com.cablered.seguridad.service.EstadoRegistroService;

@ManagedBean(name = "estadoRegistroConverter")
@RequestScoped
public class EstadoRegistroConverter implements Converter {

	@Inject
	EstadoRegistroService estadoRegistroManager;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(value !=null &&  value.trim().length()>0){
			//System.out.println(" ESTADO CONVERTER :"+value);
			EstadoRegistro estadoRegistro = estadoRegistroManager.getEstadoRegistro(value.trim());
			return estadoRegistro;
		}else{
			return null;
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) {
		if (object != null) {
			//return ((EstadoRegistro)object).getCodEsta().toString();
			return object.toString();
		} else {
			return null;
		}

	}

}
