package pe.com.cablered.seguridad.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import pe.com.cablered.mistia.util.ConstantSecurity;
import pe.com.cablered.seguridad.dao.PerfilDao;
import pe.com.cablered.seguridad.model.Modulo;
import pe.com.cablered.seguridad.model.Perfil;
import pe.com.cablered.seguridad.model.PerfilPK;

@Stateless
@LocalBean
public class PerfilService {

	@Inject
	PerfilDao perfilDao;

	public PerfilService() {

	}

	public List<Perfil> getPerfilList(Modulo modulo) {

		return perfilDao.getPerfilList(modulo);
	}

	public ResponseSecurity create(Perfil perfil) {

		ResponseSecurity response = new ResponseSecurity();

		try {
			perfilDao.setClass(Perfil.class);
			int count = perfilDao.getCount(perfil.getModulo());
			perfil.setId(new PerfilPK(perfil.getModulo().getCodModu(),
					count + 1));

			perfilDao.create(perfil);
			response.setCodigo(ConstantSecurity.COD_OK);
			response.setMessage(ConstantSecurity.MSG_OK);
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response.setCodigo(ConstantSecurity.COD_ERR);
			response.setMessage(ConstantSecurity.MSG_ERRO);
			return response;

		}

	}

	public Perfil getPerfil(Perfil perfil) {

		return perfilDao.find(perfil.getId());
	}

	public ResponseSecurity update(Perfil perfil) {

		ResponseSecurity response = new ResponseSecurity();

		try {
			perfilDao.update(perfil);
			response.setCodigo(ConstantSecurity.COD_OK);
			response.setMessage(ConstantSecurity.MSG_OK);
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response.setCodigo(ConstantSecurity.COD_ERR);
			response.setMessage(ConstantSecurity.MSG_ERRO);
			return response;

		}

	}

	public ResponseSecurity remove(Perfil perfil) {
		ResponseSecurity response = new ResponseSecurity();

		try {
			
			perfilDao.remove(perfil);
			response.setCodigo(ConstantSecurity.COD_OK);
			response.setMessage(ConstantSecurity.MSG_OK);
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response.setCodigo(ConstantSecurity.COD_ERR);
			response.setMessage(ConstantSecurity.MSG_ERRO);
			return response;

		}
	}

}
