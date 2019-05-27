package pe.com.cablered.mistia.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import static pe.com.cablered.mistia.util.ConstantBusiness.ACCION_PROGRAMACION;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.geometria.Punto;

import pe.com.cablered.mistia.model.GrupoAtencion;
import pe.com.cablered.mistia.service.CoberturaService;
import pe.com.cablered.mistia.service.NodosService;



@Named
@Path("/cobertura")
@SessionScoped
public class CobterturaTecnicaRest {

	
	
	@Inject
	private NodosService nodosService;
        
        @Inject
        private CoberturaService  coberturaService;
        
        final static Logger logger = Logger.getLogger(CobterturaTecnicaRest.class);
	
	
	public void init(){		
	}

	@POST
	@GET
	@Path("/coberturanodos.html")
	@Produces(MediaType.APPLICATION_JSON)
	public 	List<Map> coberturaNodos( @Context HttpServletRequest request ){
                // visualizacion de los postes
                logger.info("metodo : coberturaNodos");
		List<Map> coberturaList  =  nodosService.getcoberuraPostes();
		return coberturaList;
	}
	

        
	@POST
	@GET
	@Path("/validarcobertura.html")
	@Produces(MediaType.APPLICATION_JSON)
	public 	Map validarCobertura( @Context HttpServletRequest request ){
            
                Map map =  Collections.EMPTY_MAP;
                
                try{    

                    logger.info("metodo : validarCobertura");    
                    double lat =  Double.parseDouble(request.getParameter("latitud"));
                    double lon =  Double.parseDouble(request.getParameter("longitud"));
                    Punto punto  =  new Punto(lat, lon);
                    map =   coberturaService.getCobertura(punto);
                    
                }catch(Exception e){
                    
                    e.printStackTrace();
                    logger.info(e);
                    logger.error(e);
                }
                return map;
	}
        
        
}
