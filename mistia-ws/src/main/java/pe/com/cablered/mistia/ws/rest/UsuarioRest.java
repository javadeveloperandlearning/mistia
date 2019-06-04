/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.ws.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.mistia.util.ConstantSecurity;
import pe.com.cablered.seguridad.model.Usuario;
import pe.com.cablered.seguridad.service.ResponseSecurity;
import pe.com.cablered.seguridad.service.UsuarioService;

/**
 *
 * @author javadeveloper
 */
@Named
@Path("/usuario")
@RequestScoped
public class UsuarioRest {

    
    @Inject
    private UsuarioService usuarioService;

    final static Logger logger = Logger.getLogger(UsuarioRest.class);

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

    /**
     * iniciarSession
     *
     * @usuario
     */
        //@Consumes(MediaType.APPLICATION_JSON)
    
    @GET
    @POST
    @Path("/inciarsession.html")
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciarSession(Usuario usuario) {
        logger.info(" metodo  iniciarSession ");
        ResponseSecurity result = usuarioService.loguear(usuario);
        
        logger.info(" result : "+result);
        Response reponse = new Response(Response.ERROR, Response.MSG_ERROR);
        /*if (result != null && result.getCodigo() == ConstantSecurity.COD_OK) {
            
            logger.info(" usuario loguado ");
            Map<String, Object> mpData = new HashMap();
            //obtenemos información del usuario 
            reponse.setCodigo(result.getCodigo());
            reponse.setMensaje(result.getMessage());
            reponse.setData(usuarioService.getUsuarioSingle(usuario));

        }else{
            reponse.setCodigo(result.getCodigo());
            reponse.setMensaje(result.getMessage());
             logger.info(" usuario no legueado");
        }*/
        
        reponse.setCodigo(result.getCodigo());
        reponse.setMensaje(result.getMessage());

        return reponse;
    }
    
    
    
    
        
    @GET
    @POST
    @Path("/infousuario.html")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario infoUsuario(Usuario usuario) {
        logger.info(" metodo  infoUsuario");
        Usuario _usuario  = usuarioService.getUsuarioSingle(usuario);
        return _usuario;
    }
    
    
    
    
    
    
    

}
