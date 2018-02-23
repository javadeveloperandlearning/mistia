package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.GrupoAtencion;
import pe.com.cablered.mistia.model.GrupoAtencionDetalle;
import pe.com.cablered.mistia.service.Response;

@ApplicationScoped
public class GrupoAtencionDao  extends CrudDao<GrupoAtencion> {
	
	
	final static Logger logger = Logger.getLogger(GrupoAtencionDao.class); 
	
	
	@Inject
	private EntityManager em;

	
	
	public GrupoAtencionDao() {
		super(GrupoAtencion.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	
	
	public List<GrupoAtencionDetalle>  getDetallesGrupoAtencion(Date fecPrgn){
		
		
		List<GrupoAtencionDetalle>  lista  =   Collections.EMPTY_LIST;	
		try{
			
			String sql  = "Select  d  from GrupoAtencionDetalle   d join  "
					+ " d.grupoAtencion g where  g.fechaProgramacion =:pfechaProgramacion";
			TypedQuery<GrupoAtencionDetalle> query  =  getEntityManager().createQuery(sql, GrupoAtencionDetalle.class);
			query.setParameter("pfechaProgramacion", fecPrgn);
			lista =  query.getResultList();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
		
		
	}



	public List<GrupoAtencion> getGruposAtencionPorProgramacion(Long numeroProgramacion) {
		
		
		
		
		logger.info(" ## metodo : getGrupoAtencionDetalleListPorProgramacion ## "  );
		
		String sql = "Select distinct  a.grupoAtencion  "
				+ " from GrupoAtencionDetalle a "
				+ " join a.solicitudServicio s  "
				+ " join s.planTrabajoDetalles ptd"
				+ " join ptd.planTrabajo pt "
				+ " join pt.programacionDetalles pd    "
				+ " where pd.programacion.numeroProgramacion=:pnumeroProgramacion";
		
		TypedQuery<GrupoAtencion> query = getEntityManager().createQuery(sql, GrupoAtencion.class);
		query.setParameter("pnumeroProgramacion", numeroProgramacion);
		List<GrupoAtencion>  grupoAtencionList = query.getResultList();
		return grupoAtencionList;
		
		

	}
	
	
	public Response remove(long numeroGrupoAtencion) {
		
		
		Response response = new Response(Response.OK, Response.MSG_OK);
		
		try{	
				
		   String sql = "delete from GrupoAtencion p  where p.numeroGrupoAtencion = :pnumerogrupoatencion";
		   Query query =  getEntityManager().createQuery(sql);
		   query.setParameter("pnumerogrupoatencion", numeroGrupoAtencion);
		   query.executeUpdate();

		}catch(Exception e ){			
			e.printStackTrace();
			response = new Response(Response.OK, Response.MSG_OK);
				
		}
			
		return response;

	}
	
	
	
	
	

}
