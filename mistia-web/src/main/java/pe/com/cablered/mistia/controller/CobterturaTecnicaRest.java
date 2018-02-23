package pe.com.cablered.mistia.controller;

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

import pe.com.cablered.mistia.model.GrupoAtencion;
import pe.com.cablered.mistia.service.NodosService;



@Named
@Path("/cobertura")
@SessionScoped
public class CobterturaTecnicaRest {

	
	
	@Inject
	private NodosService nodosService;
	
	
	public void init(){		
	}

	@POST
	@GET
	@Path("/coberturanodos.html")
	@Produces(MediaType.APPLICATION_JSON)
	public 	List<Map> coberturaNodos( @Context HttpServletRequest request ){
		//List<Map> coberturaList  =  nodosService.getcoberuraNodos();
		List<Map> coberturaList  =  nodosService.getcoberuraPostes();
		return coberturaList;
	}
	
}
