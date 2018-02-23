package pe.com.cablered.mistia.ws.rest;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@Path("/generacion")
@SessionScoped
public class EjecucionRest {

	
	
	@GET
	@Path("/lista.html")
	
	public List<Map> lista() {

		List<Map> lista = new ArrayList<>();
		Map<String, String> map1 = new HashMap<>();
		return lista;

	}

}
