package pe.com.eb.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.eb.data.PerfilOpcionDao;
import pe.com.eb.data.UsuarioPerfilDao;
import pe.com.eb.model.Opcion;
import pe.com.eb.model.Perfil;
import pe.com.eb.model.PerfilOpcion;
import pe.com.eb.model.Usuario;
import pe.com.eb.model.UsuarioPerfil;

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
		List<UsuarioPerfil> usuarioPerfilList = usuarioPerfilRepository
				.getUsuarioPerfilList(usuario);
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
