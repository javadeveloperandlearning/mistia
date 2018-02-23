package pe.com.cablered.mistia.dao;


import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Tipo;

@ApplicationScoped
public class TipoDao extends CrudDao<Tipo> {
	
	
	
	final static Logger logger = Logger.getLogger(TipoDao.class);
	
	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Tipo> getTipoList(int codigoGrupo) {
		logger.info(" metodo : getTipoList (codigoGrupo) ");
		List<Tipo> tipoList =  Collections.EMPTY_LIST;
		try{
			String sql =  " Select t from Tipo  t  where t.codigoGrupo = :pcodigotipo";
			TypedQuery<Tipo> query =  getEntityManager().createQuery(sql, Tipo.class);	
			query.setParameter("pcodigotipo", codigoGrupo);
			tipoList =  query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return tipoList;
	}
	
	

}
