package pe.com.cablered.mistia.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.behavior.FacesBehavior;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.ContratoServicioDao;
import pe.com.cablered.mistia.model.Cliente;
import pe.com.cablered.mistia.model.ContratoServicio;
import pe.com.cablered.mistia.model.ContratoServicioDetalle;
import pe.com.cablered.mistia.model.ContratoServicioDetallePK;
import pe.com.cablered.mistia.model.Distrito;
import pe.com.cablered.mistia.model.Poste;
import pe.com.cablered.mistia.model.Provincia;
import pe.com.cablered.mistia.model.Servicio;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.model.SolicitudServicioHorarioAtencion;
import pe.com.cablered.mistia.model.Tipo;
import pe.com.cablered.mistia.model.TipoSolicitud;
import pe.com.cablered.mistia.service.ClienteService;
import pe.com.cablered.mistia.service.ContratoServicioService;
import pe.com.cablered.mistia.service.DistritoService;
import pe.com.cablered.mistia.service.HorarioAtencionDetalleService;
import pe.com.cablered.mistia.service.ProvinciaService;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.mistia.service.ServicioService;
import pe.com.cablered.mistia.service.SolicitudServicioService;
import pe.com.cablered.mistia.service.TipoService;
import pe.com.cablered.mistia.service.TipoSolicitudService;
import pe.com.cablered.mistia.util.ConstantBusiness;

import static pe.com.cablered.mistia.util.ConstantBusiness.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ManagedBean(name="soliservgene")
@SessionScoped
public class SolicitudServicioGenerarController extends AbastractSolicitudServicioBean  implements Serializable {
	
	
	private List<Map> horarioList;
	private List<Provincia> provinciaList;
	private List<Distrito> distritoList;
	private List<Tipo>     tipoDomicilioList;
	private List<Tipo>     tipoPaqueteList;
	private List<Tipo>     tipoVelocidadInternetList;
	private List<Map> mpcontratoList;
	private List<Map> cantTelevisorList;
	private List<TipoSolicitud> tipoSolicitudList;
	private List<ContratoServicio> contratoServicioList ;
	private List<SolicitudServicioHorarioAtencion>  solicitudHorarioList;
	private List<Servicio> servicioList;
	
	private String  numeroContrato;
	private Integer codigoProvincia;
	private Integer codigoDisrito;
	private Integer codigoTipoDomicilio;
	private Integer codigoTipoPaquete;
	private Integer codigoTipoVeloInternet;
	private Integer codigoTipoSolicitud;
	private Integer codigoCantTele;
	private Integer codigoCliente;
	private String  nombreCliente;
	private  String direccion;

	private String nroDomicilio;
	private String dptoIntDomicilio;
	private String referencia;
	private String urbanizacion;
	
	private Double tarifaMensual;
	private Double tarifaSolicitud;
	
	
	
	
	private  int accion;
	

	
	private boolean disabled;
	private boolean disabled2;
	
	final static Logger logger = Logger.getLogger(SolicitudServicioGenerarController.class);
	

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@Inject
	private SolicitudServicioService solicitudServicioService;
	
	@Inject
	private DistritoService dístritoService;

	@Inject
	private TipoSolicitudService tipoSolicitudService;
	
	@Inject
	private ProvinciaService provinciaService;
	
	@Inject
	private TipoService tipoService;
	
	@Inject
	private ContratoServicioService contratoServicioService;
	
	@Inject
	private ClienteService clienteService;
	
	@Inject
	private HorarioAtencionDetalleService horarioAtencionDetalleService;
	
	@Inject
	private ServicioService servicioService;
	
