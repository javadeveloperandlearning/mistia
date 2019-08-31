package pe.com.cablered.mistia.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import pe.com.cablered.mistia.dao.NodoDao;
import pe.com.cablered.mistia.dao.PosteDao;
import pe.com.cablered.mistia.geometria.Geometria;
import pe.com.cablered.mistia.geometria.Punto;
import pe.com.cablered.mistia.ia.clustering.Cluster;
import pe.com.cablered.mistia.ia.clustering.KMeans;
import pe.com.cablered.mistia.ia.clustering.KMeans2;
import pe.com.cablered.mistia.ia.clustering.Point;
import pe.com.cablered.mistia.ia.clustering.PointGeo;
import pe.com.cablered.mistia.model.GrupoAtencionDetalle;
import pe.com.cablered.mistia.model.Nodo;
import pe.com.cablered.mistia.model.Poste;
import static pe.com.cablered.mistia.service.CoberturaService.logger;
import pe.com.cablered.mistia.util.Util;

@Stateless
public class NodosService {

    @Inject
    private PosteDao posteDao;

    @Inject
    private NodoDao nodoDao;

    final static Logger logger = Logger.getLogger(NodosService.class);

    public Response registrar(Nodo nodo) {

        Response response = new Response(Response.OK, Response.MSG_OK);

        try {

            if (nodo.getDescripcion() == null) {
                response = new Response(Response.ERROR, " El nombre del nodo no es válido");
                return response;
            }

            if (nodo.getDescripcion() != null) {
                Nodo _nodo = nodoDao.getNodo(nodo.getDescripcion());
                if (_nodo != null) {
                    response = new Response(Response.ERROR, " El nombre del nodo ya existe. Ingrese otro nombre");
                    return response;
                }
            }

            int codigoNodo = nodoDao.getMax(nodo) + 1;
            nodo.setCodigoNodo(codigoNodo);
            nodoDao.create(nodo);

        } catch (Exception e) {

            logger.info(e);
            logger.error(e);
            e.printStackTrace();

            response = new Response(Response.ERROR, Response.MSG_ERROR);

        }

        return response;
    }

    public Response modificar(Nodo nodo) {

        Response response = new Response(Response.OK, Response.MSG_OK);

        try {

            if (nodo.getDescripcion() == null) {
                response = new Response(Response.ERROR, " El nombre del nodo no es válido");
                return response;
            }

            if (nodo.getDescripcion() != null) {
                Nodo _nodo = nodoDao.getNodo(nodo.getDescripcion());
                if (_nodo != null) {
                    response = new Response(Response.ERROR, " El nombre del nodo ya existe. Ingrese otro nombre");
                    return response;
                }
            }

            nodoDao.update(nodo);

        } catch (Exception e) {

            logger.info(e);
            logger.error(e);
            e.printStackTrace();

            response = new Response(Response.ERROR, Response.MSG_ERROR);
        }

        return response;
    }

    public Response eliminar(Nodo nodo) {

        Response response = new Response(Response.OK, Response.MSG_OK);

        if (nodoDao.hasPostes(nodo)) {
            response = new Response(Response.ERROR, "El nodo tiene postes asociados");
            return response;
        }

        try {
            nodoDao.remove(nodo);

        } catch (Exception e) {

            logger.info(e);
            logger.error(e);
            e.printStackTrace();

            response = new Response(Response.ERROR, Response.MSG_ERROR);

        }

        return response;
    }

    public List<Map> getcoberuraPostes() {
        logger.info("metodo : getcoberuraPostes");
        List<Map> coberturaList = new ArrayList<>();
        List<Poste> posteList = posteDao.getPostesCobertura();

        for (Poste poste : posteList) {
            Map map = new HashMap<>();
            map.put("area", null);
            map.put("numero_poste", poste.getCodigoPoste());
            map.put("descripcion", "nodo");
            map.put("latitudcentral", poste.getLatitud());
            map.put("longitudcentral", poste.getLongitud());
            map.put("radio", 0);
            coberturaList.add(map);
        }
        
        logger.info("cant postes :"+coberturaList.size());
        
        //getcoberuraListaPostes() ;
        
        return coberturaList;
    }

