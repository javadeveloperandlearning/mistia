package pe.com.cablered.mistia.service;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.TipoSolicitudDao;
import pe.com.cablered.mistia.model.TipoSolicitud;

@Stateless
public class TipoSolicitudService {
	
	
	
	@Inject
	TipoSolicitudDao tipoSolicitudDao;
	
	public List<TipoSolicitud> getTipoSolicitudList(){
		
		List<TipoSolicitud> tipoSolicitudList = Collections.emptyList();
		
		try{
		
			tipoSolicitudList =   tipoSolicitudDao.getTipoSolicitudList();
		
		}catch (Exception e) {

		}
		
		return tipoSolicitudList;
	}
	
	

}
