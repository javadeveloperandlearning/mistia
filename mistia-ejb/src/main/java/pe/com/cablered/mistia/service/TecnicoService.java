package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.CuadrillaDao;
import pe.com.cablered.mistia.dao.CuadrillasDetalleDao;
import pe.com.cablered.mistia.dao.TecnicoDao;
import pe.com.cablered.mistia.model.Cargo;
import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Tecnico;
import pe.com.cablered.mistia.model.Vehiculo;

@Stateless
public class TecnicoService {
	
	
	final static Logger logger = Logger.getLogger(TecnicoService.class);
	
	@Inject
	private TecnicoDao tecnicoDao;
	
	@Inject
	private CuadrillasDetalleDao cuadrillasDetalleDao;
	
	
	public Response registrar(Tecnico tecnico){
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		
		try{
			
			int codigoTenico = tecnicoDao.getMax(tecnico)+1;
			tecnico.setCodigoTecnico(codigoTenico);
			tecnicoDao.create(tecnico);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
		return response;
	}
	
	
	public Response modificar(Tecnico tecnico){
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		
		try{
			
			boolean isasignado =  cuadrillasDetalleDao.isAsigando(tecnico);
			
			if(isasignado){
				response =  new Response(Response.ERROR, " El técnico se encuentra asignado");
				return response;
			}
			
			tecnicoDao.update(tecnico);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
		return response;
	}
	
	
	
	public Response eliminar(Tecnico tecnico){
		
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		
		try{
			
			
			boolean asigando  =  cuadrillasDetalleDao.isAsigando(tecnico);
			if(asigando){
				
				response =  new Response(Response.ERROR , "El técnico ya se encuentra asignado a una cuadrilla");
				
				return response;
			}			
		
			tecnicoDao.remove(tecnico);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
		return response;
	}
	
	
	public List<Tecnico> getTecnicoList() {
		return tecnicoDao.getTecnicoList();
	}


	public List<Tecnico> getTecnicoList(String criterio) {

		return null;
	}


	public List<Tecnico> getTecnicoList(Distrito distrito, Cargo cargo, String criterio) {

		return tecnicoDao.getTecnicoList(distrito,cargo, criterio);
	}


	public Tecnico getTecnico(Integer codigoTecnico) {

		return tecnicoDao.getTecnico(codigoTecnico);
	}


	public Tecnico getTecnico(String nombres) {
			
		return tecnicoDao.getTecnico(nombres);
		
	}
	
	

}
