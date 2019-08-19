package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pe.com.cablered.mistia.model.SolicitudServicioHorarioAtencion;

@ApplicationScoped
public class SolicitudServicioHorarioAtencionDao extends CrudDao<SolicitudServicioHorarioAtencion> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<SolicitudServicioHorarioAtencion> getListSolicitudServicioHorarioAtencion(Long numeroSolicitud) {

        List<SolicitudServicioHorarioAtencion> list = Collections.EMPTY_LIST;
        try {

            String sql = "Select s "
                    + " from SolicitudServicioHorarioAtencion s  where  s.solicitudServicio.numeroSolicitud  =:pnumerosolicitud ";
            TypedQuery<SolicitudServicioHorarioAtencion> query = getEntityManager().createQuery(sql, SolicitudServicioHorarioAtencion.class);
            query.setParameter("pnumerosolicitud", numeroSolicitud);
            list = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteBySolicitudServicio(long numeroSolicitud) {

        try {

            String sql = "delete from SolicitudServicioHorarioAtencion d  where d.id.numeroSolicitud = :pnumeroSolicitud";
            Query query = getEntityManager().createQuery(sql);
            query.setParameter("pnumeroSolicitud", numeroSolicitud);
            query.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
