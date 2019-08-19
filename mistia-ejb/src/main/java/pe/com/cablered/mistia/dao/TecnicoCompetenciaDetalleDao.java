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
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.model.TecnicoCompetenciaDetalle;

/**
 *
 * @author javadeveloper
 */
@ApplicationScoped
public class TecnicoCompetenciaDetalleDao extends CrudDao<TecnicoCompetenciaDetalle> {

    final static Logger logger = Logger.getLogger(TecnicoCompetenciaDetalleDao.class);

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<TecnicoCompetenciaDetalle> getTecnicoCompetenciaDetalleList(Integer codigoTecnico) {

        List<TecnicoCompetenciaDetalle> tecnicoCompetenciaDetalleList = Collections.EMPTY_LIST;

        try {
            String sql = "Select d from TecnicoCompetenciaDetalle d where d.tecnico.codigoTecnico =:pcodigotecnico  ";
            TypedQuery<TecnicoCompetenciaDetalle> query = getEntityManager().createQuery(sql, TecnicoCompetenciaDetalle.class);
            query.setParameter("pcodigotecnico", codigoTecnico);
            tecnicoCompetenciaDetalleList = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return tecnicoCompetenciaDetalleList;

    }
    
   
    
    
    
   

}
