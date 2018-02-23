package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.VehiculoMarcaDao;
import pe.com.cablered.mistia.dao.VehiculoModeloDao;
import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.model.VehiculoMarca;
import pe.com.cablered.mistia.model.VehiculoModelo;

@Stateless
public class VehiculoMarcaService {
	
	@Inject
	private VehiculoMarcaDao vehiculoMarcaDao;
	
	
	public List<VehiculoMarca> getVehiculoMarcaList(){
		
		return vehiculoMarcaDao.getVehiculoMarcaList();
	}


	public VehiculoMarca getVehiculoMarca(String descripcion) {
		return vehiculoMarcaDao.getVehiculoMarca(descripcion);
	}
	
	
	
	

}
