package pe.com.cablered.mistia.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pe.com.cablered.mistia.model.TipoSolicitud;
import pe.com.cablered.mistia.service.TipoSolicitudService;

@Named
@Path("/tiposolicitud")
public class TipoSolicitudRest {
	
	
	
	@Inject
	private TipoSolicitudService tipoSolicitudService;
	
	@POST
	@GET
	@Path("/tipoSolicitudlist.html")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TipoSolicitud> getTipoSolicitudList(){
		
		return tipoSolicitudService.getTipoSolicitudList();
		
	}
	
	
	

}
