/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.model.TipoSolicitud;
import pe.com.cablered.mistia.model.TipoSolicitudCompetenciaDetalle;

/**
 *
 * @author javadeveloper
 */
public class TipoSolicitudCompetenciaDetalleDao extends  CrudDao<TipoSolicitudCompetenciaDetalle>{


    final static Logger logger = Logger.getLogger(TecnicoCompetenciaDetalleDao.class);

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    List<TipoSolicitudCompetenciaDetalle> getTipoSolicitudCompetenciaDetalleList(TipoSolicitud tipoSolicitud){
        
         List<TipoSolicitudCompetenciaDetalle>  lista =  Collections.EMPTY_LIST;
         
         String sql = "Select d from TipoSolicitudCompetenciaDetalle"; 
         
         TypedQuery  query  =  getEntityManager().createQuery(sql, TipoSolicitudCompetenciaDetalle.class);
         
         
         
         
         
         
         return lista;
    
    }
    
        
    
    
}
