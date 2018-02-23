package pe.com.cablered.mistia.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.CuadrillaDao;
import pe.com.cablered.mistia.dao.CuadrillasDetalleDao;
import pe.com.cablered.mistia.dao.GrupoAtencionDao;
import pe.com.cablered.mistia.dao.GrupoAtencionDetalleDao;
import pe.com.cablered.mistia.dao.PlanTrabajoDao;
import pe.com.cablered.mistia.dao.PlanTrabajoDetalleDao;
import pe.com.cablered.mistia.dao.ProgramacionDao;
import pe.com.cablered.mistia.dao.ProgramacionDetalleDao;
import pe.com.cablered.mistia.dao.RedNeuronalDetalleDao;
import pe.com.cablered.mistia.dao.SolicitudServicioDao;
import pe.com.cablered.mistia.dao.SolicitudServicioEstadoDao;
import pe.com.cablered.mistia.dao.RedNeuronalDao;
import pe.com.cablered.mistia.geometria.Geometria;
import pe.com.cablered.mistia.ia.rna.NeuralNetwork;
import pe.com.cablered.mistia.model.Cuadrilla;
import pe.com.cablered.mistia.model.CuadrillasDetalle;
import pe.com.cablered.mistia.model.Estado;
import pe.com.cablered.mistia.model.GrupoAtencion;
import pe.com.cablered.mistia.model.GrupoAtencionDetalle;
import pe.com.cablered.mistia.model.GrupoAtencionDetallePK;
import pe.com.cablered.mistia.model.PlanTrabajo;
import pe.com.cablered.mistia.model.PlanTrabajoDetalle;
import pe.com.cablered.mistia.model.PlanTrabajoDetallePK;
import pe.com.cablered.mistia.model.Programacion;
import pe.com.cablered.mistia.model.ProgramacionDetalle;
import pe.com.cablered.mistia.model.RedNeuronal;
import pe.com.cablered.mistia.model.RedNeuronalDetalle;
import pe.com.cablered.mistia.model.RedNeuronalDetallePK;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.model.TecnicoCompetenciaDetalle;
import pe.com.cablered.mistia.model.sort.GrupoAtencionDetalleSortNumeroGrupo;
import pe.com.cablered.mistia.model.sort.GrupoAtencionSortNumeroAtencion;
import pe.com.cablered.mistia.util.ConstantBusiness;
import pe.com.cablered.mistia.util.Util;
import static pe.com.cablered.mistia.util.ConstantBusiness.*;


@Stateful
@LocalBean
public class ProgramacionService  implements Serializable {
	
	
	@Inject
	private EntityManager em;
	
    //@Resource
   // private UserTransaction userTransaction;
	
	private ArrayList<Integer> gruposidx ;

	@Inject
	private ProgramacionDao programacionDao;
	@Inject
	private  ProgramacionDetalleDao programacionDetalleDao;
	@Inject
	private SolicitudServicioDao solicitudServicioDao;
	
	@Inject
	private GrupoAtencionDao grupoAtencionDao;
	@Inject
	private GrupoAtencionDetalleDao grupoAtencionDetalleDao;
	
	@Inject
	private PlanTrabajoDao planTrabajoDao;
	
	@Inject
	private PlanTrabajoDetalleDao planTrabajoDetalleDao;
	
	@Inject
	private CuadrillaDao cuadrillaDao;
	
	@Inject
	private	RedNeuronalDao redNeuronalDao;
	
	@Inject
	private RedNeuronalDetalleDao redNeuronalDetalleDao ;
	
	@Inject
	private SolicitudServicioEstadoDao solicitudServicioEstadoDao;
	
	@Inject
	private CuadrillasDetalleDao cuadrillasDetalleDao;
	
	@Inject
	private GrupoService grupoService;
	
	private List< Map<String,Object>> combinaciones;
	
	
	final static Logger logger = Logger.getLogger(ProgramacionService.class);
	
	
	

	
	
	
	

	// lista de grupos en session
	private  Map<Long, GrupoAtencion> mpGruposCached  = new LinkedHashMap<>();
	private  List<SolicitudServicio>  solicitudesCached =  new ArrayList<>();
	private  List<PlanTrabajo> planTrabajoCachedList = new ArrayList<>();
	
	private Map<Long, Long>  reasignaciones = new LinkedHashMap<>();
	private Map<Long, Long>  asignaciones = new LinkedHashMap<>();
	

	
	
	public List<Programacion> getProgramacionList(Date fechaInicio, Date fechaFin ) {
		return programacionDao.getProgramacionList(fechaInicio, fechaFin);
	}

	
	public ProgramacionService() {
		
		logger.info("##### creando objeto ProgramacionService #####");
	
	}

	public List<PlanTrabajoDetalle> getPlanTrabajoDetallePorGrupo(Integer accion, Long numeroProgramacion,  Long numeroccuadrilla){
		
		
		if(accion!=null &&  accion  ==  ConstantBusiness.ACCION_NUEVA_PROGRAMACION){
			for(PlanTrabajo pt:  planTrabajoCachedList){
				if(pt.getCuadrilla().getNumeroCuadrilla()  == numeroccuadrilla ){
					return pt.getPlanTrabajoDetalles();
				}
			}
		}else if(accion!=null &&  accion  ==  ConstantBusiness.ACCION_EDITA_PROGRAMACION){
			
			List<PlanTrabajoDetalle> planTrabajoDetalleList  =  planTrabajoDetalleDao.getPlanTrabajoDetalleList(numeroProgramacion,numeroccuadrilla);
			
			return planTrabajoDetalleList;
			
		}
		
		
		return Collections.EMPTY_LIST;
		
	}
	
	
	
