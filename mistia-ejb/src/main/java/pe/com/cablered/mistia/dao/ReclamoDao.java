package pe.com.cablered.mistia.dao;


import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Reclamo;



@ApplicationScoped
public class ReclamoDao extends CrudDao<Reclamo> {

	@Inject
	private EntityManager em;

	
	final static Logger logger = Logger.getLogger(ReclamoDao.class);
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public List<Reclamo> getReclamoList(){
		
		List<Reclamo> reclamoList =  Collections.emptyList();
		try{
			
			String sql =  " Select p from Reclamo p";
			TypedQuery<Reclamo> query = 	getEntityManager().createQuery(sql, Reclamo.class);
			reclamoList = query.getResultList();
			
		}catch(Exception e ){
			logger.info(e);
			e.printStackTrace();
		}
	    
	    return reclamoList;
	}

	public List<Reclamo> getReclamoList(String criterio,Date fechaRegistroIni, Date fechaRegistroFin) {
		
		List<Reclamo> reclamoList =  Collections.emptyList();
		System.out.println(" ### criterio ### " + criterio) ;
		System.out.println(criterio);
		
		try{
			
			String sql =  " Select p from Reclamo p"
						+ " where (upper(descripcion) like :pcriterio or :pcriterio is null ) "
						+ " and date_trunc('day',p.fechaReclamo) between :pfechaRegistroIni and :pfechaRegistroFin";
			
			TypedQuery<Reclamo> query = 	getEntityManager().createQuery(sql, Reclamo.class);
			query.setParameter("pfechaRegistroIni", fechaRegistroIni, TemporalType.TIMESTAMP);
			query.setParameter("pfechaRegistroFin", fechaRegistroFin, TemporalType.TIMESTAMP);
			
			criterio = criterio==null?null:"%"+criterio.trim().trim().toUpperCase()+"%";
			query.setParameter("pcriterio", criterio);
			reclamoList = query.getResultList();
			
		}catch(Exception e ){
			logger.info(e);
			e.printStackTrace();
			
		}
	    
	    return reclamoList;
	}



}
 