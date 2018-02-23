package pe.com.eb.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.util.ConstantSecurity;
import pe.com.eb.commons.TripleDES;
import pe.com.eb.data.UsuarioDao;
import pe.com.eb.model.Usuario;

/**
 * Session Bean implementation class UsuarioManager
 */
@Stateless
public class UsuarioService {

	//@EJB
	@Inject
	UsuarioDao usuarioRepository;
	
	final static Logger logger = Logger.getLogger(UsuarioService.class);

	/**
	 * Default constructor.
	 */
	public UsuarioService() {

	}

	/**
	 * autenticar
	 * */

	public ResponseSecurity loguear(Usuario usuario) {

		
		if(usuario!=null && usuario.getCodUsua()!=null){
			usuario.setCodUsua(usuario.getCodUsua().toUpperCase());
		}
		
		ResponseSecurity responseSecurity = new ResponseSecurity();

		Usuario _usuario = usuarioRepository.getUsuario(usuario);

		logger.info(" El usuario:" + usuario);

		/* El usuario y clave son incorrecto */
		if (_usuario == null) {

			responseSecurity.setCodigo(ConstantSecurity.COD_USER_ERRO_SESI);
			responseSecurity.setMessage(ConstantSecurity.MSG_USER_ERRO_SESI);

			return responseSecurity;

			/* El usuario esta bloqueado */
		} else if (_usuario != null
				&& _usuario.getEstadoRegistro().getCodEsta() == 2) {

			responseSecurity.setCodigo(ConstantSecurity.COD_USER_BLOQ);
			responseSecurity.setMessage(ConstantSecurity.MSG_USER_BLOQ);

			return responseSecurity;

		} else {

			responseSecurity.setCodigo(ConstantSecurity.COD_OK);
			responseSecurity.setMessage(ConstantSecurity.MSG_OK);

			return responseSecurity;
		}

	}

	public List<Usuario> getUsuarioList() {
		// TODO Auto-generated method stub
		return usuarioRepository.getUsuarioList();
	}

	public List<Usuario> getUsuariosList(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.getUsuarioList(usuario);
	}

	public ResponseSecurity create(pe.com.eb.model.Usuario usuario) {

		// validaciones

		ResponseSecurity responseSecurity = new ResponseSecurity();
		// MSG_ERRO_VALI
		if ((usuario.getCodUsua() != null && usuario.getCodUsua().trim().equals(""))) {

			responseSecurity.setCodigo(ConstantSecurity.COD_ERRO_VALI);
			responseSecurity.setMessage(ConstantSecurity.MSG_ERRO_VALI);
			return responseSecurity;
		}

		if (usuario.getFec_cadu() == null) {

			responseSecurity.setCodigo(ConstantSecurity.COD_ERRO_VALI);
			responseSecurity.setMessage(ConstantSecurity.MSG_ERRO_VALI);
			return responseSecurity;
		}

		if (usuario.getFec_cadu_clave() == null) {

			responseSecurity.setCodigo(ConstantSecurity.COD_ERRO_VALI);
			responseSecurity.setMessage(ConstantSecurity.MSG_ERRO_VALI);
			return responseSecurity;
		}

		// la cuenta de usuario no se debe repetir
		Usuario _usuario = usuarioRepository.getUsuarioSingle(usuario);

		if (_usuario != null) {

			responseSecurity.setCodigo(ConstantSecurity.COD_USER_EXIST);
			responseSecurity.setMessage(ConstantSecurity.MSG_USER_EXIST);
			return responseSecurity;
		}

		// la clave y clave de confirmación deben ser iguales
		if (!usuario.getClave().equals(usuario.getClaveConfirmacion())) {

			responseSecurity.setCodigo(ConstantSecurity.COD_USER_CLAVE_DIF);
			responseSecurity.setMessage(ConstantSecurity.MSG_USER_CLAVE_DIF);
			return responseSecurity;
		}

		usuario.setClave(TripleDES.encrypt(usuario.getClave()));
		
		usuarioRepository.create(usuario);
		responseSecurity.setCodigo(ConstantSecurity.COD_OK);
		responseSecurity.setMessage(ConstantSecurity.MSG_OK);

		return responseSecurity;
	}

	public ResponseSecurity update(Usuario usuario) {

		ResponseSecurity responseSecurity = new ResponseSecurity();
		usuario.setClave(TripleDES.encrypt(usuario.getClave()));
		usuarioRepository.update(usuario);

		responseSecurity.setCodigo(ConstantSecurity.COD_OK);
		responseSecurity.setMessage(ConstantSecurity.MSG_OK);
		return responseSecurity;
	}

	
	public Usuario getUsuarioSingle(Usuario usuario) {
		Usuario usuario1 = usuarioRepository.getUsuarioSingle(usuario);
		//logger.info("usuario : "+usuario1.getClave());
		Usuario _usuario = (Usuario) usuario1.clone();
		logger.info("claveee :"+_usuario.getClave());
		_usuario.setClave(TripleDES.decrypt(_usuario.getClave()));
		_usuario.setClaveConfirmacion(_usuario.getClave());
		logger.info("claveee desencriptada :"+_usuario.getClave());
		return _usuario;
	}

	public ResponseSecurity remove(Usuario usuario) {
		
		
		ResponseSecurity responseSecurity = new ResponseSecurity();
		try{
			
			
			responseSecurity.setCodigo(ConstantSecurity.COD_OK);
			responseSecurity.setMessage(ConstantSecurity.MSG_OK);
			
			usuarioRepository.remove(usuario);
			
		}catch( Exception e){
			logger.info(e);
			
			responseSecurity.setCodigo(ConstantSecurity.COD_ERR);
			responseSecurity.setMessage(ConstantSecurity.MSG_ERRO);
		}
		return responseSecurity;

		
	}

}
