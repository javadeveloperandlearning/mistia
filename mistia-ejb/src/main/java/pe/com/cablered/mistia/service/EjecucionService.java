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

import pe.com.cablered.mistia.dao.CuadrillasDetalleDao;
import pe.com.cablered.mistia.dao.PlanTrabajoDao;
import pe.com.cablered.mistia.dao.PlanTrabajoDetalleDao;
import pe.com.cablered.mistia.dao.SolicitudServicioDao;
import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.PlanTrabajo;
import pe.com.cablered.mistia.model.PlanTrabajoDetalle;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.util.ConstantBusiness;



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


	public Response getPlanTrabajo(Date fecPrgn, String  Usuario){
		


		
		Response response = new Response(Response.OK, Response.MSG_OK);
		
		try{
			PlanTrabajo planTrabajo =  null;
			Integer codigoTecnico =  1;
			Cuadrilla cuadrilla =  cuadrillasDetalleDao.getCuadrillaPorTecnico(fecPrgn, codigoTecnico);
			System.out.println("cuadrilla : "+cuadrilla);
			Long numeroCuadrilla = cuadrilla==null?0l:cuadrilla.getNumeroCuadrilla() ; 		
			planTrabajo =   planTrabajoDao.getPlanTrabajoPorCualdrilla(fecPrgn, numeroCuadrilla);
			
			List<PlanTrabajoDetalle> planTrabajoDetallelist =  planTrabajoDetalleDao.getPlanTrabajoDetalleList(planTrabajo.getNumeroPlanTrabajo(), ConstantBusiness.ESTADO_PROGRAMACION_EJECUCION);
			response.setData(planTrabajo);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
			response = new Response(Response.ERROR, Response.MSG_ERROR);
		}
		
		
		return response;
		
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


	public Response registrarEvidencia(Long numeroSolicitud, String file) {

		try {
			
			byte[] bytes =  	Base64.getDecoder().decode(file);
			File f =  new File("/temp");		
			FileOutputStream fos  =  new FileOutputStream(f);
			fos.write(bytes);
			fos.flush();
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  	System.out.println(file);
	
		return null;
	}



	
	
	// actualizar detalle de plan de trabajo

}
