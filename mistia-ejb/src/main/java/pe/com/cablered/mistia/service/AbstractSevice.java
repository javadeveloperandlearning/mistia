package pe.com.cablered.mistia.service;

import pe.com.cablered.mistia.model.ObjectBean;

public abstract  class AbstractSevice<T extends ObjectBean> {

	public abstract  Response registrar(T t);
	
	public abstract  Response modificar( T t);
	
	public abstract  Response eliminar(T t);
	

}
