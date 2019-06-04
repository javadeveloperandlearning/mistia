/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import pe.com.cablered.mistia.dao.PosteDao;
import pe.com.cablered.mistia.geometria.Punto;
import pe.com.cablered.mistia.ia.clustering.Cluster;
import pe.com.cablered.mistia.ia.clustering.KMeans;
import pe.com.cablered.mistia.ia.clustering.Point;
import pe.com.cablered.mistia.ia.clustering.PointGeo;
import pe.com.cablered.mistia.model.Poste;

/**
 *
 * @author javadeveloper
 */
@Stateless
public class CoberturaService {

    final static Logger logger = Logger.getLogger(CuadrillaService.class);

    @Inject
    private PosteDao posteDao;

    public Map getCobertura(Punto punto) {

        //punto  =  new Punto(-12.054972,-75.202468);
        logger.info("punto central :" + punto.toString());
        Map coberturaMap = new HashMap<Object, Object>();

        try {
            List<Map> posteCercanoList = new ArrayList<>();
            List<Poste> posteList = posteDao.getPostesLIst();

            // aplicación de algoritmo kmeans para buscar los poste mas cercanos
            Map<Integer, Poste> mpPostes = new LinkedHashMap<Integer, Poste>();
            List<PointGeo> points = new ArrayList<PointGeo>();

            PointGeo _pointGeoCentral = new PointGeo(0, punto.getLatitud(), punto.getLongitud());
            points.add(_pointGeoCentral);

            for (Poste poste : posteList) {
                points.add(new PointGeo(poste.getCodigoPoste(), poste.getLatitud().doubleValue(), poste.getLongitud().doubleValue()));
                mpPostes.put(poste.getCodigoPoste(), poste);
                // System.out.println("punto poste : "+poste.toString());
            }

            Long cantCluster = Math.round(((posteList.size() * 1.0) / 50));
            logger.info("cant cluster :" + cantCluster);

            KMeans kMeans = new KMeans();
            kMeans.setPoints(points);
            kMeans.setNumClusters(cantCluster.intValue());
            List<Cluster> clusters = new ArrayList<>();

            // se define como Kmeans++ por definir el  primer cluster
            // ya para est cluster se establece su centro con el punto seleccionado
            Cluster cluster0 = new Cluster(0);
            //cluster0.setCentroid(new PointGeo(-12.05789, -75.19835));
            logger.info("punto central :" + punto.toString());
            PointGeo pointGeoCentral = new PointGeo(punto.getLatitud(), punto.getLongitud());
            cluster0.setCentroid(pointGeoCentral);
            clusters.add(cluster0);

            for (int i = 1; i < kMeans.getNumClusters(); i++) {
                Cluster cluster = new Cluster(i);
                Point centroid = points.get(i);
                System.out.println("centroide  cluster : " + centroid.toString());
                cluster.setCentroid(centroid);
                clusters.add(cluster);
            }

            kMeans.setClusters(clusters);
            // inicia el proceso de calculo de los grupos
            kMeans.calculate();

            // Cluster cluster  =  kMeans.getClusters().get(0);  
            //calcular distancias del punto centraal o los del grupo
            //List<Point> pointList =  cluster.getPoints();
            Cluster cluster = null;
            for (Cluster _cluster : kMeans.getClusters()) {
                for (Point _point : _cluster.getPoints()) {
                    if (_point.getX() == punto.getLatitud() && _point.getY() == punto.getLongitud()) {
                        cluster = _cluster;
                        System.out.println("cluster encontrado " + _cluster.toString());
                    }
                }
            }
            List<Point> pointList = cluster.getPoints();

            //calcular distancias del punto centraal o los del grupo
            logger.info("calculando distancias");
            for (Point point : pointList) {
                double distance = PointGeo.distance(point, _pointGeoCentral);
                point.setDistance(distance);
            }

            logger.info("ordenando distancias distancias");
            /*Collections.sort(pointList, (new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return o1.getDistance().compareTo(o2.getDistance());
                }
            }));*/
            Collections.sort(pointList,(p1,p2)->p1.getDistance().compareTo(p2.getDistance()));

            //solo los 10 primeros
            List<Point> _pointList = new ArrayList<>();
            
            for (Point point : pointList) {
                if(point.getDistance()<=200){
                    _pointList.add(point);
                }
            }
            /*if (pointList.size() > 10) {
                _pointList = pointList.subList(0, 9);
            } else {
                _pointList = pointList;
            }*/

            posteList.clear();

            System.out.println(" centoride :" + cluster.centroid.toString());
            System.out.println(" puntos mas cercanos :" + _pointList.size());
            for (Point point : _pointList) {
                System.out.println(" point :" + point.toString3());
                posteList.add(mpPostes.get(point.getNumber()));
            }

            for (Poste poste : posteList) {
                if (poste != null) {
                    Map map = new HashMap<>();
                    map.put("area", null);
                    map.put("descripcion", poste.getDescripcion());
                    map.put("latitudcentral", poste.getLatitud());
                    map.put("longitudcentral", poste.getLongitud());
                    map.put("radio", 0);
                    posteCercanoList.add(map);
                }
            }

            // si no tiene puntos cercanos se considera que no tiene cobertura
            coberturaMap.put("puntocentral", punto);
            coberturaMap.put("postesceranos", posteCercanoList);

        } catch (Exception e) {

            logger.info(e);
            logger.error(e);
            e.printStackTrace();

        }

        return coberturaMap;
    }

}
