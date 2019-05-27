package pe.com.cablered.mistia.rest;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.service.DistritoService;

@Named
@Path("/distrito")
public class DistritoRest implements Serializable {
	
	
	
	@Inject
	private DistritoService distritoService;
	
	
	
	@GET
	@POST
	@Path("/distritoslist.html")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Distrito>  getDistritoList(){
		
		
		return distritoService.getDistritoList();
		
	}

}
