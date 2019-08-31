package pe.com.cablered.mistia.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.GrupoAtencionDao;
import pe.com.cablered.mistia.dao.GrupoAtencionDetalleDao;
import pe.com.cablered.mistia.dao.PlanTrabajoDetalleDao;
import pe.com.cablered.mistia.geometria.Geometria;
import pe.com.cablered.mistia.geometria.Punto;
import pe.com.cablered.mistia.ia.clustering.Clustering;
import pe.com.cablered.mistia.ia.tsp.TSP;
import pe.com.cablered.mistia.ia.tsp.TSPHeuristica;
import pe.com.cablered.mistia.model.GrupoAtencion;
import pe.com.cablered.mistia.model.GrupoAtencionDetalle;
import pe.com.cablered.mistia.model.GrupoAtencionDetallePK;
import pe.com.cablered.mistia.model.PlanTrabajo;
import pe.com.cablered.mistia.model.PlanTrabajoDetalle;
import pe.com.cablered.mistia.model.PlanTrabajoDetallePK;
import pe.com.cablered.mistia.model.SolicitudServicio;
import pe.com.cablered.mistia.model.TipoSolicitud;
import pe.com.cablered.mistia.model.sort.GrupoAtencionDetalleSortPrioridad;
import static pe.com.cablered.mistia.util.ConstantBusiness.*;
import pe.com.cablered.mistia.util.ConstantBusiness;
import pe.com.cablered.mistia.util.Util;

@Stateless
@LocalBean
public class GrupoService {

    @Inject
    private SolicitudServicioService service;

    @Inject
    private PlanTrabajoDetalleDao planTrabajoDetalleDao;

    @Inject
    private GrupoAtencionDetalleDao grupoAtencionDetalleDao;

    @Inject
    private GrupoAtencionDao grupoAtencionDao;

    //private  List<SolicitudServicio>  solicitudesCached;
    final static Logger logger = Logger.getLogger(GrupoService.class);

    private List<SolicitudServicio> solicitudesToObject(String solicitudesselect) {

        if (solicitudesselect == null || solicitudesselect.trim().equals("")) {
            return null;
        }

        List<SolicitudServicio> solicitudes = service.getSolicitudListPorEstado(ESTADO_SOLICITUD_PENDIENTE);
        System.out.println(" cantidad soli " + solicitudes.size());

        List<SolicitudServicio> solicitudesSelect = new ArrayList<>();
        String[] _solicitudesselect = solicitudesselect.split(",");
        for (String ns : _solicitudesselect) {
            Long _ns = Long.parseLong(ns, 10);// numero de solicittud

            for (SolicitudServicio s : solicitudes) {

                if (s.getNumeroSolicitud() == _ns.longValue()) {
                    solicitudesSelect.add(s);
                    break;
                }
            }

        }

        return solicitudesSelect;
    }

