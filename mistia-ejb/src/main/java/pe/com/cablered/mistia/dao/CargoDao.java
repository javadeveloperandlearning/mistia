package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Cargo;


@ApplicationScoped
public class CargoDao extends CrudDao<Cargo>{
	
	
	
	final static Logger logger = Logger.getLogger(CargoDao.class);

	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public List<Cargo> getCargoList() {
		
		List<Cargo> list =  Collections.EMPTY_LIST;
		
		try{
			
			String sql =  "Select d from Cargo d";
			TypedQuery<Cargo> query =  getEntityManager().createQuery(sql, Cargo.class);	
			list =  query.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}

	public Cargo getCargo(String descripcion) {
		
		Cargo cargo = null;
		
		try{
			
			String sql = "Select p From Cargo p where upper(trim(p.descripcion)) = upper(:pdescripcion)";
			TypedQuery<Cargo> query  = getEntityManager().createQuery(sql, Cargo.class);
			query.setParameter("pdescripcion", descripcion.trim());
			if(query.getResultList().size()>0){
				cargo =  query.getResultList().get(0);
			}
			
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		return cargo;
	}

}
