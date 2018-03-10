package pe.com.cablered.seguridad.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.com.cablered.seguridad.model.EstadoRegistro;

@ApplicationScoped
public class EstadoRegistroDao extends CrudRepository<EstadoRegistro>  {
	
	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	
	public List<EstadoRegistro> getEstadoRegistroList(int codGrupo) {
	
		
		String sql =  "Select e From EstadoRegistro  e";
		TypedQuery<EstadoRegistro> query = getEntityManager().createQuery(sql, EstadoRegistro.class);

		return query.getResultList();
	}


	public EstadoRegistro getEstadoRegistro(String desEsta) {
		
		String sql =  "Select e From EstadoRegistro  e Where e.desEsta =:desEsta";
		TypedQuery<EstadoRegistro> query = getEntityManager().createQuery(sql, EstadoRegistro.class);
		query.setParameter("desEsta", desEsta);
		List<EstadoRegistro> estadoRegistroList =  query.getResultList();
		if(estadoRegistroList.size()>0){
			return  estadoRegistroList.get(0);
		}
		return null;
	}
	
	
	

}
