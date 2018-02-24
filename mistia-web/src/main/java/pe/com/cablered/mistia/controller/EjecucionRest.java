package pe.com.cablered.mistia.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import pe.com.cablered.mistia.model.PlanTrabajo;
import pe.com.cablered.mistia.model.PlanTrabajoDetalle;
import pe.com.cablered.mistia.service.EjecucionService;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.mistia.util.ConstantBusiness;
import pe.com.cablered.mistia.util.RequestUtil;
import pe.com.cablered.mistia.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Named
//@Path("/ejecucion")  nuevvvvv



public class EjecucionRest {

	/*
	final static Logger logger = Logger.getLogger(EjecucionRest.class);
	@Inject
	private EjecucionService  ejecucionService;
	
	@GET
	@POST
	@Path("/lista.html")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Map> lista() {

		List<Map> lista = new ArrayList<>();
		Map<String, String> map1 = new HashMap<>();
		map1.put("1", "test1");
		map1.put("2", "test2");
		map1.put("3", "test3");	
		lista.add(map1);
		return lista;

	}

	
	@GET
	@Path("/planTrabajo.html")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPlanTrabajo() {
		Response response =    ejecucionService.getPlanTrabajo(new Date (), "RCHANAME");
		return  response;
	}
	
	
	
	@GET
	@POST
	@Path("/plantrabajodetallelist.html")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getplanTrabajoDetalleList(  @Context HttpServletRequest request) {
		
		
		Response response = new Response(Response.OK, Response.MSG_OK);
		
		String _numeroPlanTrabajo  =  request.getParameter("numeroPlanTrabajo");
		logger.info(" Plan de trabajo "+_numeroPlanTrabajo);
		Long numeroPlanTrabajo  =  1l;
		
		List<PlanTrabajoDetalle>  planTrabajoDetalleList = ejecucionService.planTrabajoDetalleList(numeroPlanTrabajo);
		
		List<Map>  _planTrabajoDetalleList  = new ArrayList();
		
		SimpleDateFormat sdf  = new SimpleDateFormat(ConstantBusiness.FORMAT_DATE_TIME);
		
		for (PlanTrabajoDetalle pd : planTrabajoDetalleList) {
			Map map =  new  HashMap<String, String>();
			
			map.put("numeroPlanTrabajo", pd.getPlanTrabajo().getNumeroPlanTrabajo());
			map.put("numeroSecuencial", pd.getId().getNumeroSecuencial());
			
			map.put("numeroSolicitud", pd.getSolicitudServicio().getNumeroSolicitud());
			map.put("tag", Util.getTag(pd.getSolicitudServicio()));
			map.put("codigoEstado", 0);// para eldetalle
			map.put("gradoPrioridad", pd.getGradoPrioridad().intValue());
			map.put("codigoTipoSolicitud", pd.getSolicitudServicio().getTipoSolicitud().getCodigoTipoSolicitud());
			map.put("desTipoSolicitud", pd.getSolicitudServicio().getTipoSolicitud().getDescripcion());
			map.put("prioridad", pd.getSolicitudServicio().getTipoSolicitud().getPrioridad());
			
			map.put("direccion", pd.getSolicitudServicio().getContratoServicio().getDireccion());	
			map.put("codigoDistrito", "1");
			map.put("desDistrito", pd.getSolicitudServicio().getContratoServicio().getDistrito().getDescripcion());
			
			
			map.put("horaInicio",sdf.format(pd.getHoraInicio()));
			map.put("horaFin", sdf.format(pd.getHoraFin()));
			
			map.put("indatnd", pd.getIndAtnd()==null?0:pd.getIndAtnd()); //para el detalle
			
			map.put("obsetvacion", pd.getObservacion());
			map.put("codMotivo", pd.getCodMotivo());
			map.put("latitud", pd.getSolicitudServicio().getContratoServicio().getLatitud());
			map.put("longitud", pd.getSolicitudServicio().getContratoServicio().getLongitud());
			
			_planTrabajoDetalleList.add(map);
			
		}
		
		Gson gson =  new Gson();
		
		
		response.setData(_planTrabajoDetalleList);
		
		return  response;
	}
	
	
	
	@GET
	@POST
	@Path("/actualizarestadoplandetalle.html")
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarEstadoPlanDetalle(@Context HttpServletRequest request) {
			
		Response response  =  null;
		
		try{
			
			String json = request.getParameter(RequestUtil.JSON);
			Gson gson =  new Gson();
			PlanTrabajoDetalle pd = gson.fromJson(json, PlanTrabajoDetalle.class); 
			System.out.println("JSON :"+json);
		    response =  ejecucionService.actualizarDetallePlanTrabajo(pd);
			
		}catch(Exception e){
			e.printStackTrace();
			
			response  =   new Response(Response.OK, Response.MSG_OK);
		}
		
		return response;
	}
	
	
	
	
	@GET
	@POST
	@Path("/registrarevidencia.html")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarEvidencia(@Context HttpServletRequest request) {
			
		Response response  =  null;
		
		try{
			
			Long  numeroSolicitud = Long.parseLong(request.getParameter("numeroSolicitud"));
			String file   = request.getParameter("file");

		    response =  ejecucionService.registrarEvidencia(numeroSolicitud, file);
			
		}catch(Exception e){
			e.printStackTrace();
			
			response  =   new Response(Response.OK, Response.MSG_OK);
		}
		
		return response;
	}
	
	
	
	
	*/
	
	
	
	
	
	
	
	
}
