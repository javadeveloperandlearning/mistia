package pe.com.cablered.mistia.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ReferencedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.Cliente;
import pe.com.cablered.mistia.model.Encuesta;
import pe.com.cablered.mistia.model.EncuestaPregunta;
import pe.com.cablered.mistia.model.EncuestaRespuesta;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.model.SolicitudServicioEstado;
import pe.com.cablered.mistia.service.EncuestaPreguntaService;
import pe.com.cablered.mistia.service.encuestaSolicitudResultadoService;
import pe.com.cablered.mistia.service.EncuestaRespuestaService;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.mistia.service.SolicitudServicioEstadoService;
import pe.com.cablered.mistia.service.SolicitudServicioService;
import static pe.com.cablered.mistia.util.ConstantBusiness.*;
import static pe.com.cablered.mistia.controller.ConstansView.*;

@ManagedBean(name = "caliatenreg")
@RequestScoped
public class CalidadAtencionRegistrarController {

	
	private Long numeroSolicitud;
	
	private List<String> preguntas;
	private List<String> respuestas;
	private String respuesta1;
	private String respuesta2;
	 
	final static Logger logger = Logger.getLogger(CalidadAtencionRegistrarController.class);
		
	@Inject
	private SolicitudServicioService SolicitudServicioService;
	
	@Inject
	private SolicitudServicioEstadoService solicitudServicioEstadoService;
	
	@Inject
	private EncuestaPreguntaService encuestaPreguntaService;
	
	@Inject
	private encuestaSolicitudResultadoService encuestaPreguntaSolicitudService;
	
	@Inject
	private EncuestaRespuestaService encuestaRespuestaService;

	@Inject
	private FacesContext facesContext;
	
	private Map solicitud;
	
	private List<Map> estados;

	@ManagedProperty("#{caliatencon}")
	private CalidadAtencionConsultaController consulta;;
	
	
	
	@PostConstruct
	public void init(){
		
		try{
		
			ExternalContext ec  =  facesContext.getExternalContext();	
			//Long numerosolicitud = Long.parseLong(ec.getRequestParameterMap().get("numerosolicitud"));
			HttpServletRequest request = (HttpServletRequest)ec.getRequest();
			Long numerosolicitud    =  (Long)request.getAttribute("numerosolicitud");
			setNumeroSolicitud(numerosolicitud);
			
			preguntas = new ArrayList<String>();

					
			// detalle de solicitud
			SolicitudServicio solicitudServicio =  SolicitudServicioService.getSolicitudServicio(numerosolicitud);			
			solicitud = new HashMap<>();
			if(solicitudServicio!=null){
				
				Cliente  c = solicitudServicio.getContratoServicio().getCliente();
				solicitud.put("cliente",c.getNombres()+" - "+c.getApellidos());
				solicitud.put("direccion",solicitudServicio.getContratoServicio().getDireccion());
				solicitud.put("tiposolicitud",solicitudServicio.getTipoSolicitud().getDescripcion());
				solicitud.put("distrito", solicitudServicio.getContratoServicio().getDistrito().getDescripcion());
				//solicitud.put("servicio", solicitudServicio.getContratoServicio().getServicio().getDescripcion());
				solicitud.put("tarifa", solicitudServicio.getContratoServicio().getTarifa());
				
			}
			
			// estados 
			List<SolicitudServicioEstado> solicitudEstadoList =  solicitudServicioEstadoService.getSolicitudServicioEstadoList(numerosolicitud);
			estados = new ArrayList<>();
			respuestas =  new ArrayList<>();
			
			int acumulado = 0;
			SimpleDateFormat sdf =  new SimpleDateFormat(FORMAT_DATE_TIME);
			Date fechante  =  null;
			for (SolicitudServicioEstado e : solicitudEstadoList) {
				
				Map map = new HashMap<String, Object>();
				
				map.put("desestado", e.getEstado().getDescripcion());
				
				map.put("fechahora", e.getFechaHora());
				
				String _acumulado =  "";
				if(fechante == null){
					_acumulado = "";
				}else{
				
					Calendar cal = Calendar.getInstance(); // locale-specific
					cal.setTime(new Date());
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					Long  time = e.getFechaHora().getTime()-fechante.getTime();
					cal.set(Calendar.MILLISECOND, time.intValue());
					
					int hora =  cal.get(Calendar.HOUR);
					int minu =  cal.get(Calendar.MINUTE);
					
					if(hora>0){
						_acumulado = ""+hora +"h ";
					}
					
					if(minu>0){
						_acumulado+= ""+minu+" min";
					}
				}
				
				map.put("acumulado", _acumulado);
				estados.add(map);
				fechante =  e.getFechaHora();
				
			}
			
			// preguntas 
			List<EncuestaPregunta> preguntaList= encuestaPreguntaService.getEncuestaPreguntaList(new Encuesta(CONSTANTE_ENCUESTA_CALIDAD_ATENCION));
			for (EncuestaPregunta p : preguntaList) {
				preguntas.add(p.getDescripcion());
			}
		
			// respuestas 
			List<EncuestaRespuesta> respuestaList =  encuestaRespuestaService.getEncuestaRespuestaList(new Encuesta(CONSTANTE_ENCUESTA_CALIDAD_ATENCION));
			for (EncuestaRespuesta e : respuestaList) {
				respuestas.add(e.getDescripcion());
			}
			
			
		}catch(Exception e ){
			
			e.printStackTrace();
			logger.info(e);
		}
		
      
	}


