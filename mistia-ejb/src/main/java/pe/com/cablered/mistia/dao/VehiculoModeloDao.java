package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.model.VehiculoMarca;
import pe.com.cablered.mistia.model.VehiculoModelo;


@ApplicationScoped
public class VehiculoModeloDao extends CrudDao<VehiculoModelo> {
	
	@Inject
	private EntityManager em;
	
	final static Logger logger = Logger.getLogger(VehiculoModeloDao.class);

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	
	public List<VehiculoModelo> getVehiculoModeloList(){
		List<VehiculoModelo> list = Collections.emptyList();	
		
		try {
			
			String sql = "Select c from VehiculoModeloDao c  ";
			System.out.println(sql);
			TypedQuery<VehiculoModelo> query = getEntityManager().createQuery(sql, VehiculoModelo.class);
			list = query.getResultList();

		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return list;
	}



	public VehiculoModelo getVehiculoModelo(String descripcion) {
		
		VehiculoModelo vehiculoModelo = null;
		try{
			
			String sql = "Select p From VehiculoModelo p where p.descripcion = :pdescripcion";
			TypedQuery<VehiculoModelo> query  = getEntityManager().createQuery(sql, VehiculoModelo.class);
			query.setParameter("pdescripcion", descripcion);
			
			if(query.getResultList().size()>0){
				vehiculoModelo =  query.getResultList().get(0);
			}
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		return vehiculoModelo;
	}
	

}
