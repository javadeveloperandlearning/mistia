package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Cargo;
import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.model.Tecnico;
import pe.com.cablered.mistia.model.Vehiculo;


@ApplicationScoped
public class TecnicoDao extends CrudDao<Tecnico> {

	
	final static Logger logger = Logger.getLogger(TecnicoDao.class);
	
	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	

	public List<Tecnico> getTecnicoList() {
		List<Tecnico> list =  Collections.EMPTY_LIST;
		try{
			String sql =  "Select d from Tecnico d order by d.codigoTecnico";
			TypedQuery<Tecnico> query =  getEntityManager().createQuery(sql, Tecnico.class);	
			list =  query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}



	public List<Tecnico> getTecnicoList(Distrito distrito, Cargo cargo, String criterio) {
		List<Tecnico> list =  Collections.EMPTY_LIST;
		try{
			String sql =  "Select d from Tecnico d "
					+ "where ( d.distrito = :pdistrito or :pdistrito is null)"
					+ "	and  ( d.cargo = :pcargo or :pcargo is null) "
					+ "   and ( ( cast(d.codigoTecnico as string ) like :pcriterio "
					+ "			or  upper(d.nombres) like :pcriterio "
					+ "			or  upper(d.apellidos) like :pcriterio "
					+ "			or  upper(d.direccion) like :pcriterio "
					+ "			or  upper(d.telefono) like :pcriterio "
					+ "			or  upper(d.dni) like :pcriterio "
					+ "			or  upper(d.cargo.descripcion) like :pcriterio "
					+ "			or  upper(d.email) like :pcriterio ) or :pcriterio is null ) "

					+ " order by d.codigoTecnico ";
			TypedQuery<Tecnico> query =  getEntityManager().createQuery(sql, Tecnico.class);	
			
			query.setParameter("pdistrito", distrito);
			query.setParameter("pcargo", cargo);
			criterio = criterio==null?null:"%"+criterio.trim().trim().toUpperCase()+"%";
			query.setParameter("pcriterio", criterio);
			
			list =  query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}



	public int getMax(Tecnico tecnico) {
		
		logger.info("metodo:  getMax");
		try{
			
			String sql = "Select max(p.codigoTecnico) From Tecnico p";
			TypedQuery<Integer> query = getEntityManager().createQuery(sql,Integer.class);
			return ((Integer)query.getSingleResult()).intValue();
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
			return 0;
		}
	}



	public Tecnico getTecnico(Integer codigoTecnico) {
				
		Tecnico tecnico = null;

		try{
			
			String sql = "Select p From Tecnico p where p.codigoTecnico = :pcodigotecnico";
			TypedQuery<Tecnico> query  = getEntityManager().createQuery(sql, Tecnico.class);
			query.setParameter("pcodigotecnico", codigoTecnico);
			if(query.getResultList().size()>0){
				tecnico =  query.getResultList().get(0);
			}
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
		return tecnico;
	}



	public Tecnico getTecnico(String nombres) {
		
		Tecnico tecnico = null;

		try{
			//apellidos,' ',nombres
			String sql = "Select p From Tecnico p where upper(concat(p.apellidos,' ',p.nombres)) = upper(:pnombres)";
			TypedQuery<Tecnico> query  = getEntityManager().createQuery(sql, Tecnico.class);
			query.setParameter("pnombres", nombres);
			if(query.getResultList().size()>0){
				tecnico =  query.getResultList().get(0);
			}
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		return tecnico;
		
	}
	
	

}
