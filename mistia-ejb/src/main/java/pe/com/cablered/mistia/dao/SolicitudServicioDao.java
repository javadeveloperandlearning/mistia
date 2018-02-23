package pe.com.cablered.mistia.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.ContratoServicio;
import pe.com.cablered.mistia.model.Estado;
import pe.com.cablered.mistia.model.PlanTrabajoDetallePK;
import pe.com.cablered.mistia.model.Programacion;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.model.SolicitudServicioEstado;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.mistia.util.Util;


@ApplicationScoped
public class SolicitudServicioDao extends CrudDao<SolicitudServicio>{
	
	
	
	@Inject
	private EntityManager em;
	
	
	@Inject
	private SolicitudServicioEstadoDao  solicitudServicioEstadoDao ;
	
	final static Logger logger = Logger.getLogger(SolicitudServicioDao.class);
	
	

	@Override
	protected EntityManager getEntityManager() {
	
		return em;
	}
	
	public SolicitudServicioDao() {
		
		super(SolicitudServicio.class);
	}
	
	
	public List<SolicitudServicio> getSolicitudList(int codigoTipoSolicitud){
		
		List<SolicitudServicio> list = Collections.EMPTY_LIST;
		try {
			

			String sql = "Select new SolicitudServicio (s.numeroSolicitud,s.fechaAtencion , s.fechaSolicitud, s.poste) "
					+ " from SolicitudServicio s join s.tipoSolicitudServicio t where  t.codigoTipo  =:pcodigotipo ";
			TypedQuery<SolicitudServicio> query = getEntityManager().createQuery(sql, SolicitudServicio.class);
			query.setParameter("pcodigotipo", codigoTipoSolicitud);
			list = query.getResultList();
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		return list;
	} 
	
	
	
	
	public SolicitudServicio getSolicitud(long numeroSolicitud){
	
		logger.info(" getSolicitud  : "+numeroSolicitud);
		
		SolicitudServicio solicitudServicio = null;
		//System.out.println(" Consultando numeroSolicitud :"+numeroSolicitud);
		
		try {
			String sql = "Select  s from SolicitudServicio s where s.numeroSolicitud =:pnumeroSolicitud ";
			
			TypedQuery<SolicitudServicio> query = getEntityManager().createQuery(sql, SolicitudServicio.class);
			query.setParameter("pnumeroSolicitud", numeroSolicitud);
			solicitudServicio = query.getSingleResult();
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		return solicitudServicio;
	}




	public List<SolicitudServicio> getSolicitudListPorEstado(int codigoEstado) {
		
		
		List<SolicitudServicio> list = Collections.EMPTY_LIST;
		try {
			String sql = "Select new SolicitudServicio (s.numeroSolicitud,s.fechaAtencion , s.fechaSolicitud, s.poste, s.tipoSolicitud, s.contratoServicio) "
					+ " from SolicitudServicio s join s.estado e where e.codigoEstado  =:pcodigoestado order by s.numeroSolicitud ";
			TypedQuery<SolicitudServicio> query = getEntityManager().createQuery(sql, SolicitudServicio.class);
			query.setParameter("pcodigoestado", codigoEstado);
			list = query.getResultList();
			
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return list;
	}




	public Response actualizarEstado(SolicitudServicio solicitudServicio) {
	
		Response response = new Response(Response.OK, Response.MSG_OK);
		
		try{	
				
			   // actualizacion del estado
				String sql =  " update SolicitudServicio s "
						+ " set s.estado =:pestado "
						+ " where s.numeroSolicitud =:pnumerosolicitud";
				Query query =  getEntityManager().createQuery(sql);
				query.setParameter("pestado", solicitudServicio.getEstado());
				query.setParameter("pnumerosolicitud", solicitudServicio.getNumeroSolicitud());
				query.executeUpdate();
				
				//actualizacion de tracking
				solicitudServicioEstadoDao.insert(solicitudServicio.getNumeroSolicitud(), 
													solicitudServicio.getEstado().getCodigoEstado());
				getEntityManager().flush();
		}catch(Exception e ){
		
			e.printStackTrace();
			response = new Response(Response.OK, Response.MSG_OK);
			
		}
		
		return response;
		
	} 
	
	
	public List<Map> getSolicitudList(Integer codigoCliente, Long numeroCuadrilla, Integer codigoDistrito, Date fechaIni, Date fechaFin, Integer codigoEstado  ) {
		
		List<Map> lista = new ArrayList<>();
		try {
			String sql = "Select  s.numeroSolicitud, "
					+ "   e.descripcion  as desEstado,"
					+ "   se.fechaHora as cambioestado, "
					+ "   s.fechaCreacion as fecharegistro, "
					+ "   c.codigoCliente,"
					+ " cu.nombre as  desCuadrilla, "
					+ " (select  count(1) from s.encuestaSolicitudResultados esr ) as cantencuestas"
					+ " from SolicitudServicio s  "
					+ " join s.estado e  "
					+ " join s.contratoServicio cs  "
					+ " join s.planTrabajoDetalles pd"
					+ " join s.solicitudServicioEstados se"
					+ " join pd.planTrabajo p"
					+ " join p.cuadrilla cu "
					+ " join cs.cliente c  "
					+ "	join s.distrito  d"
					+ " where "
					+ " s.estado =  se.estado	"
					+ " and s.estado.codigoEstado = :pcodigoestado"
					+ " and (cu.numeroCuadrilla = :pnumerocuadrilla or :pnumerocuadrilla is null)"
					+ " and (d.codigoDistrito = :pcodigodistrito or :pcodigodistrito is null)"
					+ " and date_trunc('day',s.fechaSolicitud)   between  :paramfecIni and  :paramfecFin"
					+ "";
					;
						
			Query  query = getEntityManager().createQuery(sql);
			query.setParameter("pcodigoestado",codigoEstado);
			query.setParameter("pnumerocuadrilla",numeroCuadrilla);
			query.setParameter("pcodigodistrito",codigoDistrito);
			query.setParameter("paramfecIni", fechaIni, TemporalType.TIMESTAMP);
			query.setParameter("paramfecFin", fechaFin, TemporalType.TIMESTAMP);
			List<Object[]> list = query.getResultList();
			
			for (Object[] result : list) {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("numeroSolicitud", result[0]);
				map.put("desEstado", result[1]);
				map.put("cambioestado", result[2]);
				map.put("fecharegistro", result[3]);
				map.put("codigoCliente", result[4]);
				map.put("desCuadrilla", result[5]);
				map.put("cantencuestas", result[6]);
				lista.add(map);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return lista;
	}
	
	
	
	
	public List<Map> getSolicitudList(
			Integer codigoCliente, 
			Integer codigoTipoSolicitud, 
			Integer codigoEstado,
			Date fechaInicio,
			Date fechaFin) {
	
		logger.info(" ### metodo : getSolicitudList");
		
		logger.info(" codigoCliente :"+codigoCliente);
		logger.info(" codigoTipoSolicitud :"+codigoTipoSolicitud);
		logger.info(" codigoEstado :"+codigoEstado);
		logger.info(" fechaInicio :"+fechaInicio);
		logger.info(" fechaFin :"+fechaFin);
		
		
		
		
		List<Map> lista = new ArrayList<>();
		try {
			String sql = "Select  s.numeroSolicitud, "
					+ "   e.descripcion  as desEstado,"
					+ "   se.fechaHora as cambioestado, "
					+ "   s.fechaCreacion as fecharegistro, "
					+ "   c.codigoCliente, "
					+ "   concat(c.apellidos,' ', c.nombres ) as nomcliente,  "
					+ "   s   "
				
					+ " from SolicitudServicio s  "
					+ " join s.estado e  "
					+ " join s.contratoServicio cs  "
					+ " left join s.solicitudServicioEstados se"
					+ " join cs.cliente c  "
					+ " where "
					+ " "
					+ "   (s.estado.codigoEstado = :pcodigoestado or :pcodigoestado is null )"
					+ " and (s.tipoSolicitud.codigoTipoSolicitud =  :pcodigotiposolicitud   or  :pcodigotiposolicitud is null)"
					+ " and (c.codigoCliente = :pcodigocliente  or :pcodigocliente is null ) "
					+ " and date_trunc('day',s.fechaSolicitud)   between  :paramfecIni and  :paramfecFin"
					+ " order by s.numeroSolicitud ";
					;
						
			Query  query = getEntityManager().createQuery(sql);
			query.setParameter("pcodigoestado",codigoEstado);
			query.setParameter("pcodigotiposolicitud",codigoTipoSolicitud);
			query.setParameter("pcodigocliente",codigoCliente);
			query.setParameter("paramfecIni", fechaInicio, TemporalType.TIMESTAMP);
			query.setParameter("paramfecFin", fechaFin, TemporalType.TIMESTAMP);
			
			List<Object[]> list = query.getResultList();
			
			logger.info(" cant items :"+list.size());
			
			for (Object[] result : list) {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("numeroSolicitud", result[0]);
				map.put("desEstado", result[1]);
				map.put("cambioestado", result[2]);
				map.put("fecharegistro", result[3]);
				map.put("codigoCliente", result[4]);
				map.put("nomcliente", result[5]);
				map.put("tag", Util.getTag((SolicitudServicio) result[6]));
				
				
				lista.add(map);
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		
		return lista;
	}
	
	
	
	
	
	public Response insert(SolicitudServicio solicitudServicio) {
		
		Response response =  new Response(Response.OK, Response.MSG_OK);
		try{
			String sqlcount  =  "Select max(numeroSolicitud) from SolicitudServicio p";
			TypedQuery<Long>  query = getEntityManager().createQuery(sqlcount, Long.class);
			Long cant  = query.getSingleResult();
			Long ns =  cant==null?1:cant+1;
			solicitudServicio.setNumeroSolicitud(ns);		
			create(solicitudServicio);
			response.setData(ns);
			
		}catch(Exception e ){
			e.printStackTrace();
			return new Response(Response.ERROR, Response.MSG_ERROR);
		}
		
		return response;
	}
	
	
	
	
	
	
	

}
