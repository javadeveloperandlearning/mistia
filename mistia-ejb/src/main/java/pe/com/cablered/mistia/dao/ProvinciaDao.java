package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Provincia;

@ApplicationScoped
public class ProvinciaDao extends CrudDao<Provincia> {
	
	
	final static Logger logger = Logger.getLogger(Provincia.class);
	
	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	
	

	public List<Provincia> getProvinciaList(int codigoDepartamento) {
		
		logger.info(" metodo : getProvinciaList");
		
		List<Provincia> provinciaList =  Collections.EMPTY_LIST;
		try{
			String sql =  "Select p from Provincia p join p.departamento dp  "
					+ " where dp.codigoDepartamento = :pcodigodepartamento";
			TypedQuery<Provincia> query =  getEntityManager().createQuery(sql, Provincia.class);	
			query.setParameter("pcodigodepartamento", codigoDepartamento);
			provinciaList =  query.getResultList();
			
		}catch(Exception e){
			
			logger.info(e);
			e.printStackTrace();
			
		}
		return provinciaList;
	}
	
	


}
