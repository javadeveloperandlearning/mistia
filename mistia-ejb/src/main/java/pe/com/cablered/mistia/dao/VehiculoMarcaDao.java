package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.model.Vehiculo;
import pe.com.cablered.mistia.model.VehiculoMarca;


@ApplicationScoped
public class VehiculoMarcaDao extends CrudDao<VehiculoMarca> {
	

	@Inject
	private EntityManager em;
	
	final static Logger logger = Logger.getLogger(VehiculoMarcaDao.class);

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public List<VehiculoMarca> getVehiculoMarcaList(){
	
		List<VehiculoMarca> list = Collections.emptyList();	
		try {
			String sql = "Select c from VehiculoMarca c  ";
			System.out.println(sql);
			TypedQuery<VehiculoMarca> query = getEntityManager().createQuery(sql, VehiculoMarca.class);
			list = query.getResultList();

		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return list;

	}

	public  VehiculoMarca getVehiculoMarca(String descripcion) {
	
		VehiculoMarca vehiculoMarca = null;
		try{
			
			String sql = "Select p From VehiculoMarca p where upper(p.descripcion) = upper(:pdescripcion)";
			TypedQuery<VehiculoMarca> query  = getEntityManager().createQuery(sql, VehiculoMarca.class);
			query.setParameter("pdescripcion", descripcion);
			if(query.getResultList().size()>0){
				vehiculoMarca =  query.getResultList().get(0);
			}
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		return vehiculoMarca;
		
		
		
		
	}
	

}