	/**
	 * ############### Solicitudes pendientes ###################
	 * obtiene las lista de solicitudes pendientes, grupos a los cuales estan asigandos y sus respectivas prioridades
	 * 
	 * */
	public List<Map<String, Object>> getSolicitudList( Long numeroProgramacion,   int accion) {
		
		logger.info(" método : getSolicitudList");

		List<Map<String, Object>> solicitudes = new ArrayList<Map<String, Object>>();
		
		
		List<SolicitudServicio> solicitudesList = null;
		
		if(accion  ==  ConstantBusiness.ACCION_NUEVA_PROGRAMACION){
			
			logger.info(" mostrando solicitudes en memoria");
				
				solicitudesList = solicitudServicioDao.getSolicitudListPorEstado(ConstantBusiness.ESTADO_SOLICITUD_PENDIENTE);
				
				for (SolicitudServicio s : solicitudesList) {
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("numeroSolicitud", s.getNumeroSolicitud());
					
					
					/*Calendar cal =  Calendar.getInstance();
					cal.setTime(s.getFechaSolicitud());
					int year     =  cal.get(Calendar.YEAR);
					String nsoli =  "00000"+s.getNumeroSolicitud() ;
					nsoli =  nsoli.substring(nsoli.length()-5,nsoli.length());
					String tag = s.getTipoSolicitud().getAbreviatura()+""+year+""+nsoli;*/
					
					map.put("tag", Util.getTag(s));
					Date fecPrgn =  Calendar.getInstance().getTime();
					GrupoAtencion g =  getGrupoAtencion(fecPrgn, s);
					map.put("descripcionGrupo", g==null?"":g.getDescripcion());
					map.put("prioridad", getPriortidad(s));
					//map.put("latitud", s.getPoste().getLatitud().doubleValue());
					//map.put("longitud", s.getPoste().getLongitud().doubleValue());
					map.put("latitud", s.getContratoServicio().getLatitud());
					map.put("longitud", s.getContratoServicio().getLongitud());
				
					map.put("tipo", s.getTipoSolicitud().getDescripcion());
					
					solicitudes.add(map);
				}
			

				
		
		}else if(accion ==  ConstantBusiness.ACCION_EDITA_PROGRAMACION){
			

			logger.info(" mostrando solicitudes registradas ");
			
			List<GrupoAtencionDetalle> GrupoAtencionDetalleList =   grupoAtencionDetalleDao.getGrupoAtencionDetalleListPorProgramacion(numeroProgramacion);
			solicitudesList =  new ArrayList<>();
			for (GrupoAtencionDetalle g : GrupoAtencionDetalleList) {
				
				Map<String, Object> map = new HashMap<String, Object>();
				SolicitudServicio s =  g.getSolicitudServicio();
				map.put("numeroSolicitud", s.getNumeroSolicitud());
		
				/*Calendar cal =  Calendar.getInstance();
				cal.setTime(s.getFechaSolicitud());
				int year     =  cal.get(Calendar.YEAR);
				String nsoli =  "00000"+s.getNumeroSolicitud() ;
				nsoli =  nsoli.substring(nsoli.length()-5,nsoli.length());
				String tag = s.getTipoSolicitud().getAbreviatura()+""+year+""+nsoli;*/
				
				map.put("tag", Util.getTag(s));
				map.put("descripcionGrupo", g==null?"":g.getGrupoAtencion().getDescripcion());
				map.put("prioridad", getPriortidad(s));
				//map.put("latitud", s.getPoste().getLatitud().doubleValue());
				//map.put("longitud", s.getPoste().getLongitud().doubleValue());
				map.put("latitud", s.getContratoServicio().getLatitud());
				map.put("longitud", s.getContratoServicio().getLongitud());
				
				map.put("tipo", s.getTipoSolicitud().getDescripcion());
				solicitudes.add(map);
				solicitudesList.add(s);
				//logger.info( s.getNumeroSolicitud() + " - "+g.getGrupoAtencion().getDescripcion());
			}
		}
		// añadiendo a temporal
		solicitudesCached =  new ArrayList<>();
		solicitudesCached.addAll(solicitudesList);
		
		return solicitudes;
	}
	
	
	
	
	
	
	
	public List<SolicitudServicio> getSolicitudListPorEstado(   int codigoEstado) {
		return solicitudServicioDao.getSolicitudListPorEstado(codigoEstado);
	}
	
	
	private String  getPriortidad( SolicitudServicio s ){
		
		String desprioridad  = "";
		
		Integer prioridad=  s.getTipoSolicitud().getPrioridad();
		if(prioridad ==null){
			return desprioridad;
		}
		
		switch (prioridad) {
		
			case PRIORIDAD_SOLICITUD_ALTA:
				desprioridad =  "ALTA";
				break;
				
			case PRIORIDAD_SOLICITUD_MEDIA:
				desprioridad =  "MEDIA";
				break;
			case PRIORIDAD_SOLICITUD_BAJA:
				desprioridad =  "BAJA";
				break;

		}
		
		return desprioridad;

	
	}
	
	/***
	 * ############### Asignar cuadrillas ###################
	 * getGrupoAtencion obtiene el grupo solicitud asociado a un grupo de atencion ya se de cache o de bd
	 * 
	 * */
	
	private GrupoAtencion getGrupoAtencion(Date fecPrgn, SolicitudServicio solicitudServicio){
		
		GrupoAtencion g =  null;		
		List<GrupoAtencionDetalle>  detalles =  Collections.EMPTY_LIST;
		
		if(mpGruposCached!=null && mpGruposCached.size()> 0){

			detalles  =  new ArrayList<GrupoAtencionDetalle>();
			Iterator<Long> it =  mpGruposCached.keySet().iterator();
			while(it.hasNext()){
			Long numerogrupo = 	it.next();
				
			List<GrupoAtencionDetalle>  _detalles =    mpGruposCached.get(numerogrupo).getGrupoAtencionDetalles();
			detalles.addAll(_detalles);
				
			}
			
		}else{			
			
			detalles =  grupoAtencionDao.getDetallesGrupoAtencion(fecPrgn);
			
			
		}

		
		if(detalles!=null){
			for (GrupoAtencionDetalle d : detalles) {
				if(  d.getSolicitudServicio().equals(solicitudServicio)){
					g =  d.getGrupoAtencion();
					break;
				};		
			}
		}
		
		return g;
	}	

	
	
