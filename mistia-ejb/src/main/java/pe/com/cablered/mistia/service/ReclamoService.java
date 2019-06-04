package pe.com.cablered.mistia.service;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import pe.com.cablered.mistia.dao.ReclamoDao;
import pe.com.cablered.mistia.model.Reclamo;


@Stateless
public class ReclamoService extends AbstractSevice<Reclamo> {

	@Inject
	private  ReclamoDao reclamoDao;
	
	
	@Override
	public Response registrar(Reclamo reclamo) {
		
		Response  response =  new Response();

		reclamoDao.create(reclamo);
		
		return response;
	}

	@Override
	public Response modificar(Reclamo reclamo) {
		 Response  response =  new Response();
		
		 reclamoDao.update(reclamo);
		 
		 return response;
	}

	@Override
	public Response eliminar(Reclamo reclamo) {
		Response  response =  new Response();
		
		reclamoDao.remove(reclamo);
		
		return response;
	}
	
	public List<Reclamo>  getReclamoList(){
	
		return  reclamoDao.getReclamoList();
		
	}

	public List<Reclamo> getReclamoList(String criterio, Date fechaRegistroIni, Date fechaRegistroFin) {
		return reclamoDao.getReclamoList(criterio,fechaRegistroIni,fechaRegistroFin);
		
	}

}
