package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.SolicitudServicioDao;
import pe.com.cablered.mistia.dao.SolicitudServicioEstadoDao;
import pe.com.cablered.mistia.model.SolicitudServicioEstado;


@Stateless
@LocalBean
public class SolicitudServicioEstadoService {
	
	
	@Inject
	private SolicitudServicioEstadoDao solicitudServicioEstadoDao;
	
	
	
	
	public List<SolicitudServicioEstado> getSolicitudServicioEstadoList(Long numeroSolicitud){
		
		return  solicitudServicioEstadoDao.getSolicitudServicioEstadoList(numeroSolicitud);
		
	}
	
	
	
}
