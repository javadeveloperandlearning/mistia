package pe.com.cablered.mistia.dao;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import pe.com.cablered.mistia.model.Programacion;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.service.Response;

@ApplicationScoped
public class ProgramacionDao extends CrudDao<Programacion> {

	@Inject
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public ProgramacionDao() {
		
		setClass(Programacion.class);
		
	}

	public List<Programacion> getProgramacionList(Date fechaInicio, Date fechaFin ) {

		List<Programacion> list = Collections.EMPTY_LIST;
		try {
			String sql = "Select p from Programacion "
					+ " p where date_trunc('day', p.fechaProgramacion) between  :paramfecIni and  :paramfecFin"
					+ " order by p.numeroProgramacion";
			
			TypedQuery<Programacion> query = getEntityManager().createQuery(sql, Programacion.class);
			
			query.setParameter("paramfecIni", fechaInicio, TemporalType.TIMESTAMP);
			query.setParameter("paramfecFin", fechaFin, TemporalType.TIMESTAMP);
			
			list = query.getResultList();

		} catch (Exception e) {
				e.printStackTrace();
		}
		return list;

	}

	public Response insert(Programacion programacion) {
		

		Response response =  new Response(Response.OK, Response.MSG_OK);
		try{
		
			
			String sqlcount  =  "Select count(1) from Programacion p";
			TypedQuery<Long>  query = getEntityManager().createQuery(sqlcount, Long.class);
			Long cant  = query.getSingleResult();
			Long np =  cant+1;
			programacion.setNumeroProgramacion(np);
			
			
			create(programacion);
			response.setData(np);
			
		}catch(Exception e ){
			e.printStackTrace();
			return new Response(Response.ERROR, Response.MSG_ERROR);
		}
		
		return response;
		
		
	}

	
	
	public Response actualizarEstado(Programacion p) {
		
		Response response = new Response(Response.OK, Response.MSG_OK);
		
	   try{	
			
				String sql =  " update Programacion p "
						+ " set p.estado =:pestado "
						+ " where p.numeroProgramacion =:pnumeroprogramacion";
				Query query =  getEntityManager().createQuery(sql);
				query.setParameter("pestado", p.getEstado());
				query.setParameter("pnumeroprogramacion", p.getNumeroProgramacion());
				query.executeUpdate();

			
		}catch(Exception e ){
		
			e.printStackTrace();
			response = new Response(Response.OK, Response.MSG_OK);
			
		}
		
		return response;

	}


	
	
	
	
	
	

}
