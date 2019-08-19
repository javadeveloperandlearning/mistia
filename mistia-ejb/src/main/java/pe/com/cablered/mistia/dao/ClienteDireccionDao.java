/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.model.Cliente;
import pe.com.cablered.mistia.model.ClienteDireccion;
import pe.com.cablered.mistia.service.Response;

/**
 *
 * @author javadeveloper
 */
@ApplicationScoped
public class ClienteDireccionDao extends CrudDao<ClienteDireccion> {

    final static Logger logger = Logger.getLogger(ClienteDireccionDao.class);

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<ClienteDireccion> getClienteDireccionList(Cliente cliente) {
        logger.info(" metodo :  getClienteDireccionList");
        List<ClienteDireccion> clienteDireccionList = Collections.EMPTY_LIST;
        try {

            String sql = "select d from ClienteDireccion d  where d.id.codigoCliente = :pcodigocliente";
            TypedQuery<ClienteDireccion> query = getEntityManager().createQuery(sql, ClienteDireccion.class);
            query.setParameter("pcodigocliente", cliente.getCodigoCliente());
            clienteDireccionList = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            clienteDireccionList = Collections.EMPTY_LIST;
        }
        return clienteDireccionList;
    }

    public Response eliminarDirecciones(Cliente cliente) {

        logger.info(" metodo :  eliminarDirecciones");
        Response response = new Response();

        try {
                String sql = "delete from ClienteDireccion d  where d.id.codigoCliente = :pcodigocliente";
                Query query = getEntityManager().createQuery(sql);
                query.setParameter("pcodigocliente", cliente.getCodigoCliente());
                query.executeUpdate();
                
        } catch (Exception e) {
            e.printStackTrace();
             response = new Response(Response.ERROR, Response.MSG_ERROR);
        }

        return response;

    }

}
