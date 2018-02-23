package pe.com.cablered.mistia.dao;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
//import org.hibernate.Session;
import pe.com.cablered.mistia.service.Response;

@Stateless
public class GeneralDao {
	
	
	@Inject
	private EntityManager em;

	//Session session;

	
	
	public void reset(){
		
		System.out.println("#### RESETANDO TABLAS ### ");
		
		EntityTransaction tx  = null;
		
		try{	
			
			   // actualizacion del estadorollback
			
				//session = em.unwrap(Session.class);
			    //tx =  em.getTransaction();
				String sql =  " delete from ProgramacionDetalle  d";
				Query query =  em.createQuery(sql);
				query.executeUpdate();
				
				sql =  " delete from Programacion  d ";
				query =  em.createQuery(sql);
				query.executeUpdate();
				
				sql =  " delete from PlanTrabajoDetalle  d ";
				query =  em.createQuery(sql);
				query.executeUpdate();
				
				sql =  " delete from PlanTrabajo  d";
				query =  em.createQuery(sql);
				query.executeUpdate();
			
				sql =  " delete from GrupoAtencionDetalle  d";
				query =  em.createQuery(sql);
				query.executeUpdate();
				
				sql =  " delete from GrupoAtencion  d ";
				query =  em.createQuery(sql);
				query.executeUpdate();
				
				
				
				sql =  " delete from SolicitudServicioEstado  d ";
				query =  em.createQuery(sql);
				query.executeUpdate();
	
				
				
				sql =  " update SolicitudServicio s set  s.estado.codigoEstado  = 5";
				query =  em.createQuery(sql);
				query.executeUpdate();
				//tx.commit();
				
				
		}catch(Exception e ){
		
			
			e.printStackTrace();
			
			
		}
		
		
		
		
		
	}
	
	
}
