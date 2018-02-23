package pe.com.cablered.mistia.service;

import pe.com.cablered.mistia.model.ObjectBean;

public abstract  class AbstracMistiaSevice {

	public abstract  Response registrar( ObjectBean objectBean);
	
	public abstract  Response modificar( ObjectBean objectBean);
	
	public abstract  Response eliminar( ObjectBean objectBean);
	

}
