package pe.com.eb.data;

import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.CuadrillaDao;
import pe.com.eb.model.Modulo;
import pe.com.eb.model.Perfil;

@Stateless
public class PerfilDao extends CrudRepository<Perfil> {
	
	
	@Inject
	EntityManager em;

	final static Logger logger = Logger.getLogger(PerfilDao.class);
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public PerfilDao() {
		setClass(Perfil.class);
	}

	public List<Perfil> getPerfilList(Modulo modulo) {
		
		List<Perfil> list =  Collections.emptyList();
		try{
			
			String sql = "Select p From Perfil p Where p.modulo=:pmodulo order by p.id";
			TypedQuery<Perfil> query = getEntityManager().createQuery(sql,Perfil.class);
			query.setParameter("pmodulo", modulo);
			list =  query.getResultList();
			
		}catch(Exception e ){
			
			logger.info(e);
			e.printStackTrace();
		}
		
		
		return list;
	}



	public int getCount(Modulo modulo) {
		
		String sql = "Select count(p) From Perfil p Where p.modulo=:modulo";
		TypedQuery<Long> query = getEntityManager().createQuery(sql,Long.class);
		query.setParameter("modulo", modulo);
		return ((Long)query.getSingleResult()).intValue();
	}

	
	
	
	

}
