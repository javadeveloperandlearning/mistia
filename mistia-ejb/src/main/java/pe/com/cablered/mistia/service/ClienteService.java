package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.ClienteDao;
import pe.com.cablered.mistia.model.Cliente;
import static pe.com.cablered.mistia.service.VehiculoService.logger;
import static pe.com.cablered.mistia.util.Util.*;

@Stateless
@LocalBean
public class ClienteService extends AbstractSevice<Cliente> {

    @Inject
    private ClienteDao clienteDao;

    final static Logger logger = Logger.getLogger(ClienteService.class);

    public List<Cliente> clienteList() {

        return clienteDao.clienteList();
    }

    public List<Cliente> clienteList(int tipoDocu, Integer codigoCliente, String nombres, String apellidoPaterno, String apellidoMaterno, String nombreRazonSocial) {
        return clienteDao.clienteList(tipoDocu, codigoCliente, nombres, apellidoPaterno, apellidoMaterno, nombreRazonSocial);
    }

    public Response registrar(Cliente cliente) {
        logger.info("metodo :  registrar");
        Response response = validar(cliente);

        try {

            if (response.getCodigo() == Response.OK) {
                clienteDao.create(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e);
            response = new Response(Response.ERROR, Response.MSG_ERROR);
        }
        return response;

    }

    public Response modificar(Cliente cliente) {
        logger.info("metodo :  modificar");
        Response response = validar(cliente);

        try {

            if (response.getCodigo() == Response.OK) {
                clienteDao.update(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = new Response(Response.ERROR, Response.MSG_ERROR);
        }

        return response;

    }

    private Response validar(Cliente cliente) {

        Response response = new Response(Response.OK, Response.MSG_OK);

        if (isEmpty(cliente.getNombreRazonSocial())) {
            response = new Response(Response.ERROR, "Ingrese un nombre de Razón social válido");
            return response;
        }

        if (isEmpty(cliente.getTelefono())) {
            response = new Response(Response.ERROR, "Ingrese teléfono válido");
            return response;
        }

        if (isEmpty(cliente.getTelefonoMovil())) {
            response = new Response(Response.ERROR, "Ingrese teléfono móvil válido");
            return response;
        }

        if (isEmpty(cliente.getApellidoPaterno())) {
            response = new Response(Response.ERROR, "Ingrese un apellido paterno válido");
            return response;
        }

        if (isEmpty(cliente.getApellidoMaterno())) {
            response = new Response(Response.ERROR, "Ingrese un apellido materno válido");
            return response;
        }

        if (isEmpty(cliente.getNombres())) {
            response = new Response(Response.ERROR, "Ingrese un nombre válido");
            return response;
        }

        if (isEmpty(cliente.getEmail())) {
            response = new Response(Response.ERROR, "Ingrese un email válido");
            return response;
        }

        if (isEmpty(cliente.getCodigoDistrito())) {
            response = new Response(Response.ERROR, "Seleccione un distrito váldo");
            return response;
        }

        if (isEmpty(cliente.getCodigoDistrito())) {
            response = new Response(Response.ERROR, "Seleccione un distrito váĺido");
            return response;
        }

        if (isEmpty(cliente.getDireccion())) {
            response = new Response(Response.ERROR, "Ingrese una dirección válida");
            return response;
        }

        return response;
    }

    @Override
    public Response eliminar(Cliente t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getMax() {
        int numeroCuadrilla = clienteDao.getMax() + 1;
        return numeroCuadrilla;
    }

}
