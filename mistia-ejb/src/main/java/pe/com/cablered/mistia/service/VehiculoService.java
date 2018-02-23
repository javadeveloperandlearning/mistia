package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.CuadrillaDao;
import pe.com.cablered.mistia.dao.VehiculoDao;
import pe.com.cablered.mistia.model.Vehiculo;
import pe.com.cablered.mistia.model.VehiculoMarca;
import pe.com.cablered.mistia.model.VehiculoModelo;

@Stateless
public class VehiculoService {
	
	
	final static Logger logger = Logger.getLogger(VehiculoService.class);
	
	@Inject
	private VehiculoDao vehiculoDao;
	
	
	@Inject
	private CuadrillaDao cuadrillaDao;
	
	
	
	public Response registrar(Vehiculo vehiculo){

		Response  response =  new Response(Response.OK, Response.MSG_OK);
		try{
		
		
			if(vehiculo.getDescripcion()==null){
				response =  new Response(Response.ERROR, " El nombre del Vehiculo no es válido");
				return response;
			}
			
			
			Vehiculo _vehiculo =  null;
			_vehiculo =  vehiculoDao.getVehiculo(vehiculo.getDescripcion());
			
			if(_vehiculo!=null){
				response =  new Response(Response.ERROR, "La descripción del vehiculo ya existe ingrese otra");
				return response;
			}
			
			
			_vehiculo =  vehiculoDao.getVehiculoByPlaca(vehiculo.getPlacaVehiculo());
			if(_vehiculo!=null){
				response =  new Response(Response.ERROR, "La placa del vehiculo ya existe ingrese otra");
				return response;
			}
			
			
			vehiculoDao.create(vehiculo);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
		
		return response;
		
	}
	
	
	public Response modificar(Vehiculo vehiculo){
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		try{
			
			if(vehiculo.getDescripcion()==null){
				response =  new Response(Response.ERROR, " El nombre del Vehiculo no es válido");
				return response;
			}
			
			Vehiculo _vehiculo =  null;
			_vehiculo =  vehiculoDao.getVehiculo(vehiculo.getDescripcion());
			
			if(_vehiculo!=null){
				response =  new Response(Response.ERROR, "La descripción del vehiculo ya existe ingrese otra");
				return response;
			}
			
			vehiculoDao.update(vehiculo);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
		return response;
	}
	
	
	public Response eliminar(Vehiculo vehiculo){
		
		Response  response =  new Response(Response.OK, Response.MSG_OK);
		try{
			boolean isasigndo  =   cuadrillaDao.isAsigando(vehiculo);
			if(isasigndo){
				response =  new Response(Response.ERROR, "El vehiculo está asignado a una cuadrilla");
				return response;
			}
			
			//validar que no este asigando
			
			vehiculoDao.remove(vehiculo);
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			e.printStackTrace();
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
		
		return response;
	}
	
	
	public List<Vehiculo> getVehiculoList() {
		return vehiculoDao.getVehiculoList();
	}


	public List<Vehiculo> getVehiculoList(VehiculoMarca vehiculoMarca,
			VehiculoModelo vehiculoModelo, String criterio) {
		return vehiculoDao.getVehiculoList(vehiculoMarca, vehiculoModelo, criterio);
	}


	public Vehiculo getVehiculo(String descripcion) {

		return vehiculoDao.getVehiculo(descripcion);
	}
	

}
