package pe.com.cablered.mistia.dao;

import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.GrupoAtencionDetalle;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.service.Response;

@ApplicationScoped
public class GrupoAtencionDetalleDao extends CrudDao<GrupoAtencionDetalle>{
	
	
	final static Logger logger = Logger.getLogger(GrupoAtencionDetalleDao.class); 
	
	
	@Inject
	private EntityManager em;

	
	public GrupoAtencionDetalleDao() {
		super(GrupoAtencionDetalle.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}


	public List<GrupoAtencionDetalle> getGruposListPorSolicitudes(List<SolicitudServicio> solicitudes) {
		
		
		logger.info(" ## metodo : getGruposListPorSolicitudes ## "  );
		
		String sql = "Select   new GrupoAtencionDetalle(d.grupoAtencion, s.numeroSolicitud) "
				+ " from GrupoAtencionDetalle d join d.solicitudServicio s"
				+ " where d.solicitudServicio in (:psolicitudes) ";
		
		TypedQuery<GrupoAtencionDetalle> query = getEntityManager().createQuery(sql, GrupoAtencionDetalle.class);
		query.setParameter("psolicitudes", solicitudes);
		List<GrupoAtencionDetalle>  grupoAtencionDetalleList = query.getResultList();
		return grupoAtencionDetalleList;
	}
	
	
	public List<GrupoAtencionDetalle> getGrupoAtencionDetalleListPorProgramacion(Long numeroProgramacion ) {	
		
		logger.info(" ## metodo : getGrupoAtencionDetalleListPorProgramacion ## "  );
		
		String sql = "Select   new GrupoAtencionDetalle(a.grupoAtencion, s) "
				+ " from GrupoAtencionDetalle a "
				+ " join a.solicitudServicio s  "
				+ " join s.planTrabajoDetalles ptd"
				+ " join ptd.planTrabajo pt "
				+ " join pt.programacionDetalles pd    "
				+ " where pd.programacion.numeroProgramacion=:pnumeroProgramacion";
		
		TypedQuery<GrupoAtencionDetalle> query = getEntityManager().createQuery(sql, GrupoAtencionDetalle.class);
		query.setParameter("pnumeroProgramacion", numeroProgramacion);
		List<GrupoAtencionDetalle>  grupoAtencionDetalleList = query.getResultList();
		return grupoAtencionDetalleList;
	}

	public Response remove(long numeroGrupoAtencion) {
		
		
		Response response = new Response(Response.OK, Response.MSG_OK);
		
		try{	
				
		   String sql = "delete from GrupoAtencionDetalle p  where p.id.numeroGrupoAtencion = :pnumerogrupoatencion";
		   Query query =  getEntityManager().createQuery(sql);
		   query.setParameter("pnumerogrupoatencion", numeroGrupoAtencion);
		   query.executeUpdate();

		}catch(Exception e ){			
			e.printStackTrace();
			response = new Response(Response.OK, Response.MSG_OK);
				
		}
			
		return response;

	}

	public List<GrupoAtencionDetalle> getGrupoAtencionDetalleList(long numeroGrupoAtencion) {
	
		logger.info(" ## metodo : getGrupoAtencionDetalleListPorProgramacion ## "  );
		List<GrupoAtencionDetalle>  grupoAtencionDetalleList =  Collections.EMPTY_LIST;
		
		try{
			
			String sql = "Select  d from GrupoAtencionDetalle d"
					+ " where d.id.numeroGrupoAtencion = :pnumerogrupoatencion";
			TypedQuery<GrupoAtencionDetalle> query = getEntityManager().createQuery(sql, GrupoAtencionDetalle.class);
			query.setParameter("pnumerogrupoatencion", numeroGrupoAtencion);
			grupoAtencionDetalleList = query.getResultList();
			
			
		}catch(Exception e ){
			
			
		}
		
		return grupoAtencionDetalleList;
	}
	
	
	
	

}
