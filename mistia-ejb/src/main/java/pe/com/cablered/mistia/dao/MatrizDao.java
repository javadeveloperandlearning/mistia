package pe.com.cablered.mistia.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;


import pe.com.cablered.mistia.model.Matriz;
import pe.com.cablered.seguridad.model.Modulo;


@ApplicationScoped
public class MatrizDao extends CrudDao<Matriz>{

	@Inject
	private EntityManager em;
	
	final static Logger logger = Logger.getLogger(MatrizDao.class);

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	
	
	public Matriz getMatriz(String  descripcion) {
		String sql =  "Select new Matriz (m.codigoMatriz, m.descripcion) From Matriz m Where descripcion=:pdescripcion";
		TypedQuery<Matriz> query = getEntityManager().createQuery(sql, Matriz.class);
		query.setParameter("pdescripcion", descripcion);
		List<Matriz> matrizList =  query.getResultList() ;
		if(matrizList.size()>0){
			return matrizList.get(0);
		}else{
			return null;
		}
	}



	public List<Matriz> getMatrizList() {
		
		logger.info(" metodo : getMatrizList");
		
		List<Matriz> matrizList =  Collections.EMPTY_LIST;
		
		try{
			
			String sql =  "Select new Matriz (m.codigoMatriz, m.descripcion) From Matriz m";
			TypedQuery<Matriz> query = getEntityManager().createQuery(sql, Matriz.class);
			matrizList =  query.getResultList() ;
			
			if(matrizList!=null && matrizList.size()>0){
				logger.info("cantidad de elementos "+matrizList.size());
			}
			
			
		}catch (Exception e) {
			
			e.getMessage();
			logger.info(e);
			logger.error(e);
		
		}
	
		return matrizList;
	}	
	
	
	

}
