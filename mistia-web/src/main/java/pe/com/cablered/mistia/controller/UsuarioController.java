package pe.com.cablered.mistia.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.primefaces.mobile.application.MobileNavigationHandler;

import pe.com.cablered.mistia.commons.util.Util;
import pe.com.cablered.mistia.util.ConstantSecurity;
import pe.com.cablered.seguridad.model.EstadoRegistro;
import pe.com.cablered.seguridad.model.Usuario;
import pe.com.cablered.seguridad.service.EstadoRegistroService;
import pe.com.cablered.seguridad.service.ResponseSecurity;
import pe.com.cablered.seguridad.service.UsuarioService;


@ManagedBean(name = "usuarioMaganeBean")
@SessionScoped
public class UsuarioController implements Mantenible {

	@Inject
	UsuarioService usuarioManager;

	@Inject
	EstadoRegistroService estadoRegistroManager;

	@Inject
	private FacesContext facesContext;

	final static Logger logger = Logger.getLogger(UsuarioController.class);

	private String codUsua;
	private String nombres;
	private EstadoRegistro estadoRegistro;
	private List<Usuario> usuarioList;
	private List<EstadoRegistro> estadoRegistroList;
	private Usuario usuario;
	private String accion;

	@PostConstruct
	public void init() {

		
		
		this.usuarioList = new ArrayList<Usuario>();
		this.estadoRegistroList = new ArrayList<EstadoRegistro>();
		this.usuarioList.addAll(usuarioManager.getUsuarioList());
		this.estadoRegistroList.addAll(estadoRegistroManager.getEstadoRegistroList(ConstantSecurity.COD_GRUPO_ACTIVO));

		if (this.usuario == null) {
			this.usuario = new Usuario();
			this.usuario.setFec_cadu(Util.getSimpleDate());
			this.usuario.setFec_cadu_clave(Util.getSimpleDate());
		}
		
		facesContext.getExternalContext().getSessionMap().put(NUEVO_KEY, NUEVO);
		facesContext.getExternalContext().getSessionMap().put(EDITAR_KEY, EDITAR);
		

	}

	@Override

	public void nuevo() {
	

		try {
			setAccion(NUEVO);
			this.usuario  = new Usuario();
			this.usuario.setFec_cadu(Util.getSimpleDate());
			this.usuario.setFec_cadu_clave(Util.getSimpleDate());
			//usuario.setClave("miclave");
			//	ExternalContext ec = facesContext.getExternalContext();
			//ec.redirect(ec.getRequestContextPath()					+ ConstansView.USUARIO_NEW_VIEW);
			
			
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	@Override
	public void editar() {
		
		logger.info("metodo: editar");
		try {
			String codUsua =facesContext.getExternalContext().getRequestParameterMap().get("codUsua");
			Usuario _usuario = new Usuario(codUsua);

			usuario  = usuarioManager. getUsuarioSingle(_usuario);
			logger.info("_usuario(clave) : "+ usuario.getClave());
			logger.info("_usuario(claveConfirmacion) : "+ usuario.getClaveConfirmacion());
			setAccion(EDITAR);

			
		//ExternalContext ec = facesContext.getExternalContext();
		//ec.redirect(ec.getRequestContextPath()+ ConstansView.USUARIO_NEW_VIEW);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	@Override
	public void grabar() {
		try {
			logger.info("metodo : grabar");
		
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			Usuario user = (Usuario) session.getAttribute(ConstantSecurity.USER_SESSION);
			
			usuario.setCodUsua(usuario.getCodUsua().toUpperCase());
			usuario.setNombres(usuario.getNombres().toUpperCase());
			
			ResponseSecurity responseSecurity = null;
			
			logger.info(user );
			if (getAccion().equals(NUEVO)) {

				responseSecurity = usuarioManager.create(usuario);
			}else if ( getAccion().equals(EDITAR)){
				responseSecurity = usuarioManager.update(usuario);
			}
			
			mostrar();
			
			if (responseSecurity.getCodigo() != ConstantSecurity.COD_OK) {
				FacesMessage msg = new FacesMessage( responseSecurity.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				facesContext.addMessage(null, msg);

			} else {
				this.usuarioList.addAll(usuarioManager.getUsuarioList());
				FacesMessage msg = new FacesMessage(responseSecurity.getMessage());
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
				facesContext.addMessage(null, msg);
				
				ExternalContext ec = facesContext.getExternalContext();
				ec.redirect(ec.getRequestContextPath()+ ConstansView.USUARIOS_VIEW);
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			
			FacesMessage msg = new FacesMessage( ConstantSecurity.MSG_ERRO);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesContext.addMessage(null, msg);
			
		}

	}

	@Override
	public void eliminar(Object object) {
		
		if(object instanceof Usuario){
			
			Usuario _usuario = (Usuario)object;
			ResponseSecurity responseSecurity = usuarioManager.remove(_usuario);
			if(responseSecurity.getCodigo() == ConstantSecurity.COD_OK){
				
				setUsuarioList(usuarioManager.getUsuarioList());
				
				logger.info(" cant de usuarios "+getUsuarioList().size());
				
			}
		}

	}

	public String getCodUsua() {
		return codUsua;
	}

	public void setCodUsua(String codUsua) {
		this.codUsua = codUsua;
	}

	public List<Usuario> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuario> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public List<EstadoRegistro> getEstadoRegistroList() {
		return estadoRegistroList;
	}

	public void setEstadoRegistroList(List<EstadoRegistro> estadoRegistroList) {
		this.estadoRegistroList = estadoRegistroList;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public EstadoRegistro getEstadoRegistro() {
		return estadoRegistro;
	}

	public void setEstadoRegistro(EstadoRegistro estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	@Override
	public void mostrar() {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		usuario.setCodUsua(codUsua.equals("") ? null : codUsua);
		usuario.setNombres(nombres.equals("") ? null : nombres);
		usuario.setEstadoRegistro(estadoRegistro);
		
		this.usuarioList  =  usuarioManager.getUsuariosList(usuario);

		this.setUsuarioList(this.usuarioList);

	}

}
