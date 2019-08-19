/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.model.SolicitudServicioDetalle;

/**
 *
 * @author javadeveloper
 */
@ApplicationScoped
public class SolicitudServicioDetalleDao extends CrudDao<SolicitudServicioDetalle> {

    @Inject
    private EntityManager em;

    final static Logger logger = Logger.getLogger(SolicitudServicioDetalleDao.class);

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudServicioDetalleDao() {

        super(SolicitudServicioDetalle.class);
    }

    public List<SolicitudServicioDetalle> getListSolicitudServicioDetalle(Long numeroSolicitud) {

        List<SolicitudServicioDetalle> list = Collections.EMPTY_LIST;
        try {

            String sql = "Select s "
                    + " from SolicitudServicioDetalle s  where  s.solicitudServicio.numeroSolicitud  =:pnumerosolicitud ";
            TypedQuery<SolicitudServicioDetalle> query = getEntityManager().createQuery(sql, SolicitudServicioDetalle.class);
            query.setParameter("pnumerosolicitud", numeroSolicitud);
            list = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public void deleteBySolicitudServicio(long numeroSolicitud) {

        try {

            String sql = "delete from SolicitudServicioDetalle h  where h.id.numeroSolicitud = :pnumeroSolicitud";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("pnumeroSolicitud", numeroSolicitud);
            query.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
