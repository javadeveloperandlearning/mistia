package pe.com.cablered.mistia.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.BubbleChartSeries;

import com.google.gson.Gson;

import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.CuadrillasDetalle;
import pe.com.cablered.mistia.model.GrupoAtencion;
import pe.com.cablered.mistia.model.GrupoAtencionDetalle;
import pe.com.cablered.mistia.model.PlanTrabajo;
import pe.com.cablered.mistia.model.PlanTrabajoDetalle;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.model.SolicitudServicioHorarioAtencion;
import pe.com.cablered.mistia.service.CuadrillaService;
import pe.com.cablered.mistia.service.ProgramacionService;
import pe.com.cablered.mistia.util.ConstantBusiness;
import pe.com.cablered.mistia.util.Util;
import pe.com.cablered.mistia.service.Response;
import static pe.com.cablered.mistia.util.ConstantBusiness.*;

@Named
@Path("/programacion")
@SessionScoped
public class ProgramacionRest implements Serializable {

    //@Inject
    //private GrupoService grupoService;
    @Inject
    private CuadrillaService CuadrillaService;

    @Inject
    private ProgramacionService programacionService;

    final static Logger logger = Logger.getLogger(ProgramacionRest.class);

    public ProgramacionRest() {
        super();

        logger.info(" #### CREANDO OBJETO ProgramacionRest #### ");
    }

    @POST
    @GET
    @Path("/generargrupos.html")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> generarGrupos(@Context HttpServletRequest request) {

        String solicitudesselect = request.getParameter("psolicitudesselect");
        String numerogrupos = request.getParameter("pnumerogrupos");
        Integer _numerogrupos = null;
        try {
            _numerogrupos = Integer.parseInt(numerogrupos);
        } catch (Exception e) {
        }

        HttpSession session = request.getSession(false);
        logger.info(" ##### INICIANDO PROCESO ##");
        //cambio
        //Map<Long, GrupoAtencion> mpGrupos  = grupoService.generarGruposAtencion(null, solicitudesselect, _numerogrupos);
        Map<Long, GrupoAtencion> mpGrupos = programacionService.generarGruposAtencion(solicitudesselect, _numerogrupos);
        logger.info(" ##### FINZALIZANDO  PROCESO ##");
        //programacionService.setMpGruposCached(mpGrupos);

        return toFormatMap(mpGrupos);

    }

    @POST
    @GET
    @Path("/generarprogramacion.html")
    @Produces(MediaType.APPLICATION_JSON)
    public Map generarProgramacion(@Context HttpServletRequest request) {

        Map<String, Object> map = new HashMap();
        String solicitudesselect = request.getParameter("psolicitudesselect");
        String numerogrupos = request.getParameter("pnumerogrupos");
        Integer _numerogrupos = null;
        logger.info(" solicitudesselect :  " + solicitudesselect);

        try {
            _numerogrupos = Integer.parseInt(numerogrupos);
        } catch (Exception e) {
        }

        HttpSession session = request.getSession(false);
        logger.info(" ##### INICIANDO PROCESO DE GENERACION ##");

        logger.info("#### generando grupos de atención");
        Map<Long, GrupoAtencion> mpGrupos = programacionService.generarGruposAtencion(solicitudesselect, _numerogrupos);

        logger.info("#### asignando grupos a cuadrillas");
        Date fecPrgn = Calendar.getInstance().getTime();

        List<PlanTrabajo> planTrabajoList = programacionService.asignarCuadrillasGrupos(fecPrgn, mpGrupos);

        logger.info(" ##### FINZALIZANDO  PROCESO ##");

        map.put("grupos", toFormatMap(mpGrupos));
        map.put("planes", planTrabajoList);

        return map;

    }

