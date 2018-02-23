package pe.com.cablered.mistia.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;


import pe.com.cablered.mistia.dao.GrupoAtencionDao;
import pe.com.cablered.mistia.dao.SolicitudServicioDao;
import pe.com.cablered.mistia.dao.SolicitudServicioEstadoDao;
import pe.com.cablered.mistia.dao.SolicitudServicioHorarioAtencionDao;
import pe.com.cablered.mistia.model.Estado;
import pe.com.cablered.mistia.model.GrupoAtencion;
import pe.com.cablered.mistia.model.GrupoAtencionDetalle;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.model.SolicitudServicioHorarioAtencion;
import pe.com.cablered.mistia.util.Util;

import static pe.com.cablered.mistia.util.ConstantBusiness.*;


@Stateless
@LocalBean
public class SolicitudServicioService {

	@Inject
	private SolicitudServicioDao solicitudServicioDao;
	

	@Inject
	private GrupoAtencionDao grupoAtencionDao;
	
	
	@Inject
	private SolicitudServicioHorarioAtencionDao horarioDau;
	
	
	@Inject
	private SolicitudServicioEstadoDao solicitudServicioEstadoDao;
	
	
	// lista de grupos en session
	private  Map<Long, GrupoAtencion> mpGruposCached ;
	
	
	
	


	
	
	/**
	 * obtiene las lista de solicitudes pendientes, grupos a los cuales estan asigandos y sus respectivas prioridades
	 * 
	 * */
	public List<Map<String, Object>> getSolicitudList( Date fecprgn,   int codigoTipoSolicitud) {

		List<Map<String, Object>> pendientes = new ArrayList<Map<String, Object>>();
		List<SolicitudServicio> ssList = solicitudServicioDao.getSolicitudList(codigoTipoSolicitud);
		for (SolicitudServicio s : ssList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("numeroSolicitud", s.getNumeroSolicitud());
			GrupoAtencion g =  getGrupoAtencion(fecprgn, s);
			map.put("descripcionGrupo", g==null?"":g.getDescripcion());
			map.put("prioridad", getPriortidad(s));
			pendientes.add(map);
		}
		return pendientes;
	}
	
	public List<SolicitudServicio> getSolicitudList(   int codigoTipoSolicitud) {

		return solicitudServicioDao.getSolicitudList(codigoTipoSolicitud);

	}
	
	
	
	public List<SolicitudServicio> getSolicitudListPorEstado(   int codigoEstado) {

		return solicitudServicioDao.getSolicitudListPorEstado(codigoEstado);

	}
	
	
	
	
	
	
	
	private String  getPriortidad( SolicitudServicio s ){
		/*if(s.getTipoSolicitudServicio()!=null &&  s.getTipoSolicitudServicio().getCodigoTipo()==1){
		}*/
		return "ALTA";
	}
	
	/***
	 * getGrupoAtencion obtiene el grupo solicitud asociado a un grupo de atencion ya se de cache o de bd
	 * 
	 * */
	
	private GrupoAtencion getGrupoAtencion(Date fecPrgn, SolicitudServicio solicitudServicio){
		
		GrupoAtencion g =  null;		
		List<GrupoAtencionDetalle>  detalles =  Collections.EMPTY_LIST;
		
		if(mpGruposCached!=null && mpGruposCached.size()> 0){

			detalles  =  new ArrayList<GrupoAtencionDetalle>();
			Iterator<Long> it =  mpGruposCached.keySet().iterator();
			while(it.hasNext()){
			Long numerogrupo = 	it.next();
				
			List<GrupoAtencionDetalle>  _detalles =    mpGruposCached.get(numerogrupo).getGrupoAtencionDetalles();
			detalles.addAll(_detalles);
				
			}
			
		}else{			
			
			detalles =  grupoAtencionDao.getDetallesGrupoAtencion(fecPrgn);
	
		}

		
		if(detalles!=null){
			for (GrupoAtencionDetalle d : detalles) {
				if(  d.getSolicitudServicio().equals(solicitudServicio)){
					g =  d.getGrupoAtencion();
					break;
					
				};		
			}
		}
		
		return g;
	}

	
	
