package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.VehiculoModeloDao;
import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.model.VehiculoModelo;

@Stateless
public class VehiculoModeloService {
	
	@Inject
	private VehiculoModeloDao vehiculoModeloDao;
	
	
	public List<VehiculoModelo> getVehiculoModeloList(){
		
		return vehiculoModeloDao.getVehiculoModeloList();
	}


	public VehiculoModelo getVehiculoModelo(String descripcion) {		
		return vehiculoModeloDao.getVehiculoModelo(descripcion);
	}
	
	

}