    @POST
    @GET
    @Path("/gruposgenerados.html")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> gruposGenerados(@Context HttpServletRequest request) {

        logger.info(" metodo : gruposgenerados.html");
        HttpSession session = request.getSession(false);
        Integer accion = (Integer) session.getAttribute(ACCION_PROGRAMACION);
        Long numeroProgramacion = (Long) session.getAttribute("numeroProgramacion");
        numeroProgramacion = numeroProgramacion == null ? 0 : numeroProgramacion;
        Map<Long, GrupoAtencion> mpGrupos = programacionService.getGruposAtentionGenerados(accion, numeroProgramacion);
        // pasando a datos a session
        //programacionService.setMpGruposCached(mpGrupos);
        return toFormatMap(mpGrupos);
    }

    @POST
    @GET
    @Path("/cantgrupossugeridos.html")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cantGruposSugeridos(@Context HttpServletRequest request) {

        String solicitudesselect = request.getParameter("psolicitudesselect");
        //Response res  = grupoService.getCantGruposSugeridos(solicitudesselect);
        Response res = programacionService.getCantGruposSugeridos(solicitudesselect);

        return res;

    }

    @POST
    @GET
    @Path("/detalleplan.html")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> detallePlan(@Context HttpServletRequest request) {

        String pnumerocuadrilla = request.getParameter("pnumerocuadrilla");
        HttpSession session = request.getSession(false);
        logger.info(" accion :  " + session.getAttribute(ConstantBusiness.ACCION_PROGRAMACION));
        Integer accion = (Integer) session.getAttribute(ConstantBusiness.ACCION_PROGRAMACION);
        Long numeroProgramacion = (Long) session.getAttribute("numeroProgramacion");
        numeroProgramacion = numeroProgramacion == null ? 0 : numeroProgramacion;

        Long numerocuadrilla = null;
        try {
            numerocuadrilla = Long.parseLong(pnumerocuadrilla, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<PlanTrabajoDetalle> detalles = programacionService.getPlanTrabajoDetallePorGrupo(accion, numeroProgramacion, numerocuadrilla);
        System.out.println(" cantidad detalle " + detalles.size());

        return toFormatMap(detalles);
    }

    @POST
    @GET
    @Path("/solicitudespendientes.html")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> solicitudesPendientes(@Context HttpServletRequest request) {

        List<Map<String, Object>> solicitudList = Collections.EMPTY_LIST;
        try {
            //@PathParam("codigoDistrito") Integer codigoDistrito, @PathParam("codigoTipoSolicitud") Integer codigoTipoSolicitud, 
            logger.info("#### LISTA DE SOLICITUDES PENDIENTES ####");
            HttpSession session = request.getSession(false);
            logger.info(" accion :  " + session.getAttribute(ConstantBusiness.ACCION_PROGRAMACION));
            int accion = ConstantBusiness.ACCION_NUEVA_PROGRAMACION;
            Long numeroProgramacion = null;
            Integer codigoTipoSolicitud = null;
            Integer codigoDistrito = null;

            try {

                accion = (Integer) session.getAttribute(ConstantBusiness.ACCION_PROGRAMACION);
                if (accion == ConstantBusiness.ACCION_NUEVA_PROGRAMACION) {

                    codigoTipoSolicitud = Integer.parseInt(request.getParameter("codigoTipoSolicitud"), 10);
                    codigoDistrito = Integer.parseInt(request.getParameter("codigoDistrito"), 10);
                    logger.info("##### codigoDistrito : " + codigoDistrito);
                    logger.info("##### codigoTipoSolicitud : " + codigoTipoSolicitud);
                    solicitudList = programacionService.getSolicitudList(codigoDistrito, codigoTipoSolicitud);
                    logger.info(" cant solicitudes : " + solicitudList.size());

                } else {

                    numeroProgramacion = (Long) session.getAttribute("numeroProgramacion");
                    numeroProgramacion = numeroProgramacion == null ? 0 : numeroProgramacion;
                    solicitudList = programacionService.getSolicitudList(numeroProgramacion);
                    logger.info(" cant solicitudes : " + solicitudList.size());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            //solicitudList = programacionService.getSolicitudList(numeroProgramacion, accion, codigoDistrito, codigoTipoSolicitud);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return solicitudList;

    }

    @POST
    @GET
    @Path("/cuadrillas.html")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> cuadrillas(@Context HttpServletRequest request) {

        logger.info(" ########## metodo :  cuadrilas ##### ###");
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date fecPrgn = cal.getTime();
        //List<Cuadrilla> cuadrillas = CuadrillaService.getCuadrillaLibresList(fecPrgn);
        //List<Cuadrilla> cuadrillas = CuadrillaService.getCuadrillasCombinadas(fecPrgn);
        logger.info("iniciando cuadrillas combinadas");
        List<Cuadrilla> cuadrillas = programacionService.getCuadrillasGeneradas();
        logger.info("finalizando cuadrillas combinadas");

        return toFormatPlantrabajo(cuadrillas);
    }

    @POST
    @GET
    @Path("/cuadrillas2.html")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cuadrilla> cuadrillas2(@Context HttpServletRequest request) {

        Date fecPrgn = Calendar.getInstance().getTime();
        List<Cuadrilla> cuadrillas = CuadrillaService.getCuadrillaList(fecPrgn);
        for (Cuadrilla c : cuadrillas) {
            for (CuadrillasDetalle d : c.getCuadrillasDetalles()) {
                System.out.println("tenico " + d.getTecnico().getNombres());
            }
        }

        return cuadrillas;

    }

    @POST
    @GET
    @Path("/asignarcuadrillas.html")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlanTrabajo> asignarcuadrillas(@Context HttpServletRequest request,
            @QueryParam("fecprgn") String _fecPrgn) {

        HttpSession session = request.getSession(false);
        Date fecPrgn = Calendar.getInstance().getTime();
        //cuadrillas s
        //List<Cuadrilla> cuadrillas = CuadrillaService.getCuadrillaList(fecPrgn);
        // planes generados 
        //List<PlanTrabajo> planTrabajoList = programacionService.asignarCuadrillasGrupos(fecPrgn);

        List<PlanTrabajo> planTrabajoList = null;
        plotPlanesTrabajo(planTrabajoList);
        return planTrabajoList;

    }

    @POST
    @GET
    @Path("/reasignarcuadrillas.html")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlanTrabajo> reasignarcuadrillas(@Context HttpServletRequest request,
            @QueryParam("fecprgn") String _fecPrgn) {

        logger.info(" ##### reasignarcuadrillas ##### ");

        List<PlanTrabajo> planTrabajoList = Collections.EMPTY_LIST;

        try {

            Date fecPrgn = Calendar.getInstance().getTime();
            Long ngrupo = Long.parseLong(request.getParameter("ngrupo").toString());
            Long ncuadrilla = Long.parseLong(request.getParameter("ncuadrilla").toString());
            Map<Long, Long> reasignados = new LinkedHashMap<>();
            reasignados.put(ngrupo, ncuadrilla);
            // planes generados 
            planTrabajoList = programacionService.reasignarCuadrillasGrupos(fecPrgn, reasignados);

            plotPlanesTrabajo(planTrabajoList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return planTrabajoList;

    }

    @POST
    @GET
    @Path("/planestrabajoasignados.html")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlanTrabajo> planesTrabajoAsignados(@Context HttpServletRequest request,
            @QueryParam("fecprgn") String _fecPrgn) {

        HttpSession session = request.getSession(false);
        logger.info(" accion :  " + session.getAttribute(ConstantBusiness.ACCION_PROGRAMACION));
        Integer accion = (Integer) session.getAttribute(ConstantBusiness.ACCION_PROGRAMACION);
        Long numeroProgramacion = (Long) session.getAttribute("numeroProgramacion");
        numeroProgramacion = numeroProgramacion == null ? 0 : numeroProgramacion;
        List<PlanTrabajo> planTrabajoList = programacionService.getPlanesAsigandos(accion, numeroProgramacion);
        plotPlanesTrabajo(planTrabajoList);
        return planTrabajoList;

    }

    @POST
    @GET
    @Path("/reasignarsolicitud.html")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> reasignarsolicitud(@Context HttpServletRequest request,
            @FormParam("numerosoli") long numerosoli,
            @FormParam("numerogrup") long numerogrup) {
        logger.info("#### reasignarsolicitud #### ");
        logger.info(" ####  numerosoli :" + numerosoli);
        logger.info(" ####  numerogrup :" + numerogrup);
        /*Map<Long, GrupoAtencion> mpGrupos  =  programacionService.getMpGruposCached();
		mpGrupos =  grupoService.reasignarSolictud(mpGrupos, numerosoli, numerogrup);
		programacionService.setMpGruposCached(mpGrupos);*/
        // cambio
        Map<Long, GrupoAtencion> mpGrupos = programacionService.reasignarSolictud(numerosoli, numerogrup);

        return toFormatMap(mpGrupos);

    }

    @POST
    @GET
    @Path("/guardarprogramacion.html")
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarProgramacion(@Context HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Long numeroProgramacion = (Long) session.getAttribute("numeroProgramacion");
        Integer accion = (Integer) session.getAttribute(ConstantBusiness.ACCION_PROGRAMACION);
        Response response = programacionService.guardarProgramacion(accion, numeroProgramacion);
        //session.setAttribute("numeroProgramacion", (Long)response.getData());
        return response;

    }

    @POST
    @GET
    @Path("/reiniciarprogramacion.html")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reiniciarprogramacion(@Context HttpServletRequest request) {
        logger.info("### metodo : reiniciarprogramacion ");

        return programacionService.clearCache();

    }

    @POST
    @GET
    @Path("/ejecutarprogramacion.html")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ejecutarprogramacion(@Context HttpServletRequest request,
            @FormParam("numeroProgramacion") Long numeroProgramacion) {

        /*HttpSession session = request.getSession(false);
		Long numeroProgramacion =  (Long)session.getAttribute("numeroProgramacion");*/
        return programacionService.ejecutar(numeroProgramacion);
    }

    private List<Map<String, Object>> toFormatPlantrabajo(List<Cuadrilla> cuadrillas) {

        List<Map<String, Object>> planList = new ArrayList<Map<String, Object>>();

        if (cuadrillas != null && cuadrillas.size() > 0) {
            for (Cuadrilla c : cuadrillas) {

                StringBuilder tecnicos = new StringBuilder("");

                for (CuadrillasDetalle cd : c.getCuadrillasDetalles()) {
                    String apellidos = cd.getTecnico().getApellidos().trim().split("\\s+")[0];
                    String letraNombre = cd.getTecnico().getNombres().trim().substring(0, 1);
                    if (tecnicos.toString().equals("")) {
                        tecnicos.append(letraNombre);
                        tecnicos.append(" ");
                        tecnicos.append(apellidos);
                    } else {
                        tecnicos.append(", ");
                        tecnicos.append(letraNombre);
                        tecnicos.append(" ");
                        tecnicos.append(apellidos);
                    }
                }

                Map<String, Object> p = new HashMap();
                p.put("id", c.getNumeroCuadrilla());
                p.put("numeroCuadrilla", c.getNumeroCuadrilla());
                p.put("nombreCuadrilla", c.getNombre());
                p.put("descripcionGrupo", "");
                p.put("tecnicos", tecnicos.toString());
                planList.add(p);

            }
        }

        return planList;
    }

    /**
     * formato resumido de los grupos de atencion para pintar en los mapas
     *
     *
     */
    private List<Map<String, Object>> toFormatMap(Map<Long, GrupoAtencion> mpGrupos) {

        Iterator it = mpGrupos.keySet().iterator();

        List<Map<String, Object>> grupoList = new ArrayList<Map<String, Object>>();

        while (it.hasNext()) {

            Long numero = (Long) it.next();
            GrupoAtencion grupo = mpGrupos.get(numero);
            Map<String, Object> g = new HashMap();
            g.put("numero", grupo.getNumeroGrupoAtencion());
            g.put("descripcion", grupo.getDescripcion());
            g.put("latitudcentral", grupo.getLatitudCentral());
            g.put("longitudcentral", grupo.getLongitudCentral());
            g.put("color", grupo.getColor());
            g.put("radio", grupo.getRadio());
            g.put("area", grupo.getArea());

            List<GrupoAtencionDetalle> detalles = grupo.getGrupoAtencionDetalles();
            List<Map<String, Object>> _detalles = new ArrayList<Map<String, Object>>();

            for (GrupoAtencionDetalle detalle : detalles) {
                Map<String, Object> d = new HashMap();
                d.put("numeroSolicitud", detalle.getSolicitudServicio().getNumeroSolicitud());
                d.put("tag", Util.getTag(detalle.getSolicitudServicio()));

                d.put("latitud", detalle.getSolicitudServicio().getLatitud());
                d.put("longitud", detalle.getSolicitudServicio().getLongitud());
                d.put("tipo", detalle.getSolicitudServicio().getTipoSolicitud().getDescripcion());

                _detalles.add(d);
            }

            g.put("detalles", _detalles);
            grupoList.add(g);

        }

        //logger.info("### mostrando formato ### ");
        // Gson gson = new Gson();
        //logger.info(gson.toJson(grupoList));
        return grupoList;
    }

    /**
     * detalleresumido de cada plan de trabajo
     *
     *
     */
    private List<Map<String, Object>> toFormatMap(List<PlanTrabajoDetalle> detalles) {

        List<Map<String, Object>> detalleList = new ArrayList<Map<String, Object>>();

        int i = 1;

        SimpleDateFormat sdf = new SimpleDateFormat(ConstantBusiness.HORA_FORMAT);

        for (PlanTrabajoDetalle d : detalles) {

            SolicitudServicio s = d.getSolicitudServicio();
            Map<String, Object> map = new HashMap();
            map.put("tag", Util.getTag(d.getSolicitudServicio()));
            map.put("numeroSecuencia", i);
            map.put("numeroSolicitud", s.getNumeroSolicitud());
            map.put("tipoSolicitud", s.getTipoSolicitud().getDescripcion());
            map.put("horaAtencion", sdf.format(s.getFechaAtencion()));
            map.put("tiempoEjecucion", d.getSolicitudServicio().getTipoSolicitud().getTiempoEjecucion());

            if (s.getSolicitudServicioHorarioAtencionList() != null) {
                List<SolicitudServicioHorarioAtencion> shList = s.getSolicitudServicioHorarioAtencionList();
                StringBuilder sb = new StringBuilder("");

                if (shList != null && !shList.isEmpty()) {
                    if (shList.size() == 1) {
                        SolicitudServicioHorarioAtencion sha  =  shList.get(0);
                        sb.append("<br>" + sha.getHoraInicio() + " - " + sha.getHoraFin());
                        sb.append(",</br>");
                        map.put("horaSolicitada", sb.toString());

                    } else {    
                        
                        SolicitudServicioHorarioAtencion sha  =  shList.get(0);
                        sb.append("<br>" + sha.getHoraInicio() + " - " + sha.getHoraFin());
                        sb.append(",</br>");
                        
                        sha  =  shList.get(shList.size()-1);
                        sb.append("<br>" + sha.getHoraInicio() + " - " + sha.getHoraFin());
                        sb.append(",</br>");
                        
                        map.put("horaSolicitada", sb.toString());
                        

                    }
                }

                /* for (SolicitudServicioHorarioAtencion sha : shList) {
                    sb.append("<br>"+sha.getHoraInicio() +" - "+sha.getHoraFin());
                    sb.append(",</br>");
                    map.put("horaSolicitada", sb.toString());
                }*/
            }
            detalleList.add(map);
            i++;
        }

        return detalleList;
    }

    private void plotPlanesTrabajo(List<PlanTrabajo> planTrabajoList) {

        if (planTrabajoList != null && planTrabajoList.size() > 0) {

            logger.info("##### mostrando #####");

            for (PlanTrabajo p : planTrabajoList) {
                GrupoAtencion g = p.getGrupoAtencion();
                String dg = (g == null) ? "" : g.getDescripcion();
                logger.info("guadrilla :" + p.getCuadrilla().getNumeroCuadrilla() + "- " + dg);
            }
        }
    }

    public void clearCache() {
        try {
            programacionService.clearCache();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e);
        }
    }

}
