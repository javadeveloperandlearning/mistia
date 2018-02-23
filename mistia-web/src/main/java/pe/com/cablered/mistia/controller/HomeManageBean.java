package pe.com.cablered.mistia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;


import org.primefaces.component.menuitem.UIMenuItem;
import org.primefaces.component.submenu.UISubmenu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuModel;

import pe.com.cablered.mistia.util.ConstantSecurity;
import pe.com.eb.model.Opcion;
import pe.com.eb.model.Usuario;
import pe.com.eb.service.AccessManger;
import static pe.com.cablered.mistia.controller.ConstansView.*;

@ManagedBean(name = "home")
@RequestScoped
public class HomeManageBean {

	private Usuario usuario;

	private DefaultMenuModel menubar = new DefaultMenuModel();

	@Inject
	AccessManger accessManger;

	@Inject
	private FacesContext facesContext;


	@PostConstruct
	public void init() {

		try {
			
			ExternalContext ec = facesContext.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(true);
			Usuario _usuario =  (Usuario) session.getAttribute("usuario");
			
			/*if(_usuario==null){
				// session.invalidate();
				 ec.redirect(ec.getRequestContextPath()+ ConstansView.LOGIN_VIEW);
			}*/

			setUsuario(_usuario);
			//List<Opcion> opciones = accessManger.getOpciones(getUsuario());
			List<Opcion> opciones = new ArrayList<Opcion>();
			Opcion opcion0 = new Opcion("Clientes", CLIENTE_CONSULTA_VIEW,ConstantSecurity.TIPO_MENU_CLIENTE); 	
			Opcion opcion1 = new Opcion("Generar Programación", PROGRAMACION_CONSULTA_VIEW, ConstantSecurity.TIPO_MENU_PROGRAMACION); 
			Opcion opcion2 = new Opcion("Monitorear", MONITOREAR_PLAN_TRABAJO_VIEW, ConstantSecurity.TIPO_MENU_MONITOREO); 
			Opcion opcion3 = new Opcion("Registrar Calidad Atención", CALIDA_ATENCION_CONSULTA_VIEW, ConstantSecurity.TIPO_MENU_ATENCION_CLIENTE);
			Opcion opcion4 = new Opcion("Actualizar Solicitud de Servicio", SOLICITUD_SERVICIO_CONSULTA_VIEW, ConstantSecurity.TIPO_MENU_ATENCION_CLIENTE);
			Opcion opcion5 = new Opcion("Nodos", NODO_VIEW, ConstantSecurity.TIPO_MENU_PROGRAMACION);
			Opcion opcion6 = new Opcion("Postes", POSTES_VIEW, ConstantSecurity.TIPO_MENU_PROGRAMACION);
			Opcion opcion7 = new Opcion("Técnicos", TECNICOS_VIEW, ConstantSecurity.TIPO_MENU_PROGRAMACION);
			Opcion opcion8 = new Opcion("Cuadrillas", CUADRILLAS_VIEW, ConstantSecurity.TIPO_MENU_PROGRAMACION, "cuadrillabean");
			Opcion opcion9 = new Opcion("Vehiculos", VEHICULOS_VIEW, ConstantSecurity.TIPO_MENU_PROGRAMACION);
			
			
			
			Opcion opcion10 = new Opcion("Reset", "reset.jsf", 11); 
			
			Opcion opcion11 = new Opcion("Perfiles", PERFIL_VIEW, ConstantSecurity.TIPO_MENU_SEGURIDAD);
			Opcion opcion12 = new Opcion("Usuarios", USUARIOS_VIEW, ConstantSecurity.TIPO_MENU_SEGURIDAD);
			//Opcion opcion3 = new Opcion("Consultar Programación", "programacion_consulta.xhtml", ConstantSecurity.TIPO_MENU_PROGRAMACION);
			opciones.add(opcion0);
			opciones.add(opcion1);
			opciones.add(opcion2);
			opciones.add(opcion3);
			opciones.add(opcion4);
			opciones.add(opcion5);
			opciones.add(opcion6);
			opciones.add(opcion7);
			opciones.add(opcion8);
			opciones.add(opcion9);
			
			
			opciones.add(opcion10);
			
			opciones.add(opcion11);
			opciones.add(opcion12);

			
			
			// creando submenus
			DefaultSubMenu sm0 = new DefaultSubMenu("Clientes");
			sm0.setIcon("ui-icon-document");
			
			DefaultSubMenu sm1 = new DefaultSubMenu("Facturación");
			sm1.setIcon("ui-icon-document");
			
			DefaultSubMenu sm2 = new DefaultSubMenu("Comercial");
			sm2.setIcon("ui-icon-pencil");
			
			DefaultSubMenu sm3 = new DefaultSubMenu("Programación");
			sm3.setIcon("ui-icon-pencil");
			
			DefaultSubMenu sm4 = new DefaultSubMenu("Monitoreo");
			sm4.setIcon("ui-icon-pencil");
			
			DefaultSubMenu sm5 = new DefaultSubMenu("Ejecución");
			sm5.setIcon("ui-icon-pencil");
			
			DefaultSubMenu sm6 = new DefaultSubMenu("Seguridad");
			sm6.setIcon("ui-icon-pencil");

			DefaultSubMenu sm7 = new DefaultSubMenu("Atención al cliente");
			sm7.setIcon("ui-icon-pencil");
			
			DefaultSubMenu sm8 = new DefaultSubMenu("Ayuda");
			sm7.setIcon("ui-icon-pencil");

			// añadiendo opciones segun tipo de opcion

			for (Opcion opcion : opciones) {

				
				DefaultMenuItem menuItem = new DefaultMenuItem();
			
				if(opcion.getUrlOpci()!=null){
					//stateParam.processOutcomeTargetLinkComponent(facesContext, menuItem, opcion.getUrlOpci());
					menuItem.setValue(opcion.getDesOpci());
					menuItem.setUrl(opcion.getUrlOpci());
					menuItem.setUrl(opcion.getUrlOpci());
					
					
					
				}else{
					menuItem.setValue(opcion.getDesOpci());
					menuItem.setUrl(opcion.getUrlOpci());
				}
				
				if(opcion.getComponente()!=null){
				
					menuItem.setCommand("#{"+opcion.getComponente()+".reset()}");
					
					
				}
				//menuItem.setCommand("#{home.doCommand('m1')}");
				if (opcion.getTipoOpci() == ConstantSecurity.TIPO_MENU_CLIENTE) {
					sm0.addElement(menuItem);
				}else if (opcion.getTipoOpci() == ConstantSecurity.TIPO_MENU_FACTURACION) {
					sm1.addElement(menuItem);
				} else if (opcion.getTipoOpci() == ConstantSecurity.TIPO_MENU_COMERCIAL) {
					sm2.addElement(menuItem);
				} else if (opcion.getTipoOpci() == ConstantSecurity.TIPO_MENU_PROGRAMACION) {
					sm3.addElement(menuItem);
				}else if (opcion.getTipoOpci() == ConstantSecurity.TIPO_MENU_MONITOREO) {
					//sm4.addElement(menuItem);
				} else if (opcion.getTipoOpci() == ConstantSecurity.TIPO_MENU_EJECUCION) {
					sm5.addElement(menuItem);
				} else if (opcion.getTipoOpci() == ConstantSecurity.TIPO_MENU_SEGURIDAD) {
					sm6.addElement(menuItem);
				}else if (opcion.getTipoOpci() == ConstantSecurity.TIPO_MENU_ATENCION_CLIENTE) {
					sm7.addElement(menuItem);
				}else if (opcion.getTipoOpci() ==11) {
					sm8.addElement(menuItem);
				}
			}
			
			

			this.menubar.addElement(sm0);
			this.menubar.addElement(sm1);
			this.menubar.addElement(sm2);
			this.menubar.addElement(sm3);
			//this.menubar.addElement(sm4);
			this.menubar.addElement(sm5);
			this.menubar.addElement(sm6);
			this.menubar.addElement(sm7);
			this.menubar.addElement(sm8);
			this.menubar.generateUniqueIds();
			
			List<MenuElement> list =  this.menubar.getElements();
			for (MenuElement menuElement : list) {
			
			}
			 
			 
		} catch (Exception e) {

		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DefaultMenuModel getMenubar() {
		return menubar;
	}

	public void setMenubar(DefaultMenuModel menubar) {
		this.menubar = menubar;
	}
	
	public void doCommand(String identifier) {
		System.out.println( identifier);
	}

}
