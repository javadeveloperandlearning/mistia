package pe.com.cablered.mistia.controller;

import java.io.IOException;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.controller.ConstansView.*;
import pe.com.cablered.mistia.util.ConstantSecurity;
import pe.com.cablered.seguridad.model.Opcion;
import pe.com.cablered.seguridad.model.Perfil;
import pe.com.cablered.seguridad.model.Usuario;
import pe.com.cablered.seguridad.service.AccessManger;
import pe.com.cablered.seguridad.service.PerfilOpcionService;
import pe.com.cablered.seguridad.service.ResponseSecurity;
import pe.com.cablered.seguridad.service.UsuarioPerfilService;
import pe.com.cablered.seguridad.service.UsuarioService;

@ManagedBean(name = "login")
@RequestScoped
public class LoginManageBean {

	@Inject
	private FacesContext facesContext;

	@Inject
	UsuarioService usuarioManager;

	@Inject
	AccessManger accessManger;

	final static Logger logger = Logger.getLogger(LoginManageBean.class);

	private Usuario user;

	private String usuario;
	private String clave;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void ingresar() {

		System.out.println("Usuario :" + this.usuario);
		System.out.println("Clave : " + this.clave);

		try {
			user = new Usuario();
			user.setCodUsua(usuario);
			user.setClave(clave);
			
		
			/*if(user.getCodUsua()==null || (user.getCodUsua()!=null && user.getCodUsua().trim().equals(""))){
				
				FacesMessage msg = new FacesMessage("Ingrese Nombre de Usuario");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesContext.addMessage("usuarioError", msg);
				
				
			}else if(user.getClave()==null || (user.getClave()!=null && user.getClave().trim().equals(""))){
				
				FacesMessage msg = new FacesMessage("Ingrese Clave");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesContext.addMessage("claveError", msg);
				
			}else{*/
		
				ResponseSecurity response = usuarioManager.loguear(user);
				ExternalContext ec = facesContext.getExternalContext();
	
				if (response.getCodigo() != ConstantSecurity.COD_OK) {
					// ec.invalidateSession();
					// ec.redirect(ec.getRequestContextPath()+ "/finalizar.jsp");
					logger.info(response.getMessage());
					FacesMessage msg = new FacesMessage(response.getMessage());
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					facesContext.addMessage("messageError", msg);
	
				} else {
	
					HttpSession session = (HttpSession) ec.getSession(true);
					session.setAttribute(ConstantSecurity.USER_SESSION, user);
					ec.redirect(ec.getRequestContextPath() + ConstansView.PRINCIPAL_VIEW);
				}
				
			//}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
