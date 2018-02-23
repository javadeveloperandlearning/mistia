package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import pe.com.cablered.mistia.dao.EncuestaSolicitudResultadoDao;
import pe.com.cablered.mistia.model.Encuesta;
import pe.com.cablered.mistia.model.EncuestaPregunta;

import pe.com.cablered.mistia.model.EncuestaRespuesta;
import pe.com.cablered.mistia.model.EncuestaSolicitudResultado;

@Stateless
@LocalBean
public class encuestaSolicitudResultadoService {
	
	
	final static Logger logger = Logger.getLogger(encuestaSolicitudResultadoService.class);
	
	@Inject
	private EncuestaSolicitudResultadoDao encuestaSolicitudResultadoDao;

	
	@Inject
	private EncuestaRespuestaService encuestaRespuestaService;

	public Response calificar(Integer numeroEncuesta, Long numeroSolicitud, Integer numeroPregunta , String respuesta) {
	
		Response response = new Response(Response.OK, Response.MSG_OK);
		try{

	
				EncuestaRespuesta er =  encuestaRespuestaService.getEncuestaRespuesta(numeroEncuesta, respuesta );
				logger.info(" respuesta encontrada : "+respuesta);
						
				Double factor =   er.getFactor();
				Double peso  =    er.getPeso();
				Double puntaje =  peso * factor;
				Integer numeroRespuesta =  er.getId().getNumeroRespuesta();
				EncuestaSolicitudResultado  esr = new EncuestaSolicitudResultado(numeroEncuesta,numeroSolicitud,numeroPregunta,numeroRespuesta );
				esr.setPeso(peso);
				esr.setFactor(factor);
				esr.setPuntaje(puntaje);
						 
				if(encuestaSolicitudResultadoDao.find(esr.getId())==null){
					logger.info(" registrando : "+respuesta);
					encuestaSolicitudResultadoDao.create(esr);
				}
						 

		}catch(Exception e){
			
			response = new Response(Response.ERROR, Response.MSG_ERROR);
			logger.info(e);
			e.printStackTrace();
			
		}

		
		return response;
	}
	
	
	
	
	
	
	

}
