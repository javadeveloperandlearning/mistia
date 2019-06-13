package pe.com.cablered.mistia.ws.rest;

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

import pe.com.cablered.mistia.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import pe.com.cablered.mistia.model.SolicitudServicioEvidencia;
import static pe.com.cablered.mistia.ws.rest.UsuarioRest.logger;
import pe.com.cablered.seguridad.model.Usuario;

@Named
@Path("/ejecucion")
@RequestScoped
public class EjecucionRest {

    final static Logger logger = Logger.getLogger(EjecucionRest.class);
    @Inject
    private EjecucionService ejecucionService;

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
    @POST
    @Path("/planTrabajo.html")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Map getPlanTrabajo(Usuario usuario) {

        logger.info(" metodo getPlanTrabajo");
        logger.info(usuario.toString());
        PlanTrabajo planTrabajo = ejecucionService.getPlanTrabajo( usuario);
        logger.info(" mi plan : "+planTrabajo);
        return RequestUtil.toMap(planTrabajo);
    }

    @GET
    @POST
    @Path("/plantrabajodetallelist.html")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getplanTrabajoDetalleList(@Context HttpServletRequest request) {
        logger.info("metodo : getplanTrabajoDetalleList ");

        Response response = new Response(Response.OK, Response.MSG_OK);
        String _numeroPlanTrabajo = request.getParameter("numeroPlanTrabajo");

        logger.info(" Plan de trabajo ## " + _numeroPlanTrabajo);

        if (_numeroPlanTrabajo != null) {
            //Long numeroPlanTrabajo  =  1l;
            Long numeroPlanTrabajo = Long.parseLong(_numeroPlanTrabajo);
            List<PlanTrabajoDetalle> planTrabajoDetalleList = ejecucionService.planTrabajoDetalleList(numeroPlanTrabajo);
            List<Map> _planTrabajoDetalleList = new ArrayList();
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantBusiness.FORMAT_DATE_TIME);

            for (PlanTrabajoDetalle pd : planTrabajoDetalleList) {
                Map map = new HashMap<String, String>();

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

                map.put("horaInicio", sdf.format(pd.getHoraInicio()));
                map.put("horaFin", sdf.format(pd.getHoraFin()));

                map.put("indatnd", pd.getIndAtnd() == null ? 0 : pd.getIndAtnd()); //para el detalle

                map.put("obsetvacion", pd.getObservacion());
                map.put("codMotivo", pd.getCodMotivo());
                map.put("latitud", pd.getSolicitudServicio().getContratoServicio().getLatitud());
                map.put("longitud", pd.getSolicitudServicio().getContratoServicio().getLongitud());

                _planTrabajoDetalleList.add(map);
            }

            response.setData(_planTrabajoDetalleList);

        } else {

            logger.info(" Número de plan de trabajo no válido ");
            response.setMensaje(" Número de plan de trabajo no válido ");
        }

        return response;
    }

    //public Response actualizarEstadoPlanDetalle(@Context HttpServletRequest request) {
    @GET
    @POST
    @Path("/actualizarestadoplandetalle.html")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarEstadoPlanDetalle(PlanTrabajoDetalle planTrabajoDetalle) {
        logger.info("metodo : actualizarEstadoPlanDetalle ");

        Response response = null;

        try {

            //String json = request.getParameter("json");
            Gson gson = new Gson();
            //PlanTrabajoDetalle pd = gson.fromJson(json, PlanTrabajoDetalle.class); 
            //logger.info("JSON :"+json);
            response = ejecucionService.actualizarDetallePlanTrabajo(planTrabajoDetalle);

        } catch (Exception e) {
            e.printStackTrace();

            response = new Response(Response.OK, Response.MSG_OK);
        }

        return response;
    }

    @GET
    @POST
    @Path("/registrarevidencia.html")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarEvidencia(SolicitudServicioEvidencia  solicitudServicioEvidencia) {
        
        logger.info(" metodo : registrarEvidencia");

        Response response = null;

        try {
            /*
            
                Long numeroSolicitud = Long.parseLong(request.getParameter("numeroSolicitud"));
                String file = request.getParameter("file");
            
            */

            response = ejecucionService.registrarEvidencia(solicitudServicioEvidencia);

        } catch (Exception e) {
            e.printStackTrace();

            response = new Response(Response.OK, Response.MSG_OK);
        }

        return response;
    }

    @GET
    @Path("/plantrabajousuario.html")
    @Produces(MediaType.APPLICATION_JSON)
    public PlanTrabajo planTrabajoUsuario() {
        logger.info(" metodo  planTrabajoUsuario");

        PlanTrabajo planTrabajo = null;

        try {

            Usuario usuario = new Usuario();
            usuario.setCodPers(3);
            Response response = new Response(Response.OK, Response.MSG_OK);
            planTrabajo = ejecucionService.getPlanTrabajo(usuario);

            logger.info(planTrabajo);
            response.setData(planTrabajo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planTrabajo;
    }

}
