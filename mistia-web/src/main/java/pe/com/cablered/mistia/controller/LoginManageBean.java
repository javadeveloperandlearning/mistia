package pe.com.cablered.mistia.controller;

import java.io.IOException;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import pe.com.cablered.mistia.controller.ConstansView.*;
import pe.com.cablered.mistia.util.ConstantSecurity;
import pe.com.eb.model.Opcion;
import pe.com.eb.model.Perfil;
import pe.com.eb.model.Usuario;
import pe.com.eb.service.AccessManger;
import pe.com.eb.service.PerfilOpcionService;
import pe.com.eb.service.ResponseSecurity;
import pe.com.eb.service.UsuarioService;
import pe.com.eb.service.UsuarioPerfilService;


@ManagedBean(name = "login")
@RequestScoped
public class LoginManageBean {
	
    @Inject
    private FacesContext facesContext;

	@Inject
	UsuarioService usuarioManager;
	
	@Inject
	AccessManger accessManger;

	

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

		user = new Usuario();
		user.setCodUsua(usuario);
		user.setClave(clave);

		ResponseSecurity response = usuarioManager.loguear(user);

		ExternalContext ec = facesContext.getExternalContext();
		try {
			if (response.getCodigo() != ConstantSecurity.COD_OK) {

				ec.invalidateSession();
				ec.redirect(ec.getRequestContextPath()+ "/finalizar.jsp");

			} else {
				
			  HttpSession session =	(HttpSession) ec.getSession(true);
			  session.setAttribute(ConstantSecurity.USER_SESSION, user);
			  ec.redirect(ec.getRequestContextPath()+ ConstansView.PRINCIPAL_VIEW);
			}

		} catch (IOException e) {
			
			e.printStackTrace();
			
			
		}

	}

}
