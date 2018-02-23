package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;


import pe.com.cablered.mistia.model.Distrito;


@ApplicationScoped
public class DistritoDao  extends CrudDao<Distrito>{
	
	
	final static Logger logger = Logger.getLogger(DistritoDao.class);
	
	@Inject
	private EntityManager em;
	
	

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	public List<Distrito> getDistritoList(){
		
		logger.info(" metodo : getDistritoList");
		
		List<Distrito> distritoList =  Collections.EMPTY_LIST;
		
		try{
			String sql =  "Select d from Distrito d";
			TypedQuery<Distrito> query =  getEntityManager().createQuery(sql, Distrito.class);	
			distritoList =  query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return distritoList;
			
	}


	public List<Distrito> getDistritoList(int  codigoProvincia) {
		logger.info(" metodo : getDistritoList (codigoProvincia) ");
		
		List<Distrito> distritoList =  Collections.EMPTY_LIST;
		
		try{
			String sql =  " Select d from Distrito d join  d.provincia p "
					+ " where p.codigoProvincia = :pcodigoprovincia";
			TypedQuery<Distrito> query =  getEntityManager().createQuery(sql, Distrito.class);	
			query.setParameter("pcodigoprovincia", codigoProvincia);
			distritoList =  query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return distritoList;
	}


	public Distrito getDistrito(String descripcion) {
		Distrito distrito = null;
		
		try{
			
			String sql = "Select d From Distrito d where upper(d.descripcion) = upper(:pdescripcion)";
			TypedQuery<Distrito> query  = getEntityManager().createQuery(sql, Distrito.class);
			query.setParameter("pdescripcion", descripcion.trim());
			if(query.getResultList().size()>0){
				distrito =  query.getResultList().get(0);
			}
			
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		return distrito;
	}
	
	
	
	

}