	public List<Map> getSolicitudList(Integer codigoCliente, Long numeroCuadrilla, Integer codigoDistrito, Date fechaIni, Date fechaFin ) {
		
		
		return solicitudServicioDao.getSolicitudList(codigoCliente, numeroCuadrilla, codigoDistrito, fechaIni, fechaFin, ESTADO_SOLICITUD_CERRADO);
	}
	
	
	
	
	
	public Map<Long, GrupoAtencion> getMpGruposCached() {
		return mpGruposCached;
	}

	public void setMpGruposCached(Map<Long, GrupoAtencion> mpGruposCached) {
		this.mpGruposCached = mpGruposCached;
	}

	public SolicitudServicio getSolicitudServicio(Long  numeroSolicitud) {
		try{
			return solicitudServicioDao.find(numeroSolicitud);
		}catch(Exception e ){
			e.printStackTrace();
			return null;
		}
	}

	public List<Map> getSolicitudList(Integer codigoCliente, Integer codigoTipoSolicitud, Integer codigoEstado,
			Date fechaInicio, Date fechaFin) {
		List<Map>  list =  solicitudServicioDao.getSolicitudList(codigoCliente, codigoTipoSolicitud, codigoEstado, fechaInicio, fechaFin);
		return list;
	}
	
	
	
	
	public Response insertar(SolicitudServicio solicitudServicio) {
		
		Response response =  new  Response(Response.OK, Response.MSG_OK) ;
		try{
			
			
			solicitudServicio.setEstado(new Estado(ESTADO_SOLICITUD_PENDIENTE));
			
			// insertando la solicitud 
			 response =    solicitudServicioDao.insert(solicitudServicio);
			
			 Long ns =  (Long) response.getData();
		
			//actualizacion de tracking
			solicitudServicioEstadoDao.insert(ns, 
												solicitudServicio.getEstado().getCodigoEstado());
			
			// insertando horario de atencion 
			int  nse  = 1;			
			for(SolicitudServicioHorarioAtencion h: solicitudServicio.getSolicitudServicioHorarioAtencionList()){
				h.setId(solicitudServicio.getNumeroSolicitud(), nse );
				nse++;
				horarioDau.create(h);
			}			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			response =  new  Response(Response.ERROR, Response.MSG_ERROR) ;
		}
		return response;
	}
	
	
	
	
	
	
	

	public List<Map> getCantidadTelevisoresList(){
		List<Map> mpList  =  new ArrayList<>();
		int cant =  4;
		for (int i = 1; i < cant; i++) {
			Map<String, Object> mp =  new HashMap<>();
			mp.put("codigo",i);
			mp.put("descripcion","0"+i+" ("+Util.getNombreNumero(i)+")");
			mp.put("cantidad",2);
			mpList.add(mp);
		}
		return mpList;
	}

	
	
	public List<Map> getHorarioAtencionList() {
		List<Map> horarioList  =  new ArrayList<>();
		horarioList.add(createhorario("08:00-10:00", LUN,MAR,MIE,JUE,VIE,SAB,DOM) );
		horarioList.add(createhorario("10:00-12:00", LUN,MAR,MIE,JUE,VIE,SAB,DOM) );
		horarioList.add(createhorario("12:00-14:00", LUN,MAR,MIE,JUE,VIE,SAB,DOM) );
		horarioList.add(createhorario("14:00-16:00", LUN,MAR,MIE,JUE,VIE,SAB,DOM) );
		horarioList.add(createhorario("16:00-18:00", LUN,MAR,MIE,JUE,VIE,SAB,DOM) );	
		return horarioList;
	}

	
	private Map createhorario(String hora, int ...dia ){
		
		Map mp =  new LinkedHashMap<>();
		mp.put("horario", hora);
		for (int i : dia) {
			mp.put(i, null);
		}
		return mp;
		
	}




}
