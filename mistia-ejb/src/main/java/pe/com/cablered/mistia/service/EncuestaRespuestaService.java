package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.EncuestaPreguntaDao;
import pe.com.cablered.mistia.dao.EncuestaRespuestaDao;
import pe.com.cablered.mistia.model.Encuesta;
import pe.com.cablered.mistia.model.EncuestaPregunta;
import pe.com.cablered.mistia.model.EncuestaRespuesta;

@Stateless
@LocalBean
public class EncuestaRespuestaService {
	
	
	@Inject
	private EncuestaRespuestaDao encuestaRespuestaDao;
	
	public List<EncuestaRespuesta> getEncuestaRespuestaList(Encuesta encuesta){
		return encuestaRespuestaDao.getEncuestaRespuestaList(encuesta);
	}

	public  EncuestaRespuesta getEncuestaRespuesta(Integer numeroEncuesta, String respuesta) {
		// TODO Auto-generated method stub
		return encuestaRespuestaDao.getEncuestaRespuesta(numeroEncuesta, respuesta);
	}

}
