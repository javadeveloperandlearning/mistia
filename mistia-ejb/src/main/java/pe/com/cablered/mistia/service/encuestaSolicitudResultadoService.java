package pe.com.cablered.mistia.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import pe.com.cablered.mistia.dao.EncuestaSolicitudResultadoDao;
import pe.com.cablered.mistia.dao.TecnicoCompetenciaDetalleDao;
import pe.com.cablered.mistia.dao.TecnicoDao;
import pe.com.cablered.mistia.model.Competencia;
import pe.com.cablered.mistia.model.Encuesta;
import pe.com.cablered.mistia.model.EncuestaPregunta;

import pe.com.cablered.mistia.model.EncuestaRespuesta;
import pe.com.cablered.mistia.model.EncuestaSolicitudResultado;
import pe.com.cablered.mistia.model.Tecnico;
import pe.com.cablered.mistia.model.TecnicoCompetenciaDetalle;

@Stateless
@LocalBean
public class encuestaSolicitudResultadoService {

    final static Logger logger = Logger.getLogger(encuestaSolicitudResultadoService.class);

    @Inject
    private EncuestaSolicitudResultadoDao encuestaSolicitudResultadoDao;

    @Inject
    private TecnicoDao tecnicoDao;

    @Inject
    private TecnicoCompetenciaDetalleDao tecnicoCompetenciaDetalleDao;

    @Inject
    private EncuestaRespuestaService encuestaRespuestaService;

    public Response calificar(Integer numeroEncuesta, Long numeroSolicitud, EncuestaPregunta encuestaPregunta, String respuesta) {

        Response response = new Response(Response.OK, Response.MSG_OK);
        try {

            EncuestaRespuesta er = encuestaRespuestaService.getEncuestaRespuesta(numeroEncuesta, respuesta);
            logger.info(" respuesta encontrada : " + respuesta);

            Double factor = er.getFactor();
            Double peso = er.getPeso();
            Double puntaje = peso * factor;
            Integer numeroRespuesta = er.getId().getNumeroRespuesta();
            EncuestaSolicitudResultado esr = new EncuestaSolicitudResultado(numeroEncuesta, numeroSolicitud, encuestaPregunta.getId().getNumeroPregunta(), numeroRespuesta);
            esr.setPeso(peso);
            esr.setFactor(factor);
            esr.setPuntaje(puntaje);

            if (encuestaSolicitudResultadoDao.find(esr.getId()) == null) {
                logger.info(" registrando : " + respuesta);
                encuestaSolicitudResultadoDao.create(esr);
            }

            //actualizand las competencias de los usuarios
            List<Tecnico> tecnicoList = tecnicoDao.getTecnicoList(numeroSolicitud);
            for (Tecnico tecnico : tecnicoList) {
                logger.info("tecnico  : "+tecnico.getCodigoTecnico());
                List<TecnicoCompetenciaDetalle> detalles = tecnicoCompetenciaDetalleDao.getTecnicoCompetenciaDetalleList(tecnico.getCodigoTecnico());
                
                if (detalles != null && !detalles.isEmpty()) {
                    
                    for (TecnicoCompetenciaDetalle detalle : detalles) {
                        if (detalle.getCompetencia().getCodigoCompetencia() == encuestaPregunta.getCodigoCompetencia()) {
                            logger.info("CodigoCompetencia :" + encuestaPregunta.getCodigoCompetencia());
                            logger.info("puntaje :" + puntaje);
                            logger.info("grado old :" + detalle.getGradoCompetencia());
                            double oldGrado = detalle.getGradoCompetencia().doubleValue();
                            BigDecimal  gradoCompetencia  = detalle.getGradoCompetencia().add(new BigDecimal(puntaje));
                            if (gradoCompetencia.doubleValue()>1.0) {
                                gradoCompetencia = new BigDecimal(1.0);
                            }
                            
                            if( gradoCompetencia.doubleValue() < 0.0){
                                gradoCompetencia =  new BigDecimal(0.0);
                            }
                            logger.info("grado add:" + gradoCompetencia);
                            detalle.setGradoCompetencia(gradoCompetencia);
                            tecnicoCompetenciaDetalleDao.update(detalle);
                            break;
                        }
                    }
                    
                } else {
                    
                    TecnicoCompetenciaDetalle tecnicoCompetenciaDetalle =  new TecnicoCompetenciaDetalle();
                    tecnicoCompetenciaDetalle.setTecnico(tecnico);
                    tecnicoCompetenciaDetalle.setCompetencia(new Competencia(encuestaPregunta.getCodigoCompetencia()));
                    tecnicoCompetenciaDetalle.setGradoCompetencia(new BigDecimal(puntaje));
                    tecnicoCompetenciaDetalleDao.create(tecnicoCompetenciaDetalle);
                }
            }

        } catch (Exception e) {

            response = new Response(Response.ERROR, Response.MSG_ERROR);
            logger.info(e);
            e.printStackTrace();

        }

        return response;
    }

}
