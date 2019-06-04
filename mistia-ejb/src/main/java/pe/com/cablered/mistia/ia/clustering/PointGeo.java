/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cablered.mistia.ia.clustering;

/**
 *
 * @author javadeveloper
 */
public class PointGeo extends Point{
    
    public PointGeo(int number, double x, double y) {
        super(number, x, y);    
    }
    
    public PointGeo(double x, double y){
        super( x, y);
    }
    
    public  static double distance(Point p, Point centroid) {
        
        double lat1 =  p.getX();
        double lon1 =  p.getY();
        
	double lat2 =  centroid.getX();
	double lon2 =  centroid.getY();
        
        double  earthRadiusKm = 6371000;

        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
        return earthRadiusKm * c;
        
   
        
    }
    
    
    
    
    public static void main(String[] args) {
        
        PointGeo pointGeo1  =  new PointGeo(-12.1761,-77.0176);
        PointGeo pointGeo2  =  new PointGeo(-12.175121842105261,-77.01689368421053);

        System.out.println( distance(pointGeo1, pointGeo2));
        
        
    }
    
}