package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.model.Poste;

@ApplicationScoped
public class PosteDao extends CrudDao<Poste> {
		
	@Inject
	private EntityManager em;
	
	final static Logger logger = Logger.getLogger(Poste.class);

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	public List<Poste> getPostesLIst(){
		List<Poste> list =  Collections.EMPTY_LIST;
		try{
			String sql =  "Select d from Poste d";
			TypedQuery<Poste> query =  getEntityManager().createQuery(sql, Poste.class);	
			list =  query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	public List<Poste> getPostesLIst(Integer codigoNodo, String criterio) {
		
		List<Poste> list =  Collections.EMPTY_LIST;
		try{
			
			String sql =  "Select p from Poste p  "
					+ " where (p.nodo.codigoNodo=:pcodigonodo  or  :pcodigonodo is null ) "
					
					+ "   and ( ( cast(p.codigoPoste as string ) like :pcriterio "
					+ "			or  upper(p.descripcion) like :pcriterio "
					+ "			or  cast(p.latitud  as string ) like :pcriterio "
					+ "			or  cast(p.longitud as string ) like :pcriterio ) or :pcriterio is null ) order by p.codigoPoste "; 
			
			
			TypedQuery<Poste> query =  getEntityManager().createQuery(sql, Poste.class);
			query.setParameter("pcodigonodo", codigoNodo);
			criterio = criterio==null?null:"%"+criterio.trim().trim().toUpperCase()+"%";
			query.setParameter("pcriterio", criterio);
			list =  query.getResultList();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}


	

	/**
	 * hasAsignaciones
	 * 
	 * 
	 * */
	public boolean hasAsignaciones(Poste poste) {
		
		boolean has = false;
		
	/*try{
			String sql = "Select count(1) From Nodo p join p.postes where  p.codigoNodo = :pcodigonodo ";
			TypedQuery<Long> query = getEntityManager().createQuery(sql,Long.class);
			query.setParameter("pcodigonodo", poste.getCodigoPoste());
			if(((Long)query.getSingleResult()).intValue()>0){
				has =  true;
			}
	
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		}*/
	
		
		return has;
	}


	public Poste getPoste(Integer codigoPoste) {
		
		
		Poste poste = null;
		
		try{
			
			String sql = "Select p From Poste p where p.codigoPoste = :pcodigoposte";
			TypedQuery<Poste> query  = getEntityManager().createQuery(sql, Poste.class);
			query.setParameter("pcodigoposte", codigoPoste);
			
			if(query.getResultList().size()>0){
				poste =  query.getResultList().get(0);
			}
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		return poste;
		
		
	}


	public int getMax(Poste poste) {
		logger.info("metodo:  getCount");
		
		try{
			
			String sql = "Select max(p.codigoPoste) From Poste p";
			TypedQuery<Integer> query = getEntityManager().createQuery(sql,Integer.class);
			return ((Integer)query.getSingleResult()).intValue();
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
			
			return 0;
		}
	}


	public Poste getPoste(String descripcion) {
		Poste poste = null;
		
		try{
			
			String sql = "Select p From Poste p where upper(p.descripcion) = upper(:pdescripcion)";
			TypedQuery<Poste> query  = getEntityManager().createQuery(sql, Poste.class);
			query.setParameter("pdescripcion", descripcion.trim());
			if(query.getResultList().size()>0){
				poste =  query.getResultList().get(0);
			}
			
			
		}catch(Exception e ){
			e.printStackTrace();
			logger.info(e);
			logger.error(e);
		
		}
	
		return poste;
	}


}
