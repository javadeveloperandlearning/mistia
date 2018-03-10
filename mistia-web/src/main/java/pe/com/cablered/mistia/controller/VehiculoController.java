package pe.com.cablered.mistia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.log4j.Logger;

import pe.com.cablered.mistia.model.ObjectBean;
import pe.com.cablered.mistia.model.Vehiculo;
import pe.com.cablered.mistia.model.VehiculoMarca;
import pe.com.cablered.mistia.model.VehiculoModelo;
import pe.com.cablered.mistia.model.sort.VehiculoMarcaComparator;
import pe.com.cablered.mistia.model.sort.VehiculoModeloComparator;
import pe.com.cablered.mistia.service.AbstractSevice;

import pe.com.cablered.mistia.service.VehiculoMarcaService;
import pe.com.cablered.mistia.service.VehiculoModeloService;
import pe.com.cablered.mistia.service.VehiculoService;

@ManagedBean(name = "vehiculobean")
@SessionScoped
public class VehiculoController extends BaseController<Vehiculo> implements Serializable{

	private static final long serialVersionUID = -32348959599327908L;

	@Inject
	private  VehiculoService vehiculoService;
	
	@Inject
	private VehiculoMarcaService vehiculoMarcaService;
	
	@Inject
	private VehiculoModeloService vehiculoModeloService;
	
	// detalle 
	private List<Vehiculo> vehiculoList;
	// combos
	private List<VehiculoMarca> vehiculoMarcaList;
	private List<VehiculoModelo> vehiculoModeloList;
	
	private List<VehiculoMarca> vehiculoMarcaList1;
	private List<VehiculoModelo> vehiculoModeloList1;
		
	@Inject
	private FacesContext facesContext;
	private Vehiculo vehiculo; 
	private VehiculoModelo vehiculoModelo;
	private VehiculoMarca vehiculoMarca;
	
	
	private String criterio;
	
	
	
	final static Logger logger = Logger.getLogger(VehiculoController.class);
	
	public VehiculoController() {

	}
	
	@PostConstruct
	public void init(){
	
		// lista de matrices
		vehiculoMarcaList =  vehiculoMarcaService.getVehiculoMarcaList();
		vehiculoModeloList = vehiculoModeloService.getVehiculoModeloList();
		 
		logger.info("cantidad de registros vehiculoMarcaList  "+vehiculoMarcaList.size());
		logger.info("cantidad de registros vehiculoModeloList "+vehiculoModeloList.size());
		
		
		if(vehiculoMarcaList==null){
			vehiculoMarcaList = new ArrayList<>();
		}

		if(vehiculoModeloList==null){
			vehiculoModeloList = new ArrayList<>();
		}
		
		vehiculoMarcaList1 =  new ArrayList<>();
		vehiculoMarcaList1.addAll(vehiculoMarcaList);
		vehiculoModeloList1 =  new ArrayList<>();
		vehiculoModeloList1.addAll(vehiculoModeloList);
	
	
		VehiculoMarca vehiculoMarca  = new VehiculoMarca(SELECCIONE_VALUE, SELECCIONE_LABEL);
		vehiculoMarcaList.add(vehiculoMarca);
		VehiculoModelo vehiculoModelo =  new VehiculoModelo(SELECCIONE_VALUE, SELECCIONE_LABEL);
		vehiculoModeloList.add(vehiculoModelo);
		
		
		VehiculoMarca vehiculoMarca1  = new VehiculoMarca(TODOS_VALUE, TODOS_LABEL);
		vehiculoMarcaList1.add(vehiculoMarca1);
		VehiculoModelo vehiculoModelo1 =  new VehiculoModelo(TODOS_VALUE, TODOS_LABEL);
		vehiculoModeloList1.add(vehiculoModelo1);

		
		Collections.sort(vehiculoMarcaList, new VehiculoMarcaComparator());
		Collections.sort(vehiculoModeloList, new VehiculoModeloComparator());
		
	
		
		Collections.sort(vehiculoMarcaList1, new VehiculoMarcaComparator());
		Collections.sort(vehiculoModeloList1, new VehiculoModeloComparator());
		
		
		vehiculo =  new Vehiculo();
		setHeaderpopup("Actualizar Vehículo");
		
		mostrar();
		
	}
	
	
	@Override
	public void mostrar() {
		logger.info(" metodo :  mostrar");
		logger.info(" criterio :"+criterio);
		criterio = (criterio==null || criterio.trim().equals(""))?null:criterio;
		vehiculoList = vehiculoService.getVehiculoList(vehiculoMarca, vehiculoModelo, criterio);
		setList(vehiculoList);
	}
	
	
	@Override
	protected FacesContext getFacesContext() {
		return facesContext;
	}

	@Override
	protected AbstractSevice getService() {
		return vehiculoService;
	}

	
	public void setObject(ObjectBean object) {
		this.object = object;
		this.vehiculo =  (Vehiculo) object;
	}
	
	@Override
	public void nuevo() {
		super.nuevo();
		this.vehiculo =  new Vehiculo();
		setObject(vehiculo);
		
	}
	

	
	@Override
	public void limpiar() {
		this.criterio =  null;
		this.vehiculoMarca = null;
		this.vehiculoModelo = null;
		mostrar();
	}
	
	@Override
	public void reset() {
		

	}

	public String getCriterio() {
		return criterio;
	}


	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}


	public VehiculoController(List<Vehiculo> vehiculoList) {
		super();
		this.vehiculoList = vehiculoList;
	}


	public List<Vehiculo> getVehiculoList() {
		return vehiculoList;
	}


	public void setVehiculoList(List<Vehiculo> vehiculoList) {
		this.vehiculoList = vehiculoList;
	}


	public List<VehiculoMarca> getVehiculoMarcaList() {
		return vehiculoMarcaList;
	}


	public void setVehiculoMarcaList(List<VehiculoMarca> vehiculoMarcaList) {
		this.vehiculoMarcaList = vehiculoMarcaList;
	}


	public List<VehiculoModelo> getVehiculoModeloList() {
		return vehiculoModeloList;
	}


	public void setVehiculoModeloList(List<VehiculoModelo> vehiculoModeloList) {
		this.vehiculoModeloList = vehiculoModeloList;
	}


	public VehiculoModelo getVehiculoModelo() {
		return vehiculoModelo;
	}


	public void setVehiculoModelo(VehiculoModelo vehiculoModelo) {
		this.vehiculoModelo = vehiculoModelo;
	}


	public VehiculoMarca getVehiculoMarca() {
		return vehiculoMarca;
	}


	public void setVehiculoMarca(VehiculoMarca vehiculoMarca) {
		this.vehiculoMarca = vehiculoMarca;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<VehiculoMarca> getVehiculoMarcaList1() {
		return vehiculoMarcaList1;
	}

	public void setVehiculoMarcaList1(List<VehiculoMarca> vehiculoMarcaList1) {
		this.vehiculoMarcaList1 = vehiculoMarcaList1;
	}

	public List<VehiculoModelo> getVehiculoModeloList1() {
		return vehiculoModeloList1;
	}

	public void setVehiculoModeloList1(List<VehiculoModelo> vehiculoModeloList1) {
		this.vehiculoModeloList1 = vehiculoModeloList1;
	}



	
	



}
