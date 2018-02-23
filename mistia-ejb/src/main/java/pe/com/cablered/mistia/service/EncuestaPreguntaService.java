package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.EncuestaPreguntaDao;
import pe.com.cablered.mistia.model.Encuesta;
import pe.com.cablered.mistia.model.EncuestaPregunta;

@LocalBean
@Stateless
public class EncuestaPreguntaService {
	
	
	@Inject
	private EncuestaPreguntaDao encuestaPreguntaDao;
	
	
	
	
	public List<EncuestaPregunta> getEncuestaPreguntaList(Encuesta encuesta){
		
		return encuestaPreguntaDao.getEncuestaPreguntaList(encuesta);
		
	}
	
	
	
	
	
	

}
