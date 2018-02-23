package pe.com.cablered.mistia.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.CuadrillasDetalle;
import pe.com.cablered.mistia.model.ObjectBean;
import pe.com.cablered.mistia.model.Tecnico;
import pe.com.cablered.mistia.model.Vehiculo;
import pe.com.cablered.mistia.model.sort.CuadrillaDetalleSortDesc;
import pe.com.cablered.mistia.model.sort.VehiculoComparator;
import pe.com.cablered.mistia.service.AbstractSevice;
import pe.com.cablered.mistia.service.CuadrillaService;
import pe.com.cablered.mistia.service.Response;
import pe.com.cablered.mistia.service.TecnicoService;
import pe.com.cablered.mistia.service.VehiculoService;
@ManagedBean(name = "cuadrillabean")
@SessionScoped
public class CuadrillaController extends BaseController<Cuadrilla>  implements Serializable{

	private static final long serialVersionUID = -32348959599327908L;
	
	
	
	@Inject
	private CuadrillaService service;	
	
	@Inject
	private  VehiculoService vehiculoService;
	
	@Inject
	private  TecnicoService tecnicoService;
	
	@Inject
	private FacesContext facesContext;
	 
	private List<Vehiculo> vehiculoList;
	private List<Tecnico> tecnicoList;

	
	private Vehiculo vehiculo;
	private Cuadrilla cuadrilla;
	
	private String criterio ;
	private Date fecPrgn;
	private Date  fechaInicio;
	private Date  fechaFin;
	
	
	private String nombreTecnico;
	
	final static Logger logger = Logger.getLogger(CuadrillaController.class);
	
	public CuadrillaController() {
		super();
	}
	

	@Override
	protected FacesContext getFacesContext() {
		return facesContext;
	}
	
	@Override
	protected AbstractSevice getService() {
		return service;
	}
		
	@Override
	public void setObject(ObjectBean object) {
		this.cuadrilla = (Cuadrilla) object;
		this.object =  object;
		
	}
	
	
	@PostConstruct
	public void init(){	
	
		setHeaderpopup("Actualizar Cuadrilla");
		// lista de matrices
		vehiculoList =  vehiculoService.getVehiculoList();
		
		tecnicoList =  tecnicoService.getTecnicoList();
		
		if(vehiculoList==null){
			vehiculoList =  new ArrayList<>();
		}
		
		Vehiculo vehiculo0 =  new Vehiculo(SELECCIONE_VALUE+"", SELECCIONE_LABEL);
		vehiculoList.add(vehiculo0);
		
		Collections.sort(vehiculoList, new VehiculoComparator());
		
		list = service.getCuadrillaList();
		vehiculo = null;
		fechaInicio =   Calendar.getInstance().getTime();
		fechaFin =   Calendar.getInstance().getTime();
		
		cuadrilla =  new Cuadrilla(0l);
		fecPrgn =  new Date();
		cuadrilla.setCuadrillasDetalles(new ArrayList());
		setObject(cuadrilla);
		
		
		mostrar();
		
		
		
	}
	
	
	@Override
	public void mostrar() {
		
		try{
			
			criterio = (criterio==null || criterio.trim().equals(""))?null:criterio;
			list = service.getCuadrillaList(fechaInicio,  fechaFin , vehiculo, criterio);
			logger.info(" cantidad items : "+list.size());
		
		}catch (Exception e) {
		
			logger.info(e);
			logger.error(e);
			
		}
	}
	
	
	
	@Override
	public void editar( ObjectBean object){
		super.editar(object);
		
		if(this.cuadrilla!=null && this.cuadrilla.getFechaProgramacion()!=null){
			this.vehiculo  =  this.cuadrilla.getVehiculo();
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(this.cuadrilla.getFechaProgramacion().getTime());
			this.fecPrgn = cal.getTime();
		}
	
	}
	
	
	@Override
	public void grabar(){
		//cuadrilla.setVehiculo(vehiculo);
		cuadrilla.setFechaProgramacion(new Timestamp(fecPrgn.getTime()));
		super.grabar();
		
	}
	
	
	@Override
	public void nuevo(){
		
		cuadrilla =  new Cuadrilla(0L);
		fecPrgn =  new Date();
		cuadrilla.setCuadrillasDetalles(new ArrayList());
		setObject(cuadrilla);
		
		super.nuevo();
		
	}

	
	
	
	
