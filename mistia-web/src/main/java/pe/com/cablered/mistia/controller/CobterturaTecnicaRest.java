package pe.com.cablered.mistia.controller;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import static pe.com.cablered.mistia.util.ConstantBusiness.ACCION_PROGRAMACION;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.dao.PosteDao;
import pe.com.cablered.mistia.geometria.Punto;

import pe.com.cablered.mistia.model.GrupoAtencion;
import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.model.Poste;
import pe.com.cablered.mistia.service.CoberturaService;
import pe.com.cablered.mistia.service.NodosService;
import pe.com.cablered.mistia.service.PosteService;
import pe.com.cablered.mistia.service.Response;

@Named
@Path("/cobertura")
@SessionScoped
public class CobterturaTecnicaRest {

    @Inject
    private NodosService nodosService;

    @Inject
    private CoberturaService coberturaService;
    
    @Inject
    private PosteService posteService;

    @Inject
    private FacesContext facesContext;
    

    final static Logger logger = Logger.getLogger(CobterturaTecnicaRest.class);

    public void init() {
    }

    @POST
    @GET
    @Path("/coberturanodos.html")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map> coberturaNodos(@Context HttpServletRequest request) {
        // visualizacion de los postes
        logger.info("metodo : coberturaNodos");
        List<Map> coberturaList = nodosService.getcoberuraPostes();
        
        
        
        
        return coberturaList;
    }

    @POST
    @GET
    @Path("/validarcobertura.html")
    @Produces(MediaType.APPLICATION_JSON)
    public Map validarCobertura(@Context HttpServletRequest request) {

        Map map = Collections.EMPTY_MAP;

        try {

            logger.info("metodo : validarCobertura");
            double lat = Double.parseDouble(request.getParameter("latitud"));
            double lon = Double.parseDouble(request.getParameter("longitud"));
            Punto punto = new Punto(lat, lon);
            map = coberturaService.getCobertura(punto);

        } catch (Exception e) {

            e.printStackTrace();
            logger.info(e);
            logger.error(e);
        }
        return map;
    }

    @POST
    @GET
    @Path("/selecionarposteservicio.html")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response seleccionarPosteServicio(Poste poste) {
        logger.info(" metodo :  seleccionarPoste");
        Response response = new Response();
        try {
            //HttpSession session  =   (HttpSession) facesContext.getExternalContext().getSession(true);
            Map mpsession = facesContext.getExternalContext().getSessionMap();
            mpsession.put("posteCobertura", poste);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @POST
    @GET
    @Path("/selecionarpuntoservcio.html")
    @Produces(MediaType.APPLICATION_JSON)
    public Response seleccionarPuntoServicio(@Context HttpServletRequest request) {
        logger.info(" metodo :  seleccionarPuntoServicio ####");
        Response response = new Response();
        try {
            logger.info(request.getParameter("latitud"));
            logger.info(request.getParameter("longitud"));
            
            Double latitud = Double.parseDouble(request.getParameter("latitud").toString());
            Double longitud = Double.parseDouble(request.getParameter("longitud").toString());
            Map<String, Double> mpPuntoServicio = new HashMap<>();
            mpPuntoServicio.put("latitud", latitud);
            mpPuntoServicio.put("longitud", longitud);
            //HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);7
            HttpSession session = request.getSession(false);
            session.setAttribute("mpPuntoServicio", mpPuntoServicio);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
    
    @POST
    @GET
    @Path("/registrarpostetest.html")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarPoste(@Context HttpServletRequest request) {
        logger.info(" metodo :  registrarpostetest ####");
        Response response = new Response();
        try {
            logger.info(request.getParameter("latitud"));
            logger.info(request.getParameter("longitud"));
            
            Double latitud = Double.parseDouble(request.getParameter("latitud").toString());
            Double longitud = Double.parseDouble(request.getParameter("longitud").toString());

     
            Poste poste =  new Poste();
            poste.setDescripcion("");
            poste.setNodo(new Nodo(1));
            poste.setLatitud(new BigDecimal(latitud));
            poste.setLongitud(new BigDecimal(longitud));
            posteService.registrar(poste);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }


}
