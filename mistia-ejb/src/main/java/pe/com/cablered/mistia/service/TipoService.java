package pe.com.cablered.mistia.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.dao.TipoDao;
import pe.com.cablered.mistia.model.Tipo;

@Stateless
public class TipoService {

	@Inject
	private TipoDao tipoDao;
	
	
	public List<Tipo> getTipoList(int codigoGrupo) {
	
		return tipoDao.getTipoList(codigoGrupo);
	}

}
