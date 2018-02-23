package pe.com.cablered.mistia.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.GrupoAtencionDao;

@Stateless
@LocalBean
public class GrupoAtencionService {
	
	
	@Inject
	private GrupoAtencionDao grupoAtencionDao;
	
	
	
	
	
	

}
