package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Poste;
import pe.com.cablered.mistia.model.Tecnico;
import pe.com.cablered.mistia.model.Vehiculo;
import pe.com.cablered.mistia.model.VehiculoMarca;
import pe.com.cablered.mistia.model.VehiculoModelo;

public class VehiculoDao extends CrudDao<Vehiculo> {

	
	final static Logger logger = Logger.getLogger(VehiculoDao.class);
	
	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	
	

	public List<Vehiculo> getVehiculoList() {
		List<Vehiculo> list =  Collections.EMPTY_LIST;
		try{
			String sql =  "Select d from Vehiculo d";
			TypedQuery<Vehiculo> query =  getEntityManager().createQuery(sql, Vehiculo.class);	
			list =  query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	

	public List<Vehiculo> getVehiculoList(VehiculoMarca vehiculoMarca,
			VehiculoModelo vehiculoModelo, String criterio) {
		
		List<Vehiculo> list =  Collections.emptyList();
		try{
			String sql =  "Select d from Vehiculo d  where "
					+ " (d.placaVehiculo like :pcriterio  or  :pcriterio is null ) "
					+ "and  (d.descripcion like :pcriterio  or  :pcriterio is null ) "	
					+ "and  (d.vehiculoMarca = :pvehiculomarca  or   :pvehiculomarca is null) "
					+ "and (d.vehiculoModelo = :pvehiculomodelo  or   :pvehiculomodelo is null) ";
					
			TypedQuery<Vehiculo> query =  getEntityManager().createQuery(sql, Vehiculo.class);	
			criterio = criterio==null?null:"%"+criterio.trim().trim().toUpperCase()+"%";
			
			query.setParameter("pvehiculomarca", vehiculoMarca);
			query.setParameter("pvehiculomodelo", vehiculoModelo);
			query.setParameter("pcriterio", criterio);
			
			list =  query.getResultList();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;

	}

	
	public Vehiculo getVehiculo(String descripcion) {
	
		Vehiculo vehiculo = null;
		try{
			
			String sql = "Select p From Vehiculo p where upper(p.descripcion) = upper(:pdescripcion)";
			TypedQuery<Vehiculo> query  = getEntityManager().createQuery(sql, Vehiculo.class);
			query.setParameter("pdescripcion", descripcion);
			if(query.getResultList().size()>0){
				vehiculo =  query.getResultList().get(0);
			}
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		return vehiculo;
	}
	
	
	
	public Vehiculo getVehiculoByPlaca(String placaVehiculo) {
	
		Vehiculo vehiculo = null;
		try{
			String sql = "Select p From Vehiculo p where upper(p.placaVehiculo) = upper(:placavehiculo)";
			TypedQuery<Vehiculo> query  = getEntityManager().createQuery(sql, Vehiculo.class);
			query.setParameter("placavehiculo", placaVehiculo);
			if(query.getResultList().size()>0){
				vehiculo =  query.getResultList().get(0);
			}
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		return vehiculo;
	}
	
	
	
	
	
	
	
	
}