    /**
     * Genera los grupos de atencion paora una determinada fecha de un
     * determinando grupo de puntos seleccionados y una cantidad de grupos
     * definida. Se utiliza el algoritmo Kmeans
     *
     * Un vez generado los grupos se establece el orden de los puntos
     * seleccionados
     *
     * @solicitudesselect solicitudes seleccionadas
     * @numerogrupos número de grupos
     *
     *
     */
    public Map<Long, GrupoAtencion> generarGruposAtencion(String solicitudesselect, Integer numerogrupos) {

        logger.info(" ### EL OBJETO :" + this);
        // pasar por cluster
        Map<Long, GrupoAtencion> mpGrupos = new LinkedHashMap<Long, GrupoAtencion>();

        List<SolicitudServicio> solicitudes = solicitudesToObject(solicitudesselect);
        if (solicitudes == null) {
            return null;
        }

        logger.info(" cantidad soli selected " + solicitudes.size());

        List<Punto> _puntos = new ArrayList<Punto>();
        int idx = 0;
        for (SolicitudServicio s : solicitudes) {
            TipoSolicitud ts = s.getTipoSolicitud();
            _puntos.add(new Punto((int) s.getNumeroSolicitud(), s.getLatitud(), s.getLongitud(), ts.getTiempoEjecucion() * 1.0));
            idx++;
        }

        // clustering 
        Clustering clustering = new Clustering();
        List<Map<Integer, Punto>> clusters = clustering.getClusters(_puntos, TIEMPO_JORNADA, numerogrupos);

        long g = 1; // descripcion del grupo
        long s = 1; //solicitud de servicio

        for (Map<Integer, Punto> map : clusters) {
            GrupoAtencion grupo = new GrupoAtencion(g, "Grupo" + g);
            logger.info("Grupo : " + grupo.getDescripcion());
            int nse = 1;
            List<GrupoAtencionDetalle> detalles = new ArrayList<GrupoAtencionDetalle>();
            Iterator<Integer> it = map.keySet().iterator();
            //double ct =  0.0;
            while (it.hasNext()) {
                Integer i = it.next();
                Punto punto = map.get(i);

                for (SolicitudServicio solicitud : solicitudes) {
                    //solicitud.setNumeroSolicitud(i);
                    // if(solicitud.getPoste().getCodigoPoste().equals(punto.getNumero())){
                    if (solicitud.getNumeroSolicitud() == punto.getNumero()) {
                        System.out.println("Punto : " + punto.toString() + " -  solicitud :" + solicitud.getNumeroSolicitud());
                        GrupoAtencionDetalle detalle = new GrupoAtencionDetalle(grupo.getNumeroGrupoAtencion(), nse, solicitud);
                        detalle.setGrupoAtencion(grupo);
                        detalles.add(detalle);
                        nse++;
                        break;
                    }
                }
            }

            // ###### iniciando TSP ###########
            // realizando ordenamiento mediante el algoritmo del problema viajero
            //TSP tsp  =  new TSP();
            TSPHeuristica tsp = new TSPHeuristica();
            Punto puntoini = TSP.PUNTO_INICIAL;
            SimpleDateFormat sdf = new SimpleDateFormat(ConstantBusiness.FORMAT_DATE_TIME);
            System.out.println("### TSP orden inicial  ");
            List<Punto> puntos = new ArrayList<>();
            for (GrupoAtencionDetalle d : detalles) {

                /*Poste poste =  d.getSolicitudServicio().getPoste();
				Punto punto =  new Punto(poste.getCodigoPoste(), 
											poste.getLatitud().doubleValue(), 
											poste.getLongitud().doubleValue(), 
											0.0   );*/
                SolicitudServicio solicitudServicio =  d.getSolicitudServicio();
                Punto punto = new Punto((int) solicitudServicio.getNumeroSolicitud(), solicitudServicio.getLatitud(), solicitudServicio.getLongitud(), 0.0);

                Date fecha = d.getSolicitudServicio().getFechaAtencion();
                String _fecha = sdf.format(fecha);
                Integer prioridad = d.getSolicitudServicio().getTipoSolicitud().getPrioridad();
                Integer urgencia = ProgramacionService.getUrgencia(d.getSolicitudServicio()) ? ConstantBusiness.URGENTE : ConstantBusiness.NO_URGENTE;
                punto.setDatos(TSPHeuristica.getDatos(_fecha, prioridad, urgencia));
                puntos.add(punto);

            }

            System.out.println("### TSP ordenado : se establecen el orden a los puntos  ");
            puntos = tsp.getRuta(puntoini, puntos);

            for (Punto punto : puntos) {
                for (GrupoAtencionDetalle d : detalles) {
                    int numerSoli = (int) d.getSolicitudServicio().getNumeroSolicitud();
                    if (numerSoli == punto.getNumero()) {
                        d.setGradoPrioridad(new BigDecimal(punto.getOrden() - 1));
                        //System.out.println("detalle prioridad " + d.getGradoPrioridad() + "   el punto " + punto + " solicitud " + d.getSolicitudServicio().getNumeroSolicitud());
                        break;
                    }
                }
            }

            Collections.sort(detalles, new GrupoAtencionDetalleSortPrioridad());
            for (GrupoAtencionDetalle d : detalles) {
                //System.out.println("solicitud : "+d.getSolicitudServicio().getNumeroSolicitud()+ " poste "+d.getSolicitudServicio().getPoste().getCodigoPoste()+    "   detalle priorrida "+ d.getGradoPrioridad()) ;
                System.out.println("solicitud : " + d.getSolicitudServicio().getNumeroSolicitud() + " detalle prioridad " + d.getGradoPrioridad());
            }

            // ###### finalizando  TSP ###########
            grupo.setGrupoAtencionDetalles(detalles);
            mpGrupos.put(g, grupo);
            s++;
            g++;
        }

        // proceso para obtener atributos que permitan graficar los grupos en el mapa		
        generargraficos(mpGrupos);

        return mpGrupos;
    }

