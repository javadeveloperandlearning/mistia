package pe.com.eb.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.eb.data.ModuloDao;
import pe.com.eb.model.Modulo;

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
