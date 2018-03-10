package pe.com.cablered.seguridad.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.seguridad.dao.EstadoRegistroDao;
import pe.com.cablered.seguridad.model.EstadoRegistro;
import pe.com.cablered.seguridad.model.Usuario;

@Stateless
public class EstadoRegistroService {
	
	
	
	@Inject
	EstadoRegistroDao estadoRegistroRepository;

	
	
	public List<EstadoRegistro> getEstadoRegistroList(int codGrupo) {
	
		return estadoRegistroRepository.getEstadoRegistroList(codGrupo);
	}



	public EstadoRegistro getEstadoRegistro(String desEsta) {
		
		return estadoRegistroRepository.getEstadoRegistro(desEsta);
	}



	
	
	

}
