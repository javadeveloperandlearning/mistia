package pe.com.cablered.seguridad.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.seguridad.dao.ModuloDao;
import pe.com.cablered.seguridad.model.Modulo;

@Stateless
@LocalBean
public class ModuloService {

	@Inject
	ModuloDao moduloRepository;
	
	public List<Modulo> getModulos() {
		
		return moduloRepository.getModulos();
	}

	public Modulo getModulo(String desModu) {
	
		return moduloRepository.getModulo(desModu);
	}

}
