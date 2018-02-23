package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.ProvinciaDao;
import pe.com.cablered.mistia.model.Provincia;

@Stateless
public class ProvinciaService {

	
	@Inject
	private ProvinciaDao provinciaDao;
	
	
	
	
	
	public List<Provincia> getProvinciaList(int codigoDepartamento) {

		return provinciaDao.getProvinciaList(codigoDepartamento);
	}

}