	/**
	 * asignarCuadrillasGruṕos utiliza la red neuronal para 
	 *  - asignar las cuadrillas a los grupos generados
	 *  - generar los planes de trabajo
	 * */
	public   List<PlanTrabajo> asignarCuadrillasGrupos(Date fecPrgn) {
		
		logger.info(" ### EL OBJETO :"+this);
		
		 System.out.println("##### asignarCuadrillasGrupos ####  ");
		
		 List<Cuadrilla> cuadrillas  = cuadrillaDao.getCuadrillaList(fecPrgn);
		 //obtenemos combinaciones 
		 Collection<GrupoAtencion>  grupos = mpGruposCached.values();
		 List<GrupoAtencion> grupoList =  new ArrayList<>();
		 grupoList.addAll(grupos);
		  
		 Map<Integer, Map<String,Object>> combinaciones  =   getCombinaciones(grupoList , cuadrillas);
		  // asignaciones realizadas por la red neuronal
		 asignaciones   =   getAsignacionMachineLearning(combinaciones);
		  
		 List<PlanTrabajo> planTrabajoList  = generarPlanesTrabajo(fecPrgn, cuadrillas, mpGruposCached, asignaciones);
		 planTrabajoCachedList = new ArrayList<PlanTrabajo>();
		 planTrabajoCachedList.addAll(planTrabajoList);
		 reasignaciones.clear();
		 
		 return planTrabajoList;
	}
	

	
	
	
	
	
	/**
	 * genera los pplanes de trabajo a partir de cuadrillas y  grupos
	 * asignaciones tiene la relaciones entre las cuadrillas y los grupos
	 * 
	 * @param cuadrillas
	 * @param mpGrupos
	 * @param asignaciones
	 * @return
	 */
	private List<PlanTrabajo> generarPlanesTrabajo( Date fecPrgn, 
			List<Cuadrilla> cuadrillas,Map<Long, GrupoAtencion> mpGrupos, 
			Map<Long, Long>  asignaciones ){
		
			List<PlanTrabajo> planTrabajoList  = new ArrayList<PlanTrabajo>();
			Set<Long> grupos  =  asignaciones.keySet();	
			long  np  =  1;
			for (Long ngrupo : grupos) {
				  Long ncuadrilla =  asignaciones.get(ngrupo);
				  GrupoAtencion grupoAtencion = mpGrupos.get(ngrupo);
				  //GrupoAtencion grupoAtencion =  asignar(cuadrilla, idx, mpGruposCached);
				  PlanTrabajo planTrabajo =  new PlanTrabajo(np);
				  int nsp =  1;
				  planTrabajo.setFechaProgramacion(new Timestamp(fecPrgn.getTime()));
				  int i  =    cuadrillas.indexOf( new Cuadrilla(ncuadrilla));
				  if(i!=-1){
					  
					  Cuadrilla cuadrilla = cuadrillas.get(i);
					  planTrabajo.setCuadrilla(cuadrilla);
					  planTrabajo.setGrupoAtencion(grupoAtencion);
					  
					  if(grupoAtencion!=null){
						  for( GrupoAtencionDetalle  d : grupoAtencion.getGrupoAtencionDetalles()){
							  // añadiendo las solicitudes de servicio	
							  SolicitudServicio s =  d.getSolicitudServicio();
							  //System.out.println(" #### añadiendo solicitud "+s.getNumeroSolicitud());
							  if(planTrabajo.getPlanTrabajoDetalles()==null){
								  planTrabajo.setPlanTrabajoDetalles(new ArrayList<PlanTrabajoDetalle>());
							  }
							  
							  PlanTrabajoDetalle pd =  new PlanTrabajoDetalle(np, nsp);
							  pd.setSolicitudServicio(s);
							  //planTrabajo.addPlanTrabajoDetalle( new PlanTrabajoDetalle(s));;
							  planTrabajo.addPlanTrabajoDetalle(pd);
							  nsp++;
						  }
						  planTrabajoList.add(planTrabajo);
					  }
				  }
				  np++;
			}
			
		return planTrabajoList;
	}; 
	
	
	private  void sincronizarPlanTrabajo(List<PlanTrabajo> planTrabajoList, Map<Long, GrupoAtencion> mpGrupos){
		

		Set<Long> keys =   mpGrupos.keySet();
	
		for (Long key : keys) {
			
			GrupoAtencion g =   mpGrupos.get(key);
			
			for(PlanTrabajo p : planTrabajoList){
		
				if(g!=null && p.getGrupoAtencion()!=null &&  
						g.equals(p.getGrupoAtencion())){
					
					int cantg =  g.getGrupoAtencionDetalles().size(); 
					int cantp =  p.getGrupoAtencion().getGrupoAtencionDetalles().size();
					
					if(cantg!=cantp){
						
					}
					
				}
			}
		}
	}
	
	
	
	
	
	private Map<Long, Long>  getAsignacionMachineLearning(Map<Integer, Map<String,Object>> combinaciones){
		
		
	    RedNeuronal red =   	redNeuronalDao.getRedNeuronal(1);
	    
	    List<RedNeuronalDetalle> redNeuronalDetalles = red.getRedNeuronalDetalles();
	    HashMap<String, Double> weightUpdate = new HashMap<String, Double>();// pesos para red neuronal
	    System.out.println(" red neuronal detalle bd ");
	    for (RedNeuronalDetalle d : redNeuronalDetalles) {
	    	weightUpdate.put("N" + d.getNumeroNeurona().intValue() + "_C" + d.getNumeroConexion().intValue(), d.getValorPeso().doubleValue())	;
	    	//System.out.println(" peso : "+d.getValorPeso());
		}
	
		NeuralNetwork neuralNetwork = new NeuralNetwork(16, 4, 1,true, weightUpdate); // true la red esta entrenada 
		
		
		Iterator<Integer> it = combinaciones.keySet().iterator();
		while(it.hasNext()){
			
			Integer i = it.next();
			Map<String,Object>  mp =  combinaciones.get(i);			
			Map<String,Object>  mpintpus =  new LinkedHashMap<String, Object>();
			mpintpus.putAll(mp);
			mpintpus.remove("nc");
			mpintpus.remove("ng");
			//System.out.println(mpintpus.toString());

			double[][]_inputs =  toFormatNeuralNetwork(mpintpus);
			
			// aplicando la red neuronal
			
			System.out.println(" aplicando la red neuronal ");
			double[][] _outputs  = neuralNetwork.getOutPuts(_inputs);
	        System.out.println(" restult output : "+_outputs[0][0] );
	        mp.put("gradasig", _outputs[0][0]);

		}
		
		//ordenando de forma descendente
		Collection< Map<String,Object>> combinacionesColl  = 	combinaciones.values();
		List< Map<String,Object>> combinacionesList  = new ArrayList<>();
		combinacionesList.addAll(combinacionesColl);
		Collections.sort(combinacionesList ,  new AsignacionNeuralNetworkSort());
		
		System.out.println("##### Mostrando combinaciones ###### ");
		
		// proceso de seleccion de cuadrillas con grupos de atencion
		Map<Long, Long>  asiganciones = new LinkedHashMap<>();
		//Map<String, Double>  pesosasig = new LinkedHashMap<>();
		
		for (Map<String, Object> map : combinacionesList) {
			System.out.println(map.toString());
			Long ng =  	(Long) map.get("ng"); // numero de grupo
			Long nc =  	(Long) map.get("nc"); // numero de cuadrilla
			
			Long _nc  =   asiganciones.get(ng);
			if(_nc==null){
				Iterator<Long>  _it =    asiganciones.keySet().iterator();
				
				boolean found =  false;
				while(_it.hasNext()){
					Long g = _it.next();
					Long c = asiganciones.get(g);
					// evaluamos si existe una cuadrilla asignada
					if(c!=null && c.equals(nc)){
						found =  true;
						break;
					}
				}
				
				if(!found){
					// <NúmeroGrupo, NumeroCuadrilla>
					asiganciones.put(ng, nc);
					//pesosasig.put(ng+"-"+nc, (Double)map.get("gradasig"));
				}
			}
		}
		
		// proceso  de seleccion
		System.out.println("mostrando asignaciones");
		System.out.println(asiganciones.toString());
		//System.out.println(pesosasig.toString());
		
		//Set<Long> keys  =   asiganciones.keySet();
		/*List<Long> cuadrillaList = new ArrayList<>();
		for (Long g : keys) {
	
		}*/

		return asiganciones;
		
	}
	
	
	
	/***
	 * getCombinaciones obtiene los valores combinados de grupos de cuadrillas 
	 * para se evaludados en la red neuronal
	 * 
	 * */
	
