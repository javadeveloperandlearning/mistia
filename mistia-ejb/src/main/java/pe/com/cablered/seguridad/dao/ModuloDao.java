package pe.com.cablered.seguridad.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.com.cablered.seguridad.model.Modulo;

@Stateless
public class ModuloDao extends CrudRepository<Modulo> {
	
	

	
	@Inject
	EntityManager em;


	public ModuloDao() {
		
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}


	public List<Modulo> getModulos() {
		String sql =  "Select new Modulo (m.codModu, m.desModu) From Modulo m";
		TypedQuery<Modulo> query = getEntityManager().createQuery(sql, Modulo.class);
		return query.getResultList();
	}

	public Modulo getModulo(String desModu) {
	
		String sql =  "Select new Modulo (m.codModu, m.desModu) From Modulo m Where desModu=:pdesModu";
		TypedQuery<Modulo> query = getEntityManager().createQuery(sql, Modulo.class);
		query.setParameter("pdesModu", desModu);
		List<Modulo> moduloList =  query.getResultList() ;
		if(moduloList.size()>0){
			return moduloList.get(0);
		}else{
			return null;
		}
	}



}
