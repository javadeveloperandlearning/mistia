package pe.com.cablered.mistia.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;


@ManagedBean
@RequestScoped
public class Prueba {
	
	private String oculto;
	private  String texto ;
	
	public String buttonId; 
	
	@PostConstruct
	public void init(){
		oculto = " Texto oculto ";
		
	}

	public Prueba() {
	
		/*try{
			throw new Exception("ddddd");
		}catch( Exception e ){
			e.printStackTrace();
			
		}*/
	
		
	}


	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	

	public String getOculto() {
		return oculto;
	}

	public void setOculto(String oculto) {
		this.oculto = oculto;
	}

	public void enviar(){
		
		System.out.println(" texto :"+texto);
	}
	
	public void printIt(ActionEvent event){
		 
		//Get submit button id
		buttonId = event.getComponent().getClientId();
 
	}

}
