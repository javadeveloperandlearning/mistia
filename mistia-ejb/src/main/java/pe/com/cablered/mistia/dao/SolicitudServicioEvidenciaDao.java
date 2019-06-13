/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.model.SolicitudServicioEvidencia;

/**
 *
 * @author javadeveloper
 */

@ApplicationScoped
public class SolicitudServicioEvidenciaDao extends CrudDao<SolicitudServicioEvidencia> {
	
	
	@Inject
	private EntityManager em ;
	
	final static Logger logger = Logger.getLogger(SolicitudServicioEstadoDao.class);

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
    
    
    
    
}
