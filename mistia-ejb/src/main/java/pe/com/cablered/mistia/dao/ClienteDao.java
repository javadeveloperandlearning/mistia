package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.com.cablered.mistia.model.Cliente;
import pe.com.cablered.mistia.model.Cuadrilla;

@ApplicationScoped
public class ClienteDao extends  CrudDao<Cliente>{
	
	
	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	
	
	public List<Cliente> clienteList(){
		
	
		List<Cliente> list = Collections.EMPTY_LIST;	
		
		try {
			String sql = "Select c from Cliente c  ";
			System.out.println(sql);
			TypedQuery<Cliente> query = getEntityManager().createQuery(sql, Cliente.class);
			list = query.getResultList();

		} catch (Exception e) {
				e.printStackTrace();
		}
		return list;

	}
	
	
	
	
	
	
	
}