	private Map<Integer, Map<String,Object>>   getCombinaciones( List<GrupoAtencion> grupos,  List<Cuadrilla> cuadrillas){
		

		Map<Long, Map<String, Double>> mpgrupos =  new LinkedHashMap<Long, Map<String, Double>>();
		// aplicando todos contra todos
		
		for (GrupoAtencion g : grupos) {
			
			Map<String, Double>mpcants =  getCantidadPorTipoSolicitudes(g);
			// añdiendo 
			mpgrupos.put(g.getNumeroGrupoAtencion(),   mpcants); 
		}
		
		
		
		Map<Long, Map<String, Double>> mpcuadrillas =  new LinkedHashMap<Long, Map<String, Double>>();
		
		for(Cuadrilla c : cuadrillas){
			Map<String, Double> mpproms  = getPromedioHabilidades(c);
			mpcuadrillas.put(c.getNumeroCuadrilla(),mpproms );
			
		}
		
		
		// todos contra todos;
		
		Iterator<Long> it =  mpgrupos.keySet().iterator();
		
		// combinaciones
		Map<Integer, Map<String,Object>>  mpcomb = new LinkedHashMap<Integer, Map<String,Object>>();
		int idxcomb = 1; // indice de combinacion
		while(it.hasNext()){
			
			Long ng  = it.next();// número de grupos
			Map<String, Double> mpcants =  mpgrupos.get(ng); // cantidades
			
			Iterator<Long> itp =  mpcuadrillas.keySet().iterator();
			while(itp.hasNext()){
				Long nc  = itp.next();// número de cuadrilla
				Map<String, Double> mpproms =  mpcuadrillas.get(nc); // promedios
				Map<String,Object> mp =  new LinkedHashMap<>();
				//System.out.println(mpproms.toString());
				
				mp.put("ng",ng);// numero grupo
				mp.put("nc",nc); // numero cuadrilla
				// añadiendo cantidades de tipos de solicitudes
				mp  =  addMap(mp, mpcants);
				// añadiendo promedio de habilidades
				mp  =  addMap(mp, mpproms);
				
				mpcomb.put(idxcomb, mp);
				//System.out.println(mp.toString());
				idxcomb++;
	
			}
			
		}

		return mpcomb ;
	}
	
	
	/**
	 * añade los valores de un mapa a otro
	 * 
	 * */
	Map<String,Object>  addMap(Map<String,Object> mptarget, Map<String, Double> mporigen){
		System.out.println(" #### addMap #####  ");
		  Set<String> keys   = mporigen.keySet();		
		  for (String key : keys) {
			  mptarget.put(key, mporigen.get(key));
		  }
		return mptarget;
	}
	
	public static  boolean  getUrgencia(SolicitudServicio s ){
		
			long  tactual =  Calendar.getInstance().getTime().getTime();
			long  facten =  s.getFechaAtencion().getTime();
			long  tiempo =  tactual - facten;
			tiempo = (tiempo/1000*60*60);
			if(tiempo > 48){
				return true;
			}

		return false;
		
	}
	
	
	
	/**
	 * getPromedioHabilidades
	 * obtiene el promedio de habilidades de los integrantes de cada cuadrilla 
	 * por cuadrilla 
	 * */
	private Map<String,Double>  getPromedioHabilidades(Cuadrilla cuadrilla){
		System.out.println("#### getPromedioHabilidades ######");
		double proprod= 0, protrae= 0, prodorm = 0, prodcaa = 0;
		int cantper = 0;
		for (CuadrillasDetalle cd : cuadrilla.getCuadrillasDetalles()) {
			
			List<TecnicoCompetenciaDetalle>  tcds = 	cd.getTecnico().getTecnicoCompetenciaDetalles();
			for (TecnicoCompetenciaDetalle tcd : tcds) {
				Integer  codigoComp =     tcd.getCompetencia().getCodigoCompetencia();
				switch(codigoComp){
					case  COMPETENCIA_PRODUCTIVIDAD:
						proprod = proprod+ tcd.getGradoCompetencia().doubleValue();
						break;
					case  COMPETENCIA_TRABAJO_EQUIPO:
						protrae = protrae + tcd.getGradoCompetencia().doubleValue();
						break;
					case  COMPETENCIA_ORIENTACION_METAS:
						prodorm = prodorm + tcd.getGradoCompetencia().doubleValue();
						break;
					case  COMPETENCIA_CALIDAD_ATENCION:
						prodcaa =  prodcaa +tcd.getGradoCompetencia().doubleValue();
						break;
				}
			}
			cantper++;			
		}
					
		// promedio de las competencias de los tenicos de cada cuadrilla
		proprod =  proprod/cantper; 
		protrae =  protrae/cantper;
		prodorm =  prodorm/cantper;
		prodcaa =  prodcaa/cantper;

		Map<String, Double> mpproms =  new LinkedHashMap<String,Double>();
		mpproms.put("proprod", proprod);
		mpproms.put("protrae", protrae);
		mpproms.put("prodorm", prodorm);
		mpproms.put("prodcaa", prodcaa);
	
		return mpproms;
		
	}
	