    /**
     * reasignar solicitud
     *
     */
    public Map<Long, GrupoAtencion> reasignarSolictud(List<PlanTrabajo> planTrabajoList, Map<Long, GrupoAtencion> mpGrupos, long numeroSolicitud, long numeroGrupoAtencion) {

        // eliminar del grupo actual  y el plan del detalle actual
        Set<Long> keys = mpGrupos.keySet();
        GrupoAtencionDetalle dfound = null;
        PlanTrabajoDetalle pdfound = null;// detalle de plan de trabajo encontrado 
        SolicitudServicio solicitudfound = null;
        try {
            for (Long numero : keys) {

                GrupoAtencion g = mpGrupos.get(numero);
                //buscar la solcitud en los detalles asignados
                List<GrupoAtencionDetalle> detalles = g.getGrupoAtencionDetalles();
                for (GrupoAtencionDetalle d : detalles) {

                    SolicitudServicio s = d.getSolicitudServicio();

                    if (s != null && s.getNumeroSolicitud() == numeroSolicitud) {
                        solicitudfound = s;
                        dfound = d;
                        break;
                    }
                }

                if (dfound != null) {
                    int idx = detalles.indexOf(dfound);
                    if (idx != -1) {
                        // eliminar el detalle encontrado
                        //logger.info("###-> eliminando detalle de grupo :"+dfound.getSolicitudServicio().getNumeroSolicitud() +"de grupo "+g.getNumeroGrupoAtencion() + " ID("+dfound.getId().getNumeroGrupoAtencion() +"-"+dfound.getId().getNumeroOrden()+")");
                        GrupoAtencionDetalle deleted = detalles.remove(idx);
                        if (deleted != null) {
                            logger.info("del grupo anterior");
                            //grupoAtencionDetalleDao.remove(deleted);
                            Response response = grupoAtencionDetalleDao.transferir(deleted);
                            logger.info(" elimando del grupo anterior");
                        }
                    }
                }

            }

            for (Long numero : keys) {

                GrupoAtencion g = mpGrupos.get(numero);
                // eliminando de plan de trabajo
                for (PlanTrabajo p : planTrabajoList) {

                    GrupoAtencion ga = p.getGrupoAtencion();

                    if (ga != null && g != null && ga.equals(g)) {
                        List<PlanTrabajoDetalle> _detalles = p.getPlanTrabajoDetalles();
                        for (PlanTrabajoDetalle d : _detalles) {
                            SolicitudServicio s = d.getSolicitudServicio();
                            if (s != null && s.getNumeroSolicitud() == numeroSolicitud) {
                                pdfound = d;
                                break;
                            }
                        }

                        if (pdfound != null) {
                            int idx = _detalles.indexOf(pdfound);
                            if (idx != -1) {
                                //logger.info("###-> eliminando detalle de plan :"+dfound.getSolicitudServicio().getNumeroSolicitud() +" de plan "+p.getNumeroPlanTrabajo() +" ID("+dfound.getId().getNumeroGrupoAtencion() +"-"+dfound.getId().getNumeroOrden()+")");

                                PlanTrabajoDetalle pddeleted = _detalles.remove(idx);
                                if (pddeleted != null) {
                                    logger.info("del plan detalle anterior");
                                    //planTrabajoDetalleDao.remove(pdfound);
                                    Response response = planTrabajoDetalleDao.transferir(pdfound);

                                    logger.info(" elimnado del plan detalle anterior");

                                }
                            }
                        }
                    }
                }
            }

            // añadir al grupo de destino
            GrupoAtencion g = mpGrupos.get(numeroGrupoAtencion);

            if (g != null && dfound != null) {
                List<GrupoAtencionDetalle> detalles = g.getGrupoAtencionDetalles();
                if (dfound != null) {

                    Integer nse = detalles.get(detalles.size() - 1).getId().getNumeroOrden() + 1;
                    dfound.setId(new GrupoAtencionDetallePK(dfound.getGrupoAtencion().getNumeroGrupoAtencion(), nse));
                    detalles.add(dfound);

                }
            }
            // añadir plan de trabajo
            if (pdfound != null) {

                for (PlanTrabajo p : planTrabajoList) {

                    GrupoAtencion ga = p.getGrupoAtencion();

                    if (ga != null && ga.getNumeroGrupoAtencion() == numeroGrupoAtencion) {

                        if (p.getPlanTrabajoDetalles() != null) {
                            List<PlanTrabajoDetalle> detalles = p.getPlanTrabajoDetalles();
                            List<GrupoAtencionDetalle> gaDetalles = ga.getGrupoAtencionDetalles();
                            //logger.info ("###-> añadiendo :"+pdfound.getSolicitudServicio().getNumeroSolicitud()+ " a plan :"+p.getNumeroPlanTrabajo()); 
                            logger.info("cant detalles:  " + detalles.size());
                            logger.info("cant gaDetalles:  " + gaDetalles.size());
                            logger.info("sorting ##");

                            detalles.sort((o1, o2) -> o1.getId().getNumeroSecuencial().compareTo(o2.getId().getNumeroSecuencial()));
                            gaDetalles.sort((o1, o2) -> o1.getId().getNumeroOrden().compareTo(o2.getId().getNumeroOrden()));

                            for (PlanTrabajoDetalle detalle : detalles) {
                                logger.info(detalle.getId().toString());
                            }

                            for (GrupoAtencionDetalle detalle : gaDetalles) {
                                logger.info(detalle.getId().toString());
                            }

                            // secuencial para plan de trabajo
                            Integer nse = detalles.get(detalles.size() - 1).getId().getNumeroSecuencial() + 1;
                            pdfound.setId(new PlanTrabajoDetallePK(p.getNumeroPlanTrabajo(), nse));
                            p.getPlanTrabajoDetalles().add(pdfound);
                            logger.info(" p.getNumeroPlanTrabajo() :  " + p.getNumeroPlanTrabajo());
                            logger.info(" nse :  " + nse);

                            logger.info("nuevo plan detalle");
                            planTrabajoDetalleDao.create(pdfound);
                            logger.info("insertado nuevo plan detalle");

                            // numero de orden para el nuevo detalple de la cuadrilla
                            GrupoAtencionDetalle gaDetalle = (GrupoAtencionDetalle) gaDetalles.get(gaDetalles.size() - 1).clone();
                            Integer numeroOrden = gaDetalle.getId().getNumeroOrden() + 1;
                            gaDetalle.setId(new GrupoAtencionDetallePK(ga.getNumeroGrupoAtencion(), numeroOrden));
                            gaDetalle.setSolicitudServicio(solicitudfound);
                            gaDetalles.add(gaDetalle);
                            grupoAtencionDetalleDao.create(gaDetalle);

                            break;

                        }
                    }
                }
            }
            logger.info("#### mostrando detalle del cambio #### ");
            generargraficos(mpGrupos);
            Set<Long> _keys = mpGrupos.keySet();
            for (Long numero : _keys) {
                //Response response = grupoAtencionDao.actualizarRadio(mpGrupos.get(numero));       
                logger.info("actualizando grupo ####");
                grupoAtencionDao.update(mpGrupos.get(numero));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mpGrupos;
    }

    /**
     * *7 genera los atributos necesarios para poder pintar los mapas
     *
     * /
     *
     * @param mpGrupos
     */
    private void generargraficos(Map<Long, GrupoAtencion> mpGrupos) {
        System.out.println("Generando graficos ");

        try {
            //ArrayList<Integer> colorsindex = Util.getRandomNonRepeatingIntegers(mpGrupos.size(), 0, Geometria.colors.length-1);
            ArrayList<Integer> colorsindex = Util.getRandomNonRepeatingIntegers(mpGrupos.size(), 0, mpGrupos.size() - 1);

            int idxcolor = 0;
            Iterator<Long> it = mpGrupos.keySet().iterator();
            while (it.hasNext()) {
                Long numero = it.next();
                GrupoAtencion g = mpGrupos.get(numero);

                // obtener atributos del punto central
                List<GrupoAtencionDetalle> detalles = g.getGrupoAtencionDetalles();

                if (detalles != null) {

                    // obtener coordenadas de los puntos a ejecutar el servicio
                    Punto[] puntos = new Punto[detalles.size()];
                    int i = 0;
                    for (GrupoAtencionDetalle d : detalles) {

                        double latitud = d.getSolicitudServicio().getLatitud();
                        double longitud = d.getSolicitudServicio().getLongitud();

                        puntos[i] = new Punto(latitud, longitud);
                        i++;
                    }

                    //obtener el punto central(latitud, longitud) del grupo
                    Punto puntoCentral = Geometria.puntoCentral(puntos);
                    g.setLatitudCentral(puntoCentral.getLatitud());
                    g.setLongitudCentral(puntoCentral.getLongitud());

                    // obtener el radio máximo del grupo
                    double radio = Geometria.getRadioMaximo(puntoCentral, puntos);
                    g.setRadio(radio);

                    System.out.println(" radio : " + radio);

                    // area del circulo
                    g.setArea(Geometria.getAreaCirculo(radio));

                    //obtener color aleatorio para el grupo
                    if (g.getColor() == null) {

                        int c = colorsindex.get(idxcolor);
                        g.setColor(Geometria.colors[c]);

                        idxcolor++;
                    }

                    // mostrar en consola los grupos 
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        //mostrarGrupos(mpGrupos);
    }

    private void mostrarGrupos(Map<Long, GrupoAtencion> mpGrupos) {

        Iterator<Long> _it = mpGrupos.keySet().iterator();
        while (_it.hasNext()) {
            Long numero = _it.next();
            GrupoAtencion g = mpGrupos.get(numero);
            logger.info(" grupo :" + g.getDescripcion());
            logger.info(" color :" + g.getColor());
            logger.info("radio : " + g.getRadio());
            List<GrupoAtencionDetalle> detalles = g.getGrupoAtencionDetalles();
            if (detalles != null) {
                for (GrupoAtencionDetalle d : detalles) {
                    logger.info(" solicitud :" + d.getSolicitudServicio().getNumeroSolicitud());
                }
            }
        }

    }

    /*public static  List<Punto> getPuntos(){
		
		List<Punto> puntos =  new ArrayList<Punto>();
		
		puntos.add(new Punto(1,-12.010948, -75.175982 ,30  ));
		puntos.add(new Punto(2,-12.012496, -75.175688 ,20));
		puntos.add(new Punto(3,-12.037512, -75.183327 ,15  ));
		puntos.add(new Punto(4,-12.019968, -75.188048,15  ));
		puntos.add(new Punto(5,-12.037261, -75.184271,30 ));
		puntos.add(new Punto(6,-12.029538, -75.194314,60 ));
		puntos.add(new Punto(7,-12.022318, -75.190280,15 ));
		puntos.add(new Punto(8,-12.021647, -75.183585,30 ));
		puntos.add(new Punto(9,-12.041961, -75.184786,50 ));
		puntos.add(new Punto(10,-12.038016, -75.195000,60 ));
		puntos.add(new Punto(11,-12.030797, -75.194399,60 ));
		puntos.add(new Punto(12,-12.038100, -75.184100,30));
		puntos.add(new Punto(13,-12.037932, -75.193713,15 ));
		puntos.add(new Punto(14,-12.036337, -75.197060,30));
		puntos.add(new Punto(15,-12.037093, -75.194228,15 ));
		puntos.add(new Punto(16,-12.041542, -75.185816,30 ));
		puntos.add(new Punto(17,-12.037764, -75.181096,30 ));
		puntos.add(new Punto(19,-12.042801, -75.190108,20 ));
		puntos.add(new Punto(21,-12.043640, -75.184014,20 ));
		puntos.add(new Punto(22,-12.045739, -75.185387,60 ));
		puntos.add(new Punto(23,-12.044396, -75.195859,30 ));
		puntos.add(new Punto(24,-12.046830, -75.187361,15 ));
		puntos.add(new Punto(25,-12.049348, -75.199378,40 ));
		puntos.add(new Punto(26,-12.050775, -75.187962,60 ));
		puntos.add(new Punto(27,-12.053797, -75.184271,80 ));
		puntos.add(new Punto(29,-12.052286, -75.201523,60 ));
		puntos.add(new Punto(30,-12.057742, -75.194399,30));
		puntos.add(new Punto(31,-12.062526, -75.189850,15 ));
		puntos.add(new Punto(32,-12.059337, -75.204270,60 ));
		puntos.add(new Punto(33,-12.060176, -75.204098,60));
		puntos.add(new Punto(34,-12.060344, -75.205901,60 ));
		puntos.add(new Punto(35,-12.069829, -75.203841,15 ));
		puntos.add(new Punto(36,-12.066639, -75.211566,60 ));
		puntos.add(new Punto(37,-12.068905, -75.213969,15 ));
		puntos.add(new Punto(38,-12.071675, -75.217745,30 ));
		puntos.add(new Punto(39,-12.072179, -75.223582,30 ));
		puntos.add(new Punto(40,-12.075517, -75.229887,60 ));
		puntos.add(new Punto(41,-12.082984, -75.231136,60 ));

		return puntos;
		
	}*/
 /*public List<SolicitudServicio> getSolicitudesCached() {
		return solicitudesCached;
	}


	public void setSolicitudesCached(List<SolicitudServicio> solicitudesCached) {
		this.solicitudesCached = solicitudesCached;
	}*/
    /**
     * *
     * getCantGruposSugeridos
     *
     * @param solicitudesselect ejemplo (1,2,3,4) devuelve la cantidad de
     * solicitudes sugeridas en banse al tiempo promedio de una jornada
     *
     */
    public Response getCantGruposSugeridos(String solicitudesselect) {

        Response response = new Response(Response.OK, Response.MSG_OK);

        try {
            // obtener la ceadena pareada de las solictudes
            List<SolicitudServicio> solicitudes = solicitudesToObject(solicitudesselect);

            if (solicitudes == null) {
                response = new Response(Response.ERROR, Response.MSG_ERROR);
                response.setData(0);

            }

            // se extraen los puntos 
            List<Punto> puntos = getPuntosFromSolicitudes(solicitudes);
            //cálculo de la cantidad promedio de grupos en base el tiempo de atencion de cada solictud 
            Double costototal = 0.0;
            for (Punto punto : puntos) {
                costototal = costototal + punto.getPeso();
            }

            Double dcantcluster = costototal / TIEMPO_JORNADA;
            int cantcluster = (int) Math.ceil(dcantcluster);

            response.setData(cantcluster);

        } catch (Exception e) {

            e.printStackTrace();
            response = new Response(Response.ERROR, Response.MSG_ERROR);

        }

        return response;

    }

    /**
     * *
     * getPuntosFromSolicitudes obtiene las coordenadas en puntos de las
     * solictudes para poder trabajarlos geograficamente
     *
     *
     */
    private List<Punto> getPuntosFromSolicitudes(List<SolicitudServicio> solicitudes) {
        List<Punto> puntos = new ArrayList<Punto>();
        int idx = 0;
        for (SolicitudServicio s : solicitudes) {
            TipoSolicitud ts = s.getTipoSolicitud();
            puntos.add(new Punto((int) s.getNumeroSolicitud(),
                    s.getLatitud(),
                    s.getLongitud(),
                    ts.getTiempoEjecucion() * 1.0) // tiempo de ejecucion de la solicitud
            );
            idx++;
        }
        return puntos;

    }

}