	@Inject
	private FacesContext facesContext;
	
	
	
	
	@PostConstruct
	public void init(){
		
		//this.horarioList =   solicitudServicioService.getHorarioAtencionList();
		this.horarioList =  horarioAtencionDetalleService.getHorarioAtencionList(1);
		this.solicitudHorarioList =  new ArrayList<>();
		//this.provinciaList = provinciaService.getProvinciaList(COD_DEPA_HUANCAYO);
		this.provinciaList  =  Collections.EMPTY_LIST;
		this.distritoList = Collections.EMPTY_LIST;
		this.tipoDomicilioList =  tipoService.getTipoList(COD_GRUPO_TIPO_DOMICILIO);
		this.tipoPaqueteList =  tipoService.getTipoList(COD_GRUPO_TIPO_PAQ_CABLE);
		this.tipoVelocidadInternetList =  tipoService.getTipoList(COD_GRUPO_TIPO_VELO_INTER);
		this.cantTelevisorList =  solicitudServicioService.getCantidadTelevisoresList();
		this.mpcontratoList =  Collections.EMPTY_LIST;
		this.tipoSolicitudList =  Collections.EMPTY_LIST;
		this.accion = ACCION_NUEVO;
		this.clienteList = clienteService.clienteList();
		
		numeroContrato = null;
		codigoProvincia = null;
		codigoDisrito = null;
		//codigoTipoDomicilio= null;
		codigoTipoPaquete = null;
		codigoTipoVeloInternet=null;
		codigoTipoSolicitud = null;
		codigoCantTele = null;
		codigoCliente = null;
		nombreCliente = null;
		direccion = null;
		disabled =  false;
		disabled2 =  false;
		
	}

	
	
	
	public void actualizarContratoList(){
	
		mpcontratoList =  new ArrayList<>();
		if(codigoTipoSolicitud!=null && codigoTipoSolicitud == TIPO_SOLICITUD_INSTALACION){ 
			
			this.codigoCliente =  getCodigoCliente(nombreCliente);
			Map<String, Object> mp  =  new HashMap();
			mp.put("numeroContrato", "Nuevo");
			mpcontratoList.add(mp);
			setDisabled(true);
			// provincias
			provinciaList =   new ArrayList<>();
			provinciaList.add(new Provincia(CODIGO_SELECCIONE, DES_SELECCIONE ));
			this.provinciaList.addAll(provinciaService.getProvinciaList(COD_DEPA_HUANCAYO));
			// distritos
			
			distritoList =  new ArrayList<>();
			distritoList.add(new Distrito(CODIGO_SELECCIONE, DES_SELECCIONE));
			
			nroDomicilio =  "";
			dptoIntDomicilio =  "";
			referencia =  "";
			urbanizacion = "";
			
			// tipo docimicilio
			
			setDisabled2(false);
			setDisabled(true);
				
			
		}else{

			this.codigoCliente =  getCodigoCliente(nombreCliente);
			this.contratoServicioList =  contratoServicioService.getContratoServicioList(codigoCliente);
			
			if(contratoServicioList!=null && contratoServicioList.size()>1){
				Map<String, Object> mp  =  new HashMap();
				mp.put("numeroContrato", DES_SELECCIONE);
				mpcontratoList.add(mp);
			}else if (contratoServicioList!=null && contratoServicioList.size()==1){
				
				this.numeroContrato = contratoServicioList.get(0).getNumeroContrato()+"";
				cargardetallecontrato();
			}
			
			for (ContratoServicio c : contratoServicioList) {
				Map<String, Object> mp  =  new HashMap();
				mp.put("numeroContrato", c.getNumeroContrato()+"");
				mpcontratoList.add(mp);
			}	
			setDisabled(false);
			setDisabled2(true);

			/*
			try {
				ExternalContext ec = facesContext.getExternalContext();
				ec.redirect(ec.getRequestContextPath()+ ConstansView.SOLICITUD_SERVICIO_GENERAR_VIEW);
				
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			
		}

	}
	
	
	
	public void cargardetallecontrato(){
		Long _numeroContrato  = null;
		try{
			_numeroContrato =  Long.parseLong(this.numeroContrato);
		}catch (Exception e) {
		}
		if(this.numeroContrato ==null){
			return ;
		}
		
		int idx  =  contratoServicioList.indexOf( new ContratoServicio( _numeroContrato));
		
		if(idx!=-1){
			ContratoServicio c = contratoServicioList.get(idx);
			
			this.provinciaList   = new ArrayList<>();
			this.provinciaList.add(c.getDistrito().getProvincia());
			
			this.distritoList = new ArrayList<>();
			this.distritoList.add(c.getDistrito());
			this.direccion =  c.getDireccion();
			this.codigoTipoDomicilio =  c.getCodigoTipoDomicilio();
			this.nroDomicilio =  c.getNroDomicilio();
			this.dptoIntDomicilio =  c.getDptoIntDomicilio();
			this.referencia =  c.getReferencia();
			this.urbanizacion =  c.getUrbanizacion();
			
			
		}
	}
	

	
	
	
	
	public void cargarTipoSolicitServicio(){
			
		if(nombreCliente!=null || nombreCliente!=null && nombreCliente.trim().equals("")){
			this.codigoTipoSolicitud =  CODIGO_SELECCIONE;
			this.tipoSolicitudList = tipoSolicitudService.getTipoSolicitudList();
			
			
		}else{
			this.tipoSolicitudList.clear();	

		}

	}
	
	
	public void cargarDistritos(){
		if(codigoProvincia!=null){
			this.distritoList  =  new ArrayList<>();
			this.distritoList.add(new Distrito(CODIGO_SELECCIONE, DES_SELECCIONE));
			this.distritoList.addAll(dístritoService.getDistritoList(codigoProvincia)); 
		}		
	}
	
	
	
	public void checkHorario(Integer dia , Integer nse){
		
		logger.info(" checkHorario  dia :"+dia+ " rango:"+nse);
		Map mphorario  =  null;
		for (Map mp : horarioList){
			Integer _nse =  (Integer) mp.get("nse");
			if(_nse!=null && _nse.equals(nse)){
				logger.info(mp.toString());
				if(mp.get("checkdias")!=null){
					LinkedHashMap<Integer,Boolean> checkdias = (LinkedHashMap<Integer, Boolean>) mp.get("checkdias");
					Set<Integer> keys  =   checkdias.keySet();
					for(Integer _dia : keys){
						Boolean check =   checkdias.get(_dia);
						if(check!=null && check==true && dia == _dia ){
							logger.info(" chekando true");
							checkdias.put(_dia, false);
							break;
						}else if (check!=null && check==false && dia  == _dia){
							logger.info(" chekando true");
							checkdias.put(_dia, true );
							break;
						}
					}
				}
			}
		}
		
		
		logger.info(" PLOT HORARIO LIST ");
		for (Map mp : horarioList){
			logger.info(mp.toString());
			
		} 
	
		logger.info(" ### RESUMEN ### ");
		
		for (Map mp : horarioList){
			if(mp.get("checkdias")!=null){
				LinkedHashMap<Integer,Boolean> checkdias = (LinkedHashMap<Integer, Boolean>) mp.get("checkdias");
				Set<Integer> keys  =   checkdias.keySet();
				for(Integer _dia : keys){
					Boolean check =   checkdias.get(_dia);
					if(check!=null && check){
						logger.info(" dia: "+_dia +" rango: "+ mp.get("rango")+" , "+check);
					}
				}
			}
		}

	
		if (solicitudHorarioList!=null){
			
		
			
		}
		
	}
	
	
	public void addListaServicios(){
		
		try{
			logger.info(" metodo : addListaServicios ");
			
			Servicio servicio1  =  servicioService.getServicioPorPaquete(codigoTipoPaquete, codigoCantTele);
			Servicio servicio2  =  servicioService.getServicioPorVelocidad(codigoTipoVeloInternet);
		
			servicioList =  new ArrayList<>();
			if(servicio1!=null){
				servicioList.add(servicio1);
			}
			
			if(servicio2!=null){
				servicioList.add(servicio2);
			}
			
			
			tarifaMensual  =   0.0 ;
			if(tipoSolicitudList!=null){
				tarifaSolicitud =  tipoSolicitudList.get(codigoTipoSolicitud).getTarifa();
			}
			if(servicioList!=null){
				for (Servicio s : servicioList) {
					tarifaMensual = tarifaMensual + s.getTarifa();
					logger.info(" s :_"+s.getDescripcion()+ " velocida " + s.getVeloInterMbps()+"  cant tele : "+s.getCantidadTelevisor());
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	
	
	public void registrar(){
		
		try{
		
			if(codigoTipoSolicitud!=null && codigoTipoSolicitud == TIPO_SOLICITUD_INSTALACION){ 
				
				
				
				Cliente cliente =  null;
				
				if(codigoCliente!=null){
					int idx =   clienteList.indexOf(new Cliente(codigoCliente));	
					if(idx!=-1){
						cliente =  clienteList.get(idx);
					}
				}

				if(cliente==null){
					logger.info("se debe registrar un cliente ");
					return ;
				}else{
					logger.info("cliente seleccionado : "+cliente.getApellidos());
					
				}
				
				
				
				// contrato 
				ContratoServicio contratoServicio =  new ContratoServicio();
				contratoServicio.setCliente(new Cliente(codigoCliente));
				Calendar cal =  Calendar.getInstance();
				Date fechaIni =  cal.getTime();
				cal.add(Calendar.MONTH, 12);
				Date fechaFin =  cal.getTime();
				contratoServicio.setFechaInicio(fechaIni);
				contratoServicio.setFechaFin(fechaFin);
				contratoServicio.setTarifa(tarifaMensual);
				contratoServicio.setCliente(cliente);
				contratoServicio.setPoste(new Poste(1));
				contratoServicio.setDistrito( new Distrito(codigoDisrito));
				contratoServicio.setDireccion(direccion);			
				contratoServicio.setCodigoTipoDomicilio(codigoTipoDomicilio);
				contratoServicio.setNroDomicilio(nroDomicilio);
				contratoServicio.setDptoIntDomicilio(dptoIntDomicilio);
				contratoServicio.setReferencia(referencia);
				contratoServicio.setUrbanizacion(urbanizacion);
				// detalle de łos servicios del contrato 
				
				List<ContratoServicioDetalle> contratoServicioDetalleList =  new ArrayList<>();
				for(Servicio servicio  : servicioList){
					ContratoServicioDetalle d =  new ContratoServicioDetalle();
					d.setId(new ContratoServicioDetallePK());
					d.getId().setCodigoServicio(servicio.getCodigoServicio());
					d.setCantidad(1);
					contratoServicioDetalleList.add(d);
					
				}
				
				contratoServicio.setContratoServicioDetalleList(contratoServicioDetalleList);
				
				
					
				// solicitud de servicio
				SolicitudServicio solicitudServicio =  new SolicitudServicio();
				solicitudServicio.setTipoSolicitud(new TipoSolicitud(codigoTipoSolicitud));
				solicitudServicio.setDistrito(new Distrito(codigoDisrito));
				solicitudServicio.setPoste(contratoServicio.getPoste());
				solicitudServicio.setTarifaAtencion(tarifaSolicitud);
				//solicitudServicio.setContratoServicio(contratoServicio);
				solicitudServicio.setDistrito(contratoServicio.getDistrito());
				solicitudServicio.setFechaSolicitud(new Date());
				solicitudServicio.setFechaAtencion(new Date());
			
				List<SolicitudServicioHorarioAtencion> soliHoraList  =  new ArrayList<>();
						
				for (Map mp : horarioList){
					if(mp.get("checkdias")!=null){
						LinkedHashMap<Integer,Boolean> checkdias = (LinkedHashMap<Integer, Boolean>) mp.get("checkdias");
						Set<Integer> keys  =   checkdias.keySet();
						for(Integer _dia : keys){
							Boolean check =   checkdias.get(_dia);
							if(check!=null && check){
								logger.info(" dia: "+_dia +" rango: "+ mp.get("rango")+" , "+check);
								
								String horaInicio =  (String) mp.get("horainicio");
								String horaFin =  (String) mp.get("horafin");
								soliHoraList.add( new SolicitudServicioHorarioAtencion(_dia, horaInicio, horaFin ));
								
							}
						}
					}
				}
			
				
				
				solicitudServicio.setSolicitudServicioHorarioAtencionList(soliHoraList);
			
				// creamos contrato 
				Response res1 =  contratoServicioService .insertar(contratoServicio);
				
				Long nc = (Long) res1.getData();
				solicitudServicio.setContratoServicio(new ContratoServicio(nc));
	
				// creamos solicitud de servicio y su respectiva atencion
				Response res2 =  solicitudServicioService .insertar(solicitudServicio);
				
				if(res1.getCodigo()== Response.OK &&  res2.getCodigo()== Response.OK){
					salir();
				}
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public  void salir(){
		try {
			ExternalContext ec = facesContext.getExternalContext();
			ec.redirect(ec.getRequestContextPath()+ ConstansView.SOLICITUD_SERVICIO_CONSULTA_VIEW);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void limpiar(){
		
		init();
	}
	

	public List<Tipo> getTipoPaqueteList() {
		return tipoPaqueteList;
	}


	public void setTipoPaqueteList(List<Tipo> tipoPaqueteList) {
		this.tipoPaqueteList = tipoPaqueteList;
	}


	public List<Tipo> getTipoVelocidadInternetList() {
		return tipoVelocidadInternetList;
	}


	public void setTipoVelocidadInternetList(List<Tipo> tipoVelocidadInternetList) {
		this.tipoVelocidadInternetList = tipoVelocidadInternetList;
	}


	public List<Map> getHorarioList() {
		return horarioList;
	}


	public void setHorarioList(List<Map> horarioList) {
		this.horarioList = horarioList;
	}


	public List<Provincia> getProvinciaList() {
		return provinciaList;
	}


	public void setProvinciaList(List<Provincia> provinciaList) {
		this.provinciaList = provinciaList;
	}


	public List<Distrito> getDistritoList() {
		return distritoList;
	}


	public void setDistritoList(List<Distrito> distritoList) {
		this.distritoList = distritoList;
	}


	public List<Tipo> getTipoDomicilioList() {
		return tipoDomicilioList;
	}


	public void setTipoDomicilioList(List<Tipo> tipoDomicilioList) {
		this.tipoDomicilioList = tipoDomicilioList;
	}




	public List<Map> getMpcontratoList() {
		return mpcontratoList;
	}

	public void setMpcontratoList(List<Map> mpcontratoList) {
		this.mpcontratoList = mpcontratoList;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}


	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}


	public Integer getCodigoProvincia() {
		return codigoProvincia;
	}


	public void setCodigoProvincia(Integer codigoProvincia) {
		this.codigoProvincia = codigoProvincia;
	}


	public Integer getCodigoDisrito() {
		return codigoDisrito;
	}


	public void setCodigoDisrito(Integer codigoDisrito) {
		this.codigoDisrito = codigoDisrito;
	}


	public Integer getCodigoTipoDomicilio() {
		return codigoTipoDomicilio;
	}


	public void setCodigoTipoDomicilio(Integer codigoTipoDomicilio) {
		this.codigoTipoDomicilio = codigoTipoDomicilio;
	}


	public Integer getCodigoTipoPaquete() {
		return codigoTipoPaquete;
	}


	public void setCodigoTipoPaquete(Integer codigoTipoPaquete) {
		this.codigoTipoPaquete = codigoTipoPaquete;
	}


	public Integer getCodigoTipoVeloInternet() {
		return codigoTipoVeloInternet;
	}


	public void setCodigoTipoVeloInternet(Integer codigoTipoVeloInternet) {
		this.codigoTipoVeloInternet = codigoTipoVeloInternet;
	}


	public List<Map> getCantTelevisorList() {
		return cantTelevisorList;
	}


	public void setCantTelevisorList(List<Map> cantTelevisorList) {
		this.cantTelevisorList = cantTelevisorList;
	}




	public Integer getCodigoTipoSolicitud() {
		return codigoTipoSolicitud;
	}




	public void setCodigoTipoSolicitud(Integer codigoTipoSolicitud) {
		this.codigoTipoSolicitud = codigoTipoSolicitud;
	}




	public List<TipoSolicitud> getTipoSolicitudList() {
		return tipoSolicitudList;
	}




	public void setTipoSolicitudList(List<TipoSolicitud> tipoSolicitudList) {
		this.tipoSolicitudList = tipoSolicitudList;
	}




	public String getNombreCliente() {
		return nombreCliente;
	}




	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public List<ContratoServicio> getContratoServicioList() {
		return contratoServicioList;
	}

	public void setContratoServicioList(List<ContratoServicio> contratoServicioList) {
		this.contratoServicioList = contratoServicioList;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getCodigoCantTele() {
		return codigoCantTele;
	}

	public void setCodigoCantTele(Integer codigoCantTele) {
		this.codigoCantTele = codigoCantTele;
	}

	public String getNroDomicilio() {
		return nroDomicilio;
	}

	public void setNroDomicilio(String nroDomicilio) {
		this.nroDomicilio = nroDomicilio;
	}

	public String getDptoIntDomicilio() {
		return dptoIntDomicilio;
	}

	public void setDptoIntDomicilio(String dptoIntDomicilio) {
		this.dptoIntDomicilio = dptoIntDomicilio;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getUrbanizacion() {
		return urbanizacion;
	}

	public void setUrbanizacion(String urbanizacion) {
		this.urbanizacion = urbanizacion;
	}

	public boolean isDisabled2() {
		return disabled2;
	}

	public void setDisabled2(boolean disabled2) {
		this.disabled2 = disabled2;
	}

	public Double getTarifaMensual() {
		return tarifaMensual;
	}

	public void setTarifaMensual(Double tarifaMensual) {
		this.tarifaMensual = tarifaMensual;
	}

	public Double getTarifaSolicitud() {
		return tarifaSolicitud;
	}

	public void setTarifaSolicitud(Double tarifaSolicitud) {
		this.tarifaSolicitud = tarifaSolicitud;
	}


	public void sendCobertura(){
		logger.info("metodo: generar");
		try {
			

			ExternalContext ec = facesContext.getExternalContext();
			HttpServletRequest request =    (HttpServletRequest) ec.getRequest();
			ec.redirect(ec.getRequestContextPath()+ ConstansView.COBERTURA_TECNICA);
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	
	
	
	

}
