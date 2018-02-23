package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.DistritoDao;
import pe.com.cablered.mistia.model.Distrito;

@Stateless
@LocalBean
public class DistritoService {
	
	
	
	@Inject
	private DistritoDao distritoDao;
	
	public List<Distrito> getDistritoList(){
		
		return distritoDao.getDistritoList();
	}

	
	
	
	public List<Distrito> getDistritoList(int codigoProvincia) {

		return distritoDao.getDistritoList(codigoProvincia);
	}




	public Distrito getDistrito(String descripcion) {
		
		return distritoDao.getDistrito(descripcion);
	}
	
	

}