    public List<Map> getcoberuraListaPostes() {
        logger.info("metodo : getcoberuraListaPostes######");
        List<Map> coberturaList = new ArrayList<>();
        List<Poste> posteList = posteDao.getPostesLIst();
        logger.info("cant postes : " + posteList.size());
        for (Poste poste : posteList) {
            Map map = new HashMap<>();
            map.put("area", null);
            map.put("numero_poste", poste.getCodigoPoste());
            map.put("descripcion", "nodo");
            map.put("latitudcentral", poste.getLatitud());
            map.put("longitudcentral", poste.getLongitud());
            map.put("radio", 0);
            map.put("color", "#003399");
            /*List<Map> mpposteList=  new ArrayList<>();
			for( Point p :points){
				Map  mpposte = new HashMap<>();
				mpposte.put("latitud", p.getX());
				mpposte.put("longitud", p.getY());
				mpposteList.add(mpposte);
			}
			map.put("postes", mpposteList);*/

            coberturaList.add(map);
        }
        
        //////////////////////////////////////////////////////
        
        List<Map> _coberturaList = new ArrayList<>();
        

        Map<Integer, Poste> mpPostes = new LinkedHashMap<Integer, Poste>();
        List<PointGeo> points = new ArrayList<PointGeo>();

        PointGeo _pointGeoCentral = new PointGeo(0, -12.055224, -75.195773);
        points.add(_pointGeoCentral);

        for (Poste poste : posteList) {
            points.add(new PointGeo(poste.getCodigoPoste(), poste.getLatitud().doubleValue(), poste.getLongitud().doubleValue()));
            mpPostes.put(poste.getCodigoPoste(), poste);
        }

        Long cantCluster = Math.round(((posteList.size() * 1.0) / 50));
        logger.info("cant cantCluster :" + cantCluster);

        KMeans kMeans = new KMeans();
        kMeans.setPoints(points);
        kMeans.setNumClusters(cantCluster.intValue());
        List<Cluster> clusters = new ArrayList<>();

        // se define como Kmeans++ por definir el  primer cluster
        // ya para est cluster se establece su centro con el punto seleccionado
        Cluster cluster0 = new Cluster(0);
        PointGeo pointGeoCentral = new PointGeo(-12.055224, -75.195773); // punto de refrencia de huancayo
        cluster0.setCentroid(pointGeoCentral);
        clusters.add(cluster0);

        for (int i = 1; i < kMeans.getNumClusters(); i++) {
            Cluster cluster = new Cluster(i);
            Point centroid = points.get(i);
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

        kMeans.setClusters(clusters);
        kMeans.calculate();
        // buscando los puntos mas cercanos

        for (Cluster cluster : kMeans.getClusters()) {
            
            List<Point> pointList = cluster.getPoints();
            //logger.info("calculando distancias");
            for (Point point : pointList) {
                double distance = PointGeo.distance(point, cluster.getCentroid());
                point.setDistance(distance);
            }

            //logger.info("ordenando distancias distancias mayorrr");
            Collections.sort(pointList, (p1, p2) -> p1.getDistance().compareTo(p2.getDistance()));
            List<Point> _pointList = new ArrayList<>(); // lista de puntosn acotados

            int cant = 1;
            for (Point point : pointList) {
                //if (cant <= 100) {
                    _pointList.add(point);
                //}
                cant++;
            }
            //logger.info("cantidad de alrederdor :" + _pointList.size());    
            cluster.setPoints(_pointList);

        }
        
        //ArrayList<Integer> colorsindex = Util.getRandomNonRepeatingIntegers(kMeans.getClusters().size(), 0, kMeans.getClusters().size() - 1);
        int idxcolor= 0;    
        for (Cluster _cluster : kMeans.getClusters()) {
            
            logger.info("numero cluster:" +_cluster.getId());
            Map map = new HashMap<>();
            map.put("descripcion", "nodo");
            map.put("latitudcentral", _cluster.getCentroid().getX());
            map.put("longitudcentral", _cluster.getCentroid().getY());
            map.put("radio", 0);
            map.put("color", "#003399");
            _coberturaList.add(map);
            List<Map> mpposteList = new ArrayList<>();
            for (Point p : _cluster.getPoints()) {
                Map mpposte = new HashMap<>();
                mpposte.put("numero", p.getNumber());
                mpposte.put("latitud", p.getX());
                mpposte.put("longitud", p.getY());
                //int c = colorsindex.get(idxcolor);
                logger.info(" p.getNumber() :"+p.getNumber());    
                
                posteDao.actualizarNodo(p.getNumber(), _cluster.getId()+1 );
                //mpposte.put("color",  Geometria.colors[c]);
                mpposteList.add(mpposte);
            }
            map.put("postes", mpposteList);
            idxcolor++;

        }
         ///////////////////////////////////////////       
        return coberturaList;
    }

    public List<Map> getcoberuraNodos() {
        List<Map> coberturaList = new ArrayList<>();

        KMeans2 kmean = new KMeans2();
        kmean.startkpp(90);
        kmean.calculate();
        List<Cluster> _clusters = kmean.getClusters();
        for (Cluster cluster : _clusters) {

            List<Point> points = cluster.getPoints();

            Punto[] puntos = new Punto[points.size()];
            int i = 0;
            for (Point p : points) {
                puntos[i] = new Punto(p.getX(), p.getY());
                i++;
            }

            Map map = new HashMap<>();

            //obtener el punto central(latitud, longitud) del grupo
            Punto puntoCentral = Geometria.puntoCentral(puntos);
            // obtener el radio máximo del grupo
            if (puntoCentral != null) {
                double radio = Geometria.getRadioMaximo(puntoCentral, puntos);
                map.put("area", Geometria.getAreaCirculo(radio));
                map.put("descripcion", "nodo 1");
                map.put("latitudcentral", puntoCentral.getLatitud());
                map.put("longitudcentral", puntoCentral.getLongitud());
                map.put("radio", radio);
                map.put("color", "#003399");

                List<Map> mpposteList = new ArrayList<>();
                for (Point p : points) {
                    Map mpposte = new HashMap<>();
                    mpposte.put("latitud", p.getX());
                    mpposte.put("longitud", p.getY());
                    mpposteList.add(mpposte);
                }

                map.put("postes", mpposteList);
            }
            /*Map map =  new HashMap<>();
			map.put("area", "400");
			map.put("descripcion", "nodo 1");
			map.put("latitudcentral", -12.057825855980015 );
			map.put("longitudcentral", -75.19839901930298);
			map.put("radio", 1066.4659765130987);
			map.put("color", "#003399");
			coberturaList.add(map);*/

            coberturaList.add(map);

        }

        return coberturaList;
    }

    public List<Nodo> getNodoList() {

        return nodoDao.getNodoList();
    }

    public List<Nodo> getNodoList(Integer codigoMatriz, String criterio) {

        return nodoDao.getNodoList(codigoMatriz, criterio);
    }

    public Nodo getNodo(Integer codigoNodo) {

        return nodoDao.getNodo(codigoNodo);
    }

    public Nodo getNodo(String descripcion) {

        return nodoDao.getNodo(descripcion);
    }

}
