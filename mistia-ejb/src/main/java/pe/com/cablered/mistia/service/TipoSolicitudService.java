package pe.com.cablered.mistia.service;

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
		
		return tipoSolicitudDao.getTipoSolicitudList();
	}
	
	

}
