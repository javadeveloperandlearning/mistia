package pe.com.cablered.mistia.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.ServicioDao;
import pe.com.cablered.mistia.model.Servicio;

@Stateless
public class ServicioService {
	
	@Inject
	private ServicioDao servicioDao;

	public Servicio getServicioPorPaquete(Integer codigoTipoPaquete, Integer codigoCantTele) {

		return servicioDao.getServicioPorPaquete(codigoTipoPaquete, codigoCantTele);
	}

	public Servicio getServicioPorVelocidad(Integer codigoTipoVeloInternet) {
		// TODO Auto-generated method stub
		return servicioDao.getServicioPorVelocidad(codigoTipoVeloInternet);
	}

}
