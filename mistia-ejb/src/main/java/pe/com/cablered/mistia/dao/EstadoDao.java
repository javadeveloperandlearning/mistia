package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Estado;

@ApplicationScoped
public class EstadoDao extends CrudDao<Estado>{
	
	@Inject
	private EntityManager em;
	
	final static Logger logger = Logger.getLogger(EstadoDao.class);

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	
	public List<Estado> getEstadoList(int codigoGrupo){
		
		logger.info(" metodo : getEstadoList");
		
		List<Estado> estadoList =  Collections.EMPTY_LIST;
		try{
			String sql =  "Select d from Estado d where d.codigoGrupo =:pcodigogrupo";
			TypedQuery<Estado> query =  getEntityManager().createQuery(sql, Estado.class);
			query.setParameter("pcodigogrupo", codigoGrupo);
			estadoList =  query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return estadoList;
		
		
	}
	
	
	
	
	

}
