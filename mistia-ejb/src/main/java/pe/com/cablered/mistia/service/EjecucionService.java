package pe.com.cablered.mistia.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.CuadrillasDetalleDao;
import pe.com.cablered.mistia.dao.PlanTrabajoDao;
import pe.com.cablered.mistia.dao.PlanTrabajoDetalleDao;
import pe.com.cablered.mistia.dao.SolicitudServicioDao;
import pe.com.cablered.mistia.dao.SolicitudServicioEvidenciaDao;
import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.PlanTrabajo;
import pe.com.cablered.mistia.model.PlanTrabajoDetalle;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.model.SolicitudServicioEvidencia;
import pe.com.cablered.mistia.util.ConstantBusiness;
import pe.com.cablered.seguridad.model.Usuario;
import pe.com.cablered.seguridad.service.UsuarioService;



@Stateless
@LocalBean
public class EjecucionService {
	
	
	@Inject
	private PlanTrabajoDao planTrabajoDao;
	
	@Inject
	private PlanTrabajoDetalleDao planTrabajoDetalleDao;
	
	@Inject
	private CuadrillasDetalleDao cuadrillasDetalleDao;
	
	@Inject
	private SolicitudServicioDao solicitudServicioDao;
        
        @Inject
        private SolicitudServicioEvidenciaDao solicitudServicioEvidenciaDao;
        
        
        
        final static Logger logger = Logger.getLogger(EjecucionService.class);


	public PlanTrabajo getPlanTrabajo(Date fecPrgn, String  Usuario){
		logger.info("metodo :  getPlanTrabajo ");

                PlanTrabajo planTrabajo =  null;
		
		//Response response = new Response(Response.OK, Response.MSG_OK);
		
		try{
			
			Integer codigoTecnico =  1;
			Cuadrilla cuadrilla =  cuadrillasDetalleDao.getCuadrillaPorTecnico(fecPrgn, codigoTecnico);
			logger.info("cuadrilla : "+cuadrilla.toString());
			Long numeroCuadrilla = cuadrilla==null?0l:cuadrilla.getNumeroCuadrilla() ; 		
			planTrabajo =   planTrabajoDao.getPlanTrabajoPorCualdrilla(fecPrgn, numeroCuadrilla);
			logger.info("planTrabajo :"+planTrabajo);
			//List<PlanTrabajoDetalle> planTrabajoDetallelist =  planTrabajoDetalleDao.getPlanTrabajoDetalleList(planTrabajo.getNumeroPlanTrabajo(), ConstantBusiness.ESTADO_PROGRAMACION_EJECUCION);
			//response.setData(planTrabajo);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			//response = new Response(Response.ERROR, Response.MSG_ERROR);
		}
		
		
		return planTrabajo;
		
	} 
	

	public List<PlanTrabajoDetalle>   planTrabajoDetalleList(Long numeroPlanTrabajo) {
	
		List<PlanTrabajoDetalle> planTrabajoDetallelist  =  null;
		try{
			
			//obtiene las listas de los detalles de los planes de trabajo por estado 
			planTrabajoDetallelist =  planTrabajoDetalleDao.getPlanTrabajoDetalleList(numeroPlanTrabajo, ConstantBusiness.ESTADO_PROGRAMACION_EJECUCION);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return planTrabajoDetallelist;
	}
	
	
	// actualizacion de plan de trabajo
	public Response actualizarDetallePlanTrabajo(PlanTrabajoDetalle planTrabajoDetalle){
	
		Response res1 =  planTrabajoDetalleDao.actualizarDetalle(planTrabajoDetalle);
		if(res1!=null && res1.getCodigo()== Response.OK){
			SolicitudServicio solicitudServicio  =  planTrabajoDetalle.getSolicitudServicio();
			Response res2 =  solicitudServicioDao.actualizarEstado(solicitudServicio); 
		}
		return res1;
	}


	public Response registrarEvidencia(SolicitudServicioEvidencia s) {
                logger.info("metodo :  registrarEvidencia ");
                
                Response response =   new Response(Response.OK, Response.MSG_OK);
		try {
                    
                        s.setId(s.getSolicitudServicio().getNumeroSolicitud(),  s.getNumeroSecuencial());
                        String ruta =  "/home/javadeveloper/proyectos/imgs/"+s.getNombre();
                        s.setRuta(ruta);
                        solicitudServicioEvidenciaDao.create(s);
                       
                        logger.info(" file : "+s.getFile());
			byte[] bytes = Base64.getDecoder().decode(s.getFile());
			File f =  new File(ruta);		
			FileOutputStream fos  =  new FileOutputStream(f);
			fos.write(bytes);
			fos.flush();
                        fos.close();
                        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}



	
	
	// actualizar detalle de plan de trabajo

    public PlanTrabajo getPlanTrabajo(Usuario usuario) {
        PlanTrabajo planTrabajo = null;
        try{
            planTrabajo =  planTrabajoDao.getPlanTrabajo(usuario);
        }catch(Exception e ){
            e.printStackTrace();
        }
        
        return planTrabajo;
    }



}
