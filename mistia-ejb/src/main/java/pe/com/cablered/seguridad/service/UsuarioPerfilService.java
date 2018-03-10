package pe.com.cablered.seguridad.service;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.seguridad.dao.UsuarioPerfilDao;
import pe.com.cablered.seguridad.model.Perfil;
import pe.com.cablered.seguridad.model.Usuario;
import pe.com.cablered.seguridad.model.UsuarioPerfil;


@Stateless
@LocalBean
public class UsuarioPerfilService {
	
	
	@Inject
	UsuarioPerfilDao usuarioPerfilRepository;
	
	
	public UsuarioPerfilService() {
		
	}
	
	/**
	 * getPerfilesByUsuario
	 * 
	 * obtiene la lista de los perfiles segun el usuario
	 * 
	 * */
	public  List<Perfil> getPerfilesByUsuario(Usuario usuario){
		
		List<Perfil>  perfilList = new ArrayList<Perfil>();
		List<UsuarioPerfil> usuarioPerfilList = usuarioPerfilRepository.getUsuarioPerfilList(usuario);
		
		for (UsuarioPerfil usuarioPerfil : usuarioPerfilList) {
			perfilList.add(usuarioPerfil.getPerfil());
		}
		
		return perfilList;
	}
	

}