	@Override
	public void reset() {
		logger.info(" metodo :  reset");
		
		
	}
	
	
	public void agregarDetalle(){
		
		logger.info(" metodo: agregarDetalle");
		
		if(nombreTecnico!=null){
			boolean found =  false;
			Tecnico tecnico  = tecnicoService.getTecnico(nombreTecnico);
			List<CuadrillasDetalle> list =     cuadrilla.getCuadrillasDetalles();
			for (CuadrillasDetalle cd : list) {
				if(cd.getTecnico()!=null && cd.getTecnico().equals(tecnico)){
					found =  true;
					break;
				}
			}
			
			if(found){
				mostrarMensaje(new Response(Response.ERROR, "El técnico ya está registrado en la lista"));
			}else{
				List<CuadrillasDetalle> _list = cuadrilla.getCuadrillasDetalles();
				
				CuadrillasDetalle cuadrillasDetalle = null;
				
				if(_list.size()>0){
					
					Collections.sort(_list, new CuadrillaDetalleSortDesc());
					Integer nse = _list.get(0).getId().getNumeroSecuencia();
					cuadrillasDetalle =  new CuadrillasDetalle(cuadrilla.getNumeroCuadrilla(),nse+1);
					
				}else{
					cuadrillasDetalle =  new CuadrillasDetalle(cuadrilla.getNumeroCuadrilla(),1);
				}

				cuadrillasDetalle.setTecnico(tecnico);
				cuadrilla.addCuadrillasDetalle(cuadrillasDetalle);
				nombreTecnico = null;
			}
			
			
		}
	}
	
	public void eliminarDetalle(Integer numeroSecuencia){
		logger.info("metodo : eliminarDetalle ");
		if(cuadrilla!=null ){
			int idx  =  cuadrilla.getCuadrillasDetalles().indexOf(new CuadrillasDetalle(cuadrilla.getNumeroCuadrilla(), numeroSecuencia));
			if(idx !=-1){
				cuadrilla.getCuadrillasDetalles().remove(idx);
			}
			
		}
		
	}

	public  void limpiar(){
		  Date fecha =  Calendar.getInstance().getTime();
		  fechaInicio = fecha;
		  fechaFin =  fecha;
		  list =  Collections.emptyList();
	}
	
	
	
	public List<String> completeText(String query) {
		return buscarTecnico(query);
	}
	
	private List<String> buscarTecnico(String criterio){
		
		
		logger.info("metodo : buscarTecnico "+criterio);
		List<String> filtro =  new ArrayList();
		for (Tecnico tecnico : tecnicoList) {
			String nombre  =  tecnico.getApellidos()+" "+tecnico.getNombres();
			if(nombre.toUpperCase().contains(criterio.trim().toUpperCase())){
				filtro.add(tecnico.getApellidos()+" "+tecnico.getNombres());
			}
		}
		logger.info(" filtro "+filtro.size());
		return filtro;
	}
	
	
	
	
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


	public String getCriterio() {
		return criterio;
	}


	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}


	public CuadrillaController(List<Vehiculo> vehiculoList) {
		super();
		this.vehiculoList = vehiculoList;
	}


	public List<Vehiculo> getVehiculoList() {
		return vehiculoList;
	}


	public void setVehiculoList(List<Vehiculo> vehiculoList) {
		this.vehiculoList = vehiculoList;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}


	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}


	public Cuadrilla getCuadrilla() {
		return cuadrilla;
	}


	public void setCuadrilla(Cuadrilla cuadrilla) {
		this.cuadrilla = cuadrilla;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getNombreTecnico() {
		return nombreTecnico;
	}

	public void setNombreTecnico(String nombreTecnico) {
		this.nombreTecnico = nombreTecnico;
	}

	public Date getFecPrgn() {
		return fecPrgn;
	}

	public void setFecPrgn(Date fecPrgn) {
		this.fecPrgn = fecPrgn;
	}



	
}
