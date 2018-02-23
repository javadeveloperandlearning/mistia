package pe.com.cablered.mistia.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import pe.com.cablered.mistia.commons.util.Util;
import pe.com.cablered.mistia.util.ConstantSecurity;
import pe.com.eb.model.EstadoRegistro;
import pe.com.eb.model.Modulo;
import pe.com.eb.model.Perfil;
import pe.com.eb.model.PerfilPK;
import pe.com.eb.model.Usuario;
import pe.com.eb.service.EstadoRegistroService;
import pe.com.eb.service.ModuloService;
import pe.com.eb.service.PerfilService;
import pe.com.eb.service.ResponseSecurity;

import org.apache.log4j.Logger;
import org.primefaces.component.inputtext.InputText;


@ManagedBean(name="perfil")
@SessionScoped
public class PerfilManageBean implements Serializable, Mantenible {
	
	/**
	 * 
	 */

	private static final long serialVersionUID = 2222726637851816028L;
	
	@Inject
	PerfilService perfilManager;
	
	InputText  p;
	
	@Inject
	ModuloService moduloManager;
	
	@Inject
	EstadoRegistroService estadoRegistroManager;
	
    @Inject
    private FacesContext facesContext;
    
    
	private String action = new String("");
	
	private Perfil perfil; // perfil para actualizar 
	
	private Modulo modulo;// modulo seleccionado
	
	private List<Modulo> moduloList;
	
	private List<Perfil> perfilList;
	
	private List<EstadoRegistro> estadoRegistroList;
	
	final static Logger logger = Logger.getLogger(PerfilManageBean.class);
	
	private String oculto = new String("");

	
	public PerfilManageBean() {

	}
	
	@PostConstruct
	public void init(){
		logger.info("Métod init");
		
		HttpSession session =  (HttpSession) facesContext.getExternalContext().getSession(false);
		Boolean load =  session.getAttribute("load")==null?null:(Boolean) session.getAttribute("load");
		
		if(load == null){
			
			ArrayList<Perfil> p = new ArrayList<Perfil>();
			perfil = new Perfil();
			perfil.setId(new PerfilPK());
			setModuloList(moduloManager.getModulos());
			setPerfilList(perfilManager.getPerfilList(new Modulo(ConstantSecurity.COD_MODU_SEGURIDAD)));
			setEstadoRegistroList(estadoRegistroManager.getEstadoRegistroList(1));
			session.setAttribute("load", true);
			
		}else{

			setPerfilList(perfilManager.getPerfilList(modulo));

		}

	}
	
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	@Override
	public void mostrar() {
		if(this.modulo!=null){
			setPerfilList(perfilManager.getPerfilList(modulo));
		}
	}

	@Override
	public void nuevo() {	
		System.out.println( " #### Nuevo ### ");
		this.perfil =  new Perfil();
		action = NUEVO;
		
	}

	@Override
	public void editar() {
		
		action = EDITAR;
		
		Integer codModu = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get("codModu"));
		Integer codPerf = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get("codPerf"));
		this.perfil =  perfilManager.getPerfil( new Perfil(codModu, codPerf));
	}
	

	@Override
	public void grabar() {
		
		
		try{
				HttpSession session =  (HttpSession) facesContext.getExternalContext().getSession(false);
				Usuario  user = (Usuario) session.getAttribute(ConstantSecurity.USER_SESSION);
				if(user==null){
					user =  Util.getUser();
				}
				
				ResponseSecurity response =null;
				if(this.action.equals(NUEVO)){
					
					perfil.setComCrea(Util.getHost());
					perfil.setComModi(Util.getHost());
					perfil.setUsuCrea(user.getCodUsua());
					perfil.setUsuModi(user.getCodUsua());
					perfil.setFecCrea( Util.getSimpleTime());
					perfil.setFecModi(Util.getSimpleTime());
					response  =  perfilManager.create(perfil);
					
				}else{
					Perfil _perfil = this.perfil;
					this.perfil =  perfilManager.getPerfil(_perfil);
					this.perfil.setDesPerf(_perfil.getDesPerf());
					this.perfil.setEstadoRegistro(_perfil.getEstadoRegistro());
					perfil.setComModi(Util.getHost());
					perfil.setUsuModi(user.getCodUsua());
					perfil.setFecModi( Util.getSimpleTime());
					response  =  perfilManager.update(perfil);
				}
				
		
				if(response.getCodigo() == ConstantSecurity.COD_OK){
					setPerfilList(perfilManager.getPerfilList(perfil.getModulo()));
				}
				System.out.println( response.getCodigo()+"-"+response.getMessage());
		
		}catch(Exception e ){
				logger.info(e);
				e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void eliminar(Object object) {
		System.out.println( "Eliminando registro");
		if(object instanceof  Perfil){
			System.out.println("perfil" +  ((Perfil)object)   .getDesPerf());
			Perfil per  =   (Perfil)object;
			ResponseSecurity response = perfilManager.remove(per);
			// cargando  lista de nuevo
			setPerfilList(perfilManager.getPerfilList(modulo));
		}
		
	}
	
	
	public void limpiar(){
		
		
	}
	
	

	public String getOculto() {
		return oculto;
	}

	public void setOculto(String oculto) {
		this.oculto = oculto;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<Modulo> getModuloList() {
		return moduloList;
	}

	public void setModuloList(List<Modulo> moduloList) {
		this.moduloList = moduloList;
	}

	public List<Perfil> getPerfilList() {
		return perfilList;
	}

	public void setPerfilList(List<Perfil> perfilList) {
		this.perfilList = perfilList;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public List<EstadoRegistro> getEstadoRegistroList() {
		return estadoRegistroList;
	}

	public void setEstadoRegistroList(List<EstadoRegistro> estadoRegistroList) {
		this.estadoRegistroList = estadoRegistroList;
	}



	
	
	
	

}
