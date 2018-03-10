package pe.com.cablered.seguridad.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.seguridad.dao.PerfilOpcionDao;
import pe.com.cablered.seguridad.dao.UsuarioPerfilDao;
import pe.com.cablered.seguridad.model.Opcion;
import pe.com.cablered.seguridad.model.Perfil;
import pe.com.cablered.seguridad.model.PerfilOpcion;
import pe.com.cablered.seguridad.model.Usuario;
import pe.com.cablered.seguridad.model.UsuarioPerfil;

@Stateless
@LocalBean
public class AccessManger {

	@Inject
	UsuarioPerfilDao usuarioPerfilRepository;

	@Inject
	PerfilOpcionDao perfilOpcionRepository;

	public List<Opcion> getOpciones(Usuario usuario) {

		List<Opcion> opciones = new ArrayList<Opcion>();
		// obteniendo lista de perfiles
		List<UsuarioPerfil> usuarioPerfilList = usuarioPerfilRepository .getUsuarioPerfilList(usuario);
		List<Perfil> perfilList = new ArrayList<Perfil>();
		for (UsuarioPerfil usuarioPerfil : usuarioPerfilList) {
			perfilList.add(usuarioPerfil.getPerfil());
		}

		List<PerfilOpcion> perfilOpcionList = perfilOpcionRepository.getPerfilOpcion(perfilList);
		if (perfilOpcionList != null) {
			for (PerfilOpcion perfilOpcion : perfilOpcionList) {
				opciones.add(perfilOpcion.getOpcion());
			}
		}

		return opciones;
	}

}