	private Map<String,Double>  getCantidadPorTipoSolicitudes(GrupoAtencion g){
		
		System.out.println("#### getCantidadPorTipoSolicitudes ######");
		
		double  canttras = 0, cantrecn = 0, cantmodi = 0, cantbaja=0,cantsusp=0, cantinst =0, cantcort=0, 	cantaver=0;
		double cantpalta = 0, cantpmedi=0, cantpbaja = 0;
		double canturge = 0;
		List<GrupoAtencionDetalle> detalles =  g.getGrupoAtencionDetalles();
		
		
		for (GrupoAtencionDetalle gd : detalles) {
			// evaluando cantidad de solicitudes de servicio
			Integer codigo =   gd.getSolicitudServicio().getTipoSolicitud().getCodigoTipoSolicitud();
			Integer prioridad =   gd.getSolicitudServicio().getTipoSolicitud().getCodigoTipoSolicitud();
			
			// cantidades de tiṕos de solicitudes 
			switch(codigo){
			
				case  TIPO_SOLICITUD_TRASLADO:
					canttras++;
				break;
				case  TIPO_SOLICITUD_RECONEXION:
					cantrecn++;
				break;
				case  TIPO_SOLICITUD_MODIFACION_SERVICIO:
					cantmodi++;
				break;
				case  TIPO_SOLICITUD_SUSPENSION:
					cantsusp++;
				break;
				
				case  TIPO_SOLICITUD_BAJA:
					cantbaja++;
				break;
				
				case  TIPO_SOLICITUD_INSTALACION:
					cantinst++;
				
				case  TIPO_SOLICITUD_CORTE:
					cantcort++;
					
				case  TIPO_SOLICITUD_AVERIA:
					cantaver++;
				break;
				
			}
			


			
			// cantida de solicitues de servicio
			switch(codigo){
			
				case  PRIOIDAD_SOLICITUD_SERVICIO_ALTA:
					cantpalta++;
				break;
				case  PRIOIDAD_SOLICITUD_SERVICIO_MEDIA:
					cantpmedi++;
				break;
				case  PRIOIDAD_SOLICITUD_SERVICIO_BAJA:
					cantpbaja++;

			}
			// indicadores de urgenca
			if(getUrgencia(  gd.getSolicitudServicio())){
				canturge++;
			}
		}

		Map<String, Double> mpcants =  new LinkedHashMap<String,Double>();
		
		mpcants.put("canttras", canttras);
		mpcants.put("cantrecn", cantrecn);
		mpcants.put("cantmodi", cantmodi);
		mpcants.put("cantbaja", cantbaja);
		mpcants.put("cantsusp", cantsusp);
		mpcants.put("cantinst", cantinst);
		mpcants.put("cantcort", cantcort);
		mpcants.put("cantaver", cantaver);
		
		mpcants.put("cantpalta", cantpalta);
		mpcants.put("cantpmedi", cantpmedi);
		mpcants.put("cantpbaja", cantpbaja);
		
		mpcants.put("canturge", canturge);
		
		return mpcants;
	}
	
	
	/**
	 * toFormatNeuralNetwork
	 * transforma un objeto de map a un  arreglos tipo double
	 * para ingresar al a red neuronal 
	 * */
	private double[][] toFormatNeuralNetwork(Map<String, Object> mpintpus){
		logger.info(" ####   toFormatNeuralNetwork "+mpintpus.values());
		
		Collection<Object>  list =     mpintpus.values();
		double[] input  =  new double[list.size()];
		int idx=0;
		for (Object o : list) {
			input[idx] = ((Double)o).doubleValue();
			idx++;
		}	
		double[][]_inputs = {input};
		return _inputs;
	}
	
	
	/**
	 * aplicacion de la red neuronal
	 * 
	 * */
	private  GrupoAtencion asignar(Cuadrilla cuadrilla,int idx , Map<Long, GrupoAtencion> mpGrupos){
		
		System.out.println(mpGrupos.toString());
		
		try{
			Long indexalt =  Long.parseLong(gruposidx.get(idx).toString());
			// provisional
			GrupoAtencion grupoAtencion =  mpGrupos.get(indexalt);			
			return grupoAtencion;
		}catch(Exception e){
			
			return null;
		}
	}
	
	
	
	
	public  Response guardarProgramacion(Integer accion, Long numeroProgramacion ){
		
		Response response = new Response(Response.OK,Response.MSG_OK);
		try{
			
			if(accion!=null && accion ==  ConstantBusiness.ACCION_EDITA_PROGRAMACION){
				
				//grupo de atencion
				//programacion
				//plan de trabajo
				
				//eliminando programacion
				Response r1 =   programacionDetalleDao.remove( numeroProgramacion);
				if(r1!=null && r1.getCodigo()==Response.OK){
					programacionDao.remove(new Programacion(numeroProgramacion));
				}
				
				
				// lista auxiliar
				List<GrupoAtencion> grupos  =  new ArrayList<>();
			
				// por dependencia guardando grupos auxiliares
				for (PlanTrabajo planTrabajo : planTrabajoCachedList) {	
					GrupoAtencion g =  planTrabajo.getGrupoAtencion();
					grupos.add((GrupoAtencion) g.clone());
				}
				
				// eliminando plan de trabajo
				
				for (PlanTrabajo planTrabajo : planTrabajoCachedList) {	
					
					Response r3 =  planTrabajoDetalleDao.remove(planTrabajo.getNumeroPlanTrabajo());
					if(r3!=null && r3.getCodigo()==Response.OK){
						planTrabajoDao.remove(planTrabajo.getNumeroPlanTrabajo());
					}
				}
			 
				// eliminando grupo de atencion
				for (GrupoAtencion g : grupos) {
					Response r2 = grupoAtencionDetalleDao.remove(g.getNumeroGrupoAtencion());
					if(r2!=null && r2.getCodigo()==Response.OK){
						grupoAtencionDao.remove(g.getNumeroGrupoAtencion());
					}
				}
	
	
			}
			
			
			
			
			System.out.println("Iniciando proceso  registro ");
		
			if(planTrabajoCachedList!=null && planTrabajoCachedList.size()>0){
				
				
		
				
					int cantsoli = 1;
					
					// guardando grupo generado
					for (PlanTrabajo planTrabajo : planTrabajoCachedList) {			
						// grupo atencion
						grupoAtencionDao.create(planTrabajo.getGrupoAtencion());
						int nse = 1;
						for(GrupoAtencionDetalle gd:planTrabajo.getGrupoAtencion().getGrupoAtencionDetalles()){
							
							gd.setId(new GrupoAtencionDetallePK(planTrabajo.getGrupoAtencion().getNumeroGrupoAtencion(), nse));
							long numeroSolicitud =  gd.getSolicitudServicio().getNumeroSolicitud();
							SolicitudServicio solicitudServicio =  solicitudServicioDao.getSolicitud(numeroSolicitud);
							gd.setSolicitudServicio(solicitudServicio);
							grupoAtencionDetalleDao.create(gd);
							
							
							nse++;
							cantsoli++;
						}			
					}
					System.out.println("Grupos registrados !!");
					
					// guardando planes de trabajo
					for (PlanTrabajo planTrabajo : planTrabajoCachedList) {
						//plan de trabajo
						Response  result =  planTrabajoDao.insert (planTrabajo);
						Long np  =  (Long) result.getData();// numero de plan de trabajo generado
						planTrabajo.setNumeroPlanTrabajo(np);
						
						int nse = 1;
						for(PlanTrabajoDetalle pd: planTrabajo.getPlanTrabajoDetalles()){
							pd.setId(new PlanTrabajoDetallePK(planTrabajo.getNumeroPlanTrabajo(), nse));
							long numeroSolicitud =  pd.getSolicitudServicio().getNumeroSolicitud();
							SolicitudServicio solicitudServicio =  solicitudServicioDao.getSolicitud(numeroSolicitud);
							 solicitudServicio =  (SolicitudServicio) solicitudServicio.clone();
							pd.setSolicitudServicio(solicitudServicio);
							pd.setGradoPrioridad(new BigDecimal(nse));
							pd.setHoraInicio(solicitudServicio.getFechaAtencion());
							// calculando hora fin
							Calendar cal = Calendar.getInstance();
							cal.setTime(solicitudServicio.getFechaAtencion());
							int timeadd =  solicitudServicio.getTipoSolicitud().getTiempoEjecucion();
							cal.add(Calendar.MINUTE, timeadd);
							pd.setHoraFin(cal.getTime());
							
							planTrabajoDetalleDao.create(pd);
							
							// estado de solicitud
							solicitudServicioEstadoDao.remove(solicitudServicio.getNumeroSolicitud(), ESTADO_SOLICITUD_ASIGNADO);
							solicitudServicio.setEstado(new Estado(ESTADO_SOLICITUD_ASIGNADO));
							//solicitudServicioDao.update(solicitudServicio);
							solicitudServicioDao.actualizarEstado(solicitudServicio);
							
							
							nse++;
						}	
					}
					System.out.println("Planes de Trabajo registrados !!");
		
					
					// insertando plan de trabajo 
					Programacion programacion =  new Programacion();
					programacion.setFechaProgramacion(new Date());
					programacion.setEstado(new Estado(ESTADO_PROGRAMACION_GENERADO));
					programacion.setCantidadAsignada(cantsoli);
					programacion.setCantidadCuadrillas(planTrabajoCachedList.size());
			
					Response resultp =   programacionDao.insert(programacion);
					Long npr =  (Long)resultp.getData();
					response.setData(npr);
					
					
					int nse = 1;
					for (PlanTrabajo planTrabajo : planTrabajoCachedList) {
						ProgramacionDetalle detalle =  new ProgramacionDetalle( npr, nse );
						logger.info("Insertando  numero de plan :"+planTrabajo.getNumeroPlanTrabajo());
						detalle.setPlanTrabajo(planTrabajo);
						programacionDetalleDao.create(detalle);
						nse ++;
					}
					
					
					System.out.println("Programacion registrada registrados !!");	
					// entrenando la red neuronal con las reasignaciones realizadas
					
					if(reasignaciones!=null && reasignaciones.size()>0){
						Date fecPgrn = Calendar.getInstance().getTime();
						List<Cuadrilla> cuadrillas  = cuadrillaDao.getCuadrillaList(fecPgrn);
						entrenarRedNeuronal(cuadrillas, mpGruposCached, reasignaciones);
					}else{
						logger.info("### NO EXISTEN REASIGNACIONES ### ");
					}
				
					reasignaciones.clear();
				
	
			}
		
		}catch(Exception e ){
			
			e.printStackTrace();
			
			response = new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
		
		return response;
	}
	
	
	/**
	 * reasigna los grupos generados y genera nuevos planes de trabajo
	 *
	 * */
	public List<PlanTrabajo> reasignarCuadrillasGrupos( Date fecPrgn, Map<Long, Long>  _reasignados){
		
		// verificar que existan valores en las reasignacione 
		if(_reasignados==null || 
			( _reasignados!=null && _reasignados.size()==0)
			){
			return null;
		}
		logger.info(" antes  reasignados "+_reasignados.toString());
		logger.info(" antes asignaciones "+asignaciones.toString());
		// verificar que los grupos existan existan
		Set<Long> _grupos =   _reasignados.keySet();
		for (Long _grupo : _grupos) {
			Long cuadrilla =   this.asignaciones.get(_grupo);
			if(cuadrilla==null){
				return null;
			}
		}
		
		// verificando que existan dieferencias entre las reasignaciones y lo reasignados
		for (Long _grupo : _grupos) {
			Long cuadrillanew =   _reasignados.get(_grupo); // obteniendo nueva cuadrilla asignada
			Long cuadrillaold =   this.asignaciones.get(_grupo); // obteniendo cuadrilla anterior
			// si no existen diferencia no se aplica el cambio
			if(cuadrillanew!=null && cuadrillaold!=null && cuadrillanew.equals(cuadrillaold)){
				return null;
			}else{
				
				
				// reasignando la cuadrilla de un grupo a otro
				//cambio {2=>1}
				//{3=>4, 2=>2, 1=>3, 4=>1}
				//{2=>1, 4=>2}
				//{3=>4, 2=>1, 1=>3, 4=>2}
				
				Set<Long> grupos = this.asignaciones.keySet();
				for (Long grupo : grupos) {
					Long ncuadrilla =   this.asignaciones.get(grupo); 
					if(ncuadrilla!=null && cuadrillanew!=null && ncuadrilla.equals(cuadrillanew)){
						_reasignados.put(grupo, cuadrillaold);
					}

				}
				
			}
		}
		
		
		logger.info(" reasignaciones procesadas :"+_reasignados.toString());
		_grupos =   _reasignados.keySet();
		// aplicar cambios a las asignaciones
		for (Long _grupo : _grupos) {
			this.reasignaciones.put(_grupo, _reasignados.get(_grupo));
			this.asignaciones.put(_grupo, _reasignados.get(_grupo));
		}
		
		logger.info(" despues "+asignaciones.toString());
		
		List<Cuadrilla> cuadrillas  = cuadrillaDao.getCuadrillaList(fecPrgn);
		List<PlanTrabajo> planTrabajoList  = generarPlanesTrabajo(fecPrgn, cuadrillas, mpGruposCached, asignaciones);
	
		planTrabajoCachedList = new ArrayList<PlanTrabajo>();
		planTrabajoCachedList.addAll(planTrabajoList);
		
		
		
		logger.info("####  reasignaciones finales :"+reasignaciones.toString());
		
		plotPlanesTrabajo(planTrabajoCachedList);
		 
		return planTrabajoList;
	}
	
	
	private void plotPlanesTrabajo(List<PlanTrabajo>	planTrabajoList){
		 logger.info("## plotPlanesTrabajo #####");
		 if( planTrabajoList!=null && planTrabajoList.size()>0){
			for (PlanTrabajo p : planTrabajoList) {
				GrupoAtencion  g  =  p.getGrupoAtencion();
				String dg =  (g==null)?"":g.getDescripcion();			
				//logger.info("##guadrilla :"+ p.getCuadrilla().getNumeroCuadrilla()+ "- "+dg);
				logger.info("##g grupo - "+dg);
				List<GrupoAtencionDetalle> grupoAtencionDetalles =   g.getGrupoAtencionDetalles();
				logger.info("##grupo - detalle ");
				for (GrupoAtencionDetalle gd : grupoAtencionDetalles) {
					logger.info("##--> solicitud :"+gd.getSolicitudServicio().getNumeroSolicitud());
				}
				
				List<PlanTrabajoDetalle> planTrabajoDetalles =  p.getPlanTrabajoDetalles();
				
				for (PlanTrabajoDetalle pd : planTrabajoDetalles) {
					logger.info("##plantrabajo - detalle " + pd.getId() );
					logger.info("##--> solicitud :" +pd.getSolicitudServicio().getNumeroSolicitud());
				}

			}
		 }
	}
	
	
	
	
	
	private  void entrenarRedNeuronal(List<Cuadrilla> cuadrillas, 
										Map<Long, GrupoAtencion> mpGrupos,
										Map<Long, Long>  reasignados ){
		
		
		Integer codrn =  ConstantBusiness.COD_RED_NEURONAL_PROGRAMACION;
		
	
	    RedNeuronal red =   	redNeuronalDao.getRedNeuronal(codrn);
	    List<RedNeuronalDetalle> redNeuronalDetalles = red.getRedNeuronalDetalles();
	    HashMap<String, Double> weightUpdate = new HashMap<String, Double>();// pesos para red neuronal
	    System.out.println(" ###    red neuronal detalle bd    ### ");
	    for (RedNeuronalDetalle d : redNeuronalDetalles) {
	    	weightUpdate.put("N" + d.getNumeroNeurona().intValue() + "_C" + d.getNumeroConexion().intValue(), d.getValorPeso().doubleValue())	;
		}
	    
	    // entrenando la red 
	    NeuralNetwork neuralNetwork = new NeuralNetwork(16, 4, 1,false, weightUpdate); // true la red esta entrenada
	   
	    
	    
	    
	    Set<Long> grupos =   reasignados.keySet();
	    logger.info(" los reasignados a procesar : "+grupos.toString());
	    for (Long  nrogrupo : grupos) {
	    	Long nrocuadrilla = reasignados.get(nrogrupo);
	    	
	    	  int idx  =    cuadrillas.indexOf(new Cuadrilla(nrocuadrilla));
	    	  if(idx !=-1){
	    		  
	    		  Cuadrilla  cuadrilla  =  cuadrillas.get(idx);
	    		  
	    		  GrupoAtencion grupoAtencion  =      mpGrupos.get(nrogrupo);
	    			
	    		  //cantidades 
	    		  Map<String, Double>mpcants =  getCantidadPorTipoSolicitudes(grupoAtencion);
	    		  // promedios 
	    		  Map<String, Double> mpproms  = getPromedioHabilidades(cuadrilla);
	    			
	    		  
	    		  Map<String,Object> mp =  new LinkedHashMap<>();
	    		  // añadiendo cantidades de solicitudes
	    		  mp  =  addMap(mp, mpcants);
					// añadiendo promedio de habilidades
	    		  mp  =  addMap(mp, mpproms);
	    		  
	    		  logger.info(" #####   iniciando el entrenamiento de la red  ### ");
	    		  /// procesnamiento de entrenamiento  
	    		  double[][]_inputs =  toFormatNeuralNetwork(mp);

	    		  neuralNetwork.inputs  = _inputs;
	 
	    	      int maxRuns = 50000;
	    	      double minErrorCondition = 0.001;
	    	      neuralNetwork.run(maxRuns, minErrorCondition);
	    	        // datos entrenados
	    	      HashMap<String, Map<String, Double>> weightUpdateOut  = neuralNetwork.getWeightUpdateOut();
	    	      
	    	     logger.info(" #####   mostrando  red neuronal entranada    ### ");
	    	      
	    	      Iterator<String> it = weightUpdateOut.keySet().iterator();
	    	      while(it.hasNext()){
	    	    	  
	    	    	  	String key =  it.next();
	    	    		Map<String, Double> values =  weightUpdateOut.get(key);
	    				Integer id = values.get("idx").intValue();// id
	    				Integer n   =  values.get("n").intValue();// neunrona
	    				Integer c   =  values.get("c").intValue();// neourona
	    				Double  p   =   values.get("peso");// peso
	    				logger.info(" #####  idx"+id +" n :"+n+" c:"+c+" p:"+p);
	    			
	    				// actualizando en bd
	    				logger.info("##### actualizando en bd ####");
	    				
	    				redNeuronalDetalleDao.setClass(RedNeuronalDetalle.class);
	    				
	    				RedNeuronalDetalle rnd =  redNeuronalDetalleDao.find( new RedNeuronalDetallePK(codrn, id));
	    				rnd.setNumeroNeurona(n);
	    				rnd.setNumeroConexion(c);
	    				rnd.setValorPeso( new BigDecimal(p));
	    				redNeuronalDetalleDao.update(rnd);
	    			
	    	      }
	    	  }
	    	
	    	
		}
	}
	

	
	
	
	/**
	 * getGruposAtentionGenerados
	 * obtiene los grupos generados en memoria o en bd
	 * */
	public Map<Long, GrupoAtencion> getGruposAtentionGenerados(Integer accion, Long numeroProgramacion) {
		logger.info(" ### getGruposAtentionGenerados ### ");
		
		Map<Long, GrupoAtencion> mpGrupos =  Collections.EMPTY_MAP;
		
		try{
			
			if(accion!=null && accion.equals(ConstantBusiness.ACCION_NUEVA_PROGRAMACION)){
				mpGrupos =   new LinkedHashMap<Long, GrupoAtencion>();
				logger.info(" mostrando mostrando grupos temporales  ");
				
				mpGrupos.putAll(mpGruposCached);
				
			}else if(accion!=null && accion.equals(ConstantBusiness.ACCION_EDITA_PROGRAMACION)){
				mpGrupos =   new LinkedHashMap<Long, GrupoAtencion>();
				logger.info(" mostrando mostrando grupos bd  ");
				List<GrupoAtencion> grupoAtencionLíst =  grupoAtencionDao.getGruposAtencionPorProgramacion(numeroProgramacion);
				List<GrupoAtencion> _grupoAtencionLíst =  new ArrayList<>();
				
				List<GrupoAtencionDetalle> _grupoDetalleAtencionLíst =  new ArrayList<>();
				
				
				
				for (GrupoAtencion g : grupoAtencionLíst) {
					logger.info(" grupo generado :"+g.getDescripcion());
					//clonando grupos 
					logger.info(" ### clonando grupo : "+g.getNumeroGrupoAtencion());
					GrupoAtencion grupoAtencion = (GrupoAtencion) g.clone();
					for(GrupoAtencionDetalle d:  g.getGrupoAtencionDetalles()){
						
						logger.info("### numero de solicitud :"+d.getSolicitudServicio().getNumeroSolicitud());
						
						_grupoDetalleAtencionLíst.add((GrupoAtencionDetalle)d.clone());
					 }
					
					_grupoAtencionLíst.add(grupoAtencion);
				}

				logger.info(" mapeando objetos clonados ");
				for (GrupoAtencion g : _grupoAtencionLíst) {
					g.setGrupoAtencionDetalles(new ArrayList<GrupoAtencionDetalle>());
					for (GrupoAtencionDetalle d : _grupoDetalleAtencionLíst) {
						if( d.getGrupoAtencion()!=null && 
								d.getGrupoAtencion().getNumeroGrupoAtencion()==g.getNumeroGrupoAtencion()){
							g.getGrupoAtencionDetalles().add(d);
						}
					}
					mpGrupos.put(g.getNumeroGrupoAtencion(),  g);
				}
				
				
				this.mpGruposCached =  mpGrupos;
				
				mostrarGrupos(mpGrupos);
			}
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return mpGrupos;
	}
	
	
	
	
	
	private void  mostrarGrupos(Map<Long, GrupoAtencion>  mpGrupos){
		
		Iterator<Long> _it =  mpGrupos.keySet().iterator();
		while(_it.hasNext()){
			Long  numero = _it.next();
		    GrupoAtencion g =   mpGrupos.get(numero);
		    logger.info(" grupo :"+g.getDescripcion());
		    logger.info(" color :"+g.getColor());
		    logger.info("radio : "+g.getRadio());
		    logger.info(" cant items : "+g.getGrupoAtencionDetalles().size());
		    List<GrupoAtencionDetalle>  detalles =  g.getGrupoAtencionDetalles();
		    if(detalles!=null){
		    	for (GrupoAtencionDetalle d : detalles) {
		    		logger.info(" solicitud :"+d.getSolicitudServicio().getNumeroSolicitud());
		    	}
		    }
		}
		
	}
	
	
	public   List<PlanTrabajo> getPlanesAsigandos ( Integer accion, Long numeroProgramacion) {
		
		 logger.info("##### getPlanesAsigandos ####  ");
		 
		 List<PlanTrabajo>  planTrabajoList =  Collections.EMPTY_LIST;

		 if(accion!=null && accion.equals(ConstantBusiness.ACCION_NUEVA_PROGRAMACION)){
			 
			 
		 }else if (accion!=null && accion.equals(ConstantBusiness.ACCION_EDITA_PROGRAMACION)){
			 
			 
			 planTrabajoList =  planTrabajoDao.getPlanTrabajoListPorProgramacion(numeroProgramacion);	
			 for (PlanTrabajo pt : planTrabajoList) {
				 
				 List<PlanTrabajoDetalle>  planTrabajoDetalleList  =  planTrabajoDetalleDao.getPlanTrabajoDetalleList(pt.getNumeroPlanTrabajo());
				 pt.setPlanTrabajoDetalles(planTrabajoDetalleList);
				 GrupoAtencion g =  pt.getGrupoAtencion();
				 List<GrupoAtencionDetalle> grupoAtencionDetalles =  grupoAtencionDetalleDao.getGrupoAtencionDetalleList(g.getNumeroGrupoAtencion());
				 g.setGrupoAtencionDetalles(grupoAtencionDetalles);
				 
				 asignaciones.put(pt.getGrupoAtencion().getNumeroGrupoAtencion(), pt.getCuadrilla().getNumeroCuadrilla());
				 
				/* Cuadrilla c =     pt.getCuadrilla();
				 List<CuadrillasDetalle> cuadrillasDetallesList = cuadrillasDetalleDao.getCuadrillasDetalleList(c);
				 c.setCuadrillasDetalles(cuadrillasDetallesList);*/
			 }
			 
			 
			 planTrabajoCachedList = new ArrayList<PlanTrabajo>();
			 planTrabajoCachedList.addAll(planTrabajoList);
			 reasignaciones.clear();

		 }

		 return planTrabajoList;
	}
	
	
	
	
	
	
	
	public  Response clearCache(){
		

		this.mpGruposCached.clear();
		this.solicitudesCached.clear();
		this.planTrabajoCachedList.clear();
		this.reasignaciones.clear();
		this.asignaciones.clear();
		return new Response(Response.OK, Response.MSG_OK);
	}

	
	/*public Map<Long, GrupoAtencion> getMpGruposCached() {
		return mpGruposCached;
	}

	public void setMpGruposCached(Map<Long, GrupoAtencion> mpGruposCached) {
		//this.mpGruposCached = mpGruposCached;
		this.mpGruposCached =  new LinkedHashMap<>();
		this.mpGruposCached.putAll(mpGruposCached);
		Gson gson = new Gson();
		logger.info(gson.toJson( this.planTrabajoCachedList));
		
	}*/


	
	
	

	
	

	public List<SolicitudServicio> getSolicitudesCached() {
		return solicitudesCached;
	}


	public void setSolicitudesCached(List<SolicitudServicio> solicitudesCached) {
		this.solicitudesCached = solicitudesCached;
	}


	public Response actualizar(Programacion p) {
	
		Response response = new Response(Response.OK, Response.MSG_OK);
		try{
		
			programacionDao.update(p);
			
			System.out.println("## programación actualizada ");
			
		}catch(Exception e){
			
			e.printStackTrace();
			response =  new Response(Response.ERROR, Response.MSG_ERROR);
			
		}
		
		return response;
	}


	/**
	 * cambia al estado de ejecución una programación registrada
	 * */
	public Response ejecutar(Long numeroProgramacion) {
		
		Response result = null;
		
		try{
			
		    
			Programacion p  = null;
			if(numeroProgramacion!=null){
				p = programacionDao.find(numeroProgramacion);
			}
			// si no se ha guardado la programnacion, se procede a registrar
			if(p==null){
				Response response = guardarProgramacion(ACCION_NUEVA_PROGRAMACION, numeroProgramacion);
				if( response !=null){
					Long npr =  (Long)response.getData();
					p =  new Programacion(npr);
					p.setEstado(new Estado(ConstantBusiness.ESTADO_PROGRAMACION_GENERADO));
				}
			}
			
			if(p!=null &&  p.getEstado().getCodigoEstado()== ConstantBusiness.ESTADO_PROGRAMACION_GENERADO){
				p.setEstado(new Estado(ConstantBusiness.ESTADO_PROGRAMACION_EJECUCION));
				result =  programacionDao.actualizarEstado(p);
			}
			
			if(result!=null && result.getCodigo()== Response.OK ){
				result.setMensaje(" La programación está en estado de ejecución");
			}
			
		}catch(Exception e){
			
			logger.info(e);
			
			result =  new Response(Response.ERROR, Response.MSG_ERROR);
		}
		return result;
	}
	
	//###### opciones de agrupamiento #### //
	public Response getCantGruposSugeridos(String solicitudesselect) {
		return grupoService.getCantGruposSugeridos(solicitudesselect);
	}


	public Map reasignarSolictud(long numerosoli, long numerogrup) {
		logger.info("#### Planes de trabajo inicial ");
		plotPlanesTrabajo(planTrabajoCachedList);
		this.mpGruposCached =    grupoService.reasignarSolictud(planTrabajoCachedList,   mpGruposCached, numerosoli, numerogrup);
		plotPlanesTrabajo(planTrabajoCachedList);
		return mpGruposCached;
	}


	public Map<Long, GrupoAtencion> generarGruposAtencion(String solicitudesselect, Integer _numerogrupos) {
		Map<Long, GrupoAtencion>  mpGrupos =  null;
		try{
		
			mpGrupos  = grupoService.generarGruposAtencion(null, solicitudesselect, _numerogrupos);
			this.mpGruposCached =  mpGrupos;
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		return mpGrupos;
	}

	
}


class AsignacionNeuralNetworkSort implements Comparator<Map>{

	@Override
	public int compare(Map o1, Map o2) {		
		Double ga1 =    (Double )o1.get("gradasig"); // grado de asignacion
		Double ga2 =    (Double )o2.get("gradasig"); // grado de asignacion
		return ga2.compareTo(ga1);
	}

}