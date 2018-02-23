package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.EstadoDao;
import pe.com.cablered.mistia.model.Estado;

@Stateless
public class EstadoService {
	
	@Inject
	private EstadoDao estadoDao;
	
	
	public List<Estado> getEstadoList(int codigoGrupo){
		
		return estadoDao.getEstadoList(codigoGrupo);
		
		
	}

}