	public List<String> getPreguntas() {
		return preguntas;
	}


	public void setPreguntas(List<String> preguntas) {
		this.preguntas = preguntas;
	}

	
	public List<String> getRespuestas() {
		return respuestas;
	}


	public void setRespuestas(List<String> respuestas) {
		this.respuestas = respuestas;
	}




	public List<Map> getEstados() {
		return estados;
	}


	public void setEstados(List<Map> estados) {
		this.estados = estados;
	}


	public  String  guardar(){	
		
		logger.info(" numeroSolicitud "+numeroSolicitud);
		logger.info(" respuesta 1 "+respuesta1);
		logger.info(" respuesta 2 "+respuesta2);
		String mg1 = "Debe seleccionar una opción para calificar el trato recibido ";
		String mg2 = "Debe seleccionar una opción para calificar la calidad del servicio instalado ";
		
		if(respuesta1==null || (respuesta1!=null && respuesta1.trim().equals(""))){
			mostrarMensaje(mg1);
			return null;
		}
		
		if(respuesta2==null || (respuesta2!=null && respuesta2.trim().equals(""))){
			mostrarMensaje(mg2);
			return null;
		}	
		
	
		Response response =  encuestaPreguntaSolicitudService.calificar(CONSTANTE_ENCUESTA_CALIDAD_ATENCION, numeroSolicitud,CONSTANTE_ṔREGUNTA_CALIDAD_ATENCION_1, respuesta1 );
				 response =  encuestaPreguntaSolicitudService.calificar(CONSTANTE_ENCUESTA_CALIDAD_ATENCION, numeroSolicitud,CONSTANTE_ṔREGUNTA_CALIDAD_ATENCION_2, respuesta2 );
				 
		if(response!=null && response.getCodigo()== Response.OK && consulta!=null){
			consulta.buscar();
		}
	
		return CALIDA_ATENCION_CONSULTA_VIEW;
	}
	
	
	private void mostrarMensaje(String texto){
		
		FacesMessage msg = new FacesMessage(texto);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		facesContext.addMessage(null, msg);
		
		
	}

	
	public Long getNumeroSolicitud() {
		return numeroSolicitud;
	}


	public void setNumeroSolicitud(Long numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}


	public void salir(){
	
		logger.info("metodo: salir");
		
		try {
			ExternalContext ec = facesContext.getExternalContext();
			ec.redirect(ec.getRequestContextPath()+ ConstansView.PRINCIPAL_VIEW);
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}


	public Map getSolicitud() {
		return solicitud;
	}


	public void setSolicitud(Map solicitud) {
		this.solicitud = solicitud;
	}


	public void setConsulta(CalidadAtencionConsultaController consulta) {
		this.consulta = consulta;
	}


	public String getRespuesta1() {
		return respuesta1;
	}


	public void setRespuesta1(String respuesta1) {
		this.respuesta1 = respuesta1;
	}


	public String getRespuesta2() {
		return respuesta2;
	}


	public void setRespuesta2(String respuesta2) {
		this.respuesta2 = respuesta2;
	}


	
	
	
}
